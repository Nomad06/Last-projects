import java.util.Scanner;

public class Test extends TestSuper implements TestInterface, Checker, BusinessCard
{
    private String str = "Stringa";

    @Override
    protected void nameProc() {
        System.out.println("Thx");
    }

    @Override
    public void pors(){
        System.out.println("how are you?");
    }

    @Override
    void bottle(){
        System.out.println("hi!");
    }

    protected void lastnameProc() {}

    private void lastpors(){}

    void bigbottle(){}

    public void pubble(){}

    public void puzzle(){}

    @Override
    public void metho() {

    }

    @Override
    public BusinessCard getBusinessCard(Scanner scanner) {
        return null;
    }

    @Override
    public String getEmployee() {
        return null;
    }

    @Override
    public String getDepartment() {
        return null;
    }

    @Override
    public int getSalary() {
        return 0;
    }

    @Override
    public int getAge() {
        return 0;
    }

    @Override
    public String getGender() {
        return null;
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }
}
