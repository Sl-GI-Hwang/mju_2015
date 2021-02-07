package com.example.LMS.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.dao.impl.LoginDAO;
import com.example.LMS.model.LoginInfo;
import com.example.LMS.model.UserInfo;

@Service
public class LoginService{

	@Autowired
	private LoginDAO loginDAO;
	
	public UserInfo login(LoginInfo logininfo){
		// TODO Auto-generated method stub
		return loginDAO.login(logininfo);
	}
	public void registForLMS(String userID, String userName, String userPassword, String category){
		loginDAO.registForLMS(userID, userName, userPassword, category);
	}

}

