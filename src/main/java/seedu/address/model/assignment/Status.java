package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

public class Status {
    public final String value;

    enum StatusType {
        COMPLETED, LATE, PENDING
    }

    /**
     * Initialises Status class
     */
    private Status(StatusType value) {
        requireNonNull(value);

        this.value = value.toString().toLowerCase();
    }

    public static Status createCompletedStatus() {
        return new Status(StatusType.COMPLETED);
    }

    public static Status createPendingStatus() {
        return new Status(StatusType.PENDING);
    }

    public static Status createLateStatus() {
        return new Status(StatusType.LATE);
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
    @Override
    public String toString() {
        return '[' + value.toUpperCase() + ']';
    }
}
