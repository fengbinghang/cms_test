package com.fbh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fbh.bean.FriendlyLink;
import com.fbh.service.FriendlyLinkService;

@RequestMapping("link")
@Controller
public class FriendlyLinkController {

	@Autowired
	private FriendlyLinkService fls;

	@RequestMapping("getLinks.do")
	public String getLinks(Model m, Integer pageNum) {
		m.addAttribute("page", fls.getLinks(pageNum));
		return "admin/link";
	}
	
	@RequestMapping("toAdd.do")
	public String toAdd() {
		return "admin/add";
	}

	@ResponseBody
	@RequestMapping("insertLink.do")
	public Boolean insertLink(FriendlyLink fl) {
		try {
			fls.insertLink(fl);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
