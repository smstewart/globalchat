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
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Message> messages = new MessageService().getRecentMessages();
		VelocityContext context = new VelocityContext();
		context.put("messages", messages);
		
		VelocityUtil.writeFileToResponse(response, CHATROOM_TEMPLATE, context);
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
