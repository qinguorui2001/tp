package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes an assignment to the person's assignment list.
 */
public class DeleteAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "a-delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the specified person's assignment identified by the "
            + "index number used in the displayed assignment list.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + "1";

    public static final String MESSAGE_DELETE_ASSIGNMENT_SUCCESS = "Deleted Assignment: %1$s";

    private final Index targetAssignmentIndex;

    /**
     * Creates an DeleteAssignmentCommand to delete the specified {@code Assignment}
     */
    public DeleteAssignmentCommand(Index targetAssignmentIndex) {
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

        Assignment assignmentToDelete = assignmentList.get(targetAssignmentIndex.getZeroBased());
        model.deleteAssignment(personToRemoveAssignment, assignmentToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_ASSIGNMENT_SUCCESS, assignmentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteAssignmentCommand // instanceof handles nulls
                && targetAssignmentIndex.equals(
                        ((DeleteAssignmentCommand) other).targetAssignmentIndex)); // state check
    }
}
