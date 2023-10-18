package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.booking.Address;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Email;
import seedu.address.model.booking.Name;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.Room;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class BookingBuilder {
    public static final String DEFAULT_ROOM = "1";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Room room;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public BookingBuilder() {
        room = new Room(DEFAULT_ROOM);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public BookingBuilder(Booking bookingToCopy) {
        room = bookingToCopy.getRoom();
        name = bookingToCopy.getName();
        phone = bookingToCopy.getPhone();
        email = bookingToCopy.getEmail();
        address = bookingToCopy.getAddress();
        tags = new HashSet<>(bookingToCopy.getTags());
    }

    /**
     * Sets the {@code Room} of the {@code Person} that we are building.
     */
    public BookingBuilder withRoom(String room) {
        this.room = new Room(room);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public BookingBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public BookingBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public BookingBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public BookingBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public BookingBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Booking build() {
        return new Booking(room, name, phone, email, address, tags, bookingStartDate, bookingEndDate);
    }

}
