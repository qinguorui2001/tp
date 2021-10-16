package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.MarkAssignmentCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class MarkAssignmentCommandParserTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final MarkAssignmentCommandParser parser = new MarkAssignmentCommandParser();
    private final Person personInList = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

    @Test
    public void parse_validArgs_returnsMarkCommand() {
        assertParseSuccess(parser,  " n/" + personInList.getName() + "1",
                new MarkAssignmentCommand(personInList.getName(), INDEX_FIRST_ASSIGNMENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " n/" + personInList.getName() + "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAssignmentCommand.MESSAGE_USAGE));
    }
}
