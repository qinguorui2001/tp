package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class MarkAssignmentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Person personToShow = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

    @Test
    void execute_validIndexAssignmentListSuccess() {
        MarkAssignmentCommand markAssignmentCommand =
                new MarkAssignmentCommand(personToShow.getName(), INDEX_FIRST_ASSIGNMENT);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.markAssignment(personToShow, personToShow.getAssignments().get(0));
        String expectedMessage =
                String.format(MarkAssignmentCommand.MESSAGE_MARK_ASSIGNMENT_SUCCESS,
                        personToShow.getAssignments().get(0));
        assertCommandSuccess(markAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexAssignmentList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList(personToShow).size() + 1);
        MarkAssignmentCommand markAssignmentCommand =
                new MarkAssignmentCommand(personToShow.getName(), outOfBoundIndex);

        assertCommandFailure(markAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Person personInList = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        MarkAssignmentCommand markFirstCommand =
                new MarkAssignmentCommand(personInList.getName(), INDEX_FIRST_ASSIGNMENT);
        MarkAssignmentCommand markSecondCommand =
                new MarkAssignmentCommand(personInList.getName(), INDEX_SECOND_ASSIGNMENT);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkAssignmentCommand markFirstCommandCopy =
                new MarkAssignmentCommand(personInList.getName(), INDEX_FIRST_ASSIGNMENT);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }
}
