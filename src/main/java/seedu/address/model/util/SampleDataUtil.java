package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Module;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Email("alexyeoh@example.com"),
                new Module("CS2100"),
                getAssignmentList(new String[] {"Assignment 1", "15 Sep 2021, 01:00 PM", "COMPLETED"},
                                  new String[] {"Lab 4", "15 Oct 2021, 01:00 PM", "PENDING"}),
                getTagSet("tutorial12", "lab22")),
            new Person(new Name("Bernice Yu"), new Email("berniceyu@example.com"),
                new Module("CS2103T"),
                getAssignmentList(new String[] {"iP", "17 Sep 2021, 11:59 PM", "COMPLETED"}),
                getTagSet("T13")),
            new Person(new Name("Charlotte Oliveiro"), new Email("charlotte@example.com"),
                new Module("CS2103T"),
                getAssignmentList(new String[] {"iP", "17 Sep 2021, 11:59 PM", "COMPLETED"}),
                getTagSet("T13")),
            new Person(new Name("David Li"), new Email("lidavid@example.com"),
                new Module("CS2103T"),
                getAssignmentList(new String[] {"Tutorial 2", "16 Sep 2021, 11:59 PM", "COMPLETED"}),
                getTagSet("T14")),
            new Person(new Name("Irfan Ibrahim"), new Email("irfan@example.com"),
                new Module("CS2100"),
                getAssignmentList(new String[] {"Assignment 1", "10 Sep 2021, 11:59 PM", "COMPLETED"}),
                getTagSet("tutorial12")),
            new Person(new Name("Roy Balakrishnan"), new Email("royb@example.com"),
                new Module("CS2100"),
                getAssignmentList(new String[] {"Lab 1", "20 Sep 2021, 11:59 PM", "COMPLETED"}),
                getTagSet("lab22"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an assignment list containing the list of strings given.
     */
    public static List<Assignment> getAssignmentList(String[]... strings) {
        return Arrays.stream(strings)
                .map(Assignment::new)
                .collect(Collectors.toList());
    }

}
