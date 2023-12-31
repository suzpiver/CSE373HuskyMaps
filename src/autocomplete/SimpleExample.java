package autocomplete;

import java.util.ArrayList;
import java.util.List;

public class SimpleExample {
    public static void main(String[] args) {
        List<CharSequence> terms = new ArrayList<>();
        terms.add("alpha");
        terms.add("delta");
        terms.add("do");
        terms.add("cats");
        terms.add("dodgy");
        terms.add("pilot");
        terms.add("dog");
        terms.add("alto");
        terms.add("alone");

// Choose your Autocomplete implementation.
        //Autocomplete autocomplete = new TreeSetAutocomplete();
        //Autocomplete autocomplete = new SequentialSearchAutocomplete();
        Autocomplete autocomplete = new BinarySearchAutocomplete();
        //Autocomplete autocomplete = new TernarySearchTreeAutocomplete();
        autocomplete.addAll(terms);
// Choose your prefix string.
        CharSequence prefix = "altone";
        List<CharSequence> matches = autocomplete.allMatches(prefix);
        for (CharSequence match : matches) {
            System.out.println(match);
        }
        }
    }
//}
