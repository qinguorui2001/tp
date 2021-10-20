package seedu.address.model;

import java.util.Stack;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;

public class CommandStack {
    private final Stack<Command> undoStack = new Stack<>();

    private int undoPointer = -1;
    private int undoStackSize;

    /**
     * Undo the command of given model.
     */
    public void undo(Model model) throws CommandException {
        if (!undoStack.empty() && undoPointer >= 0) {
            Command command = undoStack.get(undoPointer);
            command.unExecute(model);
            undoPointer--;
        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_UNDO);
        }
    }

    /**
     * Update undo stack whenever a command is executed(except undo command).
     */
    public void updateUndoStack(Command nextCommand) {
        deleteElementsAfterPointer(undoPointer);
        undoStack.push(nextCommand);
        undoPointer++;
        undoStackSize = undoPointer + 1;
    }

    //Solution adapted from
    //https://stackoverflow.com/questions/11530276/how-do-i-implement-a-simple-undo-redo-for-actions-in-java
    /**
     * Deletes any commands in undo stack after the current point when a new command is added at that point.
     * @param undoPointer The current point where undo stack is at.
     */
    private void deleteElementsAfterPointer(int undoPointer) {
        if (undoStackSize < 1) {
            return;
        }

        if (undoStackSize > undoPointer + 1) {
            undoStack.subList(undoPointer + 1, undoStackSize).clear();
        }
    }
}
