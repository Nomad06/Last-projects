import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameCreator extends NamesCreatorAbstr{

    public FileNameCreator(String query)
    {
        super(query);
    }
    @Override
    public String getImageName() {
        String imageNameLink = super.getQuery();
        return super.getName(imageNameLink);
    }

    @Override
    public String getResourceName() {
        String resourceNameLink = super.getQuery();
        return super.getName(resourceNameLink);
    }

    public String getHtmlPageName() {

        String pageName = "";
        String link = super.getQuery();

        if(link.matches("(https?:[/]*)?(.*\\.[a-z]{1,})*[/]{1,}"))
        {
            pageName = "index.html";
        }
        else
        {
            pageName = super.getName(link);

            if(!pageName.contains("html"))
            {
                pageName = pageName + ".html";
            }
            System.out.println(pageName);
            NamesCreatorAbstr.htmlName = pageName.replace(".html", "");
        }
        return pageName;

    }

}
