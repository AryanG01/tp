package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.booking.Booking;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final BookingsBook bookingsBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Booking> filteredBookings;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyBookingsBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.bookingsBook = new BookingsBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredBookings = new FilteredList<>(this.bookingsBook.getRoomList());

    }

    public ModelManager() {
        this(new BookingsBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getBookingBookFilePath() {
        return userPrefs.getBookingBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setBookingBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setBookingsBook(ReadOnlyBookingsBook bookingsBook) {
        this.bookingsBook.resetData(bookingsBook);
    }

    @Override
    public ReadOnlyBookingsBook getBookingsBook() {
        return bookingsBook;
    }

    @Override
    public boolean hasBooking(Booking booking) {
        requireNonNull(booking);
        return bookingsBook.hasBooking(booking);
    }

    @Override
    public void deleteBooking(Booking target) {
        bookingsBook.removeRoom(target);
    }

    @Override
    public void addBooking(Booking booking) {
        bookingsBook.addBooking(booking);
        updateFilteredBookingList(PREDICATE_SHOW_ALL_ROOMS);
    }

    @Override
    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);

        bookingsBook.setBooking(target, editedBooking);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Booking> getFilteredBookingList() {
        return filteredBookings;
    }

    @Override
    public void updateFilteredBookingList(Predicate<Booking> predicate) {
        requireNonNull(predicate);
        filteredBookings.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return bookingsBook.equals(otherModelManager.bookingsBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredBookings.equals(otherModelManager.filteredBookings);
    }

}
