package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DueDate {

    public static final String MESSAGE_CONSTRAINTS = "Due dates should be in a format dd/MM/yyyy,HHmm";

    protected static final DateTimeFormatter PARSE_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static final DateTimeFormatter PARSE_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");
    //Todo change the TOW REGEX.
    public static final String VALIDATION_DATE_REGEX = "(((0[1-9]|1[0-9]|2[0-8])[/](0[1-9]|1[012]))|((29|30|31)[/](0[13578]|1[02]))|((29|30)[/](0[4,6,9]|11))[/][0-9]{4})";
    public static final String VALIDATION_TIME_REGEX = ",([0-1]?0[0-9]|2[0-3])[0-5][0-9]";

    private final String value;

    private final LocalDate date;
    private final LocalDateTime dateTime;
    private final LocalTime time;

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
        // checkArgument(isValidDueDate(date.toString() + time.toString()), MESSAGE_CONSTRAINTS);
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
        this(date, "2359");
    }

    public static boolean isValidOnlyDate(String date) {
        return !date.contains(",") && date.matches(VALIDATION_DATE_REGEX);
    }

    public static boolean isValidDateAndTime(String date) {
        return date.contains(",") && date.matches(VALIDATION_TIME_REGEX) && date.matches(VALIDATION_DATE_REGEX);
    }

    public static boolean isValidDueDate(String date) {
        return isValidOnlyDate(date) || isValidDateAndTime(date);
    }

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
