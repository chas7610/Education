package ru.sbt.school.day7;

import ru.sbt.school.day7.api.Calculator;
import ru.sbt.school.day7.app.App;
import ru.sbt.school.day7.cloaders.ApiClassloader;
import ru.sbt.school.day7.cloaders.AppClassloader;
import ru.sbt.school.day7.cloaders.ImplClassloader;

public class Main {
    public static void main(String[] args) {
        String path = Main.class.getResource("Main.class").getPath().substring(0, Main.class.getResource("Main.class").getPath().lastIndexOf("/")+1);
        System.out.println("1: SystemClassloader: " + Main.class.getClassLoader());
        System.out.println();
        AppClassloader appLoader = new AppClassloader(ClassLoader.getSystemClassLoader(), path + "app/");
        try {
            Class clazz = appLoader.loadClass("App");
            System.out.println("2: AppClassloader: " + clazz.getClassLoader());
        }catch (ClassNotFoundException ex){ex.printStackTrace();}

        ApiClassloader apiLoader = new ApiClassloader(ClassLoader.getSystemClassLoader(), path + "api/");
        Class apiClazz = null;
        try {
            apiClazz = apiLoader.loadClass("Calculator");
            System.out.println("3: AppClassloader: " + apiClazz.getClassLoader());
        }catch (ClassNotFoundException ex){ex.printStackTrace();}

        ImplClassloader implLoader = new ImplClassloader(ClassLoader.getSystemClassLoader(), path + "impl/");
        Class implClazz = null;
        try {
            implClazz = implLoader.loadClass("CalculatorImpl");
            System.out.println("4: ImplClassloader: " + implClazz.getClassLoader());
        }catch (ClassNotFoundException ex){ex.printStackTrace();}

//        try {
//            Class clazz = appLoader.loadClass("CalculatorImpl");
//            System.out.println("5: appClassloader: " + clazz.getClassLoader());
//        }catch (ClassNotFoundException ex){ex.printStackTrace();}

        try {
            Calculator calc = (Calculator)implClazz.newInstance();
            System.out.println("Calculator.plus(4,5) = " + calc.plus(4,5));
            System.out.println("5: calc: " + calc.getClass().getClassLoader());
            System.out.println(Calculator.class.getClassLoader());
        }catch (InstantiationException ex){ex.printStackTrace();}
        catch (IllegalAccessException ex){ex.printStackTrace();}

        System.out.println();

        try {
            App app = new App((Calculator)implClazz.newInstance());
            System.out.println("6: calc: " +  app.getCalculator().plus(2,5));
            System.out.println(app.getCalculator().getClass().getClassLoader());
        }catch (InstantiationException ex){ex.printStackTrace();}
        catch (IllegalAccessException ex){ex.printStackTrace();}
    }
}
