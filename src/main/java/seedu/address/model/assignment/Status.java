package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

public class Status {
    public static final String MESSAGE_CONSTRAINTS = "Status of assignment should be clearly defined!";
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

    /**
     * Creates a status based on the status string in the Json file
     *
     * @param value the status string in the Json file
     * @return the corresponding Status
     */
    public static Status createStatus(String value) {
        StatusType currentStatus = StatusType.PENDING;
        for (StatusType status: StatusType.values()) {
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
        for (StatusType status: StatusType.values()) {
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
    @Override
    public String toString() {
        return '[' + value.toUpperCase() + ']';
    }
}
