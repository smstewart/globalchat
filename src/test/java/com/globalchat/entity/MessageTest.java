package com.globalchat.entity;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.globalchat.service.MessageService;

import static org.junit.Assert.assertEquals;

public class MessageTest {

	@Before
	public void clearMessages() {
		new MessageService().deleteMessagesForUnitTests();
	}
	
	@Test
	public void testCreateAndLoad() {
		String author = "testAuthor";
		String text = "some great insight";
		
		Message m = Message.createMessage(author, text);
		
		List<Message> messages = new MessageService().getRecentMessages();
		assertEquals(1, messages.size());
		
		Message loaded = messages.get(0);
		
		assertEquals(m.getAuthor(), loaded.getAuthor());
		assertEquals(m.getContent(), loaded.getContent());
	}
}
