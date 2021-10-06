package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
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
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setAssignments(Person person, List<Assignment> assignments) {
        person.getAssignments().setAssignments(assignments);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

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
    public boolean hasAssignment(Name name, Assignment assignment) {
        requireNonNull(assignment);
        return persons.personWithSameName(name).getAssignments().contains(assignment);
    }

    /**
     * Adds an assignment to the person's assignment list.
     * The assignment must not already exist in the person's assignment list.
     */
    public void addAssignment(Name name, Assignment assignment) {
        persons.personWithSameName(name).getAssignments().add(assignment);
    }

    /**
     * Removes {@code key} from this {@code AddressBook's person assignment list}.
     * {@code key} must exist in the person assignment list.
     */
    public void removeAssignment(Name name, Assignment key) {
        persons.personWithSameName(name).getAssignments().delete(key);
    }

    /**
     * Marks {@code key} from this {@code AddressBook's person assignment list}.
     * {@code key} must exist in the person assignment list.
     */
    public void markAssignment(Name name, Assignment key) {
        persons.personWithSameName(name).getAssignments().done(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

   /* public ObservableList<Assignment> getAssignmentList(Index index) {
        if (getPersonList().size() != 0) {
            return getPersonList().get(index.getZeroBased()).getAssignments().asUnmodifiableObservableList();
        }
        return null;
    }
*/
    @Override
    public List<Assignment> getAssignmentList(Name name) {
        return persons.assignmentsOfPersonWithSameName(name).asUnmodifiableObservableList();
    }
  
    @Override
    public ObservableList<Assignment> getAssignmentList(Person person) {
        return assignments.asUnmodifiableObservableList(person.getAssignments());
    }

    public ObservableList<Assignment> emptyAssignmentList() {
        return assignments.asUnmodifiableObservableList(new ArrayList<>());
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
