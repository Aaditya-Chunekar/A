import java.util.*;

class MinHeap {
    // MinHeap class to maintain the heap property
    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    // Method to insert an element into the Min Heap
    public void insert(int mark) {
        heap.add(mark);
        heapifyUp(heap.size() - 1);
    }

    // Method to maintain heap property after insertion
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap.get(index) < heap.get(parentIndex)) {
            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    // Method to get the minimum element (root of Min Heap)
    public int getMin() {
        if (heap.size() == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    // Method to find the maximum element in the heap
    public int getMax() {
        if (heap.size() == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        // Traverse the heap to find the maximum element
        int max = Integer.MIN_VALUE;
        for (int mark : heap) {
            max = Math.max(max, mark);
        }
        return max;
    }

    // Method to get the size of the heap
    public int size() {
        return heap.size();
    }
}

public class StudentMarks {
    public static void main(String[] args) {
        // Example marks obtained by students in an online examination
        int[] marks = {56, 89, 42, 74, 95, 63, 21, 87, 45, 60};

        MinHeap minHeap = new MinHeap();

        // Insert all marks into the Min Heap
        for (int mark : marks) {
            minHeap.insert(mark);
        }

        // Find the minimum and maximum marks
        int minMarks = minHeap.getMin();
        int maxMarks = minHeap.getMax();

        // Output the results
        System.out.println("Minimum marks obtained: " + minMarks);
        System.out.println("Maximum marks obtained: " + maxMarks);
    }
}
