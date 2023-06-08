package com.earth.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Ignore;
import org.junit.Test;

public class JDBCTest {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Ignore
	@Test
	public void testConnection() {
		
		try(Connection con = 
				DriverManager.getConnection(
						"jdbc:postgresql://localhost:5432/test?serverTimezone=Asia/Seoul",
						"postgres",
						"0629")){
			System.out.println(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
}
