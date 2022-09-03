package com.sangle.common.connector;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Hibernate {
    private static SessionFactory session = null;
    public static final Hibernate Instance = new Hibernate();

    public Hibernate() {
        try {
            File configFile;
            configFile = new File("config/hibernate.cfg.xml");
            Configuration configuration = new Configuration();
            configuration.configure(configFile);
            configuration.addFile("config/tables.xml");

            session = configuration.buildSessionFactory();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Session getSession() {
        try {
            return session.openSession();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
