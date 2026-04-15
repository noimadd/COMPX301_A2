import java.io.*;

public class LineReader {
    private InputStream input;

    public LineReader(InputStream input) {
        this.input = input;
    }

    public String ReadLine() throws IOException {
        StringBuilder line = new StringBuilder();
        int c;

        while ((c = input.read()) != -1) {
            line.append((char) c);

            if (c == '\n') {
                return line.toString();
            }
        }

        if (line.length() > 0) return line.toString();
        
        return null;
    }
}
