package com.akua.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.akua.model.AppUser;

@Component
public class AppUserMapper implements RowMapper<AppUser> {

	public static final String BASE_SQL = "Select u.User_Id, u.User_Name, u.Encryted_Password From App_User u ";

	@Override
	public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		AppUser u = new AppUser();

		u.setUserId(rs.getLong("User_Id"));
		u.setUserName(rs.getString("User_Name"));
		u.setEncrytedPassword(rs.getString("Encryted_Password"));

		return u;
	}
}