package com.earth.clean;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

// Spring JDBC 이용
public class DBConnectionTest2 {
	
	public static void main(String[] args) throws SQLException {
		
		//데이터베이스 접속 정보 변수 선언
		String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
		//DB 사용자 정보
		String DB_USER = "postgres";
		String DB_PASSWORD = "0629";
		String DB_DRIVER = "org.postgresql.Driver";
		
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(DB_DRIVER);
		ds.setUrl(DB_URL);
		ds.setUsername(DB_USER);
		ds.setPassword(DB_PASSWORD);
		
		Connection conn = ds.getConnection();
		System.out.println(conn);
	}
	
}
