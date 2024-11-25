import java.util.*;

class MaxHeap {
    // MaxHeap class to maintain the heap property
    private ArrayList<Integer> heap;

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    // Method to insert an element into the Max Heap
    public void insert(int mark) {
        heap.add(mark);
        heapifyUp(heap.size() - 1);
    }

    // Method to maintain heap property after insertion
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heap.get(index) > heap.get(parentIndex)) {
            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    // Method to get the maximum element (root of Max Heap)
    public int getMax() {
        if (heap.size() == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        return heap.get(0);
    }

    // Method to find the minimum element in the heap
    public int getMin() {
        if (heap.size() == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        // Traverse the heap to find the minimum element
        int min = Integer.MAX_VALUE;
        for (int mark : heap) {
            min = Math.min(min, mark);
        }
        return min;
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

        MaxHeap maxHeap = new MaxHeap();

        // Insert all marks into the Max Heap
        for (int mark : marks) {
            maxHeap.insert(mark);
        }

        // Find the maximum and minimum marks
        int maxMarks = maxHeap.getMax();
        int minMarks = maxHeap.getMin();

        // Output the results
        System.out.println("Maximum marks obtained: " + maxMarks);
        System.out.println("Minimum marks obtained: " + minMarks);
    }
}
