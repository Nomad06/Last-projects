import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

interface URLDownloader{

    public void setQuery(String query);

    public String getQuery();

    public void getHtmlCode() throws MalformedURLException;

}
