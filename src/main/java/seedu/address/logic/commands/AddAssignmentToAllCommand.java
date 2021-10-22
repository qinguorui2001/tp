package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Module;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_MODULE;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds an assignment to the person's assignment list.
 */
public class AddAssignmentToAllCommand extends Command {

    public static final String COMMAND_WORD = "giveall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an assignment to assignment list of all persons under same module "
            + "Parameters: "
            + PREFIX_MODULE + "MODULE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DUEDATE + "DUEDATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2100 "
            + PREFIX_DESCRIPTION + "assignment2 "
            + PREFIX_DUEDATE + "11/11/2021 ";

    public static final String MESSAGE_SUCCESS = "New assignment added to all persons in %1$s: %2$s";

    private final Assignment toAdd;
    private final Module module;
    private ReadOnlyAddressBook addressBook;

    /**
     * Creates an AddAssignmentCommand to add the specified {@code Assignment}
     */
    public AddAssignmentToAllCommand(Module module, Assignment assignment) {
        requireNonNull(assignment);
        requireNonNull(module);
        toAdd = assignment;
        this.module = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        addressBook = model.getAddressBook();
        // Get Person that match the Module
        List<Person> filteredPersonList =
                model.getFilteredPersonList()
                        .stream()
                        .filter(person -> person.hasModule(module))
                        .collect(Collectors.toList());

        if (filteredPersonList.size() == 0) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_MODULE);
        }

        for (Person person: filteredPersonList) {
            if (!model.hasAssignment(person, toAdd)) {
                model.addAssignment(person, toAdd);
            }
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, module, toAdd));
    }

    @Override
    public void unExecute(Model model) throws CommandException {
        model.setAddressBook(addressBook);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAssignmentToAllCommand // instanceof handles nulls
                && toAdd.equals(((AddAssignmentToAllCommand) other).toAdd)
                && module.equals(((AddAssignmentToAllCommand) other).module));
    }
}
