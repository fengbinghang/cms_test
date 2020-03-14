package com.fbh.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.fbh.bean.Article;

public interface ArticleRepository extends ElasticsearchRepository<Article, Integer> {
	
	List<Article> findByTitle(String title);
	
}
