package com.fbh.service;

import com.fbh.bean.Comment;
import com.github.pagehelper.PageInfo;

public interface CommentService {
	void insertComment(Comment c);

	PageInfo<Comment> getById(Integer id,Integer pageNum);

}
