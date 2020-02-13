package com.fbh.dao;

import java.util.List;

import com.fbh.bean.Comment;

public interface CommentDao {
	void insertComment(Comment c);
	
	List<Comment> getById(Integer id);
	
}
