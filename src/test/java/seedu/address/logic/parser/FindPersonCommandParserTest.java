package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.person.Module.MESSAGE_CONSTRAINTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class FindCommandParserTest {

    private FindPersonCommandParser parser = new FindPersonCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces

        FindPersonCommand expectedFindCommand =
                new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "n/ \n Alice \n \t Bob  \t", expectedFindCommand);

        //  finding a valid module
        FindPersonCommand expectedFindCommandWithModule =
                new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList("CS1101S")));
        assertParseSuccess(parser, "m/CS1101S", expectedFindCommandWithModule);

        // finding both name and valid module
        FindPersonCommand expectedFindCommandWithBoth =
                new FindPersonCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob", "CS1101S")));
        assertParseSuccess(parser, "n/Alice Bob m/CS1101S", expectedFindCommandWithBoth);
    }

    @Test
    public void parse_invalidArgs_returnsError() {
        // no prefix n/ or m/
        assertParseFailure(parser, "Alice Bob CS1101S",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));

        // invalid module codes
        assertParseFailure(parser, "m/Alice", MESSAGE_CONSTRAINTS);

        // invalid module codes with valid name
        assertParseFailure(parser, "n/Alice m/Bob", MESSAGE_CONSTRAINTS);

        // inverted prefixes
        assertParseFailure(parser, "m/MA1521 n/Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
    }

}
