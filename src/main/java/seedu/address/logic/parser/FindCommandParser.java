package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private final String NAME_PREFIX = "n/";
    private final String MODULE_PREFIX = "m/";
    private final String WHITESPACE = "|\\s+";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // Default Regex
        String regex;

        Pattern namePattern = Pattern.compile(NAME_PREFIX);
        Pattern modulePattern = Pattern.compile(MODULE_PREFIX);
        Matcher nameMatcher = namePattern.matcher(trimmedArgs);
        Matcher moduleMatcher = modulePattern.matcher(trimmedArgs);
        boolean hasName = nameMatcher.find();
        boolean hasModule = moduleMatcher.find();

        if (hasName && hasModule) {
            regex = "(\\s*([nm]/))+|(\\s+)";
        } else if (hasName) {
            regex = NAME_PREFIX + WHITESPACE;
        } else if (hasModule) {
            regex = MODULE_PREFIX + WHITESPACE;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // New regex matches our search by name / module prefixes of n/ or m/
        String[] nameKeywords = trimmedArgs.split(regex);

        ArrayList<String> keywords = new ArrayList<>();
        for (String str : nameKeywords) {
            if (!Objects.equals(str, "")) {
                keywords.add(str);
            }
        }

        return new FindCommand(new NameContainsKeywordsPredicate(keywords));
    }

}
