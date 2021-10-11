package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Person;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalIndexes;

class AddAssignmentCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPersonAssignment_success() {
        Assignment validAssignment = new AssignmentBuilder().build();
        Person validPerson_for_expectedModel = new PersonBuilder().build();
        Person validPerson_for_model = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson_for_expectedModel);
        expectedModel.addAssignment(validPerson_for_expectedModel, validAssignment);
        model.addPerson(validPerson_for_model);

        assertCommandSuccess(new AddAssignmentCommand(validPerson_for_model.getName(), validAssignment), model,
                String.format(AddAssignmentCommand.MESSAGE_SUCCESS, validAssignment), expectedModel);
    }

    @Test
    public void execute_duplicateAssignment_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList()
                .get(TypicalIndexes.INDEX_THIRD_PERSON.getZeroBased());
        model.updateFilteredAssignmentList(personInList);
        Assignment assignmentInList = model.getAddressBook().getAssignmentsList()
                .get(TypicalIndexes.INDEX_FIRST_ASSIGNMENT.getZeroBased());
        assertCommandFailure(new AddAssignmentCommand(personInList.getName(), assignmentInList),
                model, AddAssignmentCommand.MESSAGE_DUPLICATE_ASSIGNMENT);
    }
}