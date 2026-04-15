import java.io.*;

/**
 * reads one character at a time until it reaches the end of a line
 */
public class LineReader {
    private InputStream input;

    /**
     * @param input the input stream to read from
     */
    public LineReader(InputStream input) {
        this.input = input;
    }

    /**
     * reads a single line from the input
     * @return the line read, or null if the end of the stream is reached
     */
    public String readLine() throws IOException {
        StringBuilder line = new StringBuilder();
        int c;

        // loops until it reaches the end of a line or the stream
        while ((c = input.read()) != -1) {
            line.append((char) c);

            if (c == '\n') {
                return line.toString();
            }
        }

        // if the line is not empty, return it
        if (line.length() > 0) return line.toString();
        
        return null;
    }
}
