package com.fbh.service.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fbh.bean.Save;
import com.fbh.exception.CMSRuntimeException;
import com.fbh.service.SaveService;
import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SaveServiceImplTest {

	@Autowired
	private SaveService ss;

	/**
	 * 
	 * @Title: testAdd
	 * @Description: 收藏添加是否正常
	 * @return: void
	 */
	@Test
	public void testAdd() {
		Save s = new Save();
		s.setText("123");
		s.setUrl("http://localhost:8090/cxd/efw/asd.do");
		s.setUid(8);
		try {
			ss.addSave(s);
			System.out.println("收藏成功");
		} catch (CMSRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("收藏失败");
		}
	}

	/**
	 * 
	 * @Title: testSelect
	 * @Description: 测试查询及分页是否正常
	 * @return: void
	 */
	@Test
	public void testSelect() {
		PageInfo<Save> info = ss.getSaves(8, 1);
		List<Save> list = info.getList();
		for (Save save : list) {
			System.out.println(save);
		}
	}
	
	/**
	 * 
	 * @Title: testDelete 
	 * @Description: 收藏删除功能是否正常
	 * @return: void
	 */
	@Test
	public void testDelete() {
		ss.deleteSave(8);
	}

}
