package seedu.address.model.booking;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Booking.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {

    // Identity fields
    private final Room room;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final BookingDate bookingStartDate;
    private final BookingDate bookingEndDate;

    /**
     * Every field must be present and not null.
     */
    public Booking(Room room, Name name, Phone phone, Email email, Address address, BookingDate bookingStartDate,
                   BookingDate bookingEndDate, Set<Tag> tags) {
        requireAllNonNull(room, name, phone, email, address, bookingStartDate, bookingEndDate, tags);
        this.room = room;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.bookingStartDate = bookingStartDate;
        this.bookingEndDate = bookingEndDate;
        this.tags.addAll(tags);
    }

    public Room getRoom() {
        return room;
    }
    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public BookingDate getBookingStartDate() {
        return bookingStartDate;
    }

    public BookingDate getBookingEndDate() {
        return bookingEndDate;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both bookings have the same name.
     * This defines a weaker notion of equality between two bookings.
     */
    public boolean isSameBooking(Booking otherBooking) {
        if (otherBooking == this) {
            return true;
        }

        return otherBooking != null
                && otherBooking.getRoom().equals(getRoom());
    }

    /**
     * Returns true if both bookings have the same identity and data fields.
     * This defines a stronger notion of equality between two bookings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Booking)) {
            return false;
        }

        Booking otherBooking = (Booking) other;
        return room.equals(otherBooking.room)
                && name.equals(otherBooking.name)
                && phone.equals(otherBooking.phone)
                && email.equals(otherBooking.email)
                && address.equals(otherBooking.address)
                && bookingStartDate.equals(otherBooking.bookingStartDate)
                && bookingEndDate.equals(otherBooking.bookingEndDate)
                && tags.equals(otherBooking.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(room, name, phone, email, address, bookingStartDate, bookingEndDate, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("room", room)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("bookingStartDate", bookingStartDate)
                .add("bookingEndDate", bookingEndDate)
                .add("tags", tags)
                .toString();
    }
}
