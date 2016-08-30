import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckerImpl implements Checker {

    private Pattern pLSQLNamesPattern = Pattern.compile("^[a-zA-Z][A-Za-z0-9_$]+");
    private Pattern hrefURLPattern = Pattern.compile("^<(a|A)([\\s]+)(h|H)(r|R)(e|E)(f|F)[\\s]*=[\\s]*((\"(\\w+[^\"]?\\w*)*\"\\s*\\/?>)|([^\\s\"]*\\s*\\/?>))");
    private Pattern eMailPattern = Pattern.compile("^([A-Za-z0-9]+[-_.]?[A-Za-z0-9]+)@([A-Za-z0-9]+[-.]?[A-Za-z0-9]*)+.(net|ru|org|com)");

    @Override
    public Pattern getPLSQLNamesPattern() {
        return this.pLSQLNamesPattern;
    }

    @Override
    public Pattern getHrefURLPattern() {
        return this.hrefURLPattern;
    }

    @Override
    public Pattern getEMailPattern() {
        return this.eMailPattern;
    }

    @Override
    public boolean checkAccordance(String inputString, Pattern pattern) throws IllegalArgumentException {
        if(inputString == null && pattern == null) return true;
        else if(inputString == null || pattern == null) throw new IllegalArgumentException();

        Matcher matcher = pattern.matcher(inputString);
        if(matcher.matches())
        {
            if(pattern.equals(this.eMailPattern)) {
                if(matcher.group(1).length() <= 22 )return true;
            }
            if(pattern.equals(this.hrefURLPattern)) return true;
            if(pattern.equals(this.pLSQLNamesPattern)){
                if(matcher.group(0).length() <= 30)return true;
            }
        }

        return false;
    }



    @Override
    public List<String> fetchAllTemplates(StringBuffer inputString, Pattern pattern) throws IllegalArgumentException {
        List<String> listOfMatches = new ArrayList<String>();

        Matcher matcher = pattern.matcher(inputString);
        while (matcher.find())
        {
            listOfMatches.add(matcher.group(0));
        }


        return listOfMatches;
    }
}
