package com.earth.heart.dao;

import java.sql.SQLException;

import com.earth.heart.domain.User;

public interface UserDao {
	User selectUser(String id);
	int deleteUser(String id);
	int updateUser(User user);
	int insertUser(User user);
	void deleteAll() throws SQLException;
}
