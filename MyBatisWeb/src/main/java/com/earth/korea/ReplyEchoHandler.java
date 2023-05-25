package com.earth.korea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
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

//		getPayload() 메서드는 메시지 객체에서 메시지의 실제 내용을 추출하는 역할을 합니다. 
//		웹소켓에서는 일반적으로 메시지 페이로드(payload)라고 불리는 실제 데이터를 포함하는 부분이 있습니다. 
//		예를 들어, 클라이언트가 서버로 메시지를 보낼 때, 메시지의 내용은 페이로드로 포함되어 전송됩니다. 
//		getPayload() 메서드를 사용하면 해당 페이로드를 추출하여 필요한 작업을 수행할 수 있습니다.
		//protocol: cmd, 댓글작성자, 게시글 작성자, bno (reply, user2, user1, bno)
		String msg = message.getPayload();
		String[] strs = msg.split(",");
		if(strs != null && strs.length == 5) {
			String cmd = strs[0]; // 응답 커
			String replyWriter = strs[1]; // 댓글 쓴 사람이 지금 접속한 사람
			String boardWriter = strs[2];
			String bno = strs[3];
			String comment = strs[4];
			
			WebSocketSession boardWriterSession = userSessions.get(boardWriter);
			if(cmd.equals("reply") && boardWriterSession != null) {
			    JSONObject replyMessage = new JSONObject();
			    replyMessage.put("cmd", "reply");
			    replyMessage.put("replyWriter", replyWriter);
			    replyMessage.put("bno", bno);
			    replyMessage.put("comment", comment);

			    TextMessage tmpMsg = new TextMessage(replyMessage.toString());
			    boardWriterSession.sendMessage(tmpMsg);
			}
		}
		else if(strs != null && strs.length == 4) {
			String cmd = strs[0]; // 응답 커
			String sender_id = strs[1]; // 댓글 쓴 사람이 지금 접속한 사람
			String receiverId = strs[2];
			String content = strs[3];
			
			WebSocketSession receiverSession = userSessions.get(receiverId);
			if(cmd.equals("chat") && receiverSession != null) {
				JSONObject chatMessage = new JSONObject();
				chatMessage.put("cmd", "chat");
				chatMessage.put("sender_id", sender_id);
				chatMessage.put("receiverId", receiverId);
				chatMessage.put("content", content);
				
				System.out.println("안녕하세요 실행중입니다.");
				
			    TextMessage tmpMsg = new TextMessage(chatMessage.toString());
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
		System.out.println("afterConnectionClosed:" + session + " : " + status);
	}
	
}
