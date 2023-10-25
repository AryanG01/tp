package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_PERIOD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_PERIOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROOM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.BookingsBook;
import seedu.address.model.booking.Booking;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Booking ALICE = new BookingBuilder().withRoom("1")
            .withBookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00")
            .withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").build();
    public static final Booking BENSON = new BookingBuilder().withRoom("2")
            .withBookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00")
            .withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").build();
    public static final Booking CARL = new BookingBuilder().withRoom("3")
            .withBookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00")
            .withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").build();
    public static final Booking DANIEL = new BookingBuilder().withRoom("4")
            .withBookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00")
            .withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com")
            .withTags("friends").build();
    public static final Booking ELLE = new BookingBuilder().withRoom("5")
            .withBookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00")
            .withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").build();
    public static final Booking FIONA = new BookingBuilder().withRoom("6")
            .withBookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00")
            .withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").build();
    public static final Booking GEORGE = new BookingBuilder().withRoom("7")
            .withBookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00")
            .withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Booking HOON = new BookingBuilder().withRoom("8")
            .withBookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00")
            .withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").build();
    public static final Booking IDA = new BookingBuilder().withRoom("9")
            .withBookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00")
            .withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Booking AMY = new BookingBuilder().withRoom(VALID_ROOM_AMY)
            .withBookingPeriod(VALID_BOOKING_PERIOD_AMY)
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Booking BOB = new BookingBuilder().withRoom(VALID_ROOM_BOB)
            .withBookingPeriod(VALID_BOOKING_PERIOD_BOB)
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static BookingsBook getTypicalAddressBook() {
        BookingsBook ab = new BookingsBook();
        for (Booking booking : getTypicalPersons()) {
            ab.addBooking(booking);
        }
        return ab;
    }

    public static List<Booking> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
