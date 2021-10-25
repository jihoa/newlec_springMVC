/**
 * 
 */
package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;

/**
 * @author jiho-kim
 *
 * 신규개발
 */
public interface NoticeService {
	List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException;
	int getCount() throws ClassNotFoundException, SQLException;
	int insert(Notice notice) throws SQLException, ClassNotFoundException;
	int update(Notice notice) throws SQLException, ClassNotFoundException;
	int delete(int id) throws ClassNotFoundException, SQLException;
	/**
	 * @param openids1
	 * @param closeids1
	 * @return
	 */
	int updatePubAll(int[] openids1, int[] closeids1);
	/**
	 * @param ids1
	 * @return
	 */
	int deleteAll(int[] ids1);
	/**
	 * @param id
	 * @return
	 */
	List<Notice> getPubViewListDetail(int id);
}
