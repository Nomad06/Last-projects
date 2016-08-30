import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class FileWriter {

    public static String htmlName = "";
    public static String resourceDirectory = "";
    private static NamesCreator namesCreator;
    static Pattern pattern;
    static Matcher matcher;
    public static String homeDirectory = "";
    public static boolean fileOpen = false;

    public static void fileWriting(BufferedReader reader, String query) throws IOException {
        int lineCount = 0;
        String line;
        String htmlFileName = "";
        File file;
        if(homeDirectory.equals(""))
        {
            file = new File(query);
        }
        else
        {
            String[] linkParts = homeDirectory.split("\\\\");
            if(linkParts.length != 0)
            {
                if((linkParts[linkParts.length - 1]).contains("html"))
                {
                    htmlName = homeDirectory;
                    htmlFileName = linkParts[linkParts.length - 1].replace(".html", "");
                }
                else
                {
                    for(int i = 0; i < linkParts.length; i++)
                    {
                        htmlName += linkParts[i] + "\\";
                    }
                    htmlName += query;
                    htmlFileName = query.replace(".html", "");
                }
            }
            file = new File(htmlName);
        }

        try {
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            String fileLink = "";
            try {

                String imagePattern = "src=\"([\\w://.]*)\"";
                String resourcePattern = "href=\"([\\w://.-]*)\"";
                String charsetPattern = "charset=(.*)'";
                while ((line = reader.readLine()) != null)
                {
                    resourceDirectory = htmlFileName + "_files\\";
                    if(line.contains("charset"))
                    {
                        Pattern pattern = Pattern.compile(charsetPattern);
                        Matcher matcher = pattern.matcher(line);
                        while (matcher.matches())
                        {
                            String charset = matcher.group(1);

                        }
                    }
                    if(line.contains("<img "))
                    {
                        line = line.replace(gettingResources(imagePattern, line, fileLink, true, false), resourceDirectory);
                    }
                    if(line.contains("<link "))
                    {
                        line = line.replace(gettingResources(resourcePattern, line, fileLink, false, true), resourceDirectory);
                    }
                    lineCount++;
                    out.println(line);
                }
            } finally {
                out.close();
                if(fileOpen)
                {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);
                }
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String gettingResources(String reg, String line, String fileLink, boolean image, boolean resource) throws MalformedURLException {

        URLDownloader fileUrlDownloader;
        String oldLink = "";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(line);
        while (matcher.find())
        {
            fileLink = matcher.group(1);
            oldLink = fileLink;
        }
        namesCreator = new LinkNameCreator(fileLink);
        if(image) fileLink = namesCreator.getImageName();
        else fileLink = namesCreator.getResourceName();

        System.out.println(fileLink);
        fileUrlDownloader = new URLDownloaderImpl(fileLink, image, resource);
        fileUrlDownloader.getHtmlCode();
        return oldLink;
    }


    public static void imageSaving(BufferedImage image, String fileName) throws IOException {
        File imageFile = new File(folderExistence() + fileName);
        if (image != null){
            ImageIO.write(image, "png",imageFile);
        }
        resourceDirectory += fileName;
    }

    private static String folderExistence( ) {
        String folderName = homeDirectory + "\\" + resourceDirectory;
        File imageFile = new File(folderName);
        if(!imageFile.exists())
        {
            imageFile.mkdir();
        }
        return folderName;
    }

    public static void resourceSaving(BufferedInputStream inputStreamReader, String fileName) throws IOException {
        int ch;
        FileOutputStream fileOutputStream = new FileOutputStream(folderExistence() + fileName);
        while ((ch = inputStreamReader.read()) != -1)
        {
            fileOutputStream.write(ch);
        }
        inputStreamReader.close();
        fileOutputStream.flush();
        fileOutputStream.close();
        resourceDirectory += fileName;
    }




}
