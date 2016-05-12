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
	private String text;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public long getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(long submit_time) {
		this.submit_time = submit_time;
	}
	
	public static Message createMessage(String author, String text) {
		Message m = new Message();
		
		m.setSubmit_time(System.currentTimeMillis());
		m.setAuthor(author);
		m.setText(text);
		
		m.save();
		
		return m;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Message load(long id) {
		return (Message) load(id, Message.class);
	}
}
