package com.earth.clean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// JDBC API 이용
public class DBConnectionTest {
	
	public static void main(String[] args) throws SQLException {
		
		//데이터베이스 접속 정보 변수 선언
		String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
		//DB 사용자 정보
		String DB_USER = "postgres";
		String DB_PASSWORD = "0629";
		
		//1) 데이터베이스 연결을 얻음 (Connection 객체)
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		
		//2) Statement 객체 생성
		Statement stmt = conn.createStatement();

		// 시스템의 현재 날짜 시간 출력하는 쿼리
		String query = "select now();";
		
		//3) Statement 객체의 executeQuery()를 실행해서 ResultSet 객체를 생성
		ResultSet rs = stmt.executeQuery(query);
		
		// 실행 결과가 담긴 rs에서 한줄씩 읽어서 출력
		while(rs.next()) {
			// 읽어온 행의 첫번째 컬럼의 값을 String으로 읽어서 curDate에 저장
			String curDate = rs.getString(1);
			System.out.println(curDate);
		}
		
	}
	
}
