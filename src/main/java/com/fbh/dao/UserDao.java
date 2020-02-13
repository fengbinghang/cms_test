package com.fbh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fbh.bean.User;
import com.fbh.vo.UserVO;

/**
 * 
 * @ClassName: UserDao
 * @Description: 用户表Dao层
 * @author:冯炳航
 * @date: 2020年1月7日 下午3:05:28
 */
public interface UserDao {
	/**
	 * 
	 * @Title: getOne
	 * @Description: 查询用户表单条数据(用作登录验证|注册唯一验证)
	 * @param username
	 * @param password
	 * @return
	 * @return: User
	 */
	User getOne(@Param("id") Integer id, @Param("username") String username, @Param("password") String password);

	/**
	 * 
	 * @Title: getUserList
	 * @Description: 查询用户列表+模糊查询
	 * @param username
	 * @return
	 * @return: List<User>
	 */
	List<User> getUserList(@Param("username") String username);

	/**
	 * 
	 * @Title: updateLocked
	 * @Description: 用户是否禁用
	 * @param u
	 * @return
	 * @return: int
	 */
	int updateLocked(User u);

	/**
	 * 
	 * @Title: register
	 * @Description: 注册
	 * @param u
	 * @return
	 * @return: int
	 */
	int register(UserVO u);
}
