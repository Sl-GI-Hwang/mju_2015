package com.example.LMS.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.security.auth.Subject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.LMS.controller.CourseController;
import com.example.LMS.model.Course;
import com.example.LMS.model.Lecture;
import com.example.LMS.model.Takes;

@Repository
public class CourseDAO{
	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);


	//수강신청 목록 불러오기, 신청
	public ArrayList<Course> getAllCourses() {
		ArrayList<Course> allCourses = new ArrayList<Course>();
		String dbUrl = "jdbc:mysql://localhost:3306/mjulms";
		String dbUser = "revenge";
		String dbPassword = "123";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select c.cid, c.gwamokname, u.name pname, c.year, "
							+ "c.grade, c.hackjum, c.maxpeople from course c join user u on c.uid = u.uid");
			logger.info("여기까지 넘어오긴 함");

			while (rs.next()) {
				Course course = new Course();
				course.setCid(rs.getInt("cid"));
				course.setGwamokname(rs.getString("gwamokname"));
				course.setPname(rs.getString("pname"));
				course.setYear(rs.getInt("year"));		
				course.setGrade(rs.getInt("grade"));
				course.setHackjum(rs.getInt("hackjum"));
				course.setMaxpeople(rs.getInt("maxpeople"));
				allCourses.add(course);
			}

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {

		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		/* 모든 과목 정보 */
		return allCourses;
	}
	public Course registForCourse(String userID, String courseID) {
		/* 수강신청 */
		String dbUrl = "jdbc:mysql://localhost:3306/mjulms";
		String dbUser = "revenge";
		String dbPassword = "123";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int hackjum, already = 0, result, maxpeople, now;
		Course course = new Course();


		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
		
			
			stmt = conn.prepareStatement("select hackjum from course where cid = ?");
			stmt.setInt(1, Integer.parseInt(courseID));
			rs = stmt.executeQuery();
			rs.next();

			hackjum = rs.getInt("hackjum");
	
			// 강좌 중복신청 카운트
			stmt = conn.prepareStatement("select cid from takes where uid = ?");
			stmt.setString(1, userID);
			rs = stmt.executeQuery();

			while (rs.next()) {
				if (Integer.toString(rs.getInt("cid")).equals(courseID)) {
					already = 1;
					course.setSerError(1);
					break;
				}
				already = 0;
				course.setSerError(0);
			}
			
			// 정원 파악
			stmt = conn.prepareStatement("select maxpeople from course where cid = ?");
			stmt.setInt(1, Integer.parseInt(courseID));
			rs = stmt.executeQuery();
			rs.next();

			maxpeople = rs.getInt("maxpeople");
			
			// 현재 수강신청인원 카운트
			stmt = conn.prepareStatement("select count(*) from takes where cid = ?");
			stmt.setInt(1, Integer.parseInt(courseID));
			rs = stmt.executeQuery();
			rs.next();
			now = rs.getInt("count(*)");
			System.out.println(course.getSerError());
			if (now > maxpeople) {
				course.setSerError(2);
			}

			if (already == 0 && now < maxpeople) {
				stmt = conn.prepareStatement("insert into takes(uid, cid, hackjum) values(?, ?, ?)");
				stmt.setString(1, userID);
				stmt.setInt(2, Integer.parseInt(courseID));
				stmt.setInt(3, hackjum);
				
				result = stmt.executeUpdate();
				return course;

			}

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}

		}
		return course;
	}


	// 교수 강좌 불러오기, 개설, 삭제	
	public ArrayList<Lecture> getAllLectures(String userID) {
		ArrayList<Lecture> allLectures = new ArrayList<Lecture>();
		String dbUrl = "jdbc:mysql://localhost:3306/mjulms";
		String dbUser = "revenge";
		String dbPassword = "123";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.prepareStatement("select * from course where uid = ?");
			stmt.setInt(1, Integer.parseInt(userID));
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Lecture lecture = new Lecture();
				lecture.setCid(rs.getInt("cid"));
				lecture.setGwamokname(rs.getString("gwamokname"));
				lecture.setUid(rs.getInt("uid"));
				lecture.setYear(rs.getInt("year"));		
				lecture.setGrade(rs.getInt("grade"));
				lecture.setHackjum(rs.getInt("hackjum"));
				lecture.setMaxpeople(rs.getInt("maxpeople"));
				allLectures.add(lecture);
			}

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		/* 모든 과목 정보 */
		return allLectures;
	}
	public void registForLecture(int cid, String gwamokname, int year, int grade, int maxpeople, int hackjum, int uid) {
		String dbUrl = "jdbc:mysql://localhost:3306/mjulms";
		String dbUser = "revenge";
		String dbPassword = "123";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.prepareStatement("insert into course(cid, gwamokname, uid, year, grade, maxpeople, hackjum) values(?, ?, ?, ?, ? ,?, ?)");
			stmt.setInt(1, cid);
			stmt.setString(2, gwamokname);
			stmt.setInt(3, uid);
			stmt.setInt(4, year);
			stmt.setInt(5, grade);
			stmt.setInt(6, maxpeople);
			stmt.setInt(7, hackjum);

			result = stmt.executeUpdate();

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
			}
		}
	}
	
	public ArrayList<Takes> getAllStudent(int cID) {
		// TODO Auto-generated method stub
		ArrayList<Takes> allTakes = new ArrayList<Takes>();
		String dbUrl = "jdbc:mysql://localhost:3306/mjulms";
		String dbUser = "revenge";
		String dbPassword = "123";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.prepareStatement("select t.cid, u.name, coalesce(t.grade, 'unwritten') grade, t.uid uid from takes t join user u on u.uid = t.uid where cid = ?");
			stmt.setInt(1, cID);
			rs = stmt.executeQuery();
			logger.info("뭡니까 이거 " + cID);

			while (rs.next()) {
				Takes takes = new Takes();
				takes.setCid(rs.getInt("cid"));
				takes.setUcode(rs.getString("name"));
				takes.setGrade(rs.getString("grade"));
				takes.setUid(rs.getInt("uid"));
				
				allTakes.add(takes);
			}

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {

		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		/* 모든 과목 정보 */
		return allTakes;
	}
	public void setStudentCredit(int uid, String grade, int cid) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
				String dbUrl = "jdbc:mysql://localhost:3306/mjulms";
				String dbUser = "revenge";
				String dbPassword = "123";
				Connection conn = null;
				Statement stmt = null;
				int rs = 0;
				String sql = null;
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				stmt = conn.createStatement();
				sql = "update takes set grade = '"+grade+"' where uid = "+ uid +" and cid = "+cid;
				rs = stmt.executeUpdate(sql);
				logger.info("왜안넘어갑니까?" + uid + "cid는? "+ cid + "grade는?" + grade);
				if (stmt != null)
					try {
						stmt.close();
					} catch (SQLException e) {
					}
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
				}
	}
	public ArrayList<Lecture> checkCredit(String userID) {
		ArrayList<Lecture> allLectures = new ArrayList<Lecture>();
		String dbUrl = "jdbc:mysql://localhost:3306/mjulms";
		String dbUser = "revenge";
		String dbPassword = "123";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.prepareStatement("select c.cid, c.gwamokname, u.name name, c.year, "
					+ "c.grade grade, c.hackjum, c.maxpeople, coalesce(t.grade, 'unwritten') credit  from course c join takes t on c.cid = t.cid "
					+ "join user u on c.uid = u.uid where t.uid = ?");
			stmt.setInt(1, Integer.parseInt(userID));
			rs = stmt.executeQuery();
			while (rs.next()) {
				Lecture lecture = new Lecture();
				lecture.setCid(rs.getInt("cid"));
				lecture.setGwamokname(rs.getString("gwamokname"));
				lecture.setProfname(rs.getString("name"));
				lecture.setYear(rs.getInt("year"));		
				lecture.setGrade(rs.getInt("grade"));
				lecture.setHackjum(rs.getInt("hackjum"));
				lecture.setMaxpeople(rs.getInt("maxpeople"));
				lecture.setCredit(rs.getString("credit"));
				allLectures.add(lecture);

			}

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		/* 모든 과목 정보 */
		return allLectures;
	}
	public void deleteLecture(int cid) {
		// TODO Auto-generated method stub
		String dbUrl = "jdbc:mysql://localhost:3306/mjulms";
		String dbUser = "revenge";
		String dbPassword = "123";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.prepareStatement("delete from course where cid= ?");
			stmt.setInt(1, cid);
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
			}
		}
	}
}
