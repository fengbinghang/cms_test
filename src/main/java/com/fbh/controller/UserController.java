package com.fbh.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fbh.bean.User;
import com.fbh.service.UserService;
import com.fbh.vo.UserVO;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: UserController
 * @Description: 用户表Controller层
 * @author:冯炳航
 * @date: 2020年1月7日 下午3:07:13
 */
@RequestMapping("user")
@Controller
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping("toLogin.do")
	public String toLogin(Model m, User u, HttpSession session) {
		m.addAttribute("error", session.getAttribute("error"));
		session.removeAttribute("error");
		m.addAttribute("u", u);
		return "index/login";
	}

	@RequestMapping("toRegister.do")
	public String toRegister(Model m, User u) {
		m.addAttribute("u", u);
		return "index/register";
	}

	@RequestMapping("login.do")
	public String login(Model m, @Validated @ModelAttribute("u") User u, BindingResult br, HttpSession session) {
		m.addAttribute("u", u);
		if (br.hasErrors()) {
			return "index/login";
		}
		User us = service.getOne(null, u.getUsername(), null);
		if (us != null) {
			if (DigestUtils.md5Hex(u.getPassword()).equals(us.getPassword())) {
				if (us.getLocked() != 1) {
					session.setAttribute("id", us.getId());
					session.setAttribute("username", u.getUsername());
					if (us.getRole().equals("0")) {
						m.addAttribute("loginMsg", "用户登录成功");
					} else {
						session.setAttribute("role", 1);
						m.addAttribute("loginMsg", "管理员登录成功");
					}
				} else {
					m.addAttribute("loginMsg", "账号已禁用");
				}
			} else {
				m.addAttribute("loginMsg", "密码错误");
			}
		} else {
			m.addAttribute("loginMsg", "用户名错误");
		}
		return "index/login";
	}

	@ResponseBody
	@RequestMapping("isUnique.do")
	public Boolean isUnique(String username) {
		return service.getOne(null, username, null) == null;
	}

	@RequestMapping("register.do")
	public String register(Model m, @Validated @ModelAttribute("u") UserVO u, BindingResult br) {
		m.addAttribute("u", u);
		if (br.hasErrors()) {
			System.out.println(br);
			return "index/register";
		}
		u.setPassword(DigestUtils.md5Hex(u.getPassword()));
		Boolean flag = service.register(u);
		if (flag) {
			m.addAttribute("registerMsg", "注册成功,请登录");
		} else {
			m.addAttribute("registerMsg", "注册失败");
		}
		return "index/register";
	}

	@RequestMapping("getUserList.do")
	public String getUserList(Model m, String username, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		PageInfo<User> info = service.getUserList(username, pageNum, pageSize);
		m.addAttribute("page", info);
		m.addAttribute("username", username);
		return "admin/user";
	}

	@ResponseBody
	@RequestMapping("updateLocked.do")
	public Boolean updateLocked(User u) {
		return service.updateLocked(u);
	}

	@RequestMapping("out.do")
	public String out(HttpSession session) {
		session.removeAttribute("id");
		session.removeAttribute("username");
		session.removeAttribute("role");
		return "redirect:../index.do";
	}
}