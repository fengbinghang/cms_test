package com.fbh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbh.bean.Comment;
import com.fbh.dao.CommentDao;
import com.fbh.service.CommentService;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao cd;

	@Override
	public void insertComment(Comment c) {
		cd.insertComment(c);
	}

	@Override
	public PageInfo<Comment> getById(Integer id, Integer pageNum) {
		if (pageNum == null) {
			pageNum = 1;
		}
		PageMethod.startPage(pageNum, 10);
		return new PageInfo<Comment>(cd.getById(id));
	}

}
