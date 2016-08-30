import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectionsImpl implements Reflections {

    List<Class> list = new ArrayList<>();

    @Override
    public Object getFieldValueByName(Object object, String fieldName) throws NoSuchFieldException {
        if(object == null || fieldName == null) throw new NullPointerException();
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Object fieldValue = null;
        try {
            fieldValue = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fieldValue;
    }

    @Override
    public Set<String> getProtectedMethodNames(Class clazz) {
        nullThrower(clazz);
        Set<String> methodsList = new HashSet<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method: methods){
            if(method.getModifiers() == 4)
            {
                methodsList.add(method.getName());
            }
        }
        return methodsList;
    }

    private void nullThrower(Class clazz) {
        if(clazz == null) throw new NullPointerException();
    }

    @Override
    public Set<Method> getAllImplementedMethodsWithSupers(Class clazz) {
        nullThrower(clazz);
        Set<Method> methodsList = new HashSet<Method>();

        Method[] methods = clazz.getDeclaredMethods();
        Method[] methodSuper = clazz.getSuperclass().getDeclaredMethods();
        Method[] unitedMethodsList = new Method[methods.length + methodSuper.length];
        System.arraycopy(methods, 0, unitedMethodsList, 0, methods.length);
        System.arraycopy(methodSuper, 0, unitedMethodsList, methods.length, methodSuper.length);
        for (int i = 0; i < unitedMethodsList.length; i++)
        {
            methodsList.add(unitedMethodsList[i]);
        }
        return methodsList;
    }

    @Override
    public List<Class> getExtendsHierarchy(Class clazz) {
        while (clazz.getSuperclass() != null)
        {
            list.add(clazz.getSuperclass());
            return getExtendsHierarchy(clazz.getSuperclass());
        }
        return list;
    }

    @Override
    public Set<Class> getImplementedInterfaces(Class clazz) {
        Set<Class> classSet = new HashSet<>();
        Class[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; i++)
        {
            classSet.add(interfaces[i]);
        }
        return classSet;
    }

    @Override
    public List<Class> getThrownExceptions(Method method) {
        Class[] exceptions = method.getExceptionTypes();
        for (int i = 0; i < exceptions.length; i++)
        {
            list.add(exceptions[i]);
        }
        return list;
    }

    @Override
    public String getFooFunctionResultForDefaultConstructedClass() {
        try {
            Class clazz = Class.forName("ru.ncedu.java.tasks.Reflections");
            clazz = clazz.getClasses()[0];

            if(clazz != null){
                Constructor<?> constructor = clazz.getDeclaredConstructor(new Class<?>[0]);
                constructor.setAccessible(true);
                Object objectInstance = constructor.newInstance(new Object[0]);

                Method fooMethod = clazz.getDeclaredMethod("foo", new Class<?>[0]);
                fooMethod.setAccessible(true);
                //Method fooMethod = clazz.getDeclaredMethod("foo", new Class<?>[]{String.class, });
                String result = (String) fooMethod.invoke(objectInstance, new Object[0]);
                return result;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getFooFunctionResultForClass(String constructorParameter, String string, Integer... integers) {
        try {
            Class clazz = Class.forName("ru.ncedu.java.tasks.Reflections");
            clazz = clazz.getClasses()[0];

            if(clazz != null){
                Constructor<?> constructor = clazz.getConstructor(String.class);

                Object objectInstance = constructor.newInstance(constructorParameter);

                Method fooMethod = clazz.getDeclaredMethod("foo", new Class<?>[]{String.class, Integer[].class});
                fooMethod.setAccessible(true);
                //Method fooMethod = clazz.getDeclaredMethod("foo", new Class<?>[]{String.class, });
                String result = (String) fooMethod.invoke(objectInstance, string, integers);
                return result;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
