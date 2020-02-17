package com.fbh.service;

import com.fbh.bean.Save;
import com.fbh.exception.CMSRuntimeException;
import com.github.pagehelper.PageInfo;

public interface SaveService {
	void addSave(Save s) throws CMSRuntimeException;

	PageInfo<Save> getSaves(Integer id, Integer pageNum);

	void deleteSave(Integer id);
}
