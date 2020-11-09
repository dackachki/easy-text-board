package com.sbs.example.eastextboard.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sbs.example.eastextboard.Container.Container;
import com.sbs.example.eastextboard.DAO.ArticleDAO;
import com.sbs.example.eastextboard.dto.Article;

public class ArticleService {
	private ArticleDAO articleDao;

	public ArticleService() {
		articleDao = Container.articleDAO;
	}
	

	public int add(int writerindex, String title, String body) {
		return articleDao.add(writerindex, title, body);
	}


	public int getArticlesize() {
		return articleDao.getArticlesize();
	}


	public List<Article> getarticles() {
		return articleDao.getarticles();
	}


	public int makeBoard(String boardName) {
		return articleDao.makeBoard(boardName);
		
	}


	public String getBoardName(String inputname) {
		return articleDao.getBoardName(inputname);
	}
	
}
