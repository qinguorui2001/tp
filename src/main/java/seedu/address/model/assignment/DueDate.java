package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DueDate {
    public static final String MESSAGE_CONSTRAINTS = "Due dates should be in a format dd/MM/yyyy HHmm";
    //TODO: decide which parse, output format to keep
    protected static final DateTimeFormatter PARSE_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static final DateTimeFormatter PARSE_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
    //TODO: change VALIDATION INDEX
    //public static final String VALIDATION_REGEX = "\\d{3,}";

    private final LocalDate date;
    private final LocalDateTime dateTime;
    private final LocalTime time;
    public final String value;

    /**
     * Constructs a {@code DueDate}.
     *
     * @param date Date of due date.
     * @param time Time of due date.
     */
    public DueDate(String date, String time) {
        requireNonNull(date);
        requireNonNull(time);
        // TODO: use Validation regex to verify dateTime format
        //checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, PARSE_DATE_FORMAT);
        this.time = LocalTime.parse(time, PARSE_TIME_FORMAT);
        this.dateTime = LocalDateTime.of(this.date, this.time);
        this.value = this.dateTime.format(OUTPUT_FORMAT);
    }

    /**
     * Constructs a {@code DueDate}.
     *
     * @param date Date of due date.
     */
    public DueDate(String date) {
        requireNonNull(date);
        // TODO: use Validation regex to verify dateTime format
        //checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, PARSE_DATE_FORMAT);
        this.time = LocalTime.parse("2359", PARSE_TIME_FORMAT);
        this.dateTime = LocalDateTime.of(this.date, this.time);
        this.value = this.dateTime.format(OUTPUT_FORMAT);

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
        return value;
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

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}
