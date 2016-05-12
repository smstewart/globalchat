package test.java.entity;

import org.junit.Test;

import com.globalchat.entity.Message;

public class MessageTest {

	@Test
	public void testCreateAndLoad() {
		String author = "testAuthor";
		String text = "some great insight";
		
		Message m = Message.createMessage(author, text);
		
		
	}
}
