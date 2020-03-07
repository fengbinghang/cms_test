package com.fbh.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.fbh.bean.Article;
import com.fbh.dao.ArticleDao;

//监听爬虫项目的文章的json串
public class ConsumerTest implements MessageListener<String, String> {

	@Autowired
	ArticleDao ad;

	// 就是监听消息的方法
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		System.err.println("收到了消息");
		String jsonString = data.value();
		// 由于jsonString是一个json字符串，所有要把这个json字符串转成文章对象，存入mysql
		Article a = JSON.parseObject(jsonString, Article.class);
		// 注入ArticleDao然后就可以直接调用保存方法了
		ad.addArticle(a);
		System.err.println("保存了文章对象到mysql数据库...");
	}

}
