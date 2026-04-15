import java.io.*;

/**
 * Name: Damion Sklenars-Clare
 * Student ID: 1638052
 * Last modified: 15/04/2026
 * test program that uses the LineReader class and prints them to an output file
 */
public class CopyTest {
    public static void main(String[] args) throws Exception {
        LineReader reader = new LineReader(System.in);
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.print(line);
        }
    }
}
