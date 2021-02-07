package com.example.LMS.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.controller.CourseController;
import com.example.LMS.dao.impl.CourseDAO;
import com.example.LMS.model.Course;
import com.example.LMS.model.Lecture;
import com.example.LMS.model.Takes;

@Service
public class CourseService{
	private static final Logger logger = LoggerFactory
			.getLogger(CourseController.class);
	@Autowired
	private CourseDAO dao;
	
	//수강신청 목록 불러오기, 신청
	public ArrayList<Course> getAllCourses() {		
		return dao.getAllCourses();
	}

	public Course registForCourse(String userID, String courseID) {		
		return dao.registForCourse(userID, courseID);
	}
	
	//교수 강좌 불러오기, 개설, 삭제, 학점부여
	
	public ArrayList<Lecture> getAllLectures(String userID){
		return dao.getAllLectures(userID);
	}
	public void registForLecture(int cid, String gwamokname, int year, int grade, int maxpeople, int hackjum, int uid) {	
		dao.registForLecture(cid, gwamokname, year, grade, maxpeople, hackjum, uid);
	}
	public void deleteLecture(int cid) {
		dao.deleteLecture(cid);
	}
	public ArrayList<Takes> getAllStudent(int cID){
		return dao.getAllStudent(cID);
	}

	public void setCredit(int uid, String grade, int cid) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		dao.setStudentCredit(uid, grade, cid);
	}

	public ArrayList<Lecture> checkCredits(String uid) {
		// TODO Auto-generated method stub
		return dao.checkCredit(uid);
	}

}