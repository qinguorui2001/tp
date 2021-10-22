package seedu.address.model;

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
    ObservableList<Assignment> copyOfAssignmentList();

    /**
     * Returns the copy of person list.
     */
    ObservableList<Person> copyOfPersonList();

    /**
     * Returns the copy of active person.
     */
    Person copyOfActivePerson();

    /**
     * Returns the copy of address book.
     */
    AddressBook copyOfAddressBook();
}
