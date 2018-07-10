package ru.sbt.school.day8.impl;

import java.io.*;
import java.util.zip.ZipOutputStream;

/**
 * Created by chernyshas on 07.06.2018.
 */
public class Util {
    public static <T> void serializObject( T obj, String file){
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos);){
            oos.writeObject(obj);
        }catch (IOException ex){ex.printStackTrace();}


    }
    public static <T> T deSerializObject(String file){
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis);){
            return   (T) ois.readObject();
        }catch (IOException ex){ex.printStackTrace();}
        catch (ClassNotFoundException ex){ex.printStackTrace();}
        return null;
    }
}
