package com.globalchat.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.globalchat.entity.Message;

public class MessageServiceTest {
	
	@Before
	public void clearMessages() {
		new MessageService().deleteMessagesForUnitTests();
	}
	
	@Test
	public void testGetRecentMessages() {
		MessageService service = new MessageService();
		String author = "author";
		for (int i = 0; i < 10; i++) {
			Message.createMessage(author, String.valueOf(i));
		}
		List<Message> messages = service.getRecentMessages();
		assertEquals(10, messages.size());
		
		author = "new author";
		for (int j = 0; j < 150; j++) {
			Message.createMessage(author, String.valueOf(j));
		}
		messages = service.getRecentMessages();
		assertEquals(100, messages.size());
		for (Message m : messages) {
			assertEquals("new author", m.getAuthor());
		}
	}
	
	@Test
	public void testGetRecentMessages_time() throws Exception {
		MessageService service = new MessageService();
		
		String author = "old author";
		for (int i = 0; i < 10; i++) {
			Message.createMessage(author, String.valueOf(i));
		}
		
		Thread.sleep(10);
		long time = System.currentTimeMillis();
		Thread.sleep(10);
		
		List<Message> messages = service.getRecentMessages(time);
		assertEquals(0, messages.size());
		
		author = "new author";
		for (int j = 0; j < 10; j++) {
			Message.createMessage(author, String.valueOf(j));
		}
		messages = service.getRecentMessages(time);
		assertEquals(10, messages.size());
		for (Message m : messages) {
			assertEquals("new author", m.getAuthor());
		}
	}
}
