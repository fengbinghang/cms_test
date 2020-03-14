package com.fbh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.fbh.bean.Article;
import com.fbh.bean.Category;
import com.fbh.bean.Channel;
import com.fbh.bean.Content;
import com.fbh.bean.Slide;
import com.fbh.service.ArticleService;
import com.fbh.service.FriendlyLinkService;
import com.fbh.service.SlideService;
import com.fbh.util.HLUtils;
import com.github.pagehelper.PageInfo;

@Controller
public class AdminController {

	@Autowired
	private ArticleService service;

	@Autowired
	private SlideService ssi;

	@Autowired
	private FriendlyLinkService fls;

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate rt;

	// 存放栏目
	private List<Channel> channelList = null;

	// 存放幻灯片
	private List<Slide> slideList = null;

	// 最新图片文章
	private List<Article> list = null;

	// 存放最新图片
	private List<Content> cons = new ArrayList<Content>();

	// 存放最新文章
	private PageInfo<Article> newPage = null;

	// 存放分类
	private List<Category> caList = null;

	// 存放热门文章
	private PageInfo<Article> hotPage = null;

	// es模板
	@Autowired
	private ElasticsearchTemplate et;

	// 首页的入口
	@RequestMapping("index.do")
	public String index(Model m, Article a, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "5") Integer pageSize) throws InterruptedException {
		Thread chThread = new Thread() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				channelList = rt.opsForList().range("channelList", 0, -1);
				if (channelList == null || channelList.size() == 0) {
					channelList = service.selectsChannel();
					System.err.println("从mysql查出来的");
					rt.opsForList().leftPushAll("channelList", channelList.toArray());
					System.err.println("存到redis中了");
				} else {
					System.err.println("从redis查出来的");
				}
			}
		};
		// 启动线程
		chThread.start();
		// 加入当前线程
		chThread.join();
		m.addAttribute("channels", channelList);
		if (null != a.getCh() && a.getCh().getId() != 0) {
			Thread caThread = new Thread() {
				@Override
				public void run() {
					caList = service.selectsCategory(a.getCh().getId());
				}
			};
			caThread.start();
			caThread.join();
			m.addAttribute("categorys", caList);
			m.addAttribute("page", service.selectsByAdmin(a, pageNum, pageSize));
		} else {
			// 幻灯片线程
			Thread sThread = new Thread() {
				@Override
				public void run() {
					slideList = ssi.selectsSlide();
				}
			};
			sThread.start();
			sThread.join();
			m.addAttribute("slides", slideList);
			a.setHot(1);
			Thread hotThread = new Thread() {
				@SuppressWarnings("unchecked")
				@Override
				public void run() {
					List<Article> hotList = rt.opsForList().range("hotList", 0, -1);
					if (hotList == null || hotList.size() == 0) {
						hotPage = service.selectsByAdmin(a, pageNum, pageSize);
						System.err.println("从mysql查出来的");
						rt.opsForList().leftPushAll("hotList", hotPage.getList().toArray());
						System.err.println("存到redis中了");
						rt.expire("hotList", 5, TimeUnit.MINUTES);
					} else {
						newPage.setList(hotList);
						System.err.println("从redis查出来的");
					}
				}
			};
			hotThread.start();
			hotThread.join();
			m.addAttribute("page", hotPage);
		}
		list = service.selectsImage();
		cons.clear();
		// 最新图片线程
		Thread cThread = new Thread() {
			@Override
			public void run() {
				int i = 0;
				for (Article article : list) {
					List<Content> cs = JSON.parseArray(article.getContent(), Content.class);
					for (Content c : cs) {
						Content con = new Content();
						con.setId(article.getId());
						con.setName(c.getName());
						cons.add(con);
						i++;
						if (i > 9) {
							break;
						}
					}
					if (i > 9) {
						break;
					}
				}
			}
		};
		cThread.start();
		cThread.join();
		m.addAttribute("images", cons);
		m.addAttribute("ls", fls.getLinks(1));
		m.addAttribute("art", a);
		// 最新文章线程
		Thread newThread = new Thread() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				List<Article> newList = rt.opsForList().range("newList", 0, -1);
				if (newList == null || newList.size() == 0) {
					newPage = service.selectsByAdmin(new Article(), pageNum, 5);
					System.err.println("从mysql查出来的");
					rt.opsForList().leftPushAll("newList", newPage.getList().toArray());
					System.err.println("存到redis中了");
					rt.expire("newList", 5, TimeUnit.MINUTES);
				} else {
					newPage.setList(newList);
					System.err.println("从redis查出来的");
				}
			}
		};
		newThread.start();
		newThread.join();
		m.addAttribute("newPage", newPage);
		return "index/index";
	}

	// 后台管理系统的入口
	@RequestMapping("admin.do")
	public String admin() {
		return "admin/index";
	}

	// 个人中心的入口
	@RequestMapping("my.do")
	public String my() {
		return "my/index";
	}

	// 搜索框使用
	@RequestMapping("search.do")
	public String search(String key, @RequestParam(defaultValue = "1") Integer pageNum, Model m) throws Exception {
		Thread chThread = new Thread() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				channelList = rt.opsForList().range("channelList", 0, -1);
				if (channelList == null || channelList.size() == 0) {
					channelList = service.selectsChannel();
					System.err.println("从mysql查出来的");
					rt.opsForList().leftPushAll("channelList", channelList.toArray());
					System.err.println("存到redis中了");
				} else {
					System.err.println("从redis查出来的");
				}
			}
		};
		// 启动线程
		chThread.start();
		// 加入当前线程
		chThread.join();
		m.addAttribute("channels", channelList);
		list = service.selectsImage();
		cons.clear();
		// 最新图片线程
		Thread cThread = new Thread() {
			@Override
			public void run() {
				int i = 0;
				for (Article article : list) {
					List<Content> cs = JSON.parseArray(article.getContent(), Content.class);
					for (Content c : cs) {
						Content con = new Content();
						con.setId(article.getId());
						con.setName(c.getName());
						cons.add(con);
						i++;
						if (i > 9) {
							break;
						}
					}
					if (i > 9) {
						break;
					}
				}
			}
		};
		cThread.start();
		cThread.join();
		m.addAttribute("images", cons);
		m.addAttribute("ls", fls.getLinks(1));
		// 最新文章线程
		Thread newThread = new Thread() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				List<Article> newList = rt.opsForList().range("newList", 0, -1);
				if (newList == null || newList.size() == 0) {
					newPage = service.selectsByAdmin(new Article(), pageNum, 5);
					System.err.println("从mysql查出来的");
					rt.opsForList().leftPushAll("newList", newPage.getList().toArray());
					System.err.println("存到redis中了");
					rt.expire("newList", 5, TimeUnit.MINUTES);
				} else {
					newPage.setList(newList);
					System.err.println("从redis查出来的");
				}
			}
		};
		newThread.start();
		newThread.join();
		m.addAttribute("newPage", newPage);
		long start = System.currentTimeMillis();
		@SuppressWarnings("unchecked")
		PageInfo<Article> page = (PageInfo<Article>) HLUtils.findByHighLight(et, Article.class, pageNum, 5,
				new String[] { "title" }, "id", key);
		long end = System.currentTimeMillis();
		System.err.println("从es读取用了：" + (end - start) + "ms");
		m.addAttribute("page", page);
		m.addAttribute("key", key);
		return "index/index";
	}

}
