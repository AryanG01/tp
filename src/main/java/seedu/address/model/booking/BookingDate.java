package seedu.address.model.booking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a date for a booking.
 */
public class BookingDate {
    /**
     * The actual date value stored in this BookingDate object.
     */
    public final LocalDate value;

    /**
     * Constructs a BookingDate object with the given date string.
     *
     * @param date The date string to be parsed into a LocalDate object.
     */
    public BookingDate(String date) {
        this.value = parse(date);
    }

    /**
     * Parses a date string into a LocalDate object using the specified format and locale.
     *
     * @param date The date string to be parsed.
     * @return A LocalDate object representing the parsed date.
     */
    public LocalDate parse(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        formatter = formatter.withLocale(Locale.US);
        return LocalDate.parse(date, formatter);
    }

    /**
     * Compares this BookingDate object with another object for equality.
     *
     * @param other The object to compare with this BookingDate.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if the same object
                || (other instanceof BookingDate // instanceof handles nulls
                && value.equals(((BookingDate) other).value)); // state check
    }

    /**
     * Returns a string representation of the BookingDate.
     *
     * @return A string representation of the LocalDate value.
     */
    public String toString() {
        return value.toString();
    }
}
