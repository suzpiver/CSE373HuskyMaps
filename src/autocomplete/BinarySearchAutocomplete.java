package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Binary search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class BinarySearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> terms;

    /**
     * Constructs an empty instance.
     */
    public BinarySearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        // TODO: Replace with your code
        this.terms.addAll(terms);
        Collections.sort(this.terms,CharSequence::compare); //null tells it to sort based on natural ordering
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        // TODO: Replace with your code
        List<CharSequence> result = new ArrayList<>();
        if (prefix == null || prefix.length() == 0) {
            return result;
        }
        int i = Collections.binarySearch(this.terms, prefix, CharSequence::compare);
        for (int j = i; j < terms.size(); j++) {
            CharSequence temp = terms.get(j).subSequence(0,prefix.length());
            if(prefix.equals(temp)) {
                result.add(terms.get(j));
            }
            else {
                break;
            }
        }
        return result;
    }
}
