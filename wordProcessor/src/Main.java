import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        WordProcessor wordProcessor = new WordProcessorImpl();
        //wordProcessor.setSourceFile("C:\\Users\\bubal\\Desktop\\new 1.txt");
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\bubal\\Desktop\\new 1.txt");
       // System.out.println(wordProcessor.getText());
        wordProcessor.setSource(fileInputStream);
        wordProcessor.wordsStartWith(null);
    }
}
