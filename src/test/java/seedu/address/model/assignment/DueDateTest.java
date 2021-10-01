package seedu.address.model.assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

class DueDateTest {
//    @Test
//    public void constructor_null_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> new DueDate(null));
//    }

    @Test
    public void constructor_invalidDueDate_throwsIllegalArgumentException() {
        String invalidDueDate = "";
        assertThrows(IllegalArgumentException.class, () -> new DueDate(invalidDueDate));
    }

    @Test
    public void isValidDate() {
        // null Date
        assertThrows(NullPointerException.class, () -> DueDate.isValidDate(null));

        // invalid DueDate
        assertFalse(DueDate.isValidDate("")); // empty string
        assertFalse(DueDate.isValidDate(" ")); // spaces only
        assertFalse(DueDate.isValidDate("32/12/2021")); // invalid day
        assertFalse(DueDate.isValidDate("12/13/2021")); // invalid month
        assertFalse(DueDate.isValidDate("99/99/2021")); // invalid day and month

        // valid DueDate
        assertTrue(DueDate.isValidDate("11/11/2021")); // double digit for day and month
        assertTrue(DueDate.isValidDate("1/1/2021")); // single digit for day and month
        assertTrue(DueDate.isValidDate("1/11/2021")); // single digit for day and double digit for month
        assertTrue(DueDate.isValidDate("11/1/2021")); // double digit for day and double digit for month
        assertTrue(DueDate.isValidDate("31/12/9999")); // maximum values for day, month and year
        assertTrue(DueDate.isValidDate("01/02/2021")); // day and month with prefix 0
        assertTrue(DueDate.isValidDate("01/1/2021")); // day with prefix 0 with single digit month
        assertTrue(DueDate.isValidDate("1/01/2021")); // month with prefix 0 with single digit day
        assertTrue(DueDate.isValidDate("11/01/2021")); // month with prefix 0 with double digit day
        assertTrue(DueDate.isValidDate("01/11/2021")); // day with prefix 0 with double digit month
    }

    @Test
    public void isValidTime() {
        // null Date
        assertThrows(NullPointerException.class, () -> DueDate.isValidTime(null));

        // invalid DueDate
        assertFalse(DueDate.isValidDate("")); // empty string
        assertFalse(DueDate.isValidDate(" ")); // spaces only
        assertFalse(DueDate.isValidTime("2400")); // invalid hour
        assertFalse(DueDate.isValidTime("0060")); // invalid minutes
        assertFalse(DueDate.isValidTime("2460")); // invalid hour and minutes

        // valid DueDate
        assertTrue(DueDate.isValidTime("2359")); // maximum hour and minutes
        assertTrue(DueDate.isValidTime("0000")); // minimum hour and minutes
        assertTrue(DueDate.isValidTime("0059")); // minimum hour and maximum minutes
        assertTrue(DueDate.isValidTime("2300")); // maximum hour and minimum minutes
    }

}
