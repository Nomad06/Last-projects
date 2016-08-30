import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordProcessorImpl implements WordProcessor{

    private String text;


    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public void setSource(String src) {
        if(src == null) throw new IllegalArgumentException();
        this.text = src;
    }

    @Override
    public void setSourceFile(String srcFile) throws IOException {
        if(srcFile == null) throw new IllegalArgumentException();
        FileReader fileReader = new FileReader(srcFile);
        BufferedReader buf = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        String string;
        while ((string = buf.readLine()) != null){
            stringBuilder.append(string);
        }

        setSource(stringBuilder.toString());
    }

    @Override
    public void setSource(FileInputStream fis) throws IOException {
        if(fis == null) throw new IllegalArgumentException();
        byte[] dataFromStream = new byte[fis.available()];
        fis.read(dataFromStream, 0, fis.available());
        String byteToString = new String(dataFromStream);
        this.text = byteToString;
    }

    @Override
    public Set<String> wordsStartWith(String begin) {
        String lowCaseText = this.getText().toLowerCase();
        String wordPattern;
        if(begin == null || begin.equals("")){
            wordPattern = "\\s*([^\\t\\s\\n\\r]*)\\s+";
        }
        else {
            wordPattern = "\\s+(" + begin.toLowerCase() + "\\w*)";
        }
        return wordsSetGetter(lowCaseText, wordPattern);
    }

    private Set<String> wordsSetGetter(String lowCaseText, String wordPattern) {
        Set<String> wordsSet = new HashSet<>();
        String str;
        Pattern pattern = Pattern.compile(wordPattern);
        Matcher matcher = pattern.matcher(lowCaseText);
        while (matcher.find()){
            str = matcher.group(1).replace(" ","");
            if(!str.equals("")){
                wordsSet.add(matcher.group(1));
                System.out.println(str);
            }
        }
        return wordsSet;
    }
}
