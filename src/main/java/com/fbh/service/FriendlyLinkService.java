package com.fbh.service;

import com.fbh.bean.FriendlyLink;
import com.github.pagehelper.PageInfo;

public interface FriendlyLinkService {
	PageInfo<FriendlyLink> getLinks(Integer pageNum);

	void insertLink(FriendlyLink fl);
}
