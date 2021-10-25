/**
 * 
 */
package com.newlecture.web.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.dao.NoticeDao;

/**
 * @author jiho-kim
 *
 * 신규개발
 */
@Service
public class NoticeServiceImp implements NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;
	/* (non-Javadoc)
	 * @see com.newlecture.web.service.NoticeService#getList(int, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Notice> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		
		System.out.println("kimjiho_impl : "+field);
		System.out.println("kimjiho_impl : "+query);

		
		return noticeDao.getList(page, field, query);
	}

	/* (non-Javadoc)
	 * @see com.newlecture.web.service.NoticeService#getCount()
	 */
	@Override
	public int getCount() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.newlecture.web.service.NoticeService#insert(com.newlecture.web.entity.Notice)
	 */
	@Override
	public int insert(Notice notice) throws SQLException, ClassNotFoundException {

		return noticeDao.insert(notice);
	}

	/* (non-Javadoc)
	 * @see com.newlecture.web.service.NoticeService#update(com.newlecture.web.entity.Notice)
	 */
	@Override
	public int update(Notice notice) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.newlecture.web.service.NoticeService#delete(int)
	 */
	@Override
	public int delete(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.newlecture.web.service.NoticeService#updatePubAll(int[], int[])
	 */
	@Override
	public int updatePubAll(int[] pubIds, int[] closeIds) {
		int result = 0;
		result += noticeDao.updatePubAll(closeIds, false);
		result += noticeDao.updatePubAll(pubIds, true);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.newlecture.web.service.NoticeService#deleteAll(int[])
	 */
	@Override
	public int deleteAll(int[] ids) {
		return noticeDao.deleteAll(ids);
	}

	/* (non-Javadoc)
	 * @see com.newlecture.web.service.NoticeService#getPubViewListDetail(int)
	 */
	@Override
	public List<Notice> getPubViewListDetail(int id) {
		return noticeDao.getPubViewListDetail(id);
	}

}
