package minpq;

public class SimpleExample {
    public static void main(String[] args) {
        ExtrinsicMinPQ<String> pq = new DoubleMapMinPQ<>();
        pq.add("1", 1.0);
        pq.add("2", 2.0);
        pq.add("3", 3.0);
        pq.add("4", 4.0);
        pq.add("5", 5.0);
        pq.add("6", 6.0);

        // Call methods to evaluate behavior.    //123456
        pq.changePriority("3", 0.0); //312456
        pq.changePriority("1", 7.0); //324561
        while (!pq.isEmpty()) {
            System.out.println(pq.removeMin());
        }
    }
}
