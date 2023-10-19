package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.BookingsBook;
import seedu.address.model.ReadOnlyBookingsBook;
import seedu.address.model.booking.Address;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingDate;
import seedu.address.model.booking.Email;
import seedu.address.model.booking.Name;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.Room;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Booking[] getSamplePersons() {
        return new Booking[] {
            new Booking(new Room("69"),
                        new Name("Alex Yeoh"),
                        new Phone("87438807"),
                        new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"),
                        new BookingDate("2019-Oct-15"),
                        new BookingDate("2019-Oct-16"),
                        getTagSet("friends")),
            new Booking(new Room("420"),
                        new Name("Bernice Yu"),
                        new Phone("99272758"),
                        new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                        new BookingDate("2019-Oct-15"),
                        new BookingDate("2019-Oct-16"),
                        getTagSet("colleagues", "friends")),
            new Booking(new Room("300"),
                        new Name("Charlotte Oliveiro"),
                        new Phone("93210283"),
                        new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                        new BookingDate("2019-Oct-15"),
                        new BookingDate("2019-Oct-16"),
                        getTagSet("neighbours")),
            new Booking(new Room("111"),
                        new Name("David Li"),
                        new Phone("91031282"),
                        new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                        new BookingDate("2019-Oct-15"),
                        new BookingDate("2019-Oct-16"),
                        getTagSet("family")),
            new Booking(new Room("500"),
                        new Name("Irfan Ibrahim"),
                        new Phone("92492021"),
                        new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"),
                        new BookingDate("2019-Oct-15"),
                        new BookingDate("2019-Oct-16"),
                        getTagSet("classmates")),
            new Booking(new Room("1"),
                        new Name("Roy Balakrishnan"),
                        new Phone("92624417"),
                        new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"),
                        new BookingDate("2019-Oct-15"),
                        new BookingDate("2019-Oct-16"),
                        getTagSet("colleagues"))
        };
    }

    public static ReadOnlyBookingsBook getSampleAddressBook() {
        BookingsBook sampleAb = new BookingsBook();
        for (Booking sampleBooking : getSamplePersons()) {
            sampleAb.addBooking(sampleBooking);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
