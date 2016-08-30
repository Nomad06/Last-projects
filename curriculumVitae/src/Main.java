import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        CurriculumVitaeImpl curriculumVitae = new CurriculumVitaeImpl();
        curriculumVitae.setText("Bogatyrev Muhammad Muvsaevich 24168424\n" +
                "Office: 077 12345678 ext.1234 vdxvsdfvsdfvdsfv 495 926-93-47 ext.1846 bubalex.06@mail. ru fdafadsfdsafdsafdsa Bogatyrev Muhammad Muvsaevich. (926)7543344 dajskdnakjd dadnksja 495 355 35 68");
        curriculumVitae.getPhones();
        System.out.println(curriculumVitae.getText());

        curriculumVitae.hide("bubalex.06@mail. ru");
        curriculumVitae.hidePhone("495 926-93-47 ext.1846");
        curriculumVitae.hidePhone("12345678 ext.1234");
        System.out.println(curriculumVitae.getText());
        System.out.println(curriculumVitae.unhideAll());
        System.out.println(curriculumVitae.getText());

    }
}
