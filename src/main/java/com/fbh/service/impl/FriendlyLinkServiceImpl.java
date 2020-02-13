package com.fbh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbh.bean.FriendlyLink;
import com.fbh.dao.FriendlyLinkDao;
import com.fbh.service.FriendlyLinkService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;

@Service
public class FriendlyLinkServiceImpl implements FriendlyLinkService {

	@Autowired
	private FriendlyLinkDao fld;

	@Override
	public PageInfo<FriendlyLink> getLinks(Integer pageNum) {
		if (pageNum == null) {
			pageNum = 1;
		}
		PageMethod.startPage(pageNum, 10);
		return new PageInfo<FriendlyLink>(fld.getLinks());
	}

	@Override
	public void insertLink(FriendlyLink fl) {
		fld.insertLink(fl);
	}

}
