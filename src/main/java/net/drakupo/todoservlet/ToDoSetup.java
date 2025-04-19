package net.drakupo.todoservlet;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.hibernate.service.ServiceRegistry;
import java.util.Properties;

public class ToDoSetup {

    public static Logger logger = LogManager.getLogger("debug");
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate-test");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "t@k3th3Gr3nd3l");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.JDBC_TIME_ZONE, "America/New_York");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                config.setProperties(settings);
                config.addAnnotatedClass(ToDoTask.class);

                ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

                sessionFactory = config.buildSessionFactory(sr);
                logger.info("Hibernate session factory created.");

                return sessionFactory;
            } catch (Exception e) {
                System.err.println("Couldn't create SessionFactory: " + e);
                logger.error("Couldn't create SessionFactory: " + e.getMessage());
            }
        }
        return sessionFactory;
    }

}
