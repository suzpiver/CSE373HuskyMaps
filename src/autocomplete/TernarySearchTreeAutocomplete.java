package autocomplete;

import java.util.ArrayList;
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
        else x.isTerm=true;
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
        return keysWithPrefix(prefix);
    }
    private Node get(Node x, CharSequence key, int d) {
        if (x == null) return null;
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        char c = key.charAt(d);
        if      (c < x.data)              return get(x.left,  key, d);
        else if (c > x.data)              return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid,   key, d+1);
        else                           return x;
    }
    public ArrayList<CharSequence> keysWithPrefix(CharSequence prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        }
        //Queue<String> queue = new Queue<String>();
        ArrayList<CharSequence> list = new ArrayList<>();
        Node x = get(overallRoot, prefix, 0);
        if (x == null) return list;
        //if (x.data != null) list;
        if (x.isTerm) list.add(prefix);
        collect(x.mid, new StringBuilder(prefix), list);
        return list;
    }
    // all keys in subtrie rooted at x with given prefix
    private void collect(Node x, StringBuilder prefix, ArrayList<CharSequence> list) {
        if (x == null) return;
        if (x.isTerm) list.add(prefix.toString() + x.data);
        collect(x.left, prefix, list);
        collect(x.mid,  prefix.append(x.data), list); //if we choose the middle node we 'add' the letter to the string we're building
        prefix.deleteCharAt(prefix.length() - 1); //if we next test right instead of mid, we need to delete the letter we added
        collect(x.right, prefix, list);
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
