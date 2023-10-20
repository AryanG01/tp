package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.booking.Booking;
import seedu.address.testutil.BookingBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Booking validBooking = new BookingBuilder().build();

        Model expectedModel = new ModelManager(model.getBookingsBook(), new UserPrefs());
        expectedModel.addBooking(validBooking);

        assertCommandSuccess(new AddCommand(validBooking), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validBooking)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Booking bookingInList = model.getBookingsBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(bookingInList), model,
                AddCommand.MESSAGE_DUPLICATE_BOOKING);
    }

}
