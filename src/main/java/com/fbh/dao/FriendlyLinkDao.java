package com.fbh.dao;

import java.util.List;

import com.fbh.bean.FriendlyLink;

public interface FriendlyLinkDao {
	List<FriendlyLink> getLinks();

	void insertLink(FriendlyLink fl);
}
