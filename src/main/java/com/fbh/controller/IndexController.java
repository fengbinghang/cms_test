package com.fbh.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fbh.bean.Article;
import com.fbh.bean.Comment;
import com.fbh.service.ArticleService;
import com.fbh.service.CommentService;

@Controller
public class IndexController {

	@Autowired
	private CommentService cs;

	@Autowired
	private ArticleService service;

	@RequestMapping("getOne.do")
	public String getOne(Model m, Integer id, Integer pageNum) {
		m.addAttribute("art", service.selectOne(id));
		m.addAttribute("page", cs.getById(id, pageNum));
		Article a = new Article();
		a.setHot(1);
		m.addAttribute("hots", service.selectsByAdmin(a, 1, 10));
		return "index/article";
	}

	@ResponseBody
	@RequestMapping("insertComment.do")
	public Boolean insertComment(Comment c) {
		try {
			c.setCreated(new Date());
			cs.insertComment(c);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
