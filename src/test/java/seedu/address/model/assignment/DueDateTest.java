package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DueDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DueDate(null));
    }

    @Test
    public void constructor_invalidDueDate_throwsDateTimeParseException() {
        String invalidDueDate = "";
        assertThrows(DateTimeParseException.class, () -> new DueDate(invalidDueDate));
    }

    @Test
    public void isValidDueDate() {
        // null dueDate
        assertThrows(NullPointerException.class, () -> DueDate.isValidDueDate(null));

        // invalid dueDate
        assertFalse(DueDate.isValidDueDate("")); // empty string
        assertFalse(DueDate.isValidDueDate(" ")); // spaces only
        assertFalse(DueDate.isValidDueDate("^")); // only non-alphanumeric characters
        assertFalse(DueDate.isValidDueDate("11/11/2021*")); // contains non-alphanumeric characters

        // valid dueDate
        assertTrue(DueDate.isValidDueDate("11/11/2021")); // date only
        assertTrue(DueDate.isValidDueDate("12/12/2011,1655")); // date and time only
    }
}
