package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.booking.Booking;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyBookingsBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Booking> getPersonList();

}
