package seedu.address.model;

import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();


    /**
     * Updates the assignment list in AddressBook.
     */
    void updateAssignmentList(Person person);

    /**
     * Returns an unmodifiable view of the assignment list.
     * This list will not contain any duplicate assignments.
     */
    ObservableList<Assignment> getAssignmentsList();

    /**
     * Returns the copy of assignment list.
     */
    ObservableList<Assignment> copyAssignmentList();

    /**
     * Returns the copy of person list.
     */
    ObservableList<Person> copyPersonList();

    /**
     * Returns the copy of active person enclosed by an Optional.
     */
    Optional<Person> copyActivePerson();

    /**
     * Returns the copy of address book.
     */
    AddressBook copyAddressBook();

    /**
     * Returns the current filter predicate acting on personList in ModelManager.
     *
     * @return the Predicate that PersonList is filtered by
     */
    Predicate<Person> getFilteredPersonListPredicate();

}
