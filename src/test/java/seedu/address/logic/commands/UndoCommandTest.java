package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class UndoCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_validUndoPersonCommandSuccess() throws Exception {
        /*AddPersonCommand addPersonCommand = new AddPersonCommand(new PersonBuilder().build());
        addPersonCommand.execute(model);

        model.commitAddressBook(model.getAddressBook());


        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());


        expectedModel.undoAddressBook();

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);*/
    }

    @Test
    void execute_validUndoAssignmentCommandSuccess() throws Exception {
        /*Person personInList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        AddAssignmentCommand addAssignmentCommand = new AddAssignmentCommand(
                personInList.getName(), TypicalAssignments.ASSIGNMENT_CS1101S_MISSION);


        VersionedAddressBook versionedAddressBook = model.getCopyOfVersionedAddressBook();
        model.commitAddressBook(model.getAddressBook());


        addAssignmentCommand.execute(model);

        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;
        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setVersionedAddressBook(versionedAddressBook);

        expectedModel.undoAddressBook();

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);*/
    }

    @Test
    void execute_validUndoOtherCommandSuccess() {
        //model.addPerson();
    }
}
