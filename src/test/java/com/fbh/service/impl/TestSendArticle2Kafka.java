package com.fbh.service.impl;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.fbh.bean.Article;
import com.fbh.bean.Category;
import com.fbh.bean.Channel;
import com.fbh.utils.StreamUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:producer.xml")
public class TestSendArticle2Kafka {

	@Autowired
	KafkaTemplate<String, String> kt;

	@Test
	public void testSend() {
		// 1.从D:\搜狗新闻下的所有文件
		File file = new File("D:\\搜狐新闻");
		// 2.遍历目录下的所有文件
		File[] files = file.listFiles();
		for (File f : files) {
			Article a = new Article();
			// 3.拿到文件的名称，作为Article对象的title
			String title = f.getName().replace(".txt", "");
			a.setTitle(title);
			// 4.拿到文件内容作为文章的content来封装
			String content = StreamUtil.readTextFile(f);
			a.setContent(content);
			// 5.补全文章对象的其他属性
			Category ca = new Category();
			ca.setId(30);
			a.setCa(ca);
			Channel ch = new Channel();
			ch.setId(9);
			a.setCh(ch);
			// 6.把这个文章对象，转成json串(提高效率)
			String js = JSON.toJSONString(a);
			// 7.发送到kafka
			kt.send("articles", js);
		}
	}

}
