package com.fbh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fbh.bean.Save;
import com.fbh.dao.SaveDao;
import com.fbh.exception.CMSRuntimeException;
import com.fbh.service.SaveService;
import com.fbh.utils.StringUtil;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;

@Transactional
@Service
public class SaveServiceImpl implements SaveService {

	@Autowired
	private SaveDao sd;

	@Override
	public void addSave(Save s) throws CMSRuntimeException {
		if (StringUtil.isHttpUrl(s.getUrl())) {
			sd.addSave(s);
		} else {
			throw new CMSRuntimeException();
		}
	}

	@Override
	public PageInfo<Save> getSaves(Integer id, Integer pageNum) {
		if (pageNum == null) {
			pageNum = 1;
		}
		PageMethod.startPage(pageNum, 5);
		return new PageInfo<Save>(sd.getSaves(id));
	}

	@Override
	public void deleteSave(Integer id) {
		sd.deleteSave(id);
	}

}
