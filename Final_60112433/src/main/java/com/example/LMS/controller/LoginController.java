package com.example.LMS.controller;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.LMS.model.LoginInfo;
import com.example.LMS.model.UserInfo;
import com.example.LMS.service.impl.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(CourseController.class);
	
	// 로그인
	@RequestMapping(value = "/LoginController/login.do")
	public String login(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException{

		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("user_id");
		String userPass = request.getParameter("user_password");
		
		if (request.getParameter("user_id") == null) {
			userID = request.getParameter(session.getAttribute("userID").toString());
			userPass = request.getParameter(session.getAttribute("userPass").toString());
		}
		
		LoginInfo logininfo = new LoginInfo(userID, userPass);
		
		logger.info("로그인 시도 ID:" + userID + ", PW:" + userPass);
		
		UserInfo sessionUser = loginService.login(logininfo);
				
		session.setAttribute("userID", sessionUser.getId());
		session.setAttribute("uid", sessionUser.getUid());
		session.setAttribute("userPass", sessionUser.getPassword());
		session.setAttribute("User", sessionUser);
		session.setAttribute("logincode", sessionUser.getLogin());
		session.setAttribute("category", sessionUser.getCategory());

		
		if (sessionUser.getLogin() == 2 && sessionUser.getCategory().equals("professor")) {
			return "professorMain"; 
		}
		else if (sessionUser.getLogin() == 2 && sessionUser.getCategory().equals("student")){
			return "studentMain";	
		}
		else if (sessionUser.getLogin() == 2 && sessionUser.getCategory().equals("administrator")){
			return "adminMain";	
		}
		else {
			return "error";	
		}
		}
	
	// 회원가입 
	@RequestMapping(value = "/LoginController/joinPage.do", method = RequestMethod.GET)
	public String joinPage(HttpSession session) {
		return "join";
	}
	
	@RequestMapping(value = "/LoginController/join.do", method = RequestMethod.POST)
	public String join(@RequestParam("userID") String userID, String userName, String userPassword, String category ) {
		logger.info("회원가입 신청");
		loginService.registForLMS(userID, userName, userPassword, category);
		return "redirect:/";
		
	}
	
	@RequestMapping(value = "/LoginController/logout.do")
	public String logout(HttpSession session) {
		logger.info("로그아웃");
		if (session.getAttribute("sessionUser") != null) {
			session.removeAttribute("sessionUser");
		}
		return "redirect:/";
	}
}
