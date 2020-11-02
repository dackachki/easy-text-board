package com.sbs.example.eastextboard.Container;

import com.sbs.example.eastextboard.DAO.ArticleDAO;
import com.sbs.example.eastextboard.DAO.MemberDAO;
import com.sbs.example.eastextboard.Session.Session;
import com.sbs.example.eastextboard.service.ArticleService;
import com.sbs.example.eastextboard.service.MemberService;

public class Container {
	public static Session session;
	public static ArticleService articleService;
	public static MemberService memberService;
	public static ArticleDAO articleDAO;
	public static MemberDAO memberDAO;

	static {
		session = new Session();
		memberDAO = new MemberDAO();
		articleDAO = new ArticleDAO();
		
		articleService = new ArticleService();
		memberService = new MemberService();
	}

}
