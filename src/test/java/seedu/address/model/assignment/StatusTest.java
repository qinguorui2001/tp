package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void equals() {
        Status completedStatus = Status.createCompletedStatus();
        Status pendingStatus = Status.createPendingStatus();
        Status lateStatus = Status.createLateStatus();

        // check null -> returns false
        assertFalse(completedStatus.equals(null));
        assertFalse(lateStatus.equals(null));
        assertFalse(pendingStatus.equals(null));

        // check same status -> returns true
        assertTrue(completedStatus.equals(completedStatus));
        assertTrue(pendingStatus.equals(pendingStatus));
        assertTrue(lateStatus.equals(lateStatus));

        // check different status -> return false
        assertFalse(completedStatus.equals(lateStatus));
        assertFalse(completedStatus.equals(pendingStatus));
        assertFalse(pendingStatus.equals(lateStatus));
    }

    @Test
    public void compareTo() {
        Status completedStatus = Status.createCompletedStatus();
        Status pendingStatus = Status.createPendingStatus();
        Status lateStatus = Status.createLateStatus();

        // comparing status: completed status
        assertTrue(completedStatus.compareTo(completedStatus) == 0); // equal weight as completed status
        assertTrue(completedStatus.compareTo(pendingStatus) > 0); // more weight than pending status
        assertTrue(completedStatus.compareTo(lateStatus) > 0); // more weight than late status

        // comparing status: pending status
        assertTrue(pendingStatus.compareTo(completedStatus) < 0); // less weight than completed status
        assertTrue(pendingStatus.compareTo(pendingStatus) == 0); // equal weight as pending status
        assertTrue(pendingStatus.compareTo(lateStatus) > 0); // more weight than late status

        // comparing status: late status
        assertTrue(lateStatus.compareTo(completedStatus) < 0); // less weight than completed status
        assertTrue(lateStatus.compareTo(pendingStatus) < 0); // less weight than completed status
        assertTrue(lateStatus.compareTo(lateStatus) == 0); // equal weight as late status
    }
}
