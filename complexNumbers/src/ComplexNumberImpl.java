

public class ComplexNumberImpl implements ComplexNumber {


    private double re;
    private double im;

    public ComplexNumberImpl(double re, double im){
        this.re = re;
        this.im = im;
    }

    public ComplexNumberImpl(String value)
    {
        this.set(value);
    }

    private double workWithStrNumbers(String s)
    {
        double toReturn = 1;
        boolean im = false;

        if(s.contains("i")) im = true;

        if(im)
        {
            s = (s.replace("i", "")).replace(" ", "");
            if(s.length() != 0)
            {
                return Double.parseDouble(s);
            }
        }
        else
        {
            return Double.parseDouble(s);
        }
        return toReturn;
    }


    public ComplexNumberImpl(){
        this.re = 0;
        this.im = 0;
    }


    @Override
    public double getRe() {
        return this.re;
    }

    @Override
    public double getIm() {
        return this.im;
    }

    @Override
    public boolean isReal() {
        if(this.im == 0)
        {
            return true;
        }
        return false;
    }

    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {
        String[] numbers = value.split("(\\+)");
        if(numbers.length == 1) {
            if (numbers[0].contains("i")) {
                this.set(0, workWithStrNumbers(numbers[0]));
            } else {
                this.set(workWithStrNumbers(numbers[0]), 0);
            }
        }
        else if(numbers.length == 2){
            this.re = workWithStrNumbers(numbers[0]);
            this.im = workWithStrNumbers(numbers[1]);
        }
        else {
            throw new NumberFormatException("Invalid input");
        }
    }

    @Override
    public ComplexNumber copy() {
        ComplexNumber complexNumber = null;
        try {
            complexNumber = this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return complexNumber;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        ComplexNumber complexNumber = (ComplexNumber) super.clone();

        return complexNumber;
    }

    @Override
    public int compareTo(ComplexNumber other) {
        double otherNumber = Math.pow(other.getIm(), 2) + Math.pow(other.getRe(), 2);
        double curNumber = Math.pow(this.im, 2) + Math.pow(this.re, 2);
        if(curNumber == otherNumber)
        {
            return 0;
        }
        else if(curNumber > otherNumber)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    @Override
    public void sort(ComplexNumber[] array) {
        ComplexNumber numberForHelp = new ComplexNumberImpl();
        int compareResult = 0;

        for (int i = 0; i < array.length; i++)
        {
            for (int k = 0; k < array.length; k++)
            {
                compareResult = array[i].compareTo(array[k]);
                if(compareResult < 0)
                {
                    numberForHelp = array[i];
                    array[i] = array[k];
                    array[k] = numberForHelp;
                }
            }
        }
        System.out.println("Sorted!");
    }

    @Override
    public ComplexNumber negate() {

        ComplexNumber arg1 = this.copy();
        this.re =  ((ComplexNumberImpl)arg1).re*(-1);
        this.im =  ((ComplexNumberImpl)arg1).im*(-1);

        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {

        ComplexNumber arg1 = this.copy();
        double arg1Re = arg1.getRe();
        double arg1Im = arg1.getIm();

        this.re = arg1Re + arg2.getRe();
        this.im = arg1Im + arg2.getIm();

        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        double arg2Re = arg2.getRe();
        double arg2Im = arg2.getIm();

        ComplexNumber arg1 = this.copy();
        double arg1Re = arg1.getRe();
        double arg1Im = arg1.getIm();

        this.re = arg1Re * arg2Re + ((-1)*arg1Im * arg2Im );
        this.im = arg1Re * arg2Im + arg1Im*arg2Re;

        return this;
    }

    @Override
    public String toString()
    {
        String stringToReturn = "";

        if(this.re != 0)
        {
            stringToReturn = stringToReturn.concat(Double.toString(this.re));
        }

        return imAdding(stringToReturn);
    }

    private String imAdding(String stringToReturn) {

        if(this.im > 0)
        {
            if(this.re != 0) {
                return stringToReturn.concat("+" + Double.toString(this.im) + "i");
            }
            else {
                return stringToReturn.concat(Double.toString(this.im) + "i");
            }
        }
        else if(this.im < 0)
        {
            return stringToReturn.concat(Double.toString(this.im) + "i");
        }

        return stringToReturn;

    }

    @Override
    public boolean equals(Object other)
    {
        if(this.re ==((ComplexNumber)other).getRe() && this.im ==((ComplexNumber)other).getIm())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
