package com.fbh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbh.bean.Article;
import com.fbh.bean.Category;
import com.fbh.bean.Channel;
import com.fbh.dao.ArticleDao;
import com.fbh.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: ArticleServiceImpl
 * @Description: 文章内容表Service层实现了
 * @author:冯炳航
 * @date: 2020年1月7日 下午3:06:47
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao dao;

	@SuppressWarnings("static-access")
	@Override
	public PageInfo<Article> selectsByAdmin(Article a, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Article>(dao.selectsByAdmin(a));
	}

	@Override
	public Article selectOne(Integer id) {
		return dao.selectOne(id);
	}

	@Override
	public List<Channel> selectsChannel() {
		return dao.selectsChannel();
	}

	@Override
	public List<Category> selectsCategory(Integer id) {
		return dao.selectsCategory(id);
	}

	@Override
	public void updateStatus(Article a) {
		dao.updateStatus(a);
	}

	@Override
	public void addArticle(Article art) {
		dao.addArticle(art);
	}

	@Override
	public List<Article> selectsImage() {
		return dao.selectsImage();
	}

}
