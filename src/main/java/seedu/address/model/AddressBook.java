package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.UniqueAssignmentList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    /* The person whose assignment is displayed on the Ui */
    private Person activePerson;
    private final UniquePersonList persons;
    private final UniqueAssignmentList assignments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        assignments = new UniqueAssignmentList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the person's assignment list with {@code assignments}.
     * {@code assignments} must not contain duplicate assignments.
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments.setAssignments(assignments);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setAssignments(newData.getAssignmentsList());
        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// assignment-level operations
    /**
     * Returns true if an assignment with the same identity as {@code assignment} exists in the person's assignment
     * list.
     */
    public boolean hasAssignment(Person person, Assignment assignment) {
        requireNonNull(assignment);
        return person.getAssignments().contains(assignment);
    }

    /**
     * Adds an assignment to the person's assignment list.
     * The assignment must not already exist in the person's assignment list.
     */
    public void addAssignment(Person person, Assignment assignment) {
        person.getAssignments().add(assignment);
    }

    /**
     * Removes {@code key} from this {@code AddressBook's person assignment list}.
     * {@code key} must exist in the person assignment list.
     */
    public void removeAssignment(Person person, Assignment key) {
        person.getAssignments().delete(key);
    }

    /**
     * Marks {@code key} from this {@code AddressBook's person assignment list}.
     * {@code key} must exist in the person assignment list.
     */
    public void markAssignment(Person person, Assignment key) {
        person.getAssignments().done(key);
    }

    /**
     * Retrieve the assignment list of the identified person {@code name} from this {@code AddressBook's person list}.
     */
    public ObservableList<Assignment> getPersonAssignmentList(Person person) {
        return person.getAssignments().asUnmodifiableObservableList();
    }

    //// util methods

    /**
     * Changes the person whose assignment list is displayed. Changes {@code activePerson} to null which represents
     * no assignment list selected if the person indicated does not exist in {@code AddressBook's person list}.
     * @param person
     */
    public void changeActivePerson(Person person) {
        if (!hasPerson(person)) {
            this.activePerson = null;
        } else {
            this.activePerson = person;
        }
    }

    /**
     * Returns true if the active person is same as the person.
     * @param person the Person which is queried to find if he/she is the current activePerson
     * @return true if the person is the current activePerson
     */
    public boolean isActivePerson(Person person) {
        return person.equals(activePerson);
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public void updateAssignmentList(Person person) {
        if (activePerson == null) {
            this.assignments.clearAllAssignments();
        } else {
            this.assignments.setAssignments(person.getAssignments());
        }
    }

    @Override
    public ObservableList<Assignment> getAssignmentsList() {
        return assignments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
