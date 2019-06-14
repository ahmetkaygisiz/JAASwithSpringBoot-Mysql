package com.akua.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.akua.mapper.AppUserMapper;
import com.akua.model.AppUser;

@Repository
@Transactional
public class UserDAO extends JdbcDaoSupport {

	@Autowired
	public UserDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public AppUser getUser(String userName) {

		String sql = AppUserMapper.BASE_SQL + " where u.User_Name = ? ";

		Object[] params = new Object[] { userName };
		AppUserMapper mapper = new AppUserMapper();
		try {
			AppUser userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}