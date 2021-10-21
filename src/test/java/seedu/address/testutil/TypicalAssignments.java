package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.assignment.Assignment;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CS2106_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS1231S_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2106_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS3230_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CS1231S_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CS3230_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CS1231S_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CS2106_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CS3230_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS2100_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIENDLY_DATE_CS2100_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CS2100_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_GEQ1000_QUIZ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIENDLY_DATE_GEQ1000_QUIZ;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_GEQ1000_QUIZ;


/**
 * A utility class containing a list of {@code Assignment} objects to be used in tests.
 */
public class TypicalAssignments {
    public static final Assignment ASSIGNMENT_CS1101S_MISSION = new AssignmentBuilder()
            .withDescription("Do CS1101S Mission Week 6")
            .withDueDate("30/09/2021", "1800")
            .withPendingStatus().build();
    public static final Assignment ASSIGNMENT_CS2103_QUIZ = new AssignmentBuilder()
            .withDescription("Do CS2103 quiz week 7")
            .withDueDate("30/11/2022", "0819")
            .withPendingStatus().build();
    public static final Assignment ASSIGNMENT_CS2100_TUTORIAL = new AssignmentBuilder()
            .withDescription("Do CS2100 tutorial 4")
            .withDueDate("5/10/2021", "2200")
            .withPendingStatus().build();
    public static final Assignment ASSIGNMENT_MA1521_TUTORIAL = new AssignmentBuilder()
            .withDescription("Do MA1521 Tutorial 3")
            .withDueDate("4/10/2020", "2100")
            .withPendingStatus().build();

    // Manually added - Assignment's details found in {@code CommandTestUtil}
    public static final Assignment ASSIGNMENT_CS1231S_TUTORIAL = new AssignmentBuilder()
            .withDescription(VALID_DESCRIPTION_CS1231S_TUTORIAL)
            .withPendingStatus()
            .withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS1231S_TUTORIAL).build();
    public static final Assignment ASSIGNMENT_CS3230_LAB = new AssignmentBuilder()
            .withDescription(VALID_DESCRIPTION_CS3230_LAB)
            .withCompletedStatus()
            .withDueDate(VALID_DATE_CS3230_LAB, VALID_TIME_CS3230_LAB)
            .build();
    public static final Assignment ASSIGNMENT_CS2106_PROJECT = new AssignmentBuilder()
            .withDescription(VALID_DESCRIPTION_CS2106_PROJECT)
            .withLateStatus()
            .withDueDate(VALID_DATE_CS2106_PROJECT, VALID_TIME_CS2106_PROJECT)
            .build();
    public static final Assignment ASSIGNMENT_CS2100_LAB = new AssignmentBuilder()
            .withDescription(VALID_DESCRIPTION_CS2100_LAB)
            .withPendingStatus()
            .withDueDate(VALID_FRIENDLY_DATE_CS2100_LAB, VALID_TIME_CS2100_LAB)
            .build();
    public static final Assignment ASSIGNMENT_GEQ1000_QUIZ = new AssignmentBuilder()
            .withDescription(VALID_DESCRIPTION_GEQ1000_QUIZ)
            .withPendingStatus()
            .withDueDate(VALID_FRIENDLY_DATE_GEQ1000_QUIZ, VALID_TIME_GEQ1000_QUIZ)
            .build();


    // prevents instantiation
    private TypicalAssignments() {}

    public static List<Assignment> getTypicalAssignments() {
        return new ArrayList<>(Arrays.asList(ASSIGNMENT_MA1521_TUTORIAL, ASSIGNMENT_CS2100_TUTORIAL,
                ASSIGNMENT_CS2103_QUIZ, ASSIGNMENT_CS1101S_MISSION));
    }
}
