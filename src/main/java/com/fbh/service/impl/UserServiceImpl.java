package com.fbh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbh.bean.User;
import com.fbh.dao.UserDao;
import com.fbh.service.UserService;
import com.fbh.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: UserServiceImpl
 * @Description: 用户表Service层实现类
 * @author:冯炳航
 * @date: 2020年1月7日 下午3:06:18
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public User getOne(Integer id, String username, String password) {
		return dao.getOne(id, username, password);
	}

	@SuppressWarnings("static-access")
	@Override
	public PageInfo<User> getUserList(String username, Integer pageNum, Integer pageSize) {
		if (pageNum == null) {
			pageNum = 1;
		}
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<User>(dao.getUserList(username));
	}

	@Override
	public Boolean updateLocked(User u) {
		return dao.updateLocked(u) > 0;
	}

	@Override
	public Boolean register(UserVO u) {
		return dao.register(u)>0;
	}

}
