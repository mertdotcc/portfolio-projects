import java.io.*;
import java.util.Scanner;

public class FileInteractor {
    private PrintWriter fileWriter;
    private Scanner fileScanner;
    private File file;

    public FileInteractor( String filename) throws IOException {
        setFileName( filename);
        fileWriter = new PrintWriter( new FileWriter( file, true));
        fileScanner = new Scanner( file);
    }


    public PrintWriter getFileWriter() {
        return fileWriter;
    }

    public Scanner getFileScanner() {
        return fileScanner;
    }
    public void setDelimiterForScanner( String str) {
        fileScanner.useDelimiter(str);
    }
    public void setFileName( String str){
        file = new File(str);
    }
}
