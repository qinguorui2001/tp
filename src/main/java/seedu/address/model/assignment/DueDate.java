package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class DueDate {

    public static final String MESSAGE_CONSTRAINTS_TIME = "Due date should be in a format d/M/yyyy";
    public static final String MESSAGE_CONSTRAINTS_DATE = "Due time should be in a format HHmm";
    public static final String MESSAGE_CONSTRAINTS_DUE_DATE = "Due dates should be in a format d/M/yyyy,HHmm";
    public static final String DATE_VALIDATION_REGEX =
            "^([1-9]|[0-2][0-9]|(3)[0-1])(/)([1-9]|((0)[0-9])|((1)[0-2]))(/)\\d{4}$";
    public static final String TIME_VALIDATION_REGEX = "^(00|[0,1][0-9]|2[0-3])([0-5][0-9])$";
    public static final String DATE_AND_TIME_VALIDATION_REGEX =
              "^([1-9]|[0-2][0-9]|(3)[0-1])(/)([1-9]|((0)[0-9])|((1)[0-2]))(/)\\d{4}(,)"
                      + "(00|[0,1][0-9]|2[0-3])([0-5][0-9])$";
    public static final String LATEST_TIME_IN_DAY = "2359";

    protected static final DateTimeFormatter PARSE_DATE_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy");
    protected static final DateTimeFormatter PARSE_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    public final String value;

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
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS_TIME);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS_DATE);
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
        this(date, LATEST_TIME_IN_DAY);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid Time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    /**
     * Returns true if the date and time is valid.
     */
    public static boolean isValidDateAndTime(String date) {
        return date.matches(DATE_AND_TIME_VALIDATION_REGEX);
    }

    /**
     * Returns true if date is valid due date form.
     */
    public static boolean isValidDueDate(String date) {
        return isValidDate(date) || isValidDateAndTime(date);
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
}
