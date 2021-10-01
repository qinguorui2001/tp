package seedu.address.model.assignment;

import java.time.format.DateTimeParseException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Status {
    enum AssignmentStatus {
        COMPLETED, PENDING, LATE
    }

    public static final String MESSAGE_CONSTRAINTS = "Status of assignment should be clearly defined!";

    public final String value;

    /**
     * Constructs a {@code Status}.
     *
     * @param assignmentStatus A valid tag name.
     */
    private Status(AssignmentStatus assignmentStatus) {
        requireNonNull(assignmentStatus);
        this.value = assignmentStatus.toString();
    }

    public static Status createCompletedStatus() {
        return new Status(AssignmentStatus.COMPLETED);
    }

    public static Status createPendingStatus() {
        return new Status(AssignmentStatus.PENDING);
    }

    public static Status createLateStatus() {
        return new Status(AssignmentStatus.LATE);
    }

    public static Status createStatus(String value) {
        AssignmentStatus currentStatus = AssignmentStatus.PENDING;
        for (AssignmentStatus status: AssignmentStatus.values()) {
            if (value.equals(status.toString())) {
                currentStatus = status;
            }
        }
        return new Status(currentStatus);
    }
    /**
     * Returns true if a given string is a valid Status.
     */
    public static boolean isValidStatus(String test) {
        for (AssignmentStatus status: AssignmentStatus.values()) {
            if (test.equals(status.toString())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Status // instanceof handles nulls
                && value.equals(((Status) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + value + ']';
    }
}
