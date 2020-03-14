package com.fbh.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fbh.bean.Article;
import com.fbh.bean.Comment;
import com.fbh.bean.Content;
import com.fbh.service.ArticleService;
import com.fbh.service.CommentService;
import com.fbh.service.FriendlyLinkService;

@Controller
public class IndexController {

	@Autowired
	private CommentService cs;

	@Autowired
	private FriendlyLinkService fls;

	@Autowired
	private ArticleService service;

	// @SuppressWarnings("rawtypes")
	// @Autowired
	// private RedisTemplate rt;

	// @Autowired
	// private ThreadPoolTaskExecutor tpte;

	@SuppressWarnings("rawtypes")
	@Autowired
	private KafkaTemplate kt;

	@SuppressWarnings("unchecked")
	@RequestMapping("getOne.do")
	public String getOne(Model m, Integer id, Integer pageNum, HttpServletRequest request) {
		Article art = service.selectOne(id);
		m.addAttribute("art", art);
		m.addAttribute("page", cs.getById(id, pageNum));
		Article a = new Article();
		a.setHot(1);
		m.addAttribute("hots", service.selectsByAdmin(a, 1, 10));
		m.addAttribute("fs", fls.getLinks(1));
		if (art.getCt().getOrdinal() == 1) {
			art.setCs(JSON.parseArray(art.getContent(), Content.class));
		}
		// redis实现浏览量增加
		/*
		 * String ip = request.getRemoteAddr(); String str = "Hits" + id + ip; if
		 * (!rt.hasKey(str)) { tpte.execute(new Runnable() {
		 * 
		 * @Override public void run() { service.updateHits(id, art.getHits() + 1);
		 * rt.opsForValue().set(str, "", 5, TimeUnit.MINUTES);
		 * System.err.println("浏览量+1============================"); }
		 * 
		 * }); }
		 */
		// kafka实现浏览量增加
		kt.send("articles", "hits|" + JSON.toJSONString(art));
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
