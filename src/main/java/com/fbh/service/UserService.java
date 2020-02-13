package com.fbh.service;

import com.fbh.bean.User;
import com.fbh.vo.UserVO;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: UserService
 * @Description: 用户表Service层
 * @author:冯炳航
 * @date: 2020年1月7日 下午3:06:02
 */
public interface UserService {
	/**
	 * 
	 * @Title: getOne
	 * @Description: 查询用户表单条数据(用作登录验证|注册唯一验证)
	 * @param username
	 * @param password
	 * @return
	 * @return: User
	 */
	User getOne(Integer id, String username, String password);

	/**
	 * 
	 * @Title: getUserList
	 * @Description: 查询用户列表+模糊查询 进行分页操作(当前页数，每页显示条数)
	 * @param username
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @return: PageInfo<User>
	 */
	PageInfo<User> getUserList(String username, Integer pageNum, Integer pageSize);

	/**
	 * 
	 * @Title: updateLocked
	 * @Description: 用户是否禁用
	 * @param u
	 * @return
	 * @return: Boolean
	 */
	Boolean updateLocked(User u);

	/**
	 * 
	 * @Title: register
	 * @Description: 注册
	 * @param u
	 * @return
	 * @return: Boolean
	 */
	Boolean register(UserVO u);
}
