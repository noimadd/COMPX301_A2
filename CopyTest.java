import java.io.*;

public class CopyTest {
    public static void main(String[] args) throws Exception {
        LineReader reader = new LineReader(System.in);
        String line;
        
        while ((line = reader.ReadLine()) != null) {
            System.out.print(line);
        }
    }
}
