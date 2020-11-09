package com.sbs.example.eastextboard.DAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sbs.example.eastextboard.Container.Container;
import com.sbs.example.eastextboard.dto.Article;
import com.sbs.example.eastextboard.dto.Board;

public class ArticleDAO {

	private static List<Article> articles;
	private static List<Board> boards;
	private int article_index;
	private int board_index;

	public List<Article> getarticles() {
		return articles;
	}

	public ArticleDAO() {
		article_index = 0;
		articles = new ArrayList<>();
		board_index = 0;
		boards = new ArrayList<>();
		
		
		for (int i = 1; i <= 32; i++) {
			add(Container.session.writerindex, "제목" + (i), "내용" + (i));
		}
		makeBoard("공지");
		makeBoard("자유");
	}

	public int add(int writerid_index, String title, String body) {
		Article article = new Article();

		article.id = article_index + 1;
		article.title = title;
		article.body = body;
		articles.add(article);
		article_index = article.id;
		
		article.belongingBoard = Container.session.presentBoard.boardIndex;
		article.writer = MemberDAO.getMembers().get(writerid_index).id;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		article.date = format.format(Calendar.getInstance().getTime());

		return article.id;

	}

	

	public int getArticlesize() {
		return articles.size();
	}

	public int makeBoard(String BoardName) {
		Board board = new Board();
		board.boardIndex = board_index + 1;
		board.boardName = BoardName;
		board_index = board.boardIndex;
		boards.add(board);
		return board_index;
	}

	public String getBoardName(String inputname) {
		for (Board board : boards) {
			if (board.boardName.equals(inputname))
				Container.session.presentBoard = board;
			return board.boardName;
		}
		return null;
	}

}
