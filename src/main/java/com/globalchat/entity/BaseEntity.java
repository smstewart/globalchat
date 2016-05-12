package com.globalchat.entity;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.globalchat.util.SessionUtil;

public abstract class BaseEntity {
	
	public void save() {
		Session sess = getSession();
		Transaction t = sess.beginTransaction();
		
		sess.persist(this);
		
		t.commit();
		sess.close();
	}
	
	public abstract <E extends BaseEntity> E load(long id);
	
	protected Object load (long id, Class clazz) {
		try (Session s = getSession()) {
			return s.get(clazz, id);
		}
	}
	private Session getSession() {
		return SessionUtil.getSession();
	}
}
