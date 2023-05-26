package com.earth.korea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ReplyEchoHandler extends TextWebSocketHandler {
	
	List<WebSocketSession> sessions = new ArrayList<>();
	Map<String, WebSocketSession> userSessions = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablished:" + session);
		sessions.add(session);
		String senderId = getId(session);
		userSessions.put(senderId, session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handleTextMessage:" + session + " : " + message);

		//protocol: cmd, 댓글작성자, 게시글 작성자, bno (reply, user2, user1, bno)
		String msg = message.getPayload();
		String[] strs = msg.split(",");
		if(strs != null && strs.length == 4) {
			String cmd = strs[0]; // 응답 커
			String replyWriter = strs[1]; // 댓글 쓴 사람이 지금 접속한 사람
			String boardWriter = strs[2];
			String bno = strs[3];
				
			WebSocketSession boardWriterSession = userSessions.get(boardWriter);
			if(cmd.equals("reply") && boardWriterSession != null) {
				TextMessage tmpMsg = new TextMessage(replyWriter + "님이 " + bno + "번 게시글에 댓글을 달았습니다.");
				boardWriterSession.sendMessage(tmpMsg);
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
		System.out.println("afterConnectionClosed:" + session + " : " + status);
	}
	
}
