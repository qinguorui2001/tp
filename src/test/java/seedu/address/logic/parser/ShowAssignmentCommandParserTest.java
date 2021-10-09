package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ShowAssignmentCommand;

public class ShowAssignmentCommandParserTest {

    private ShowAssignmentCommandParser parser = new ShowAssignmentCommandParser();

    @Test
    public void parse_validArgs_returnsShowAssignmentCommand() {
        assertParseSuccess(parser, "1", new ShowAssignmentCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowAssignmentCommand.MESSAGE_USAGE));
    }
}
