package ru.sbt.school.day7.cloaders;

import java.io.*;

public class ApiClassloader extends ClassLoader {
    private String path;

    public ApiClassloader(ClassLoader parent, String path) {
        super(parent);
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            File fName = new File(path + name+ ".class");
            InputStream is = new FileInputStream(fName);
            byte [] bytes = new byte[(int)fName.length()];
            int position = 0;
            int read = 0;
            while ( (position < bytes.length) && (read = is.read(bytes,position, bytes.length - position)) >= 0){
                position += read;
            }
            return defineClass(null, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return super.findClass(path + name + ".class");
    }
}
