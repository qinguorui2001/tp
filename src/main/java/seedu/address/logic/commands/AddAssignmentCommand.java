package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DUEDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Adds an assignment to the person's assignment list.
 */
public class AddAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "give";

    public static final String FRIENDLY_COMMAND_SYNTAX =
            "Here are some friendly command syntax for to replace d/M/yyyy:\n"
                    + "today - sets due date to tonight 2359\n"
                    + "tmr - sets due date to tomorrow\n"
                    + "week - sets due date to a week (7 days) from now\n"
                    + "mon - sets due date to the coming monday\n"
                    + "tue - sets due date to the coming tuesday\n"
                    + "wed - sets due date to the coming wednesday\n"
                    + "thu - sets due date to the coming thursday\n"
                    + "fri - sets due date to the coming friday\n"
                    + "sat - sets due date to the coming saturday\n"
                    + "sun - sets due date to the coming sunday\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assignment to the person's assignment list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DUEDATE + "DUEDATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DESCRIPTION + "assignment2 "
            + PREFIX_DUEDATE + "11/11/2021 \n\n"
            + FRIENDLY_COMMAND_SYNTAX;

    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in the assignment list";

    private final Assignment toAdd;
    private final Name name;

    /**
     * Creates an AddAssignmentCommand to add the specified {@code Assignment}
     */
    public AddAssignmentCommand(Name name, Assignment assignment) {
        requireNonNull(assignment);
        requireNonNull(name);
        toAdd = assignment;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Get Person that match the name
        List<Person> filteredPersonList =
                model.getFilteredPersonList()
                        .stream()
                        .filter(person -> person.isSameName(name))
                        .collect(Collectors.toList());

        if (filteredPersonList.size() == 0) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }

        Person selectedPerson = filteredPersonList.get(0);
        if (model.hasAssignment(selectedPerson, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.addAssignment(selectedPerson, toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAssignmentCommand // instanceof handles nulls
                && toAdd.equals(((AddAssignmentCommand) other).toAdd));
    }
}
