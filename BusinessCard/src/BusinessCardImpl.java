
import java.text.SimpleDateFormat;
import java.util.*;


public class BusinessCardImpl implements BusinessCard{

    private String name;
    private String lastName;
    private int age = -1;
    private String department;
    private String birthDate;
    private char gender;
    private int salary;
    private String phone;
    private String[] scannerData;

    public BusinessCardImpl(){}

    public BusinessCardImpl(Scanner scanner)
    {
        String[] data = scannerReader(scanner);
        dataInitialization(data);
    }

    private void dataInitialization(String[] data) {
        this.name = data[0];
        this.lastName = data[1];
        this.department = data[2];
        this.birthDate = data[3];
        this.gender = data[4].charAt(0);
        this.phone = data[6];
        this.salary = Integer.parseInt(data[5]);
        this.age = ageCalculation();
    }

    @Override
    public BusinessCard getBusinessCard(Scanner scanner) {
        dataInitialization(scannerReader(scanner));
        return this;
    }

    private String[] scannerReader(Scanner scanner) {
        String string = "";
        if(scanner.hasNext())
        {
            string = scanner.nextLine();
        }
        scannerData = string.replaceAll(" ", "").split(";");

        if(checkingForIncorrectData() == false)
        {
            throw new InputMismatchException();
        }
        return scannerData;
    }


    private boolean checkingForIncorrectData() {
        if(scannerData.length != 7) return false;
        if(!scannerData[4].matches("[MF]")) return false;
        if(!scannerData[6].matches("\\d{10}")) return false;
        if(!scannerData[3].matches("([0][1-9]|[1-2]\\d|3[0-1])-(0[1-9]|1[0-2])-(1[9]\\d{2}|20[0-1][0-6])")) return false;
        if(scannerData[5].matches("\\d{3,6}"))
        {
            if(!(Integer.parseInt(scannerData[5]) > 100 && Integer.parseInt(scannerData[5]) < 100000))
            {
                return false;
            }
        }
        else return false;

        return true;
    }

    @Override
    public String getEmployee() {
        return this.name + " " + this.lastName;
    }

    @Override
    public String getDepartment() {
        return this.department;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public int getAge() {
        if(this.age == -1) this.age = ageCalculation();
        return this.age;
    }

    private int ageCalculation() {
        int age = 0;
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(this.birthDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            Calendar calendarCur = Calendar.getInstance();
            age = calendarCur.get(Calendar.YEAR) - calendar.get(Calendar.YEAR) - 1;
            int month = calendarCur.get(Calendar.MONTH) - calendar.get(Calendar.MONTH);
            int days = calendarCur.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
            if(month > 0)
            {
                age++;
            }
            else if(month == 0)
            {
                if(days >= 0)
                {
                    age++;
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return age;
    }

    @Override
    public String getGender() {
        if(this.gender == 'M') return "Male";
        return "Female";
    }

    @Override
    public String getPhoneNumber() {
        String phone = this.phone;
        phone = "+7 " + phone.substring(0,3) + "-" + phone.substring(3,6) + "-" + phone.substring(6,8) + "-"
                + phone.substring(8,10);
        return phone;
    }
}
