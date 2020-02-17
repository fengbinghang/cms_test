package com.fbh.dao;

import java.util.List;

import com.fbh.bean.Save;

public interface SaveDao {
	void addSave(Save s);

	List<Save> getSaves(Integer id);
	
	void deleteSave(Integer id);
}
