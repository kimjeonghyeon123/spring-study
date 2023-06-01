package com.earth.korea.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.earth.korea.domain.ChatRoomDTO;
import com.earth.korea.domain.ChattingDTO;
import com.earth.korea.service.ChattingService;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
@Controller
public class ChatController {

	private ChattingService chattingService;

	public ChatController(ChattingService chattingService) {
		this.chattingService = chattingService;
	}
	
	@GetMapping("/chat")
	public String chat(String otherId, HttpSession session, Model m) {
		String loginId = (String) session.getAttribute("id");
		if(otherId != null) {
			try {
				Integer chatroomId = chattingService.getChattingRoomId(loginId, otherId);
				if(chatroomId == null) {
					throw new Exception();
				}
				m.addAttribute("chatroomId", chatroomId);
			} catch (Exception e) {
				e.printStackTrace();
				m.addAttribute("otherId", otherId);
			}
		}
		return "chat";
	}
	
	@GetMapping("/hi")
	@ResponseBody
	public ResponseEntity<List<Map<String, String>>> hi() throws IOException {
		
        StringBuilder urlBuilder = new StringBuilder("https://api.vworld.kr/req/data"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=430A9486-5035-3F51-B4BF-93CB0A1F4533"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("domain","UTF-8") + "=" + URLEncoder.encode("http://localhost:8080", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("service","UTF-8") + "=" + URLEncoder.encode("data", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("version","UTF-8") + "=" + URLEncoder.encode("2.0", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("request","UTF-8") + "=" + URLEncoder.encode("getfeature", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("size","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("geometry","UTF-8") + "=" + URLEncoder.encode("false", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("attribute","UTF-8") + "=" + URLEncoder.encode("true", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("crs","UTF-8") + "=" + URLEncoder.encode("EPSG:3857", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("geomfilter","UTF-8") + "=" + URLEncoder.encode("BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("data","UTF-8") + "=" + URLEncoder.encode("LT_C_ADSIDO_INFO", "UTF-8"));
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray features = jsonObject.getJSONObject("response").getJSONObject("result")
                .getJSONObject("featureCollection").getJSONArray("features");

        List<Map<String, String>> resultList = new ArrayList<>();

        for (int i = 0; i < features.length(); i++) {
            JSONObject feature = features.getJSONObject(i);
            JSONObject properties = feature.getJSONObject("properties");
            String ctprvn_cd = properties.getString("ctprvn_cd");
            String ctp_kor_nm = properties.getString("ctp_kor_nm");

            Map<String, String> map = new HashMap<>();
            map.put("ctprvn_cd", ctprvn_cd);
            map.put("ctp_kor_nm", ctp_kor_nm);

            resultList.add(map);
        }
		return new ResponseEntity<List<Map<String, String>>>(resultList, HttpStatus.OK);
	}

	@GetMapping("/hi2")
	@ResponseBody
	public ResponseEntity<List<Map<String, String>>> hi2(String ctprvn_cd) throws IOException {
		System.out.println(ctprvn_cd);
		
        StringBuilder urlBuilder = new StringBuilder("https://api.vworld.kr/req/data"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=430A9486-5035-3F51-B4BF-93CB0A1F4533"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("domain","UTF-8") + "=" + URLEncoder.encode("http://localhost:8080", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("service","UTF-8") + "=" + URLEncoder.encode("data", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("version","UTF-8") + "=" + URLEncoder.encode("2.0", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("request","UTF-8") + "=" + URLEncoder.encode("getfeature", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("size","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("geometry","UTF-8") + "=" + URLEncoder.encode("false", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("attribute","UTF-8") + "=" + URLEncoder.encode("true", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("crs","UTF-8") + "=" + URLEncoder.encode("EPSG:3857", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("geomfilter","UTF-8") + "=" + URLEncoder.encode("BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("data","UTF-8") + "=" + URLEncoder.encode("LT_C_ADSIGG_INFO", "UTF-8"));
        String str = "sig_cd:like:"+ctprvn_cd;
        urlBuilder.append("&" + URLEncoder.encode("attrfilter","UTF-8") + "=" + URLEncoder.encode(str, "UTF-8"));
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray features = jsonObject.getJSONObject("response").getJSONObject("result")
                .getJSONObject("featureCollection").getJSONArray("features");

        List<Map<String, String>> resultList = new ArrayList<>();

        for (int i = 0; i < features.length(); i++) {
            JSONObject feature = features.getJSONObject(i);
            JSONObject properties = feature.getJSONObject("properties");
            String sig_cd = properties.getString("sig_cd");
            String sig_kor_nm = properties.getString("sig_kor_nm");

            Map<String, String> map = new HashMap<>();
            map.put("sig_cd", sig_cd);
            map.put("sig_kor_nm", sig_kor_nm);

            resultList.add(map);
        }
		return new ResponseEntity<List<Map<String, String>>>(resultList, HttpStatus.OK);
	}
	
	@GetMapping("/hi3")
	@ResponseBody
	public ResponseEntity<List<Map<String, String>>> hi3(String sig_cd) throws IOException {
		System.out.println(sig_cd);
		
        StringBuilder urlBuilder = new StringBuilder("https://api.vworld.kr/req/data"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") + "=430A9486-5035-3F51-B4BF-93CB0A1F4533"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("domain","UTF-8") + "=" + URLEncoder.encode("http://localhost:8080", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("service","UTF-8") + "=" + URLEncoder.encode("data", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("version","UTF-8") + "=" + URLEncoder.encode("2.0", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("request","UTF-8") + "=" + URLEncoder.encode("getfeature", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("format","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("size","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("geometry","UTF-8") + "=" + URLEncoder.encode("false", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("attribute","UTF-8") + "=" + URLEncoder.encode("true", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("crs","UTF-8") + "=" + URLEncoder.encode("EPSG:3857", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("geomfilter","UTF-8") + "=" + URLEncoder.encode("BOX(13663271.680031825,3894007.9689600193,14817776.555251127,4688953.0631258525)", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("data","UTF-8") + "=" + URLEncoder.encode("LT_C_ADEMD_INFO", "UTF-8"));
        String str = "emd_cd:like:"+sig_cd;
        urlBuilder.append("&" + URLEncoder.encode("attrfilter","UTF-8") + "=" + URLEncoder.encode(str, "UTF-8"));
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONArray features = jsonObject.getJSONObject("response").getJSONObject("result")
                .getJSONObject("featureCollection").getJSONArray("features");

        List<Map<String, String>> resultList = new ArrayList<>();

        for (int i = 0; i < features.length(); i++) {
            JSONObject feature = features.getJSONObject(i);
            JSONObject properties = feature.getJSONObject("properties");
            String emd_cd = properties.getString("emd_cd");
            String emd_kor_nm = properties.getString("emd_kor_nm");

            Map<String, String> map = new HashMap<>();
            map.put("emd_cd", emd_cd);
            map.put("emd_kor_nm", emd_kor_nm);

            resultList.add(map);
        }
		return new ResponseEntity<List<Map<String, String>>>(resultList, HttpStatus.OK);
	}
	
	@PostMapping("/chats")
	@ResponseBody
	public ResponseEntity<List<ChatRoomDTO>> chat(@RequestBody Map<String, String> requestData) {
		String login_id = requestData.get("loginId");
		List<ChatRoomDTO> list = null;
		
		try {
			list = chattingService.showChatRoomList(login_id);
			return new ResponseEntity<List<ChatRoomDTO>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ChatRoomDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/chattings")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> chatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroomId = (Integer) requestData.get("chatroomId");
		String loginId = (String) requestData.get("loginId");
		
		List<ChattingDTO> list = null;
		
		try {
			list = chattingService.readChatting(chatroomId, loginId);
			int chatroomCnt = chattingService.getChatRoomCnt(chatroomId);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			map.put("chatroomCnt", chatroomCnt);
			
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/sendchatting")
	@ResponseBody
	public ResponseEntity<Integer> sendchatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroomId = (Integer) requestData.get("chatroomId");
		String loginId = (String) requestData.get("loginId");
		String otherId = (String) requestData.get("otherId");
		String chat = (String) requestData.get("chat");
		
		if(chatroomId != null) {
			try {
				chattingService.sendChatting(chatroomId, loginId, chat);			
				return new ResponseEntity<Integer>(chatroomId, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
		}
		else {
			try {
				chatroomId = chattingService.makeChattingRoom(loginId, otherId, chat);
				return new ResponseEntity<Integer>(chatroomId, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	@PostMapping("/deletechatting")
	@ResponseBody
	public ResponseEntity<String> deletechatting(@RequestBody Map<String, Object> requestData) {
		Integer chatroomId = (Integer) requestData.get("chatroomId");
		String loginId = (String) requestData.get("loginId");
		
		try {
			chattingService.deleteChattingRoom(chatroomId, loginId);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/chattingcnt")
	@ResponseBody
	public ResponseEntity<Integer> chattingcnt(@RequestBody Map<String, Object> requestData) {
		Integer chatroomId = (Integer) requestData.get("chatroomId");
		
		try {
			int cnt = chattingService.getChatRoomCnt(chatroomId);
			return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
}
