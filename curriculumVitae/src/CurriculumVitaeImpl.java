import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurriculumVitaeImpl implements CurriculumVitae {

    private String text;
    private Pattern pattern;
    private static final String FIO_PATTERN = "([A-Z][a-z]+|[A-Z][a-z]*\\.) ([A-Z][a-z]+|[A-Z][a-z]*\\.)( [A-Z][a-z]+|[A-Z][a-z]*\\.)?";
    private ArrayList<String[]> hiddenList = new ArrayList<String[]>();
    private boolean changed = false;
    private int hiddenCount = 0;

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() throws IllegalStateException {
        if(this.text == null)  throw new IllegalStateException();

        return this.text;
    }

    @Override
    public List<Phone> getPhones() throws IllegalStateException {
        List<Phone> phoneList = new ArrayList<Phone>();
        Phone phone;
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(this.getText());

        while (matcher.find()) {
            int ext = -1;
            int areaCode = -1;
            String number = matcher.group(0);

            if(matcher.group(2) != null && matcher.group(7) != null)
            {
                areaCode = Integer.parseInt(matcher.group(2));
                ext = Integer.parseInt(matcher.group(7));
            }
            else if(matcher.group(2) == null && matcher.group(7) != null)  ext = Integer.parseInt(matcher.group(7));
            else if(matcher.group(2)!= null && matcher.group(7) == null)  areaCode = Integer.parseInt(matcher.group(2));
            phoneList.add(new Phone(number, areaCode, ext));
        }
        return phoneList;
    }

    @Override
    public String getFullName() throws NoSuchElementException, IllegalStateException {

        String[] fio = getFIO();
        if (fio != null) return fio[0];
        throw new NoSuchElementException();
    }

    private String[] getFIO() {
        String fullName;
        String[] fio = new String[4];
        pattern = Pattern.compile(FIO_PATTERN);
        Matcher matcher = pattern.matcher(this.getText());
        while (matcher.find()){
            fio[0] = matcher.group(0);
            fio[1] = matcher.group(1);
            fio[2] = matcher.group(2);
            fio[3] = matcher.group(3);
            return fio;
        }
        return null;
    }

    @Override
    public String getFirstName() throws NoSuchElementException, IllegalStateException {
        String[] fio = getFIO();
        if (fio != null) return fio[1];
        throw new NoSuchElementException();

    }

    @Override
    public String getMiddleName() throws NoSuchElementException, IllegalStateException {
        String[] fio = getFIO();
        if (fio != null) return fio[2];
        throw new NoSuchElementException();
    }

    @Override
    public String getLastName() throws NoSuchElementException, IllegalStateException {
        String[] fio = getFIO();
        if (fio != null) return fio[3];
        throw new NoSuchElementException();
    }



    @Override
    public void updateLastName(String newLastName) throws NoSuchElementException, IllegalStateException {
        this.setText(this.text.replace(this.getLastName(), newLastName));
    }

    @Override
    public void updatePhone(Phone oldPhone, Phone newPhone) throws IllegalArgumentException, IllegalStateException {
        String cvText = this.text;
        if(!cvText.contains(oldPhone.getNumber())) throw new IllegalArgumentException();

        this.setText(cvText.replace(oldPhone.getNumber(), newPhone.getNumber()));
    }

    @Override
    public void hide(String piece) throws IllegalArgumentException, IllegalStateException {
        String withChanges = "";
        if(this.text.contains(piece))
        {
            withChanges = piece.replaceAll("[^\\.\\s@]", "X");
            if (!changed) {
                changed = true;
            }
            hiddenCount++;
            addToHiddenList(piece, withChanges, this.text.indexOf(piece));
            this.setText(this.text.replace(piece, withChanges));
        }
        else throw new IllegalArgumentException();

    }

    private void addToHiddenList(String piece, String withChanges, int pos) {
        String[] list = new String[3];
        list[0] = withChanges;
        list[1] = piece;
        list[2] = Integer.toString(pos);
        hiddenList.add(list);
    }

    @Override
    public void hidePhone(String phone) throws IllegalArgumentException, IllegalStateException {
        String withChanges = "";
        if(this.text.contains(phone))
        {
            withChanges = phone.replaceAll("\\d", "X");
            if (!changed) {
                changed = true;
            }
            hiddenCount++;
            addToHiddenList(phone, withChanges, this.text.indexOf(phone));
            this.setText(this.text.replace(phone, withChanges));
        }
        else throw new IllegalArgumentException();
    }

    @Override
    public int unhideAll() throws IllegalStateException {
        String textOld = this.getText();
        if(changed)
        {
            for(int i = 0; i < this.hiddenList.size(); i++)
            {
                if()
                    textOld.r
                textOld = textOld.replace(hiddenList.get(i)[0], hiddenList.get(i)[1]);
            }
            this.setText(textOld);
            hiddenList.clear();
        }
        return hiddenCount;
    }
}
