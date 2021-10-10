package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.assignment.Assignment;
<<<<<<< HEAD
import seedu.address.model.assignment.UniqueAssignmentList;
=======
>>>>>>> 46408507be602f4239f60cb9bd17e3b9a16dff12
import seedu.address.model.person.Module;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_MODULE = "CS2103T";

    private Name name;
    private Email email;
    private Module module;
<<<<<<< HEAD
    private UniqueAssignmentList assignments;
=======
    private List<Assignment> assignmentList;
>>>>>>> 46408507be602f4239f60cb9bd17e3b9a16dff12
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        module = new Module(DEFAULT_MODULE);
<<<<<<< HEAD
        assignments = new UniqueAssignmentList();
=======
        assignmentList = new ArrayList<>();
>>>>>>> 46408507be602f4239f60cb9bd17e3b9a16dff12
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        email = personToCopy.getEmail();
        module = personToCopy.getModule();
<<<<<<< HEAD
        assignments = personToCopy.getAssignments();
=======
        assignmentList = personToCopy.getAssignments().asUnmodifiableObservableList();
>>>>>>> 46408507be602f4239f60cb9bd17e3b9a16dff12
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code Person} that we are building.
     */
    public PersonBuilder withModule(String address) {
        this.module = new Module(address);
        return this;
    }

    /**
     * Sets the {@code UniqueAssignmentList} of the {@code Person} that we are building.
     */
    public PersonBuilder withAssignment(Assignment assignment) {
        this.assignments.add(assignment);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code assignmentList} into a {@code List<Assignment>}
     * and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withAssignmentList(String[]... assignmentList) {
        this.assignmentList = SampleDataUtil.getAssignmentList(assignmentList);
        return this;
    }

    public Person build() {
<<<<<<< HEAD
        Person person = new Person(name, email, module, tags);
        person.getAssignments().setAssignments(assignments);
        return person;
=======
        return new Person(name, email, module, assignmentList, tags);
>>>>>>> 46408507be602f4239f60cb9bd17e3b9a16dff12
    }

}
