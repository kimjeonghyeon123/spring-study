package com.earth.tdd;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DMLTest {
	
	@Autowired
	DataSource ds;
	
	@Test
	public void springJdbcConnTest() throws SQLException {
		Connection conn = ds.getConnection();
		System.out.println("conn = " + conn);
		assertTrue(conn != null);	//괄호 안의 조건식이 true면 테스트 성공, 아니면 실패
	}
	
	@Test
	public void insertUserTest() throws SQLException {
		User user = new User("earth", "0629", "earth", "earth@gmail.com", new Date(), "youtube", new Date());
		
		deleteAll();
		int rowCnt = insertUser(user);
		
		assertTrue(rowCnt == 1);
	}

	private int insertUser(User user) throws SQLException {
		Connection conn = ds.getConnection();
		String sql = "INSERT INTO t_user VALUES(?, ?, ?, ?, ?, ?, now())";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		/*
		 * 인파라미터 설정 시 데이터 타입에 맞는 set메서드를 사용함
		 */
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		pstmt.setDate  (5, new java.sql.Date(user.getBirth().getTime()));
		pstmt.setString(6, user.getSns());
		
		int rowCnt = pstmt.executeUpdate();	//insert, update, delete
		
		return rowCnt;
	}

	private void deleteAll() throws SQLException {
		Connection conn = ds.getConnection();
		String sql = "delete from t_user";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.executeUpdate();
	}
	
	@Test
	public void selectUserTest() throws SQLException {
		deleteAll();
		User user = new User("earth", "0629", "earth", "earth@gmail.com", new Date(), "fb", new Date());
		int rowCnt = insertUser(user);
		
		User user2 = selectUser("earth");
		assertTrue(user.getId().equals(user2.getId()));
	}

	private User selectUser(String id) throws SQLException {
		Connection conn = ds.getConnection();
		String sql = "select * from t_user where id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			User user = new User();
			user.setId(rs.getString(1));
			user.setPwd(rs.getString(2));
			user.setName(rs.getString(3));
			user.setEmail(rs.getString(4));
			user.setBirth(new Date(rs.getDate(5).getTime()));
			user.setSns(rs.getString(6));
			user.setReg_date(new Date(rs.getDate(7).getTime()));
			return user;
		}
		return null;
	}	
	
	@Test
	public void deleteUserTest() throws SQLException {
		deleteAll();
		
		int rowCnt = deleteUser("earth");
		assertTrue(rowCnt == 0);
		
		User user = new User("earth", "0629", "earth", "earth@gmail.com", new Date(), "fb", new Date());
		rowCnt = insertUser(user);
		assertTrue(rowCnt == 1);
		
		rowCnt = deleteUser("earth");
		assertTrue(selectUser(user.getId()) == null);
	}

	private int deleteUser(String id) throws SQLException {
		Connection conn = ds.getConnection();
		
		String sql = "delete from t_user where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		return pstmt.executeUpdate();
	}
	
	@Test
	public void updateUserTest() throws SQLException {
		deleteAll();
		
		User user = new User("earth", "0629", "earth", "earth@gmail.com", new Date(), "fb", new Date());
		int rowCnt = insertUser(user);
		assertTrue(rowCnt == 1);
		
		user.setPwd("0630");
		user.setName("earth1");
		user.setEmail("earth1@gmail.com");
		rowCnt = updateUser(user);
		assertTrue(rowCnt == 1);
	}

	private int updateUser(User user) throws SQLException {
		Connection conn = ds.getConnection();
		
		String sql = "update t_user set pwd = ?, name = ?, email = ?, birth = ?, sns = ?, reg_date = ? where id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, user.getPwd());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getEmail());
		pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
		pstmt.setString(5, user.getSns());
		pstmt.setDate(6, new java.sql.Date(user.getReg_date().getTime()));
		pstmt.setString(7, user.getId());
		
		return pstmt.executeUpdate();
	}
}


















