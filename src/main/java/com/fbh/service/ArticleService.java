package com.fbh.service;

import java.util.List;

import com.fbh.bean.Article;
import com.fbh.bean.Category;
import com.fbh.bean.Channel;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: ArticleService
 * @Description: 文章内容表Service层
 * @author:冯炳航
 * @date: 2020年1月7日 下午3:06:31
 */
public interface ArticleService {
	public PageInfo<Article> selectsByAdmin(Article a, Integer pageNum, Integer pageSize);

	public Article selectOne(Integer id);

	public List<Channel> selectsChannel();

	public List<Category> selectsCategory(Integer id);

	public void updateStatus(Article a);

	public void updateHits(Integer id, Integer hits);

	public void addArticle(Article art);

	public List<Article> selectsImage();
}
