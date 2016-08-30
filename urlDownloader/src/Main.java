import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String query = args[0];
        if(args.length > 1)
        {
            FileWriter.homeDirectory = args[1];
            if(args.length > 2)
            {
                if(args[2].equals("1"))
                {
                    FileWriter.fileOpen = true;
                }
            }

        }
        URLDownloader urlDownloader = new URLDownloaderImpl(query);
        urlDownloader.getHtmlCode();
    }
}
