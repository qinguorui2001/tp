package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

public class Status {
    enum AssignmentStatus {
        COMPLETED, PENDING, LATE
    }

    private final String value;

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
