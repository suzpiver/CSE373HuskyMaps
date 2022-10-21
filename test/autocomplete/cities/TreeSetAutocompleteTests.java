package autocomplete.cities;

import autocomplete.Autocomplete;
import autocomplete.TernarySearchTreeAutocomplete;
import autocomplete.TreeSetAutocomplete;

public class TreeSetAutocompleteTests extends AutocompleteTests {
    @Override
    public Autocomplete createAutocomplete() {
        return new TreeSetAutocomplete();
    }
}
