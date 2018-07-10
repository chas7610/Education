package ru.sbt.school.day12;

import ru.sbt.school.day12.impl.FixedThreadPool;
import ru.sbt.school.day12.impl.ScalableThreadPool;
import ru.sbt.school.day12.model.ThreadPool;



/**
 * Created by chernyshas on 20.06.2018.
 */
public class Main {

    public static void main(String[] args) {

        ThreadPool pool = FixedThreadPool.newFixedThreadPool(3);

//        ThreadPool pool = ScalableThreadPool.newScalableThreadPool(2,4);

        pool.execute(() -> {String name = Thread.currentThread().getName();
            System.out.println("=======Start thread 1=======");
            for (int i = 0; i <7; i ++){
                System.out.println("\t\t" +name + " " + i);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex){ex.printStackTrace();}
            }
            System.out.println("=======Stop thread 1=======");
        });

        pool.execute(() -> {String name = Thread.currentThread().getName();
            System.out.println("=======Start thread 2=======");
            for (int i = 0; i <10; i ++){
                System.out.println("\t\t" +name + " " + i);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex){ex.printStackTrace();}
            }
            System.out.println("=======Stop thread 2=======");
        });
        pool.execute(() -> {String name = Thread.currentThread().getName();
            System.out.println("=======Start thread 3=======");
            for (int i = 0; i <11; i ++){
                System.out.println("\t\t" +name + " " + i);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex){ex.printStackTrace();}
            }
            System.out.println("=======Stop thread 3=======");
        });
        pool.execute(() -> {String name = Thread.currentThread().getName();
            System.out.println("=======Start thread 4=======");
            for (int i = 0; i <5; i ++){
                System.out.println("\t\t" +name + " " + i);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex){ex.printStackTrace();}
            }
            System.out.println("=======Stop thread 4=======");
        });
        pool.execute(() -> {String name = Thread.currentThread().getName();
            System.out.println("=======Start thread 5=======");
            for (int i = 0; i <10; i ++){
                System.out.println("\t\t" +name + " " + i);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex){ex.printStackTrace();}
            }
            System.out.println("=======Stop thread 5=======");
        });
        pool.execute(() -> {String name = Thread.currentThread().getName();
            System.out.println("=======Start thread 6=======");
            for (int i = 0; i <4; i ++){
                System.out.println("\t\t" +name + " " + i);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex){ex.printStackTrace();}
            }
            System.out.println("=======Stop thread 6=======");
        });
    }
}
