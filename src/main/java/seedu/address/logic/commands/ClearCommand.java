package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    private ReadOnlyAddressBook addressBook;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        addressBook = new AddressBook(model.getAddressBook());
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public void unExecute(Model model) throws CommandException {
        model.setAddressBook(addressBook);
    }
}
