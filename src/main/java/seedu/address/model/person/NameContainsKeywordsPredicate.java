package seedu.address.model.person;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if the person contains any of the keywords provided. This method
     * is used for finding matching results to the find command.
     *
     * @param person the input person to check.
     * @return true if any of the keywords matches the name, module or tag.
     */
    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> {
                    boolean result;
                    result = StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword)
                            || StringUtil.containsWordIgnoreCase(person.getModule().moduleCode, keyword)
                            || person.hasTag(keyword);
                    return result;
                });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && listEquals(keywords, ((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

    public boolean listEquals(List<String> a, List<String> b) {

        if (a.size() != b.size()) {
            return false;
        }

        int len = a.size();

        for (int i = 0; i < len; i++) {
            if (!a.get(i).toUpperCase(Locale.ROOT).equals(b.get(i).toUpperCase(Locale.ROOT))) {
                return false;
            }
        }
        return true;
    }

}
