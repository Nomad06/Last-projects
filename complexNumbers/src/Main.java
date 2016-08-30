import java.util.ArrayList;

public class Main {

    static public void main(String[] args)
    {
        ComplexNumber complexNumber = new ComplexNumberImpl(0, -15);
        ComplexNumber complexNumber1 = new ComplexNumberImpl(5, -3);
        ComplexNumber complexNumber2 = new ComplexNumberImpl(4, 0);
        ComplexNumber complexNumber3 = new ComplexNumberImpl(3, -1);

        ComplexNumber stringNumber = new ComplexNumberImpl("-4+2.5i");
        System.out.println(stringNumber.toString());
        ComplexNumber[] arrayList = new ComplexNumber[4];

        try {
            stringNumber.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        ComplexNumber complexNumber4 = complexNumber1.copy();
        System.out.println(complexNumber.toString());
        System.out.println(complexNumber1.toString());
        System.out.println(complexNumber2.toString());
        System.out.println(complexNumber3.toString());

        if(complexNumber2.equals(complexNumber))
        {
            System.out.println("equal");
        }

        if(complexNumber4 == complexNumber1)
        {
            System.out.println("falseeee");
        }
        arrayList[0] = complexNumber;
        arrayList[1] = complexNumber1;
        arrayList[2] = complexNumber2;
        arrayList[3] = complexNumber3;

        complexNumber.compareTo(complexNumber1);

        complexNumber.sort(arrayList);
        complexNumber.multiply(complexNumber);

        System.out.println("Hello World!");
    }
}
