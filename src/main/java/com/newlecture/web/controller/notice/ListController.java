package com.newlecture.web.controller.notice;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;
import com.newlecture.web.service.jdbc.JDBCNoticeService;


public class ListController implements Controller {
	
	@Autowired
	private NoticeService noticeService;

	//어노테이션으로 쓸모없어짐.
//	public void setNoticeService(NoticeService noticeService) {
//		this.noticeService = noticeService;
//	}

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ModelAndView mv = new ModelAndView("notice.list");
		//mv.addObject("data", "Hello Spring MVC~");
		//mv.setViewName("/WEB-INF/view/index.jsp");
		List<Notice> list = noticeService.getList(1, "TITLE", "");
		mv.addObject("list", list);
		
		
		
		return mv;
	
	}
}
