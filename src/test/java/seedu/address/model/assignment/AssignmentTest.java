package seedu.address.model.assignment;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssignmentBuilder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalAssignments.*;

public class AssignmentTest {


    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(ASSIGNMENT_CS1101S_MISSION.isSameAssignment(ASSIGNMENT_CS1101S_MISSION));

        // null -> returns false
        assertFalse(ASSIGNMENT_CS1101S_MISSION.isSameAssignment(null));

        // same description, all other attributes different -> returns false
        Assignment editedCs1101sAssignment = new AssignmentBuilder(ASSIGNMENT_CS1101S_MISSION)
                .withLateStatus().withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS1231S_TUTORIAL).build();
        assertFalse(ASSIGNMENT_CS1101S_MISSION.isSameAssignment(editedCs1101sAssignment));

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

        // same description and same due date constructed using friendly commands -> returns true
        Assignment cs2100LabDueTmr = new AssignmentBuilder(ASSIGNMENT_CS2100_LAB).build();
        Assignment cs2100LabDueTmrManual = new AssignmentBuilder().withDescription(VALID_DESCRIPTION_CS2100_LAB)
                .withDueDate("tmr", "1300").withPendingStatus().build();
        assertEquals(cs2100LabDueTmr, cs2100LabDueTmrManual);

        // same description and same due date constructed using friendly commands manually -> returns true
        Assignment geq1000QuizDueToday = new AssignmentBuilder(ASSIGNMENT_GEQ1000_QUIZ).build();
        String[] dateArr = LocalDate.now().toString().split("-");
        List<String> list = Arrays.asList(dateArr);
        Collections.reverse(list);
        String date = formatDate(list);
        String[] args = new String[]{
            VALID_DESCRIPTION_GEQ1000_QUIZ,
            new DueDate(date, "2359").toString(),
            String.valueOf(Status.createPendingStatus())};
        Assignment geq1000QuizDueTodayManual = new Assignment(args);
        assertEquals(geq1000QuizDueToday, geq1000QuizDueTodayManual);
    }

    /**
     * Reverses a list containing YYYY/MM/DD to form DD/MM/YYYY
     *
     * @param list containing wrong format local date
     * @return String in the form of DD/MM/YYYY
     */
    private String formatDate(List<String> list) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String s : list) {
            sb.append((s));
            if (index < 2) {
                sb.append("/");
            }
            index++;
        }
        return sb.toString();
    }

    @Test
    public void equals() {
        // same values -> returns true
        Assignment cs3230LabCopy = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB).build();
        assertEquals(cs3230LabCopy, ASSIGNMENT_CS3230_LAB);

        // same object -> returns true
        assertEquals(ASSIGNMENT_CS3230_LAB, ASSIGNMENT_CS3230_LAB);

        // null -> returns false
        assertNotEquals(null, ASSIGNMENT_CS3230_LAB);

        // different type -> returns false
        assertNotEquals(5, ASSIGNMENT_CS3230_LAB);

        // different assignment -> returns false
        assertNotEquals(ASSIGNMENT_CS3230_LAB, ASSIGNMENT_CS1231S_TUTORIAL);

        // different description -> returns false
        Assignment editedCs1231sTutorial = new AssignmentBuilder(ASSIGNMENT_CS1231S_TUTORIAL)
                .withDescription(VALID_DESCRIPTION_CS3230_LAB).build();
        assertFalse(ASSIGNMENT_CS1231S_TUTORIAL.equals(editedCs1231sTutorial));

        // different status, change from pending to late -> returns false
        editedCs1231sTutorial = new AssignmentBuilder(ASSIGNMENT_CS1231S_TUTORIAL).withLateStatus().build();
        assertNotEquals(ASSIGNMENT_CS1231S_TUTORIAL, editedCs1231sTutorial);

        // different status, change from pending to completed -> returns false
        editedCs1231sTutorial = new AssignmentBuilder(ASSIGNMENT_CS1231S_TUTORIAL).withCompletedStatus().build();
        assertNotEquals(ASSIGNMENT_CS1231S_TUTORIAL, editedCs1231sTutorial);
        // different status, change from completed to late -> returns false
        Assignment editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB).withLateStatus().build();
        assertNotEquals(ASSIGNMENT_CS3230_LAB, editedCs3230Lab);

        // different status, change from late to pending -> returns false
        Assignment editedCs2106Project = new AssignmentBuilder(ASSIGNMENT_CS2106_PROJECT).withPendingStatus().build();
        assertNotEquals(ASSIGNMENT_CS2106_PROJECT, editedCs2106Project);

        // different due date -> returns false
        editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS1231S_TUTORIAL).build();
        assertNotEquals(ASSIGNMENT_CS3230_LAB, editedCs3230Lab);

        // same time but different date -> returns false
        editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS1231S_TUTORIAL, VALID_TIME_CS3230_LAB).build();
        assertNotEquals(ASSIGNMENT_CS3230_LAB, editedCs3230Lab);

        // same date but different time -> returns false
        editedCs3230Lab = new AssignmentBuilder(ASSIGNMENT_CS3230_LAB)
                .withDueDate(VALID_DATE_CS3230_LAB, VALID_TIME_CS1231S_TUTORIAL).build();
        assertNotEquals(ASSIGNMENT_CS3230_LAB, editedCs3230Lab);

    }
}
