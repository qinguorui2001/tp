package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidModule_throwsIllegalArgumentException() {
        String invalidModule = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidModule));
    }

    @Test
    public void isValidModule() {
        // null address
        assertThrows(NullPointerException.class, () -> Module.isValidModule(null));

        // invalid addresses
        assertFalse(Module.isValidModule("")); // empty string
        assertFalse(Module.isValidModule(" ")); // spaces only

        // valid addresses
        assertTrue(Module.isValidModule("Blk 456, Den Road, #01-355"));
        assertTrue(Module.isValidModule("-")); // one character
        assertTrue(Module.isValidModule("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
