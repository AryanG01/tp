package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
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

//import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Booking ALICE = new BookingBuilder().withRoom("1").withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withStartDate("2019-Oct-15").withEndDate("2023-Oct-19")
            .withTags("friends").build();
    public static final Booking BENSON = new BookingBuilder().withRoom("2").withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withStartDate("2019-Oct-15")
            .withEndDate("2023-Oct-19").withTags("owesMoney", "friends").build();
    public static final Booking CARL = new BookingBuilder().withRoom("3").withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").withStartDate("2019-Oct-15")
            .withEndDate("2023-Oct-19").build();
    public static final Booking DANIEL = new BookingBuilder().withRoom("4").withName("Daniel Meier")
            .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street")
            .withStartDate("2019-Oct-15").withEndDate("2023-Oct-19").withTags("friends").build();
    public static final Booking ELLE = new BookingBuilder().withRoom("5").withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").withStartDate("2019-Oct-15")
            .withEndDate("2023-Oct-19").build();
    public static final Booking FIONA = new BookingBuilder().withRoom("6").withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").withStartDate("2019-Oct-15")
            .withEndDate("2023-Oct-19").build();
    public static final Booking GEORGE = new BookingBuilder().withRoom("7").withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").withStartDate("2019-Oct-15")
            .withEndDate("2023-Oct-19").build();

    // Manually added
    public static final Booking HOON = new BookingBuilder().withRoom("8").withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withStartDate("2019-Oct-15")
            .withEndDate("2023-Oct-19").build();
    public static final Booking IDA = new BookingBuilder().withRoom("9").withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withStartDate("2019-Oct-15")
            .withEndDate("2023-Oct-19").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Booking AMY = new BookingBuilder().withRoom(VALID_ROOM_AMY).withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withStartDate(VALID_DATE_AMY).withEndDate(VALID_DATE_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Booking BOB = new BookingBuilder().withRoom(VALID_ROOM_BOB).withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withStartDate(VALID_DATE_BOB).withEndDate(VALID_DATE_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

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
