package com.newlecture.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//     http://.../WEB
@RestController
public class HomeController {

//	@RequestMapping("index")
//	public String index() {
//		
//		return "root.index";
//	}
//	
//	
//	@RequestMapping("home.do")
//	public String home() {
//		
//		return "home";
//	}
	
//	@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// TODO Auto-generated method stub
//		ModelAndView mv = new ModelAndView("root.index");
//		mv.addObject("data", "Hello Spring MVC~");
//		//mv.setViewName("/WEB-INF/view/index.jsp");
//		return mv;
//	}
	
	@GetMapping("/index")
	public String ad() {
		
		return "test";
		
	}
	
	
	@GetMapping("/delete/{variable}")
	public String DeleteVariable(@PathVariable String variable) {
		return variable;
	}
	
	
	
}
