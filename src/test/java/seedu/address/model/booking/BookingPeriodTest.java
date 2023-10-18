package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BookingPeriodTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BookingPeriod(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new BookingPeriod(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> BookingPeriod.isValidBookingPeriod(null));

        // invalid addresses
        assertFalse(BookingPeriod.isValidBookingPeriod("")); // empty string
        assertFalse(BookingPeriod.isValidBookingPeriod(" ")); // spaces only

        // valid addresses
        assertTrue(BookingPeriod.isValidBookingPeriod("Blk 456, Den Road, #01-355"));
        assertTrue(BookingPeriod.isValidBookingPeriod("-")); // one character
        assertTrue(BookingPeriod.isValidBookingPeriod("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); //
        // long
        // address
    }

    @Test
    public void equals() {
        BookingPeriod bookingPeriod = new BookingPeriod("Valid Address");

        // same values -> returns true
        assertTrue(bookingPeriod.equals(new BookingPeriod("Valid Address")));

        // same object -> returns true
        assertTrue(bookingPeriod.equals(bookingPeriod));

        // null -> returns false
        assertFalse(bookingPeriod.equals(null));

        // different types -> returns false
        assertFalse(bookingPeriod.equals(5.0f));

        // different values -> returns false
        assertFalse(bookingPeriod.equals(new BookingPeriod("Other Valid Address")));
    }
}
