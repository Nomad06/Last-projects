import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        Main main = new Main();
        WordCounter wordCounter = new WordCounterImpl("Вам не обязательно нужно создавать объекты шаблонов и вызывать их методы;" +
                "<Эти функции просто соответствующий метод> Вам не обязательно нужно создавать объекты шаблонов и вызывать их методы;" +
                "<Эти функции просто соответствующий метод>" );

        main.mapPrint(wordCounter.getWordCounts());
        main.listPrint(wordCounter.getWordCountsSorted());

        //main.dataUrlTest();
    }

    public void dataUrlTest() throws IOException {

        String encodingPrefix = "base64,";

        String dataUrl = "data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSI4MiIg" +
                "aGVpZ2h0PSIzNCIgdmlld0JveD0iMCAwIDE2NDAgNjgwIj48cGF0aCBkPSJNNTU2IDIwMGgtNDdjLTQgMC02IDMtNiA1djE0NWgtODd2LTE" +
                "0NWMwLTQtMi01LTUtNWgtNDdjLTQgMC02IDItNiA1djM2OGMwIDQgMiA2IDUgNmg0OGMzIDAgNS0yIDUtNnYtMTc2aDg3djE3NmMwIDQgMiA" +
                "2IDYgNmg0N2M0IDAgNS0zIDUtNnYtMzY4YzAtMy0yLTUtNS01em0xMDY2IDMyOWMtMS0yLTItMy00LTMtNCAwLTI0IDgtNTQgOC01OC" +
                "AwLTg2LTU1LTg2LTE0MyAwLTc3IDIzLTE1NCA2My0xNTQgMTEgMCAyMyAzIDQ0IDIyIDIgMiA0IDMgNiAzczUtMiA3LTVsMjAtMzNjMi0" +
                "yIDItNSAyLTcgMC0zLTMtNS04LTgtMzEtMTYtNTItMTktNzAtMTktNzEgMC0xMjYgODUtMTI2IDIxNyAwIDExNCA1MyAxODAgMTI5I" +
                "DE4MCAzMiAwIDY2LTE1IDgwLTI1IDMtMiA0LTUgMy04bC02LTI2em0tMzIzLTE1OGw5MS0xNjZjMi0yIDEtNS0yLTVoLTQ1Yy0xMyAw" +
                "LTE3IDUtMjIgMTRsLTY4IDE0MmMtNCA4LTggMTQtMTEgMTZ2LTE2N2MwLTMtMi01LTUtNWgtNDljLTIgMC01IDItNSA1djM2OWMwIDIgM" +
                "iA1IDUgNWg0OWMzIDAgNS0yIDUtNXYtMTkwYzIgMiA0IDQgNSA3bDg2IDE3N2M1IDkgMTEgMTEgMjEgMTFoNDhjMyAwIDQtMyAyLTVsLTEw" +
                "Ni0yMDN6bS00MzQgMTYxaC05Yy0xMSAwLTE0LTUtMTQtMTR2LTMxM2MwLTQtMi01LTUtNWgtMTUxYy00IDAtNiAyLTYgNXYyN2MwIDky" +
                "LTUgMjEwLTU0IDI5OWgtMTdjLTUgMC02IDItNiA2djExMWMwIDQgMyA1IDUgNWgzNmM4IDAgMTEtNiAxMS0xMXYtNjNoMTYydjY5Yz" +
                "AgMyAyIDUgNSA1aDM5YzUgMCA5LTQgOS0xMXYtMTA1YzAtNC0yLTYtNi02em0tODIgMGgtMTEwYzM5LTY2IDU0LTE0MCA1OS0yMzEg" +
                "Mi0yMCAyLTQwIDItNTNoNDh2Mjg0em0yMzYtMzQxYy04MiAwLTExOCAxMDMtMTE4IDIxNSAwIDExNCA1MiAxODMgMTI3IDE4MyAzNy" +
                "AwIDcwLTE0IDg2LTI1IDMtMiA0LTQgMy03bC02LTI2Yy0xLTMtMi01LTYtNS0zIDAtMjUgOC01NiA4LTU4IDAtODYtNTEtODYtMTE3di" +
                "0xOHMzMCA0IDQ3IDRoMTExdi0zMmMwLTExNy00Mi0xODAtMTAyLTE4MHptLTggMTY0Yy0xNyAwLTQ1IDMtNDUgM3MwLTExIDItMjNj" +
                "Ny02NCAyMy05NyA0OS05NyAyNCAwIDM5IDMzIDQzIDkyIDEgMTIgMSAyNiAxIDI2aC01MHoiLz48cGF0aCBmaWxsPSIjZjAwIi" +
                "BkPSJNMjcxIDExaC04NmMtODUgMC0xNTcgNjQtMTU3IDE4OSAwIDc1IDM1IDEzMCA5NyAxNThsLTExNiAyMDljLTQgNyAwIDEy" +
                "IDYgMTJoNTRjNSAwIDgtMiA5LTVsMTA1LTIwNWgzOHYyMDVjMCAyIDIgNSA1IDVoNDdjNSAwIDYtMiA2LTZ2LTU1NGMwLTUtMy04" +
                "LTgtOHptLTUwIDMwOWgtMzJjLTUwIDAtOTgtMzYtOTgtMTI3IDAtOTUgNDUtMTMzIDkyLTEzM2gzOXYyNjB6Ii8+PC9zdmc+\n";

        int contentStartIndex = dataUrl.indexOf(encodingPrefix) + encodingPrefix.length();
        byte[] imageData = Base64.getDecoder().decode(dataUrl);

        BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(imageData));

        if (inputImage == null) {
            System.out.println("Unknown image");
        }
    }

    public void mapPrint(Map<String, Long> words)
    {
        for (Map.Entry<String, Long> word: words.entrySet())
        {
            System.out.println(word);
        }
    }
    public void listPrint(List<Map.Entry<String, Long>> sortedList) {
        for (int i = 0; i < sortedList.size(); i++)
        {
            System.out.println(sortedList.get(i));;
        }

    }
}
