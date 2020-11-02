package com.sbs.example.eastextboard.DAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sbs.example.eastextboard.Container.Container;
import com.sbs.example.eastextboard.dto.Article;

public class ArticleDAO {
	private static List<Article> articles;
	private int article_index;
	public List<Article> getarticles() {
		return articles;
	}
	public ArticleDAO() {
		article_index = 0;
		articles = new ArrayList<>();

		for (int i = 0; i <= 32; i++) {
			add(Container.session.writerindex,"제목" + (i + 1), "내용" + (i + 1));
		}
	}

	public int add(int writerid_index, String title, String body) {
		Article article = new Article();

		article.id = article_index + 1;
		article.title = title;
		article.body = body;
		articles.add(article);
		article_index = article.id;
		article.memberindex = Container.session.writerindex;
		article.writer = MemberDAO.getMembers().get(writerid_index).id;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		article.date = format.format(Calendar.getInstance().getTime());

		return article.id;

	}
	public int getArticlesize() {
		return articles.size();
	}

}



