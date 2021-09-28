package seedu.address.model.assignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of assignments that enforces uniqueness between its elements and does not allow nulls.
 * An assignment is considered unique by comparing using {@code Assignment#isSameAssignment(Assignment)}. As such, adding and updating of
 * assignments uses Assignment#isSameAssignment(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniqueAssignmentList implements Iterable<Assignment>{
    private final ObservableList<Assignment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Assignment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns the assignment list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Assignment> asUnmodifiableObservableList(ArrayList<Assignment> assignments) {
        internalList.addAll(assignments);
        return internalUnmodifiableList;
    }
    /**
     * Returns true if the list contains an equivalent assignment as the given argument.
     */
    public boolean contains(Assignment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAssignment);
    }

    /**
     * Adds an assignment to the list.
     * The person must not already exist in the list.
     */
    public void add(Assignment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAssignmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the assignment {@code target} in the list with {@code editedAssignment}.
     * {@code target} must exist in the list.
     * The assignment identity of {@code editedAssignment}
     * must not be the same as another existing assignment in the list.
     */
    public void setAssignment(Assignment target, Assignment editedAssignment) {
        requireAllNonNull(target, editedAssignment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameAssignment(editedAssignment) && contains(editedAssignment)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedAssignment);
    }

    /**
     * Removes the equivalent assignment from the list.
     * The assignment must exist in the list.
     */
    public void delete(Assignment toDelete) {
        requireNonNull(toDelete);
        if (!internalList.remove(toDelete)) {
            throw new PersonNotFoundException();
        }
    }

    public void setAssignments(UniqueAssignmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code assignments}.
     * {@code assignments} must not contain duplicate assignments.
     */
    public void setAssignments(List<Assignment> assignments) {
        requireAllNonNull(assignments);
        if (!assignmentsAreUnique(assignments)) {
            throw new DuplicateAssignmentException();
        }

        internalList.setAll(assignments);
    }

    @Override
    public Iterator<Assignment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAssignmentList // instanceof handles nulls
                && internalList.equals(((UniqueAssignmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code assignments} contains only unique assignments.
     */
    private boolean assignmentsAreUnique(List<Assignment> assignments) {
        for (int i = 0; i < assignments.size() - 1; i++) {
            for (int j = i + 1; j < assignments.size(); j++) {
                if (assignments.get(i).isSameAssignment(assignments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
