package com.fbh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbh.bean.Slide;
import com.fbh.dao.SlideDao;
import com.fbh.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService {

	@Autowired
	private SlideDao dao;

	@Override
	public List<Slide> selectsSlide() {
		return dao.selectsSlide();
	}

}
