import java.io.*;


/**
 * Name: Damion Sklenars-Clare
 * Student ID: 1638052
 * Last modified: 17/04/2026
 * MergeRuns.java merges sorted runs until only one sorted run remains
 * the sorted run is outputted to a new text file
 */
public class MergeRuns {
    public static void main(String[] args) throws IOException {
        int K = 2; // number of files to merge

        // num files arg check
        if (args.length > 0 && args[0].startsWith("-k")) {
            try {
                K = Integer.parseInt(args[0].substring(2));
            } catch (NumberFormatException e) {
                K = 2; 
            }
        }

        K = 2; // forced for the 80% solution requirement

        distribute(K); // distributes runs into K files

    }

    /**
     * distributes runs from init.runs into K files
     * @param K the number of files to distribute runs into
     * @throws IOException if an I/O error occurs
     */
    private static void distribute(int K) throws IOException {
        LineReader lineReader = new LineReader(new FileInputStream("init.runs")); // reads lines from the init.runs 
        String line; // current line

        // create K output files
        BufferedWriter[] writer = new BufferedWriter[K];
        for (int i = 0; i < K; i++) {
            writer[i] = new BufferedWriter(new FileWriter("run" + i + ".txt"));
        }

        int currentFile = 0; // current file to write to 
        while ((line = lineReader.readLine()) != null) {
            // if the line = to the end of run marker write line and move to next file
            // else write to current file
            if (line.trim().equals(MakeRuns.END_OF_RUN)) {
                writer[currentFile].write(line);
                currentFile = (currentFile + 1) % K;
            } else {
                writer[currentFile].write(line);
            }
        }

        // closes all writers
        for (BufferedWriter w : writer) w.close();
    }
}