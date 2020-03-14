package com.fbh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fbh.bean.Article;
import com.fbh.bean.Category;
import com.fbh.bean.Channel;

/**
 * 
 * 
 * @ClassName: ArticleDao
 * @Description: 文章内容表Dao层
 * @author:冯炳航
 * @date: 2020年1月7日 下午3:05:42
 */
public interface ArticleDao {

	public List<Article> selectsByAdmin(Article a);

	public Article selectOne(Integer id);

	public List<Channel> selectsChannel();

	public List<Category> selectsCategory(Integer id);

	public void updateStatus(Article a);

	public void updateHits(@Param("id")Integer id,@Param("hits") Integer hits);

	public void addArticle(Article art);

	public List<Article> selectsImage();

}
