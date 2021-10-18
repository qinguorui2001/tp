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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.clonePerson;
import static seedu.address.logic.commands.CommandTestUtil.replacePersonWithClone;
import static seedu.address.logic.commands.CommandTestUtil.setUpNewModelWithClonedPerson;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class DeleteAssignmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        /* The selectedPerson is cloned to avoid race conditions happening with JsonSerializableAddressBookTest
           and is cloned for both actual and expected models because a change in expectedModel will affect the actual
           model*/
        Person selectedPerson = model.getFilteredPersonList().get(INDEX_SIXTH_PERSON.getZeroBased());
        Person clonedActualPerson = clonePerson(selectedPerson);
        Person clonedExpectedPerson = clonePerson(selectedPerson);

        Assignment assignmentToDelete = selectedPerson.getAssignments()
                .asUnmodifiableObservableList().get(INDEX_SECOND_ASSIGNMENT.getZeroBased());

        Model actualModel = replacePersonWithClone(model, selectedPerson, clonedActualPerson);
        Model expectedModel = setUpNewModelWithClonedPerson(selectedPerson, clonedExpectedPerson);
        expectedModel.deleteAssignment(clonedExpectedPerson, assignmentToDelete);

        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(clonedActualPerson.getName(), INDEX_SECOND_ASSIGNMENT);

        String expectedMessage = String.format(DeleteAssignmentCommand.MESSAGE_DELETE_ASSIGNMENT_SUCCESS,
                assignmentToDelete);

        assertCommandSuccess(deleteAssignmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(model.getFilteredPersonList()
                        .get(INDEX_FIRST_PERSON.getZeroBased()).getName(), outOfBoundIndex);

        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredPersonList_success() {
        Person selectedPerson = model.getFilteredPersonList().get(INDEX_SIXTH_PERSON.getZeroBased());
        Person clonedActualPerson = clonePerson(selectedPerson);
        Person clonedExpectedPerson = clonePerson(selectedPerson);

        Model expectedModel = setUpNewModelWithClonedPerson(selectedPerson, clonedExpectedPerson);
        Model actualModel = replacePersonWithClone(model, selectedPerson, clonedActualPerson);

        Assignment assignmentToDelete = selectedPerson.getAssignments()
                .asUnmodifiableObservableList().get(INDEX_SECOND_ASSIGNMENT.getZeroBased());

        showPersonAtIndex(actualModel, INDEX_SIXTH_PERSON);
        showPersonAtIndex(expectedModel, INDEX_SIXTH_PERSON);
        expectedModel.deleteAssignment(clonedExpectedPerson, assignmentToDelete);

        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(clonedActualPerson.getName(), INDEX_SECOND_ASSIGNMENT);

        String expectedMessage =
                String.format(DeleteAssignmentCommand.MESSAGE_DELETE_ASSIGNMENT_SUCCESS, assignmentToDelete);

        assertCommandSuccess(deleteAssignmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredPersonList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book's person list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteAssignmentCommand deleteAssignmentCommand = new DeleteAssignmentCommand(
                model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()).getName(), outOfBoundIndex);

        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPerson_throwsCommandException() {
        Person personNotInList = HOON;

        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(personNotInList.getName(), INDEX_FIRST_ASSIGNMENT);

        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }

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

        // different deleted assignment -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
