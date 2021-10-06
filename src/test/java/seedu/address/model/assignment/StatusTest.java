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
}
