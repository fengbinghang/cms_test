package com.fbh.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.fbh.bean.Article;
import com.fbh.dao.ArticleDao;
import com.fbh.dao.ArticleRepository;

//监听爬虫项目的文章的json串
public class ConsumerTest implements MessageListener<String, String> {

	@Autowired
	ArticleDao ad;

	@Autowired
	ArticleRepository ar;

	// 就是监听消息的方法
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		System.err.println("收到了消息");
		String jsonString = data.value();
		if (jsonString.startsWith("updateEs")) {
			String[] split = jsonString.split("\\|");
			Article a = JSON.parseObject(split[1], Article.class);
			a = ad.selectOne(a.getId());
			a.setUsername(a.getU().getUsername());
			a.setU(null);
			a.setCt(null);
			ar.save(a);
		} else if (jsonString.startsWith("hits")) {
			String[] split = jsonString.split("\\|");
			Article a = JSON.parseObject(split[1], Article.class);
			ad.updateHits(a.getId(), a.getHits() + 1);
			System.err.println("================kafka浏览量+1====================");
		} else {
			// 由于jsonString是一个json字符串，所有要把这个json字符串转成文章对象，存入mysql
			Article a = JSON.parseObject(jsonString, Article.class);
			// 注入ArticleDao然后就可以直接调用保存方法了
			ad.addArticle(a);
			System.err.println("保存了文章对象到mysql数据库...");
		}
	}

}
