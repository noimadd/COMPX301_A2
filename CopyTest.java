import java.io.*;

/**
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
