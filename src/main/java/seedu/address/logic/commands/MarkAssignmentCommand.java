package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Marks an assignment to the person's assignment list.
 */
public class MarkAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the specified person's assignment identified by the "
            + "index number used in the displayed assignment list.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + "1";

    public static final String MESSAGE_MARK_ASSIGNMENT_SUCCESS = "Marked Assignment: %1$s";

    private final Index targetAssignmentIndex;

    /**
     * Creates an MarkAssignmentCommand to mark the specified {@code Assignment}
     */
    public MarkAssignmentCommand(Index targetAssignmentIndex) {
        this.targetAssignmentIndex = targetAssignmentIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasActivePerson()) {
            throw new CommandException(Messages.MESSAGE_NO_ASSIGNMENT_LIST_DISPLAYED);
        }

        Person personToRemoveAssignment = model.getActivePerson();

        List<Assignment> assignmentList = model.getFilteredAssignmentList();

        if (targetAssignmentIndex.getZeroBased() >= assignmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment assignmentToMark = assignmentList.get(targetAssignmentIndex.getZeroBased());
        model.markAssignment(personToRemoveAssignment, assignmentToMark);

        return new CommandResult(String.format(MESSAGE_MARK_ASSIGNMENT_SUCCESS, assignmentToMark));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAssignmentCommand // instanceof handles nulls
                && targetAssignmentIndex.equals(((MarkAssignmentCommand) other).targetAssignmentIndex)); // state check
    }
}
