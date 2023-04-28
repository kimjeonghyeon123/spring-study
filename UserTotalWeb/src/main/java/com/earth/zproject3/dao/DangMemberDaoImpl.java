package com.earth.zproject3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.earth.zproject3.domain.DangMember;

@Repository
public class DangMemberDaoImpl implements DangMemberDao {

	@Autowired
	DataSource ds;
	final int FAIL = 0;
	
	@Override
	public DangMember selectDangMember(String email) {
		Connection conn = null;
		String sql = "SELECT * FROM dang_member WHERE email = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DangMember dangMember = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dangMember = new DangMember();
				dangMember.setEmail(rs.getString(1));
				dangMember.setPwd(rs.getString(2));
				dangMember.setName(rs.getString(3));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs, pstmt, conn);
		}
		
		return dangMember;
	}

	@Override
	public int deleteDangMember(String email) {
		Connection conn = null;
		String sql = "DELETE FROM dang_member WHERE email = ?";
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			return pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return FAIL;
		}
		finally {
			close(pstmt, conn);
		}
	}

	@Override
	public int insertDangMember(DangMember dangMember) {
		Connection conn = null;
		String sql = "INSERT INTO dang_member VALUES(?, ?, ?)";
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dangMember.getEmail());
			pstmt.setString(2, dangMember.getPwd());
			pstmt.setString(3, dangMember.getName());
			return pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return FAIL;
		}
		finally {
			close(pstmt, conn);
		}
	}

	@Override
	public int updateDangMember(DangMember dangMember) {
		Connection conn = null;
		String sql = "UPDATE dang_member SET pwd = ?, name = ? WHERE email = ?";
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dangMember.getPwd());
			pstmt.setString(2, dangMember.getName());
			pstmt.setString(3, dangMember.getEmail());
			return pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return FAIL;
		}
		finally {
			close(pstmt, conn);
		}
	}

	@Override
	public void deleteAll() {
		Connection conn = null;
		String sql = "DELETE FROM dang_member";
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(pstmt, conn);
		}
	}
	
	private void close(AutoCloseable...closeables) {
		for(AutoCloseable autoCloseable : closeables) {
			if(autoCloseable != null) try { autoCloseable.close(); } catch (Exception e) { e.printStackTrace(); }
		}
	}
}
