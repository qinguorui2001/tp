package seedu.address.logic.parser;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.model.person.Module.MESSAGE_CONSTRAINTS;
import static seedu.address.model.person.Module.VALIDATION_REGEX;

/**
 * Parses input arguments and creates a new FindPersonCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    private static final String PREFIXES = "[nmt]/";

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    // Serves as the constant values for different branches
    private enum Mode {
        NAME_MODULE,
        NAME,
        MODULE
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindPersonCommand
     * and returns a FindPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPersonCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        Pattern prefixesPattern = Pattern.compile(PREFIXES);
        Matcher prefixesMatcher = prefixesPattern.matcher(trimmedArgs);
        boolean hasPrefixes = prefixesMatcher.find();

        if (!hasPrefixes) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        Pattern modulePattern = Pattern.compile(PREFIX_MODULE.getPrefix());
        Matcher moduleMatcher = modulePattern.matcher(trimmedArgs);

        if (!moduleMatcher.find()) {

            String[] split = trimmedArgs.split(PREFIXES);
            ArrayList<String> keywords = new ArrayList<>();

            for (String s : split) {
                if (!s.equals("")) {
                    keywords.add(s.trim());
                }
            }

            return new FindPersonCommand(new NameContainsKeywordsPredicate(keywords));
        } else {

            String[] splitByModule = trimmedArgs.split(PREFIX_MODULE.getPrefix());
            String moduleHalf = splitByModule[1];
            logger.info("DOESM ODULE: " + Arrays.toString(moduleHalf.split(PREFIXES)));

            return null;
        }

    }
//    public FindPersonCommand parse(String args) throws ParseException {
//        String trimmedArgs = args.trim();
//
//        if (trimmedArgs.isEmpty()) {
//            throw new ParseException(
//                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
//        }
//
//        // Regex depends on user input
//        String regex;
//
//        Pattern namePattern = Pattern.compile(namePrefix.getPrefix());
//        Pattern modulePattern = Pattern.compile(modulePrefix.getPrefix());
//        Matcher nameMatcher = namePattern.matcher(trimmedArgs);
//        Matcher moduleMatcher = modulePattern.matcher(trimmedArgs);
//        boolean hasName = nameMatcher.find();
//        boolean hasModule = moduleMatcher.find();
//
//        // Represents simply which branch it matches
//        Mode mode;
//
//        if (hasName && hasModule) {
//            // Branch 0
//            regex = nameModulePrefix.getPrefix();
//            mode = Mode.NAME_MODULE;
//        } else if (hasName) {
//            // Branch 1
//            regex = namePrefix + orWhitespace;
//            mode = Mode.NAME;
//        } else if (hasModule) {
//            // Branch 2
//            regex = modulePrefix + orWhitespace;
//            mode = Mode.MODULE;
//        } else {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
//        }
//
//        String[] nameKeywords = generateKeywords(trimmedArgs, regex, mode);
//
//        ArrayList<String> keywords = new ArrayList<>();
//        for (String str : nameKeywords) {
//            if (!Objects.equals(str, "")) {
//                keywords.add(str);
//            }
//        }
//
//        return new FindPersonCommand(new NameContainsKeywordsPredicate(keywords));
//    }

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
//    public String[] generateKeywords(String input, String regex, Mode mode) throws ParseException {
//
//        String[] tempReturn = input.split(regex);
//
//        if (mode.equals(Mode.NAME_MODULE)) {
//            // Both name and module
//            // Splits by module prefix
//            String[] splitByModulePrefix = input.split(modulePrefix.getPrefix());
//
//            String[] splitByWhiteSpace;
//
//            // Splits modules by whitespace if m/ comes after n/
//            if (!splitByModulePrefix[0].equals("") && splitByModulePrefix[0].charAt(0) == 'n') {
//                splitByWhiteSpace = splitByModulePrefix[1].split(whitespace);
//            } else {
//                // Throw error and restate command use
//                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
//                        FindPersonCommand.MESSAGE_USAGE));
//            }
//
//            // Check each module fits the constraints
//            checkModuleValidity(splitByWhiteSpace);
//
//            // Past here, modules are all valid
//        } else if (mode.equals(Mode.NAME)) {
//            // Only name
//            return tempReturn;
//        } else if (mode.equals(Mode.MODULE)) {
//            // Only module
//            checkModuleValidity(tempReturn);
//        }
//
//        return tempReturn;
//    }

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
            matcher = pattern.matcher(s.toUpperCase(Locale.ROOT));
            if (!matcher.find() && !Objects.equals(s, "")) {
                throw new ParseException(MESSAGE_CONSTRAINTS);
            }
        }
    }

}
