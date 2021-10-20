package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

/**
 * Lists all persons in the address book to the user.
 */
public class ListPersonCommand extends Command {

    public static final String COMMAND_WORD = "p-list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private ReadOnlyAddressBook addressBook;
    private ObservableList<Person> personFilteredList;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        addressBook = new AddressBook(model.getAddressBook());
        personFilteredList = model.getFilteredPersonListCopy();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public void unExecute(Model model) throws CommandException {
        model.setAddressBook(addressBook);
    }
}
