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
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class DeleteAssignmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Model setUpModel(Model inputModel, Person selectedPerson, Person clonedPerson) {
        inputModel.setPerson(selectedPerson, clonedPerson);
        return inputModel;
    }

    private Model setUpActualModel(Person selectedPerson, Person clonedPerson) {
        return setUpModel(model, selectedPerson, clonedPerson);
    }

    private Model setUpExpectedModel(Person selectedPerson, Person clonedPerson) {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        return setUpModel(expectedModel, selectedPerson, clonedPerson);
    }

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

        Model actualModel = setUpActualModel(selectedPerson, clonedActualPerson);
        Model expectedModel = setUpExpectedModel(selectedPerson, clonedExpectedPerson);
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
                new DeleteAssignmentCommand(model.getFilteredPersonList().get(0).getName(), outOfBoundIndex);

        assertCommandFailure(deleteAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredPersonList_success() {
        Person selectedPerson = model.getFilteredPersonList().get(INDEX_SIXTH_PERSON.getZeroBased());
        Person clonedActualPerson = clonePerson(selectedPerson);
        Person clonedExpectedPerson = clonePerson(selectedPerson);

        Model expectedModel = setUpExpectedModel(selectedPerson, clonedExpectedPerson);
        Model actualModel = setUpActualModel(selectedPerson, clonedActualPerson);

        Assignment assignmentToDelete = selectedPerson.getAssignments()
                .asUnmodifiableObservableList().get(INDEX_SECOND_ASSIGNMENT.getZeroBased());

        expectedModel.updateFilteredPersonList(person -> person.isSameName(clonedExpectedPerson.getName()));
        actualModel.updateFilteredPersonList(person -> person.isSameName(selectedPerson.getName()));
        expectedModel.deleteAssignment(clonedExpectedPerson, assignmentToDelete);

        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(clonedActualPerson.getName(), INDEX_SECOND_ASSIGNMENT);

        String expectedMessage =
                String.format(DeleteAssignmentCommand.MESSAGE_DELETE_ASSIGNMENT_SUCCESS, assignmentToDelete);

        assertCommandSuccess(deleteAssignmentCommand, actualModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredPersonList_throwsCommandException() {
        Person selectedPerson = model.getFilteredPersonList().get(INDEX_SIXTH_PERSON.getZeroBased());
        Person filteredPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // ensures that outOfBoundIndex is still in bounds of address book's person list
        assertTrue(INDEX_SIXTH_PERSON.getZeroBased() < model.getAddressBook().getPersonList().size());
        model.updateFilteredPersonList(person -> person.isSameName(filteredPerson.getName()));
        DeleteAssignmentCommand deleteAssignmentCommand =
                new DeleteAssignmentCommand(filteredPerson.getName(), INDEX_FIRST_ASSIGNMENT);

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
