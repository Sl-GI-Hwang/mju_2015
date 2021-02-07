package com.example.LMS.model;

import javax.security.auth.Subject;

public class Course {
	private int cid; // 강좌번호
	private String gwamokname; // 강좌명
	private String pname; // 교수명
	private int year; // 학점
	private int grade; // 학점
	private int hackjum; // 연도
	private int maxpeople; //수강인원
	private int serError;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getGwamokname() {
		return gwamokname;
	}
	public void setGwamokname(String gwamokname) {
		this.gwamokname = gwamokname;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getHackjum() {
		return hackjum;
	}
	public void setHackjum(int hackjum) {
		this.hackjum = hackjum;
	}
	public int getMaxpeople() {
		return maxpeople;
	}
	public void setMaxpeople(int maxpeople) {
		this.maxpeople = maxpeople;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getSerError() {
		return serError;
	}
	public void setSerError(int serError) {
		this.serError = serError;
	}
	

}

