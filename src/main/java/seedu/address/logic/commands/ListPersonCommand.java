package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Lists all persons in the address book to the user.
 */
public class ListPersonCommand extends Command {

    public static final String COMMAND_WORD = "p-list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private ReadOnlyAddressBook addressBook;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        addressBook = new AddressBook(model.getAddressBook());
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public void unExecute(Model model) throws CommandException {
        model.setAddressBook(addressBook);
    }
}
