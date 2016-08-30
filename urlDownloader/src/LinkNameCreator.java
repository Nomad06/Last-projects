import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkNameCreator extends NamesCreatorAbstr{

    public LinkNameCreator(String query)
    {
        super(query);
    }
    @Override
    public String getImageName() {
        return getRightLink();
    }

    @Override
    public String getResourceName() {
        return getRightLink();
    }

    private String getRightLink() {
        String query = super.getQuery();
        if(!query.contains(NamesCreatorAbstr.host) && !query.contains(NamesCreatorAbstr.protocol))
        {
            return NamesCreatorAbstr.protocol + "://" + NamesCreatorAbstr.host + super.getQuery();
        }
        return query;
    }


}
