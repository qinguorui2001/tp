package seedu.address.model;

import java.util.Stack;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;

public class CommandStack {
    private final Stack<Command> undoStack = new Stack<>();

    private int undoRedoPointer = -1;
    private int undoStackSize;

    /**
     * Undo by changing the state of the NoteBook to the previous state.
     */
    public void undo(Model model) throws CommandException {
        if (!undoStack.empty() && undoRedoPointer >= 0) {
            Command command = undoStack.get(undoRedoPointer);
            command.unExecute(model);
            undoRedoPointer--;
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_UNDO);
        }
    }

    /**
     * Updates undo stack on redo command call.
     */
    public void updateUndoStack(Command nextCommand) {
        deleteElementsAfterPointer(undoRedoPointer);
        undoStack.push(nextCommand);
        undoRedoPointer++;
        undoStackSize = undoRedoPointer + 1;
    }

    private void deleteElementsAfterPointer(int undoRedoPointer) {
        if (undoStackSize < 1) {
            return;
        }

        for(int i = undoStackSize - 1; i > undoRedoPointer; i--) {
             undoStack.remove(i);
        }
    }

    public Command retrieveCurrentCommand() {
        return undoStack.get(undoStackSize - 1);
    }
}
