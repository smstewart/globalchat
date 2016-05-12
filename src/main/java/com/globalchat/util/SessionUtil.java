package com.globalchat.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionUtil {
	private static SessionFactory sessionFactory = null;
	
	public static Session getSession() {
		if (sessionFactory == null) {
			createSessionFactory();
		}
		return sessionFactory.openSession();
	}
	
	public static void closeSession(Session sess) {
		sess.getTransaction().commit();
		sess.flush();
		sess.close();
	}
	
	private static void createSessionFactory() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
}
