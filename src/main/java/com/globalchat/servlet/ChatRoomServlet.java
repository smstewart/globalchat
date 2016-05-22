package com.globalchat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;

import com.globalchat.entity.Message;
import com.globalchat.service.MessageService;
import com.globalchat.util.VelocityUtil;

public class ChatRoomServlet extends HttpServlet {
	private final static String CHATROOM_TEMPLATE = "chatroom.vm";
	private final static String MESSAGE_TEMPLATE = "message_template.vm";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Message> messages;
		VelocityContext context = new VelocityContext();
		String template;
		String updateString = request.getParameter("update");
		if (updateString == null) {
			messages = new MessageService().getRecentMessages();
			template = CHATROOM_TEMPLATE;
		} else {
			messages = new MessageService().getRecentMessages(Long.parseLong(updateString));
			template = MESSAGE_TEMPLATE;
		}	
		context.put("messages", messages);
		if (messages.size() > 0) {
			long latestTime = messages.get(messages.size() -1).getSubmitTime();
			context.put("latestUpdate", latestTime);
		} else {
			context.put("latestUpdate", System.currentTimeMillis());
		}
		VelocityUtil.writeFileToResponse(response, template, context);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String author = request.getParameter("author");
			String text = request.getParameter("text");

			Message.createMessage(author, text);

			doGet(request, response);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
