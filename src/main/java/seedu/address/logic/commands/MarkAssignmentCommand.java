package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Name;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

/**
 * Marks an assignment to the person's assignment list.
 */
public class MarkAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "a-done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the specified person's assignment identified by the "
            + "index number used in the displayed assignment list.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + "1";

    public static final String MESSAGE_MARK_ASSIGNMENT_SUCCESS = "Marked Assignment: %1$s";

    private final Index targetIndex;
    private final Name name;

    /**
     * Creates an MarkAssignmentCommand to mark the specified {@code Assignment}
     */
    public MarkAssignmentCommand(Name name, Index targetIndex) {
        this.targetIndex = targetIndex;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Assignment> lastShownList = model.getPersonAssignmentList(name);

        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_ASSIGNMENTS_EMPTY);
        }

        Assignment assignmentToMark = lastShownList.get(targetIndex.getZeroBased());
        model.markAssignment(name, assignmentToMark);
        return new CommandResult(String.format(MESSAGE_MARK_ASSIGNMENT_SUCCESS, assignmentToMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAssignmentCommand // instanceof handles nulls
                && targetIndex.equals(((MarkAssignmentCommand) other).targetIndex)); // state check
    }
}
