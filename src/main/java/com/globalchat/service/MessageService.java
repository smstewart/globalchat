package com.globalchat.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.globalchat.entity.Message;
import com.globalchat.util.SessionUtil;

public class MessageService {
	public MessageService() {
		
	}
	
	public List<Message> getRecentMessages() {
		Session sess = SessionUtil.getSession();
		try {
			Query q = sess.createQuery(
					"from Message order by submit_time desc")
				.setMaxResults(getMaxResults());

			return q.list();
		} finally {
			SessionUtil.closeSession(sess);
		}
	}
	
	public List<Message> getRecentMessages(long startTime) {
		Session sess = SessionUtil.getSession();
		try {
			Query q = sess.createQuery(
					"from Message where submit_time > :start order by submit_time desc")
				.setParameter("start", startTime)
				.setMaxResults(getMaxResults());
			
			return q.list();
		} finally {
			SessionUtil.closeSession(sess);
		}
	}
	
	protected int getMaxResults() {
		return 100;
	}
	
	public void deleteMessagesForUnitTests() {
		Session sess = SessionUtil.getSession();
		try {
			Query q = sess.createQuery("delete Message");
			q.executeUpdate();
		} finally {
			SessionUtil.closeSession(sess);
		}
	}
}
