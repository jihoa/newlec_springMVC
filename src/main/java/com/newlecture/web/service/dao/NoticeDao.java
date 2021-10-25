package com.newlecture.web.service.dao;

import java.util.List;

import com.newlecture.web.entity.Notice;



public interface NoticeDao {
	//@Select("select * from notice")
	List<Notice> getViewList(int offset, int size, String field, String query, boolean pub);
	List<Notice> getPubViewList(int offset, int size, String field, String query);
	
	int getCount(String field, String query);

	int deleteAll(int[] ids);

	int updatePubAll(int[] pubIds, boolean pub);

	int updatePubAll(int[] pubIds, int[] closeIds);
	
	int insert(Notice notice);
	/**
	 * @return
	 */
	List<Notice> getList(int page, String field, String query);
	/**
	 * @param id
	 * @return
	 */
	List<Notice> getPubViewListDetail(int id);


	//int deleteAll(int[] ids);

}
