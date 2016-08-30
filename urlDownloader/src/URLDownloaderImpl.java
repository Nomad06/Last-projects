import javax.imageio.ImageIO;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class URLDownloaderImpl implements URLDownloader {

    private String query;
    private boolean image = false;
    private boolean resource = false;
    private static int count = 0;
    private URL url;;
    private String fileName;

    URLDownloaderImpl(String query)
    {
        this.query = query;
        objectInitialization();

    }

    private void objectInitialization() {
        try {
            url = new URL(this.query);
            NamesCreatorAbstr.host = url.getHost();
            NamesCreatorAbstr.path = url.getPath();
            NamesCreatorAbstr.protocol = url.getProtocol();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    URLDownloaderImpl(String query, boolean image, boolean resource)
    {
        this.query = query;
        this.image = image;
        this.resource = resource;
    }

    URLDownloaderImpl(){}

    @Override
    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String getQuery()
    {
        return this.query;
    }

    public  void getHtmlCode() throws MalformedURLException {

        URLConnection connection = null;
        try
        {
            url = new URL(this.query);

            System.out.println("Get connection");

            pageOrResource(connection);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void pageOrResource(URLConnection connection) throws IOException {
        NamesCreator namesCreator = new FileNameCreator(this.query);
        ((FileNameCreator)namesCreator).setQuery(this.query);

        if(image == false && resource == false)
        {
            connection = getUrlConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            FileWriter.fileWriting(reader, ((FileNameCreator)namesCreator).getHtmlPageName());
        }
        else
        {
            if(image)
            {
                FileWriter.imageSaving(ImageIO.read(url), namesCreator.getImageName());
            }
            else
            {
                connection = getUrlConnection();
                fileName = namesCreator.getResourceName();
                BufferedInputStream inputStreamReader = new BufferedInputStream(connection.getInputStream());
                FileWriter.resourceSaving(inputStreamReader, fileName);
                System.out.println("File");
            }
        }
    }

    private URLConnection getUrlConnection() throws IOException {
        URLConnection connection;
        connection = (URLConnection) url.openConnection();
        connection.connect();
        return connection;
    }


}