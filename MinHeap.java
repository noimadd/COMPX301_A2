/**
 * MinHeap is a class that implements a minimum heap with an array of Strings
 */
public class MinHeap {
    private String[] minHeap; // array to store min heap
    private int size; // current size

    /**
     * constructor to initialise min heap
     * @param capacity the maximum capacity of the min heap
     */
    public MinHeap(int capacity) {
        minHeap = new String[capacity];
        size = 0;
    }

    /**
     * returns true if min heap is empty
     */
    public boolean isEmpty() { return size == 0; }

    /**
     * returns true if min heap is full
     */
    public boolean isFull() { return size == minHeap.length; }

    /**
     * returns the minimum value from the heap
     */
    private String getMin() { return minHeap[0]; }

    /**
    * returns the current size of the heap
    */
    public int getSize() { return size; }

    /**
     * inserts a value into the min heap
     */
    public void insert(String val) {
        minHeap[size] = val;
        size++;
        heapifyUp(size - 1);
    }

    /**
     * removes the min value from the heap
     * @return the removed value
     */
    public String removeMin() {
        String min = getMin();
        minHeap[0] = minHeap[size - 1];
        size--;
        heapifyDown(0);
        return min;
    }

    /**
     * heapifies up at a given index
     * recursively swaps value at index with its parent if it's smaller
     * @param index the index to heapify up from
     */
    private void heapifyUp(int index) {
        int parent = (index - 1) / 2;

        if (index > 0 && minHeap[index].compareTo(minHeap[parent]) < 0) {
            String temp = minHeap[index];
            minHeap[index] = minHeap[parent];
            minHeap[parent] = temp;
            heapifyUp(parent);
        }
    }

    /**
     * heapifies down at a given index
     * recursively swaps value at index with its smallest child if it's larger
     * @param index the index to heapify down from
     */
    private void heapifyDown(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallest = index;

        if (left < size && minHeap[left].compareTo(minHeap[smallest]) < 0) smallest = left;

        if (right < size && minHeap[right].compareTo(minHeap[smallest]) < 0) smallest = right;

        if (smallest != index) {
            String temp = minHeap[index];
            minHeap[index] = minHeap[smallest];
            minHeap[smallest] = temp;
            heapifyDown(smallest);
        }
    }
}
