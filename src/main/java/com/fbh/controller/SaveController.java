package com.fbh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fbh.bean.Save;
import com.fbh.exception.CMSRuntimeException;
import com.fbh.service.SaveService;

@RequestMapping("save")
@Controller
public class SaveController {

	@Autowired
	private SaveService ss;

	@RequestMapping("getSaves.do")
	public String getSaves(Model m, Integer pageNum, Integer id) {
		m.addAttribute("page", ss.getSaves(id, pageNum));
		return "my/saves";
	}

	@ResponseBody
	@RequestMapping("addSave.do")
	public Boolean addSave(Save s) {
		try {
			s.setUrl("http://localhost:8090/fbh_cms/getOne.do?id="+s.getUrl());
			ss.addSave(s);
			return true;
		} catch (CMSRuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	@ResponseBody
	@RequestMapping("deleteSave.do")
	public Boolean deleteSave(Integer id) {
		try {
			ss.deleteSave(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
