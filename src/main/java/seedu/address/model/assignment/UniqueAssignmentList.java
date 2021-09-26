package seedu.address.model.assignment;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is a Dummy class that needs to have more features.
 * Remember to change this JavaDoc comment.
 */
public class UniqueAssignmentList {
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
}
