/**
 * 
 */
package com.newlecture.web.controller.admin.board;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	//ServletContext는 IOC에 자동으로 담겨있음.
	@Autowired
	private  ServletContext ctx;
	
	@GetMapping("list")
	public String list(@RequestParam(name="p", defaultValue="1") int page, Model model) throws ClassNotFoundException, SQLException {
	//public String list() throws ClassNotFoundException, SQLException {
		
		List<Notice> list = noticeService.getList(1, "TITLE", "");
		
		model.addAttribute("list",list);
		
		return "/admin/board/notice/list";
	}
	

	@PostMapping("list")
	public String list(HttpServletRequest request, 
			//@RequestParam(value = "open-id") String[] openIds,
			//@RequestParam(value = "del-id") String[] delIds,
			@RequestParam(value = "cmd") String cmd,
			@RequestParam(value = "ids") String ids_) {

		String[] openIds = request.getParameterValues("open-id");
		String[] delIds = request.getParameterValues("del-id");
		//String cmd = request.getParameter("cmd");
		//String ids_ = request.getParameter("ids");
		String[] ids = ids_.trim().split(" ");

		
		
		
		
		switch(cmd) {
		
		
		
			case "일괄공개":
				int[] openids1 = new int[openIds.length];
				for(int i=0; i<openIds.length; i++)
					openids1[i] = Integer.parseInt(openIds[i]);

				int[] closeids1 = new int[ids.length];
				for(int i=0; i<ids.length; i++)
					closeids1[i] = Integer.parseInt(ids[i]);
				
				for(int i=0; i<openIds.length; i++)
					System.out.println("jiho_openID::::::::"+openids1[i]);
				
				for(int i=0; i<ids.length; i++)
					System.out.println("jiho_closeID::::::::"+ids[i]);
				
				//int result = service.deleteNoticeAll(ids1);
				
				
				//공개만 구현
				//int openresult = service.updatePubAll(openids1, true);
				
				
				//전부
				int allresult = noticeService.updatePubAll(openids1, closeids1);
			break;		
				
			case "일괄삭제":
				int[] ids1 = new int[delIds.length];
				for(int i=0; i<delIds.length; i++)
					ids1[i] = Integer.parseInt(delIds[i]);
				
				System.out.println("jiho_ID::::::::"+ids1[0]);
				
				//int result = service.deleteNoticeAll(ids1);

				int result = noticeService.deleteAll(ids1);
				
				break;
				
				
			default :
				break;
		}
		

		return "redirect:list";
	}	
	
	@GetMapping("detail")
	public String detail(Model model, int id) {
		
		
		
		List<Notice> list = noticeService.getPubViewListDetail(id);
		
		System.out.println("jiho LIST :::::"+list);

		model.addAttribute("n", list);
		
		
		return "/admin/board/notice/detail";
	}
	
	
	@GetMapping("reg")
	public String reg() {
		return "/admin/board/notice/reg";
	}
	
	@PostMapping("reg")
	//@ResponseBody
	public String reg(String title, String content, String open, MultipartFile[] files, String category, String[] foods) throws IllegalStateException, IOException, ClassNotFoundException, SQLException {
		
		StringBuilder builder = new StringBuilder();
		
		for(MultipartFile file : files) {
			
			long size = file.getSize();
			
			String fileName = file.getOriginalFilename();
			builder.append(fileName);
			builder.append(",");
			
			
			System.out.printf("fileName: %s, fileSize: %d\n", fileName, size);
			
			String webPath = "/static/upload";
			String realPath = ctx.getRealPath(webPath);
			
			System.out.printf("realPath: %s\n", realPath);
			
			File savePath = new File(realPath);
			if(!savePath.exists())
				savePath.mkdirs();
			
			realPath += File.separator +  fileName;
			
			File saveFile = new File(realPath);
			file.transferTo(saveFile);
		}
		builder.delete(builder.length()-1, builder.length());
	
		//NOTICE1 테이블 FILES 컬럼 저장 위함
		System.out.println(builder.toString());
		
		boolean pub = false;
		
		if(open != null)
			pub = true;
		
		Notice notice = new Notice();
		
		notice.setTitle(title);
		notice.setContent(content);
		notice.setPub(pub);
		notice.setFiles(builder.toString());
		

		int result = noticeService.insert(notice);
		
//		for(String f : foods)
//			System.out.println(f);
		
		//return String.format("title:%s<br>content:%s<br>category:%s<br>", title, content, category);
		//포워딩 : return "/admin/board/notice/list";
		return "redirect:list";
	}

}
