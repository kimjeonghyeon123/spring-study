package com.earth.danggeun;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ReplyEchoHandler extends TextWebSocketHandler {

	List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
	Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		String senderEmail = getEmail(session);
		userSessions.put(senderEmail, session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
		String senderEmail = getEmail(session);
		userSessions.remove(senderEmail);
	}

	private String getEmail(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		String sessionEmail = (String) httpSession.get("email");
		
		return sessionEmail;
	}
	
}












