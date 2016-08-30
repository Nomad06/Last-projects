import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class NamesCreatorAbstr implements NamesCreator {

    private String query;
    public static String host;
    public static String path;
    public static String protocol;
    public static String htmlName;

    public static String getPath() {
        return path;
    }

    public void setQuery(String query)
    {
        this.query = query;
    }

    public String getQuery()
    {
        return this.query;
    }

    NamesCreatorAbstr(String query)
    {
        this.query = query;
    }

    NamesCreatorAbstr()
    {
        this.query = query;
    }


    public String getName(String link) {
        String name = "";
        Pattern pattern = Pattern.compile("(https?:[/]*)?([a-z0-9]*\\.[a-z]{1,})*[/]{0,}(.*\\/)+(.*)[?].*");
        Matcher matcher = pattern.matcher(link);
        if(matcher.matches())
        {
            name = matcher.group(4);
        }
        else
        {
            String[] linkParts = link.split("/");
            if(linkParts.length != 0) name = linkParts[linkParts.length - 1];
        }
        return name;
    }


}
