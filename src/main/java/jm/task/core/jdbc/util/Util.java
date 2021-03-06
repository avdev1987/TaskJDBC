package jm.task.core.jdbc.util;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;


    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Mytest?serverTimezone=Europe/Moscow&useSSL=false";
        String username = "root";
        String password = "root";
        return DriverManager.getConnection(url, username, password);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/Mytest?serverTimezone=Europe/Moscow&useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Well done!! Connection successful! Hibernate is working)");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Sorry, сonnection failed. Hibernate is not working(");
            }
        }
        return sessionFactory;
    }
}


