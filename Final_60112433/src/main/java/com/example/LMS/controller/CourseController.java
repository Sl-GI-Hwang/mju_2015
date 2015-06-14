package com.example.LMS.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.LMS.model.Course;
import com.example.LMS.model.Lecture;
import com.example.LMS.model.Takes;
import com.example.LMS.service.impl.CourseService;

@Controller
public class CourseController {
	@Autowired
	private CourseService courseService;
	private static final Logger logger = LoggerFactory
			.getLogger(CourseController.class);

	
	//학생 Page : 수강신청 목록 불러오기, 신청
	@RequestMapping(value = "/CourseController/requestForCoursePage", method = RequestMethod.GET)
	public ModelAndView showRequestForCoursePage() {
		logger.info("수강신청:수강 과목 나열");
		ModelAndView model = new ModelAndView();
		ArrayList<Course> allCourses = courseService.getAllCourses();
		model.addObject("allCourses", allCourses);
		model.setViewName("requestCourse");
		return model;
	}
	
	@RequestMapping(value = "/CourseController/requestCourse", method = RequestMethod.POST)
	public String requestForCourse(HttpSession session, @RequestParam("cid") String courseID, Model model) {
		logger.info("수강신청:수강 신청한 과목 번호->" + courseID);
		Course c = new Course();
		c = courseService.registForCourse(session.getAttribute("uid").toString(), courseID);
		session.setAttribute("courseErr", c.getSerError());
		logger.info("errormsg->" +  c.getSerError());
		if (c.getSerError() == 1) {
			return "error";
		} else if (c.getSerError() == 2) {
			return "error";
		} else {
			return "redirect:requestForCoursePage";
		}
	}
	
	// 학생이 점수를 확인함
	@RequestMapping(value = "/CourseController/checkCredit", method = RequestMethod.GET)
	public ModelAndView CheckCredit(HttpSession session) {
		ModelAndView model = new ModelAndView();
		ArrayList<Lecture> allLecture = courseService.checkCredits(session.getAttribute("uid").toString());
		model.addObject("allLecture", allLecture);
		model.setViewName("checkCredit");
		return model;
	}
	
	//강좌 목록 불러오기, 조회, 수정
	@RequestMapping(value = "/CourseController/registLecturePage", method = RequestMethod.GET)
	public ModelAndView showRequestForLecturePage(HttpSession session) {
		logger.info("강좌개설:개설 중인 강좌");
		ModelAndView model = new ModelAndView();
		ArrayList<Lecture> allLectures = courseService.getAllLectures(session.getAttribute("uid").toString());
		model.addObject("allLectures", allLectures);
		model.setViewName("registLecture");
		return model;
	}
	@RequestMapping(value = "/CourseController/registLecture", method = RequestMethod.POST)
	public String requestForLecture(HttpSession session,  @RequestParam("cid") int cid,
			 @RequestParam("gwamokname") String gwamokName,  @RequestParam("year") int year,
			 @RequestParam("grade") int grade,  @RequestParam("maxpeople") int maxpeople,
			 @RequestParam("hackjum") int hackjum) {
		int uid = Integer.parseInt(session.getAttribute("uid").toString());
		courseService.registForLecture (cid, gwamokName, year, grade, maxpeople, hackjum, uid);
		return "redirect:registLecturePage";
	}
	@RequestMapping(value = "/CourseController/deleteLecture", method = RequestMethod.POST)
	public String deleteLecture(@RequestParam("cid") int cid){
		logger.info("강좌삭제:삭제 신청한 강좌 번호->" + cid);
		courseService.deleteLecture(cid);
		return "redirect:registLecturePage";
	}
	
	
	// 교수 : 성적 입력 Page
	@RequestMapping(value = "/CourseController/setCreditMain", method = RequestMethod.GET)
	public ModelAndView setCreditMain(HttpSession session) {
		logger.info("성적 입력 Main");
		ModelAndView model = new ModelAndView();
		ArrayList<Lecture> allLecture = courseService.getAllLectures(session.getAttribute("uid").toString());
		model.addObject("allLecture", allLecture);
		model.setViewName("setCreditMain");
		return model;
	}
	
	@RequestMapping(value = "/CourseController/setCreditPage", method = RequestMethod.POST)
	public ModelAndView setCreditPage(@RequestParam("cid") int cid) {
		logger.info("성적 입력 Pagess");
		ModelAndView model = new ModelAndView();
		ArrayList<Takes> allTakes = courseService.getAllStudent(cid);
		model.addObject("allTakes", allTakes);
		model.setViewName("setCredit");
		return model;
	}
	
	 //학셍에게 성적 부여
	@RequestMapping(value = "/CourseController/setCredits", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView SetCredit(@RequestParam("uid") int uid, @RequestParam("cid") int cid, @RequestParam("grade") String grade) throws ClassNotFoundException, SQLException {
		logger.info("학생에게 성적을 주다");
		courseService.setCredit(uid, grade, cid);
		ModelAndView model = new ModelAndView();
		ArrayList<Takes> allTakes = courseService.getAllStudent(cid);
		model.addObject("allTakes", allTakes);
		model.setViewName("setCredit");
		return model;
	}
	
	

	
}
