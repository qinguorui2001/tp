package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Assignment} objects to be used in tests.
 */
public class TypicalAssignments {
    // TODO: typical assignment rewrite + CommandTestUtil
    public static final Assignment ASSIGNMENT_TUTORIAL = new AssignmentBuilder()
            .withDescription("Do CS2100 tutorial 4")
            .withDueDate("30/9/2021, 1800")
            .withPendingStatus().build();
    public static final Assignment ASSIGNMENT_QUIZ = new AssignmentBuilder()
            .withDescription("Do CS2103 quiz week 7")
            .withDueDate("30/11/2022, 0819")
            .withPendingStatus().build();
    public static final Assignment ASSIGNMENT_CS2100_TUTORIAL = new AssignmentBuilder()
            .withDescription("Do CS2100 tutorial 4")
            .withDueDate("30/09/2021, 1854")
            .withPendingStatus().build();
    public static final Assignment ASSIGNMENT_CS2100_TUTORIAL = new AssignmentBuilder()
            .withDescription("Do CS2100 tutorial 4")
            .withDueDate("30/9/2021, 1800")
            .withPendingStatus().build();
    public static final Assignment ASSIGNMENT_CS2100_TUTORIAL = new AssignmentBuilder()
            .withDescription("Do CS2100 tutorial 4")
            .withDueDate("30/9/2021, 1800")
            .withPendingStatus().build();
    public static final Assignment ASSIGNMENT_CS2100_TUTORIAL = new AssignmentBuilder()
            .withDescription("Do CS2100 tutorial 4")
            .withDueDate("30/9/2021, 1800")
            .withPendingStatus().build();

    // prevents instantiation
    private TypicalAssignments() {}

    public static List<Assignment> getTypicalAssignments() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
