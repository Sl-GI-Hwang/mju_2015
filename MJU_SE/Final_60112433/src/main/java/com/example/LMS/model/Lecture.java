package com.example.LMS.model;

public class Lecture {
	private int cid; // 강좌번호
	private String gwamokname; // 강좌명
	private int uid; // 교수번호
	private int year; // 학점
	private int grade; // 학점
	private int hackjum; // 연도
	private int maxpeople; //수강인원
	private String profname;
	private String credit;
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
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	public String getProfname() {
		return profname;
	}
	public void setProfname(String profname) {
		this.profname = profname;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	
}
