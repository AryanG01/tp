package seedu.address.model.booking;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Booking {

    // Identity fields
    private final Room room;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final BookingPeriod bookingPeriod;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Booking(Room room, Name name, Phone phone, Email email, BookingPeriod bookingPeriod, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, bookingPeriod, tags);
        this.room = room;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bookingPeriod = bookingPeriod;
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

    public BookingPeriod getBookingPeriod() {
        return bookingPeriod;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both bookings have the same name and room.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameBooking(Booking otherBooking) {
        if (otherBooking == this) {
            return true;
        }

        return otherBooking != null
                && otherBooking.getRoom().equals(getRoom())
                && otherBooking.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
                && bookingPeriod.equals(otherBooking.bookingPeriod)
                && tags.equals(otherBooking.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(room, name, phone, email, bookingPeriod, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("room", room)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", bookingPeriod)
                .add("tags", tags)
                .toString();
    }

}
