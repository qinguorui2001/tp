package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DueDate {
    public static final String MESSAGE_CONSTRAINTS = "Due dates should be in a format dd/MM/yyyy HHmm";
    //TODO: decide which parse, output format to keep
    protected static final DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
    //TODO: change VALIDATION INDEX
    //public static final String VALIDATION_REGEX = "\\d{3,}";
    public final LocalDateTime value;

    /**
     * Constructs a {@code DueDate}.
     *
     * @param dateTime A valid dateTime.
     */
    public DueDate(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        // TODO: use Validation regex to verify dateTime format
        //checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        this.value = dateTime;
    }

    // TODO: need change according to validation index
    //    /**
    //     * Returns true if a given string is a valid assignment.
    //     */
    //    public static boolean isValidAssignment(String test) {
    //
    //        return test.matches(VALIDATION_REGEX);
    //    }

    @Override
    public String toString() {
        return value.format(OUTPUT_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueDate // instanceof handles nulls
                && value.equals(((DueDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
