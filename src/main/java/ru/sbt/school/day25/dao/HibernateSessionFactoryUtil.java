package ru.sbt.school.day25.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.sbt.school.day25.model.CookingRecipe;


public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(CookingRecipe.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void close(){
        if (sessionFactory != null)
            sessionFactory.close();
    }
}
