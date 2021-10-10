package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddAssignmentCommand;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalAssignments;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

public class AddAssignmentCommandParserTest {
    private AddAssignmentCommandParser parser = new AddAssignmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
        Assignment expectedAssignment = TypicalAssignments.ASSIGNMENT_CS1231S_TUTORIAL;
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + DESCRIPTION_DESC_CS1231S_TUTORIAL
                + DATE_DESC_CS1231S_TUTORIAL, new AddAssignmentCommand(expectedPerson.getName(), expectedAssignment));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAssignmentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                DESCRIPTION_DESC_CS1231S_TUTORIAL + DATE_DESC_CS1231S_TUTORIAL,
                expectedMessage);

        // missing description prefix
        assertParseFailure(parser, NAME_DESC_BOB + DATE_DESC_CS1231S_TUTORIAL,
                expectedMessage);

        // missing due date prefix
        assertParseFailure(parser, NAME_DESC_BOB + DESCRIPTION_DESC_CS1231S_TUTORIAL,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_EMAIL_BOB + VALID_MODULE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
                INVALID_NAME_DESC + DESCRIPTION_DESC_CS1231S_TUTORIAL + DATE_DESC_CS1231S_TUTORIAL,
                Name.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_DESCRIPTION_DESC + DATE_DESC_CS1231S_TUTORIAL,
                Description.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, NAME_DESC_BOB + DESCRIPTION_DESC_CS1231S_TUTORIAL + INVALID_DATE_DESC,
                DueDate.MESSAGE_CONSTRAINTS);
    }
}
