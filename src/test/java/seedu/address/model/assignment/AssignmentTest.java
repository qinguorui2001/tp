package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT_CS1101S_MISSION;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT_CS1231S_TUTORIAL;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT_CS2106_PROJECT;
import static seedu.address.testutil.TypicalAssignments.ASSIGNMENT_CS3230_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CS1231S_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CS1231S_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CS3230_LAB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssignmentBuilder;

public class AssignmentTest {
    /*miss two imports here
    import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CS3230_LAB;
    import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CS3230_LAB;
    */

    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(ASSIGNMENT_CS1101S_MISSION.isSameAssignment(ASSIGNMENT_CS1101S_MISSION));

        // null -> returns false
        assertFalse(ASSIGNMENT_CS1101S_MISSION.isSameAssignment(null));

        // same description, all other attributes different -> returns true
        Assignment editedCs1101sAssignment = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION)
                .withLateStatus().withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS1231S_TUTORIAL).build();
        assertTrue(ASSIGNMENT_CS1101S_MISSION.isSameAssignment(editedCs1101sAssignment));

        // different description, all other attributes same -> returns false
        editedCs1101sAssignment = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION)
                .withDescription("I like CS").build();
        assertFalse(ASSIGNMENT_CS1101S_MISSION.isSameAssignment(editedCs1101sAssignment));

        // different status, all other attributes same -> return true
        Assignment editedCs2106Lab = new AssignmentBuilder(ASSIGNMENT_CS2106_PROJECT)
                .withPendingStatus().build();
        assertTrue(ASSIGNMENT_CS2106_PROJECT.isSameAssignment(editedCs2106Lab));

        // description differs in case, all other attributes same -> returns false
        Assignment editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDescription(VALID_DESCRIPTION_CS3230_LAB.toLowerCase()).build();
        assertFalse(ASSIGNMENT_CS3230_LAB.isSameAssignment(editedCs3230Lab));

        // description has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_DESCRIPTION_CS3230_LAB + " ";
        editedCs3230Lab = new AssignmentBuilder().withDescription(nameWithTrailingSpaces).build();
        assertFalse(ASSIGNMENT_CS3230_LAB.isSameAssignment(editedCs3230Lab));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Assignment cs3230LabCopy = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB).build();
        assertTrue(cs3230LabCopy.equals(ASSIGNMENT_CS3230_LAB));

        // same object -> returns true
        assertTrue(ASSIGNMENT_CS3230_LAB.equals(ASSIGNMENT_CS3230_LAB));

        // null -> returns false
        assertFalse(ASSIGNMENT_CS3230_LAB.equals(null));

        // different type -> returns false
        assertFalse(ASSIGNMENT_CS3230_LAB.equals(5));

        // different assignment -> returns false
        assertFalse(ASSIGNMENT_CS3230_LAB.equals(ASSIGNMENT_CS1231S_TUTORIAL));

        // different description -> returns false
        Assignment editedCs1231sTutorial = new AssignmentBuilder(ASSIGNMENT_CS1231S_TUTORIAL)
                .withDescription(VALID_DESCRIPTION_CS3230_LAB).build();
        assertFalse(ASSIGNMENT_CS1231S_TUTORIAL.equals(editedCs1231sTutorial));
        /*
        // different status, change from pending to late -> returns false
        editedCs1231sTutorial = new AssignmentBuilder(ASSIGNMENT_CS1231S_TUTORIAL).withLateStatus().build();
        assertFalse(ASSIGNMENT_CS1231S_TUTORIAL.equals(editedCs1231sTutorial));
        */
        // different status, change from pending to completed -> returns false
        /* editedCs1231sTutorial = new AssignmentBuilder(ASSIGNMENT_CS1231S_TUTORIAL).withCompletedStatus().build();
        assertFalse(ASSIGNMENT_CS1231S_TUTORIAL.equals(editedCs1231sTutorial));
        */
        /*// different status, change from completed to late -> returns false
        Assignment editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB).withLateStatus().build();
        assertFalse(ASSIGNMENT_CS3230_LAB.equals(editedCs3230Lab));

        // different status, change from late to pending -> returns false
        Assignment editedCs2106Project = new AssignmentBuilder(ASSIGNMENT_CS2106_PROJECT).withPendingStatus().build();
        assertFalse(ASSIGNMENT_CS2106_PROJECT.equals(editedCs2106Project));

        // different due date -> returns false
        editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS1231S_TUTORIAL).build();
        assertFalse(ASSIGNMENT_CS3230_LAB.equals(editedCs3230Lab));

        // same time but different date -> returns false
        editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS3230_LAB).build();
        assertFalse(ASSIGNMENT_CS3230_LAB.equals(editedCs3230Lab));

        // same date but different time -> returns false
        editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS3230_LAB, VALID_TIME_CS1231S_TUTORIAL).build();
        assertFalse(ASSIGNMENT_CS3230_LAB.equals(editedCs3230Lab));
        */
    }
}
