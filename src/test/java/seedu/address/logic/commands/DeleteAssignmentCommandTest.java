package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalAssignments;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class DeleteAssignmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personInList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Assignment assignmentToDelete_expected =
                TypicalAssignments.getTypicalAssignments().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.addAssignment(personInList, assignmentToDelete_expected);
        expectedModel.deleteAssignment(personInList, assignmentToDelete_expected);

        personInList.getAssignments().setAssignments(TypicalAssignments.getTypicalAssignments());
        model.updateFilteredAssignmentList(personInList);
        Assignment assignmentToDelete_model =
                model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(personInList.getName(), INDEX_FIRST_ASSIGNMENT);

        String expectedMessage =
                String.format(DeleteAssignmentCommand.MESSAGE_DELETE_ASSIGNMENT_SUCCESS, assignmentToDelete_model);

        assertCommandSuccess(deleteAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(model.getFilteredPersonList().get(0).getName(), outOfBoundIndex);

        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personInList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Assignment assignmentToDelete = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        expectedModel.addAssignment(personInList, assignmentToDelete);
        expectedModel.deleteAssignment(personInList, assignmentToDelete);
        showNoAssignment(expectedModel, personInList);
        showAssignmentAtIndex(model,
                model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), INDEX_FIRST_ASSIGNMENT);


        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(personInList.getName(), INDEX_FIRST_ASSIGNMENT);

        String expectedMessage =
                String.format(DeleteAssignmentCommand.MESSAGE_DELETE_ASSIGNMENT_SUCCESS, assignmentToDelete);

        assertCommandSuccess(deleteAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personInList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        showAssignmentAtIndex(model, personInList, INDEX_FIRST_ASSIGNMENT);

        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getAssignmentsList().size());

        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(personInList.getName(), outOfBoundIndex);

        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }
/*
    @Test
    public void equals() {
        DeletePersonCommand deleteFirstCommand = new DeletePersonCommand(INDEX_FIRST_PERSON);
        DeletePersonCommand deleteSecondCommand = new DeletePersonCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePersonCommand deleteFirstCommandCopy = new DeletePersonCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
   /* private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePersonCommand deletePersonCommand = new DeletePersonCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deletePersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(new PersonBuilder().build().getName(), outOfBoundIndex);

        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Assignment assignmentToDelete = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(new PersonBuilder().build().getName(), INDEX_FIRST_ASSIGNMENT);

        String expectedMessage = String.format(DeleteAssignmentCommand.MESSAGE_DELETE_ASSIGNMENT_SUCCESS, assignmentToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteAssignment(new PersonBuilder().build(), assignmentToDelete);
        showNoAssignment(expectedModel);

        assertCommandSuccess(deleteAssignmentCommand, model, expectedMessage, expectedModel);
    }
*/
<<<<<<< HEAD
<<<<<<< HEAD
   /* @Test
=======
    @Test
>>>>>>> parent of 79a7c637 (update parser test)
=======
   /* @Test
>>>>>>> 6c76cd13607d7199ab271ab58276400eed2514b1
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAssignmentAtIndex(model,
                model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), INDEX_FIRST_ASSIGNMENT);

        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getAssignmentsList().size());

        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(new PersonBuilder().build().getName(), outOfBoundIndex);

        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }
<<<<<<< HEAD

=======
*/
>>>>>>> 6c76cd13607d7199ab271ab58276400eed2514b1
    @Test
    public void equals() {
        DeleteAssignmentCommand deleteFirstCommand =
                new DeleteAssignmentCommand(new PersonBuilder().build().getName(), INDEX_FIRST_ASSIGNMENT);
        DeleteAssignmentCommand deleteSecondCommand =
                new DeleteAssignmentCommand(new PersonBuilder().build().getName(), INDEX_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAssignmentCommand deleteFirstCommandCopy =
                new DeleteAssignmentCommand(new PersonBuilder().build().getName(), INDEX_FIRST_ASSIGNMENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoAssignment(Model model, Person person) {
        model.updateFilteredAssignmentList(person);
        assertTrue(model.getFilteredAssignmentList().isEmpty());
    }
}
