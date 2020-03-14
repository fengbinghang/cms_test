package com.fbh.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.fbh.bean.Article;
import com.fbh.bean.Category;
import com.fbh.bean.Channel;
import com.fbh.bean.Content;
import com.fbh.enums.ContentType;
import com.fbh.service.ArticleService;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @ClassName: ArticleController
 * @Description: 文章内容表Controller层
 * @author:冯炳航
 * @date: 2020年1月7日 下午3:07:28
 */
@RequestMapping("article")
@Controller
public class ArticleController {

	@Autowired
	private KafkaTemplate<String, String> kt;

	@Autowired
	private ArticleService service;

	@RequestMapping("selects.do")
	public String selects(Model m, Article a, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		PageInfo<Article> page = service.selectsByAdmin(a, pageNum, pageSize);
		m.addAttribute("page", page);
		m.addAttribute("a", a);
		return "admin/article";
	}

	@RequestMapping("selectsMy.do")
	public String selectsMy(Model m, Article a, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		PageInfo<Article> page = service.selectsByAdmin(a, pageNum, pageSize);
		m.addAttribute("page", page);
		m.addAttribute("a", a);
		return "my/article";
	}

	@ResponseBody
	@RequestMapping("selectOne.do")
	public Article selectOne(Integer id) {
		return service.selectOne(id);
	}

	@ResponseBody
	@RequestMapping("selectsChannel.do")
	public List<Channel> selectsChannel() {
		return service.selectsChannel();
	}

	@ResponseBody
	@RequestMapping("selectsCategory.do")
	public List<Category> selectsCategory(Integer id) {
		return service.selectsCategory(id);
	}

	@ResponseBody
	@RequestMapping("updateStatus.do")
	public Boolean updateStatus(Article a) {
		try {
			service.updateStatus(a);
			kt.send("articles", "updateEs|"+JSON.toJSONString(a));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RequestMapping("toAdd.do")
	public String toAdd() {
		return "my/publish";
	}

	@RequestMapping("toAddImage.do")
	public String toAddImage() {
		return "my/addImage";
	}

	@ResponseBody
	@RequestMapping("addArticle.do")
	public Boolean addArticle(Article art, MultipartFile myFile) {
		try {
			if (myFile.getSize() > 0) {
				// 上传文件名
				String realName = myFile.getOriginalFilename();
				// 图片存放位置
				String path = "d:/pic/";
				// 上传文件随机前缀
				String startName = UUID.randomUUID().toString();
				// 上传文件后缀
				String endName = realName.substring(realName.lastIndexOf("."));
				// 创建上传的文件
				File f = new File(path + startName + endName);
				// 在指定位置创建文件
				myFile.transferTo(f);
				art.setPicture(startName + endName);
			}
			art.setCt(ContentType.HTML);
			service.addArticle(art);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@ResponseBody
	@RequestMapping("addArticleImage.do")
	public Boolean addArticleImage(Article art, MultipartFile titleImage, MultipartFile[] myFiles,
			String[] myMessages) {
		try {
			if (titleImage.getSize() > 0) {
				// 上传文件名
				String realName = titleImage.getOriginalFilename();
				// 图片存放位置
				String path = "d:/pic/";
				// 上传文件随机前缀
				String startName = UUID.randomUUID().toString();
				// 上传文件后缀
				String endName = realName.substring(realName.lastIndexOf("."));
				// 创建上传的文件
				File f = new File(path + startName + endName);
				// 在指定位置创建文件
				titleImage.transferTo(f);
				art.setPicture(startName + endName);
			}
			List<Content> cs = new ArrayList<Content>();
			int i = 0;
			for (MultipartFile myFile : myFiles) {
				if (myFile.getSize() > 0) {
					// 上传文件名
					String realName = myFile.getOriginalFilename();
					// 图片存放位置
					String path = "d:/pic/";
					// 上传文件随机前缀
					String startName = UUID.randomUUID().toString();
					// 上传文件后缀
					String endName = realName.substring(realName.lastIndexOf("."));
					// 创建上传的文件
					File f = new File(path + startName + endName);
					// 在指定位置创建文件
					myFile.transferTo(f);
					String pic = startName + endName;
					Content c = new Content(pic, myMessages[i]);
					cs.add(c);
				}
				i++;
			}
			art.setContent(JSON.toJSONString(cs));
			art.setCt(ContentType.IMAGE);
			service.addArticle(art);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
