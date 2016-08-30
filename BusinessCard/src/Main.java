import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println("Hello World!");

        Integer[] params = new Integer[5];
        params[0] = 5;
        params[1] = 3;
        params[2] = 1;
        params[3] = 6;
        params[4] = 2;
        Checker checker = new CheckerImpl();
        Test test = new Test();
        Reflections reflections = new ReflectionsImpl();
        reflections.getFieldValueByName(new Test(), "str");
        reflections.getProtectedMethodNames(test.getClass());
        printer(reflections.getAllImplementedMethodsWithSupers(test.getClass()));
        List<Class> list = new ArrayList<>();
        list = reflections.getExtendsHierarchy(test.getClass());
        System.out.println(reflections.getFooFunctionResultForDefaultConstructedClass());
        System.out.println(reflections.getFooFunctionResultForClass("text", "strext", params));
        System.out.println("Complete");

    }

    private static void printer(Set<Method> list) {
        for(Method str : list)
        {
            System.out.println(str);
        }
    }
}
