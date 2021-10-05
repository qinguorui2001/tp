package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.person.Module.MESSAGE_CONSTRAINTS;
import static seedu.address.model.person.Module.VALIDATION_REGEX;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    // Serves as regex to match for name prefix
    private final Prefix namePrefix = new Prefix("n/");

    // Serves as regex to match for module prefix
    private final Prefix modulePrefix = new Prefix("m/");

    // Serves as regex to match for any whitespace with an OR
    private final String orWhitespace = "|\\s+";

    // Serves as regex to match for any whitespace
    private final String whitespace = "\\s+";

    // Serves as regex to match for both name and module prefix, along with whitespaces
    private final Prefix nameModulePrefix = new Prefix("(\\s*([nm]/))+|(\\s+)");

    // Serves as the constant values for different branches
    private enum Mode {
        NAME_MODULE,
        NAME,
        MODULE
    }

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

        // Regex depends on user input
        String regex;

        Pattern namePattern = Pattern.compile(namePrefix.getPrefix());
        Pattern modulePattern = Pattern.compile(modulePrefix.getPrefix());
        Matcher nameMatcher = namePattern.matcher(trimmedArgs);
        Matcher moduleMatcher = modulePattern.matcher(trimmedArgs);
        boolean hasName = nameMatcher.find();
        boolean hasModule = moduleMatcher.find();

        // Represents simply which branch it matches
        Mode mode;

        if (hasName && hasModule) {
            // Branch 0
            regex = nameModulePrefix.getPrefix();
            mode = Mode.NAME_MODULE;
        } else if (hasName) {
            // Branch 1
            regex = namePrefix + orWhitespace;
            mode = Mode.NAME;
        } else if (hasModule) {
            // Branch 2
            regex = modulePrefix + orWhitespace;
            mode = Mode.MODULE;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = generateKeywords(trimmedArgs, regex, mode);

        ArrayList<String> keywords = new ArrayList<>();
        for (String str : nameKeywords) {
            if (!Objects.equals(str, "")) {
                keywords.add(str);
            }
        }

        return new FindCommand(new NameContainsKeywordsPredicate(keywords));
    }

    /**
     * Generates the keywords based on the given user input, corresponding regex and
     * mode which defines what kind of input is further given by the user.
     *
     * @param input user input in command line
     * @param regex chosen regex to parse user input
     * @param mode mode which defines what input the user has given
     * @return a String array that contains the generated keywords
     * @throws ParseException if the user input does not match defined command usage
     */
    public String[] generateKeywords(String input, String regex, Mode mode) throws ParseException {

        String[] tempReturn = input.split(regex);

        if (mode.equals(Mode.NAME_MODULE)) {
            // Both name and module
            // Splits by module prefix
            String[] splitByModulePrefix = input.split(modulePrefix.getPrefix());

            String[] splitByWhiteSpace;

            // Splits modules by whitespace if m/ comes after n/
            if (!splitByModulePrefix[0].equals("") && splitByModulePrefix[0].charAt(0) == 'n') {
                splitByWhiteSpace = splitByModulePrefix[1].split(whitespace);
            } else {
                // Throw error and restate command use
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            // Check each module fits the constraints
            checkModuleValidity(splitByWhiteSpace);

            // Past here, modules are all valid
        } else if (mode.equals(Mode.NAME)) {
            // Only name
            return tempReturn;
        } else if (mode.equals(Mode.MODULE)) {
            // Only module
            checkModuleValidity(tempReturn);
        }

        return tempReturn;
    }

    /**
     * Checks if the provided modules are valid.
     *
     * @param modules an array of modules to be checked for validity
     * @throws ParseException if any one of the modules do not match a valid module code
     */
    private void checkModuleValidity(String[] modules) throws ParseException {

        Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(VALIDATION_REGEX);
        for (String s : modules) {
            matcher = pattern.matcher(s);
            if (!matcher.find() && !Objects.equals(s, "")) {
                throw new ParseException(MESSAGE_CONSTRAINTS);
            }
        }
    }

}
