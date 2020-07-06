package hibernate;

import model.Group;
import model.Student;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;

import java.io.File;


public class HibernateFactory {
    public static class HibernateUtil {
        public static final SessionFactory sessionFactory = buildSessionFactory();

        public static SessionFactory buildSessionFactory() {
            try {


                // Create the SessionFactory from hibernate.cfg.xml
                Configuration config = new Configuration();

                config.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
                config.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/test3");
                config.setProperty("hibernate.connection.username", "postgres");
                config.setProperty("hibernate.connection.password", "290798is");
                config.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgresPlusDialect");
                config.setProperty("hibernate.hbm2ddl.auto","create");
                config.addAnnotatedClass(User.class);
                config.addAnnotatedClass(Student.class);
                config.addAnnotatedClass(Group.class);
                //config.addPackage("model");
                return config.buildSessionFactory();
              // return new Configuration().configure(new File("/resources/hibernate.cgf.xml")).buildSessionFactory();

            } catch (Throwable ex) {
                // Make sure you log the exception, as it might be swallowed
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }

        public static void shutdown() {
            // Close caches and connection pools
            getSessionFactory().close();
        }
    }
}
