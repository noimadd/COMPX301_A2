import java.io.*;

/**
 * Name: Damion Sklenars-Clare
 * Student ID: 1638052
 * Last modified: 17/04/2026
 * MakeRuns.java Sorts runs from a textfile a sorts them using a min-heap Sorted
 * runs are exported to a new text file
 */
public class MakeRuns {

    public static final String END_OF_RUN = "------------------END-OF-RUN------------------"; // shows the end of a run in the output file

    /**
     * reads lines from a text file and sorts them using the min-heap
     * sorted runs are outputted to a new text file 
     * each run is separated by the END_OF_RUN string variable
     * @param args the command line arg for the heap size in the format
     * @throws IOException if an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        int H = 256; // heap size

        // heaps size arg check
        if (args.length > 0 && args[0].startsWith("-h")) {
            try {
                H = Integer.parseInt(args[0].substring(2));
            } catch (NumberFormatException e) {
                H = 256; 
            }
        }

        if (H < 32 || H > 2048) H = 256; // default heap size 

        LineReader reader = new LineReader(System.in); // reads stings from file
        MinHeap heap = new MinHeap(H); // new min-heap object with size = H

        String line; // current line
        // processes input at size H until the end of the file
        while (true) {
            // fills heap up to H elements
            while (!heap.isFull() && (line = reader.readLine()) != null) { heap.insert(line); }
            
            if (heap.isEmpty()) break;
            
            // removes all elements from the heap
            // creates a sorted run
            while (!heap.isEmpty()) { System.out.print(heap.removeMin()); }
            
            line = reader.readLine();
            if (line != null) {
                System.out.print(END_OF_RUN + "\n");
                heap.insert(line);
            }
        }
    }
}
