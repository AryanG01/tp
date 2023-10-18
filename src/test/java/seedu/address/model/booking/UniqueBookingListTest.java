package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKINGPERIOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.booking.exceptions.DuplicateBookingException;
import seedu.address.model.booking.exceptions.PersonNotFoundException;
import seedu.address.testutil.BookingBuilder;

public class UniqueBookingListTest {

    private final UniqueBookingList uniqueBookingList = new UniqueBookingList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueBookingList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueBookingList.add(ALICE);
        assertTrue(uniqueBookingList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBookingList.add(ALICE);
        Booking editedAlice = new BookingBuilder(ALICE).withAddress(VALID_BOOKINGPERIOD_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueBookingList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueBookingList.add(ALICE);
        assertThrows(DuplicateBookingException.class, () -> uniqueBookingList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBooking(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBooking(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueBookingList.setBooking(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueBookingList.add(ALICE);
        uniqueBookingList.setBooking(ALICE, ALICE);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(ALICE);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueBookingList.add(ALICE);
        Booking editedAlice = new BookingBuilder(ALICE).withAddress(VALID_BOOKINGPERIOD_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueBookingList.setBooking(ALICE, editedAlice);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(editedAlice);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueBookingList.add(ALICE);
        uniqueBookingList.setBooking(ALICE, BOB);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOB);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueBookingList.add(ALICE);
        uniqueBookingList.add(BOB);
        assertThrows(DuplicateBookingException.class, () -> uniqueBookingList.setBooking(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueBookingList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueBookingList.add(ALICE);
        uniqueBookingList.remove(ALICE);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBookings((UniqueBookingList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueBookingList.add(ALICE);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOB);
        uniqueBookingList.setBookings(expectedUniqueBookingList);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBookings((List<Booking>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueBookingList.add(ALICE);
        List<Booking> bookingList = Collections.singletonList(BOB);
        uniqueBookingList.setBookings(bookingList);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOB);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Booking> listWithDuplicateBookings = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateBookingException.class, () -> uniqueBookingList.setBookings(listWithDuplicateBookings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBookingList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueBookingList.asUnmodifiableObservableList().toString(), uniqueBookingList.toString());
    }
}
