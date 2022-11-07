package minpq;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Unsorted array (or {@link ArrayList}) implementation of the {@link ExtrinsicMinPQ} interface.
 *
 * @param <T> the type of elements in this priority queue.
 * @see ExtrinsicMinPQ
 */
public class UnsortedArrayMinPQ<T> implements ExtrinsicMinPQ<T> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the item-priority pairs in no specific order.
     */
    private final List<PriorityNode<T>> items;

    /**
     * Constructs an empty instance.
     */
    public UnsortedArrayMinPQ() {
        items = new ArrayList<>();
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Already contains " + item);
        }
        // TODO: Replace with your code
        items.add(new PriorityNode<T>(item, priority));
    }

    @Override
    public boolean contains(T item) {
        // TODO: Replace with your code
        // items=(node1, node2, node3) node1=item1, priority1, node1.item=item1
        for (int i = 0; i < this.items.size(); i++) {
            if (items.get(i).item()==item)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public T peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        PriorityNode<T> min = this.items.get(0);
        PriorityNode<T> temp;
        // TODO: Replace with your code
        for (int i = 1; i < this.items.size(); i++) {
            temp = this.items.get(i);
            if (temp.priority()<min.priority()) min=temp;
        }
        return min.item();
    }

    @Override
    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code
        PriorityNode<T> min = this.items.get(0);
        PriorityNode<T> temp;
        int j=0;
        // TODO: Replace with your code
        for (int i = 1; i < this.items.size(); i++) {
            temp = this.items.get(i);
            if (temp.priority()<min.priority()) {
                min = temp;
                j = i;
            }
        }
        T result = min.item();
        this.items.remove(j);
        return result;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("PQ does not contain " + item);
        }
        // TODO: Replace with your code
        for (int i = 0; i < this.items.size(); i++) {
            if (items.get(i).item()==item)
            {
                items.get(i).setPriority(priority);
            }
        }
    }

    @Override
    public int size() {
        // TODO: Replace with your code
        return this.items.size();
    }
}
