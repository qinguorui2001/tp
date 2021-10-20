package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class UndoCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_validUndoPersonCommandSuccess() throws Exception{
        AddPersonCommand addPersonCommand = new AddPersonCommand(new PersonBuilder().build());
        model.updateCommandStack(addPersonCommand);
        addPersonCommand.execute(model);
        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = String.format(UndoCommand.MESSAGE_UNDO_SUCCESS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

       try {
           expectedModel.undoAddressBook();
       } catch (CommandException e) {

       }
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_validUndoAssignmentCommandSuccess() {
        //model.addPerson();
    }

    @Test
    void execute_validUndoOtherCommandSuccess() {
        //model.addPerson();
    }
}
