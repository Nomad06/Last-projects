import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounterImpl implements WordCounter{

    private String text;
    private final String SWORDS_PATTERN = "([a-zA-Z]+|[А-Яа-я]+)";
    private final String WORDS_PATTERN = "([^\\{}\\n\\t\\s])+";

    public WordCounterImpl(){};

    public WordCounterImpl(String text)
    {
        this.text = text;
    }
    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() throws IllegalStateException{
        if(text != null) return this.text;
        throw new IllegalStateException();
    }

    @Override
    public Map<String, Long> getWordCounts() throws IllegalStateException {
        String badPattern = "(<[^<>]+>)";
        Map<String, Long> words = new HashMap<>();
        String text = this.getText();
        System.out.println(text);
        text = textClearing(badPattern, text);
        Pattern pattern = Pattern.compile(WORDS_PATTERN);
        Matcher matcher = pattern.matcher(text);
        while(matcher.find())
        {
            String word = (matcher.group(0)).toLowerCase();
            if(words.containsKey(word)) words.put(word, words.get(word) + 1);
            else words.put(word, (long) 1);

        }
        System.out.println(words);
        return words;
    }

    private String textClearing(String badPattern, String words) {
        Pattern pattern = Pattern.compile(badPattern);
        Matcher matcher = pattern.matcher(this.getText());
        while(matcher.find())
        {
            words = words.replaceAll(matcher.group(1), "");
        }
        return words;
    }

    @Override
    public List<Map.Entry<String, Long>> getWordCountsSorted() throws IllegalStateException {

        List<Map.Entry<String, Long>> wordList = new ArrayList(this.getWordCounts().entrySet());
        Collections.sort(wordList, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                if(o1.getValue().compareTo(o2.getValue()) == 0)
                {
                    return o1.getKey().compareTo(o2.getKey());
                }
                else if(o1.getValue().compareTo(o2.getValue()) == -1)
                {
                    return 1;
                }
                else
                {
                    return -1;
                }

            }
        });
        return wordList;
    }

    @Override
    public List<Map.Entry<String, Long>> sortWordCounts(Map<String, Long> orig) {
        List<Map.Entry<String, Long>> wordList = new ArrayList(orig.entrySet());
        Collections.sort(wordList, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                if(o1.getValue().compareTo(o2.getValue()) == 0)
                {
                    return o1.getKey().compareTo(o2.getKey());
                }
                else if(o1.getValue().compareTo(o2.getValue()) == -1)
                {
                    return 1;
                }
                else
                {
                    return -1;
                }

            }
        });
        return wordList;
    }
}
