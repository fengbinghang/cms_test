package com.fbh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fbh.bean.Article;
import com.fbh.service.ArticleService;
import com.fbh.service.FriendlyLinkService;
import com.fbh.service.SlideService;

@Controller
public class AdminController {

	@Autowired
	private ArticleService service;

	@Autowired
	private SlideService ssi;

	@Autowired
	private FriendlyLinkService fls;

	// 首页的入口
	@RequestMapping("index.do")
	public String index(Model m, Article a, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		m.addAttribute("channels", service.selectsChannel());
		if (null != a.getCh() && a.getCh().getId() != 0) {
			m.addAttribute("categorys", service.selectsCategory(a.getCh().getId()));
			m.addAttribute("page", service.selectsByAdmin(a, pageNum, pageSize));
		} else {
			m.addAttribute("slides", ssi.selectsSlide());
			a.setHot(1);
			m.addAttribute("page", service.selectsByAdmin(a, pageNum, pageSize));
		}
		m.addAttribute("ls", fls.getLinks(1));
		m.addAttribute("art", a);
		m.addAttribute("newPage", service.selectsByAdmin(new Article(), pageNum, 5));
		return "index/index";
	}

	// 后台管理系统的入口
	@RequestMapping("admin.do")
	public String admin() {
		return "admin/index";
	}

	// 个人中心的入口
	@RequestMapping("my.do")
	public String my() {
		return "my/index";
	}

}
