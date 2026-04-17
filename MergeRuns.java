import java.io.*;


/**
 * Name: Damion Sklenars-Clare
 * Student ID: 1638052
 * Last modified: 18/04/2026
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
                if (K < 2 || K > 16) K = 2; // default to 2 if out of range
            } catch (NumberFormatException e) {
                K = 2; 
            }
        }

        K = 2; // forced for the 80% solution requirement

        distribute(K); // distributes runs into K files

        int pass = 0; // current pass
        int runs; // total number of runs in current pass

        // continues to merge until one remains
        do {
            runs = merge(K, pass);
            pass++;
        } while (runs > 1);

        // outputs sorted run
        LineReader reader = new LineReader(new FileInputStream("run0_" + pass + ".txt"));
        String line;

        // writes lines until end of line marker is reached
        while ((line = reader.readLine()) != null) {
            if (!line.trim().equals(MakeRuns.END_OF_RUN)) {
                System.out.print(line);
            }
        }


        reader.close(); // closes the reader
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

    /**
     * merges K files from distribution or previous pass into K new files 
     * @param K the number of files to merge
     * @param pass the current pass number 
     * @return the number of runs produced in this pass
     * @throws IOException if an I/O error occurs
     */
    private static int merge(int K, int pass) throws IOException {
        // input files from previous pass
        LineReader[] reader = new LineReader[K];
        for (int i = 0; i < K; i++) {
            reader[i] = new LineReader(new FileInputStream("run" + i + (pass == 0 ? "" : "_" + pass) + ".txt")); 
        }

        // output files for this pass
        BufferedWriter[] writer = new BufferedWriter[K];
        for (int i = 0; i < K; i++) {
            writer[i] = new BufferedWriter(new FileWriter("run" + i + "_" + (pass + 1) + ".txt"));
        }

        // current line from each reader
        // also checks if reader is done - end of file reached
        String[] current = new String[K];
        boolean[] done = new boolean[K];
        for (int i = 0; i < K; i++) {
            current[i] = reader[i].readLine();
            if (current[i] == null) done[i] = true;
        }

        int runs = 0; // number of runs produced
        int outFile = 0; // number of outputted files

        // keep going while any reader has data
        while (true) {
            // if all done break
            boolean anyActive = false;
            for (int i = 0; i < K; i++) if (!done[i]) { anyActive = true; break; }
            if (!anyActive) break;

            boolean[] endOfRun = new boolean[K]; // tracks which readers are at the end of their run

            while (true) {
                String minLine = null; // min line among all readers
                int minIdx = -1; // reader index with min line

                // finds min line
                for (int i = 0; i < K; i++) {
                    if (!done[i] && !endOfRun[i]) {
                        if (current[i].trim().equals(MakeRuns.END_OF_RUN)) {
                            endOfRun[i] = true;
                            continue;
                        }
                        if (minLine == null || current[i].compareTo(minLine) < 0) {
                            minLine = current[i];
                            minIdx = i;
                        }
                    }
                }

                if (minLine == null) break; // all readers hit end of run

                // write min line to output file
                writer[outFile].write(minLine);
                current[minIdx] = reader[minIdx].readLine();
                if (current[minIdx] == null) done[minIdx] = true;
            }

            // write end of run marker and rotate output file
            writer[outFile].write(MakeRuns.END_OF_RUN + "\n");
            runs++;
            outFile = (outFile + 1) % K;

            for (int i = 0; i < K; i++) {
                if (endOfRun[i]) {
                    current[i] = reader[i].readLine();
                    if (current[i] == null) done[i] = true;
                }
            }
        }

        // closes all readers and writers
        for (LineReader r : reader) r.close();
        for (BufferedWriter w : writer) w.close();

        return runs; // returns total runs in this pass
    }
}