package minpq;

import java.util.*;

/**
 * Optimized binary heap implementation of the {@link ExtrinsicMinPQ} interface.
 *
 * @param <T> the type of elements in this priority queue.
 * @see ExtrinsicMinPQ
 */
public class OptimizedHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    /**
     * {@link List} of {@link PriorityNode} objects representing the heap of item-priority pairs.
     */
    private final List<PriorityNode<T>> items;
    /**
     * {@link Map} of each item to its associated index in the {@code items} heap.
     */
    private final Map<T, Integer> itemToIndex;

    /**
     * Constructs an empty instance.
     */
    public OptimizedHeapMinPQ() {
        items = new ArrayList<>();
        itemToIndex = new HashMap<>();
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Already contains " + item);
        }
        // TODO: Replace with your code
        this.items.add(new PriorityNode<>(item, priority));//adding to list
        if(items.size()>1) swim(items.size()-1);//swimming item up if needed
        //itemToIndex.put(item, );//adding to hashmap
    }

    @Override
    public boolean contains(T item) {
        // TODO: Replace with your code
        return itemToIndex.get(item) != null;
    }

    @Override
    public T peekMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code
        return items.get(0).item();
    }

    @Override
    public T removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        // TODO: Replace with your code
        T min = peekMin();//constant
        itemToIndex.remove(min); //remove from hashmap
        swap(0, size()-1);//constant
        items.remove(size()-1);//constant to pull off last element
        sink(0);//log(N)
        return min;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("PQ does not contain " + item);
        }
        // TODO: Replace with your code
        int i=0;
        for (PriorityNode<T> node : items) {
            if (node.item() == item) {
                if (priority != node.priority()) {
                    itemToIndex.remove(item); //remove from hashmap
                    swap(i,size()-1); //constant
                    items.remove(size()-1);//constant, last element
                    sink(i);//because we swap with last element, it should always need to sink
                    add(item, priority);
                    break;
                }
            }
            i+=1;
        }
    }

    @Override
    public int size() {
        // TODO: Replace with your code
        return items.size();
    }


    /** Returns the index of the given index's parent node. */
    private static int parent(int index) {
        return (index-1) / 2;
    }

    /** Returns the index of the given index's left child. */
    private static int left(int index) {
        return index * 2 + 1;
    }

    /** Returns the index of the given index's right child. */
    private static int right(int index) {
        return left(index) +1;
    }

    /** Returns true if and only if the index is accessible. */
    private boolean accessible(int index) {
        return (0 <= index) && (index < size());
    }

    /** Returns the index with the lower priority, or 0 if neither is accessible. */
    private int min(int index1, int index2) {
        if (!accessible(index1) && !accessible(index2)) {
            return 0;
        } else if (accessible(index1) && (!accessible(index2)
                || items.get(index1).priority()<items.get(index2).priority())) {
            return index1;
        } else {
            return index2;
        }
    }

    /** Swap the nodes at the two indices. */
    private void swap(int index1, int index2) {
        PriorityNode<T> temp = items.get(index1);
        items.set(index1, items.get(index2));
        items.set(index2, temp);
        //itemToIndex.put(items.get(index1).item(), )
    }

    /** Bubbles up the node currently at the given index. */
    private void swim(int index) {
        int parent = parent(index);
        while (accessible(parent) && items.get(index).priority() < items.get(parent).priority()) {
            swap(index, parent);
            index = parent;
            parent = parent(index);
        }
    }

    /** Bubbles down the node currently at the given index. */
    private void sink(int index) {
        int child = min(left(index), right(index));
        while (accessible(child) && items.get(index).priority() > items.get(child).priority()) {
            swap(index, child);
            index = child;
            child = min(left(index), right(index));
            if (child==0) break;
        }
    }
}
