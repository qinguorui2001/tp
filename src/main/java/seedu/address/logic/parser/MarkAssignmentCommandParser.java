package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

/**
 * Parses input arguments and creates a new MarkAssignmentCommand object
 */
public class MarkAssignmentCommandParser implements Parser<MarkAssignmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkAssignmentCommand
     * and returns an MarkAssignmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkAssignmentCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_INDEX);

            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEX)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        MarkAssignmentCommand.MESSAGE_USAGE));
            }

            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            return new MarkAssignmentCommand(name, index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAssignmentCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
