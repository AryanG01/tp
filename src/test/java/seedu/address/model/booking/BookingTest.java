package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BookingBuilder;

public class BookingTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Booking booking = new BookingBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> booking.getTags().remove(0));
    }

    @Test
    public void isSameRoom() {
        // same object -> returns true
        assertTrue(ALICE.isSameBooking(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameBooking(null));

        // same name, all other attributes different -> returns true
        Booking editedAlice = new BookingBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withStartDate(VALID_DATE_BOB)
                .withEndDate(VALID_DATE_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameBooking(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new BookingBuilder(ALICE).withRoom(VALID_ROOM_BOB).build();
        assertFalse(ALICE.isSameBooking(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Booking editedBob = new BookingBuilder(BOB).withRoom(VALID_ROOM_AMY).build();
        assertFalse(BOB.isSameBooking(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new BookingBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertTrue(BOB.isSameBooking(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Booking aliceCopy = new BookingBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Booking editedAlice = new BookingBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new BookingBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new BookingBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new BookingBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new BookingBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Booking.class.getCanonicalName() + "{room=" + ALICE.getRoom() + ", name=" + ALICE.getName()
                + ", phone=" + ALICE.getPhone() + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress()
                + ", startDate=" + ALICE.getStartDate() + ", endDate=" + ALICE.getEndDate()
                + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
