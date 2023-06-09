package com.earth.danggeun;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONObject;
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
		String msg = message.getPayload();
		String[] strs = msg.split(",");
		String cmd = strs[0];
		
		if(cmd.equals("sendchat")) {
			String receiver_email = strs[1];
			Integer chatroom_id = Integer.parseInt(strs[2]);
			WebSocketSession receiverSession = userSessions.get(receiver_email);
			
			if(receiverSession != null) {
				JSONObject replyMessage = new JSONObject();
				replyMessage.put("cmd", "sendchat");
				replyMessage.put("chatroom_id", chatroom_id);
				
				TextMessage tmpMsg = new TextMessage(replyMessage.toString());
				receiverSession.sendMessage(tmpMsg);
			}
		}
		
		if(cmd.equals("readchat")) {
			String receiver_email = strs[1];
			Integer chatroom_id = Integer.parseInt(strs[2]);
			
			WebSocketSession receiverSession = userSessions.get(receiver_email);
			if(receiverSession != null) {
				JSONObject replyMessage = new JSONObject();
				replyMessage.put("cmd", "readchat");
				replyMessage.put("chatroom_id", chatroom_id);
				
				TextMessage tmpMsg = new TextMessage(replyMessage.toString());
				receiverSession.sendMessage(tmpMsg);
			}
		}
		
		if(cmd.equals("removechat")) {
			String receiver_email = strs[1];
			Integer chatroom_id = Integer.parseInt(strs[2]);
			
			WebSocketSession receiverSession = userSessions.get(receiver_email);
			if(receiverSession != null) {
				JSONObject replyMessage = new JSONObject();
				replyMessage.put("cmd", "removechat");
				replyMessage.put("chatroom_id", chatroom_id);
				
				TextMessage tmpMsg = new TextMessage(replyMessage.toString());
				receiverSession.sendMessage(tmpMsg);
			}
		}
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












