package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ROOMS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Address;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.BookingDate;
import seedu.address.model.booking.Email;
import seedu.address.model.booking.Name;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.Room;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_ROOM + "ROOM] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_DATE + "START DATE] "
            + "[" + PREFIX_DATE + "END DATE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_BOOKING_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This person already exists in the address book.";

    private final Index index;
    private final EditRoomDescriptor editRoomDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editRoomDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditRoomDescriptor editRoomDescriptor) {
        requireNonNull(index);
        requireNonNull(editRoomDescriptor);

        this.index = index;
        this.editRoomDescriptor = new EditRoomDescriptor(editRoomDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }

        Booking bookingToEdit = lastShownList.get(index.getZeroBased());
        Booking editedBooking = createEditedBooking(bookingToEdit, editRoomDescriptor);

        if (!bookingToEdit.isSameBooking(editedBooking) && model.hasBooking(editedBooking)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOKING);
        }

        model.setBooking(bookingToEdit, editedBooking);
        model.updateFilteredBookingList(PREDICATE_SHOW_ALL_ROOMS);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOKING_SUCCESS, Messages.format(editedBooking)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Booking createEditedBooking(Booking bookingToEdit, EditRoomDescriptor editRoomDescriptor) {
        assert bookingToEdit != null;

        Room updatedRoom = editRoomDescriptor.getRoom().orElse(bookingToEdit.getRoom());
        Name updatedName = editRoomDescriptor.getName().orElse(bookingToEdit.getName());
        Phone updatedPhone = editRoomDescriptor.getPhone().orElse(bookingToEdit.getPhone());
        Email updatedEmail = editRoomDescriptor.getEmail().orElse(bookingToEdit.getEmail());
        Address updatedAddress = editRoomDescriptor.getAddress().orElse(bookingToEdit.getAddress());
        BookingDate updatedStartDate = editRoomDescriptor.getStartDate().orElse(bookingToEdit.getStartDate());
        BookingDate updatedEndDate = editRoomDescriptor.getEndDate().orElse(bookingToEdit.getEndDate());
        Set<Tag> updatedTags = editRoomDescriptor.getTags().orElse(bookingToEdit.getTags());

        return new Booking(updatedRoom, updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedStartDate, updatedEndDate, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editRoomDescriptor.equals(otherEditCommand.editRoomDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editRoomDescriptor", editRoomDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditRoomDescriptor {
        private Room room;
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private BookingDate startDate;
        private BookingDate endDate;
        private Set<Tag> tags;

        public EditRoomDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditRoomDescriptor(EditRoomDescriptor toCopy) {
            setRoom(toCopy.room);
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setStartDate(toCopy.startDate);
            setEndDate(toCopy.endDate);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(room, name, phone, email, address, startDate, endDate, tags);
        }

        public void setRoom(Room room) {
            this.room = room;
        }

        public Optional<Room> getRoom() {
            return Optional.ofNullable(room);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setStartDate(BookingDate startDate) {
            this.startDate = startDate;
        }

        public Optional<BookingDate> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setEndDate(BookingDate endDate) {
            this.endDate = endDate;
        }

        public Optional<BookingDate> getEndDate() {
            return Optional.ofNullable(endDate);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRoomDescriptor)) {
                return false;
            }

            EditRoomDescriptor otherEditRoomDescriptor = (EditRoomDescriptor) other;
            return Objects.equals(room, otherEditRoomDescriptor.room)
                    && Objects.equals(name, otherEditRoomDescriptor.name)
                    && Objects.equals(phone, otherEditRoomDescriptor.phone)
                    && Objects.equals(email, otherEditRoomDescriptor.email)
                    && Objects.equals(address, otherEditRoomDescriptor.address)
                    && Objects.equals(startDate, otherEditRoomDescriptor.startDate)
                    && Objects.equals(endDate, otherEditRoomDescriptor.endDate)
                    && Objects.equals(tags, otherEditRoomDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("room", room)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("start date", startDate)
                    .add("end date", endDate)
                    .add("tags", tags)
                    .toString();
        }
    }
}
