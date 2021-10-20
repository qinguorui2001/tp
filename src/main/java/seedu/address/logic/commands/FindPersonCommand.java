package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_WORD = "p-find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: n/NAME_KEYWORD [MORE_KEYWORDS] m/MODULE_KEYWORD [MORE_KEYWORDS]...\n"
            + "Both types of keywords are optional, but at least one is necessary\n"
            + "Example: " + COMMAND_WORD + " n/alice bob charlie m/CS1101S";

    private final NameContainsKeywordsPredicate predicate;
    private ReadOnlyAddressBook addressBook;
    private ObservableList<Person> personFilteredList;

    public FindPersonCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        addressBook = new AddressBook(model.getAddressBook());
        personFilteredList = model.getFilteredPersonListCopy();
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public void unExecute(Model model) throws CommandException {
        model.setAddressBook(addressBook);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && predicate.equals(((FindPersonCommand) other).predicate)); // state check
    }
}
