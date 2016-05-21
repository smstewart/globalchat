package com.globalchat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Session;

import com.globalchat.util.SessionUtil;

@Entity
@Table(name = "message")
public class Message extends BaseEntity {
	@Id
	@GeneratedValue(generator = "message_id_gen")
	@SequenceGenerator(name = "message_id_gen", sequenceName = "message_seq")
	private long id;
	
	private String author;
	private String content;
	private long submit_time;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String text) {
		this.content = text;
	}
	public long getSubmitTime() {
		return submit_time;
	}
	public void setSubmitTime(long submit_time) {
		this.submit_time = submit_time;
	}
	
	public static Message createMessage(String author, String content) {
		Message m = new Message();
		
		m.setSubmitTime(System.currentTimeMillis());
		m.setAuthor(author);
		m.setContent(content);
		
		m.save();
		
		return m;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Message load(long id) {
		return (Message) load(id, Message.class);
	}
}
