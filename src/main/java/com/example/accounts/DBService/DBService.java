package com.example.accounts.DBService;

import java.util.Properties;

import io.github.cdimascio.dotenv.Dotenv;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBService {
    private final SessionFactory sessionFactory;

    public DBService() {
        try {
            Dotenv dotenv = Dotenv.configure()
                    .ignoreIfMissing()
                    .load();

            String dbUrl = dotenv.get("DB_URL");
            String dbUser = dotenv.get("DB_USER");
            String dbPassword = dotenv.get("DB_PASSWORD");

            if (dbUrl == null || dbUser == null || dbPassword == null) {
                throw new RuntimeException("Не удалось загрузить настройки БД из .env файла");
            }

            Properties hibernateProps = new Properties();
            hibernateProps.setProperty("hibernate.connection.url", dbUrl);
            hibernateProps.setProperty("hibernate.connection.username", dbUser);
            hibernateProps.setProperty("hibernate.connection.password", dbPassword);
            hibernateProps.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            hibernateProps.setProperty("hibernate.hbm2ddl.auto", "update");
            hibernateProps.setProperty("hibernate.show_sql", "true");

            Configuration config = new Configuration()
                    .setProperties(hibernateProps)
                    .addAnnotatedClass(UsersDataSet.class);

            this.sessionFactory = config.buildSessionFactory();

        } catch (Exception e) {
            throw new RuntimeException("Не удалось запустить Hibernate", e);
        }
    }

    public void addUser(UsersDataSet user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public UsersDataSet getUserByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(UsersDataSet.class, name);
        } finally {
            session.close();
        }
    }

    public void close() throws HibernateException {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}