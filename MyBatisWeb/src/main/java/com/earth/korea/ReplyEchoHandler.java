package com.earth.korea;

import java.util.ArrayList;
import java.util.HashMap;
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
		System.out.println("afterConnectionEstablished:" + session);
		sessions.add(session);
		String senderId = getId(session);
		userSessions.put(senderId, session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("handleTextMessage:" + session + " : " + message);
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("======================================");
		//protocol: cmd, 댓글작성자, 게시글 작성자, bno (reply, user2, user1, bno)
		String msg = message.getPayload();
		String[] strs = msg.split(",");
		String cmd = strs[0];
		
		if(cmd.equals("sendchat")) {
			String receiverId = strs[1];
			Integer chatroomId = Integer.parseInt(strs[2]);
			WebSocketSession receiverSession = userSessions.get(receiverId);
			
			if(receiverSession != null) {
				JSONObject replyMessage = new JSONObject();
				replyMessage.put("cmd", "sendchat");
				replyMessage.put("chatroomId", chatroomId);
				
				TextMessage tmpMsg = new TextMessage(replyMessage.toString());
				receiverSession.sendMessage(tmpMsg);
			}
		}
		
		if(cmd.equals("readchat")) {
			String receiverId = strs[1];
			Integer chatroomId = Integer.parseInt(strs[2]);
			
			WebSocketSession receiverSession = userSessions.get(receiverId);
			if(receiverSession != null) {
				JSONObject replyMessage = new JSONObject();
				replyMessage.put("cmd", "readchat");
				replyMessage.put("chatroomId", chatroomId);
				
				TextMessage tmpMsg = new TextMessage(replyMessage.toString());
				receiverSession.sendMessage(tmpMsg);
			}
		}
	}

	//로그인한 사용자의 세션 아이디를 추출하기 위해 사용되며, 
	//해당 아이디를 이용하여 채팅 메시지를 보내는 클라이언트를 
	//식별하고 구분할 수 있습니다.
	private String getId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		String sessionId = (String) httpSession.get("id");
		
		return sessionId;
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
		String senderId = getId(session);
		userSessions.remove(senderId);
		System.out.println("afterConnectionClosed:" + session + " : " + status);
	}
	
}
