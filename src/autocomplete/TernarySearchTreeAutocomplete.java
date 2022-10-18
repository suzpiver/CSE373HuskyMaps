package autocomplete;

import java.util.Collection;
import java.util.List;

/**
 * Ternary search tree (TST) implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class TernarySearchTreeAutocomplete implements Autocomplete {
    /**
     * The overall root of the tree: the first character of the first autocompletion term added to this tree.
     */
    private Node overallRoot;

    /**
     * Constructs an empty instance.
     */
    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
    }

    private Node put(Node x, CharSequence key, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node(c);
        }
        if      (c < x.data)               x.left  = put(x.left,  key, d);
        else if (c > x.data)               x.right = put(x.right, key, d);
        else if (d < key.length() - 1)  x.mid   = put(x.mid,   key, d+1);
        return x;
    }
    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
       for (CharSequence term : terms) {
           overallRoot=put(overallRoot, term, 0);
       }

    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        // TODO: Replace with your code
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * A search tree node representing a single character in an autocompletion term.
     */
    private static class Node {
        private final char data;
        private boolean isTerm;
        private Node left;
        private Node mid;
        private Node right;

        public Node(char data) {
            this.data = data;
            this.isTerm = false;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}
