package com.sbs.example.eastextboard.Controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.sbs.example.eastextboard.Article;

public class ArticleController extends Controller {
	private List<Article> articles;
	private int article_index;

	public ArticleController() {
		article_index = 0;
		articles = new ArrayList<>();

		for (int i = 0; i < 32; i++) {
			add("제목" + (i + 1), "내용" + (i + 1));
		}
	}

	private int add(String title, String body) {
		Article article = new Article();

		article.id = article_index + 1;
		article.title = title;
		article.body = body;
		articles.add(article);
		article_index = article.id;
		return article.id;

	}

	public void run(Scanner sc, String command) {

		if (command.equals("article add")) {
			System.out.println("== 게시물 생성 ==");
			System.out.printf("제목 입력 : ");
			String title = sc.nextLine();
			System.out.printf("내용 입력 : ");
			String body = sc.nextLine();
			int id = add(title, body);
			System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

		} 
			else if (command.startsWith("article list")) {

			int page = Integer.parseInt(command.split(" ")[2]);
			if (page == 0) {
				System.out.println("페이지 번호는 1 이상입니다.");
			}
			System.out.println("== 게시물 리스트 ==");
			System.out.printf("== %d 페이지 입니다.==\n", page);
			System.out.println("번호  /  제목  / 내용 / 생성일자 ");
			int items = 10;
			int start = 0;
			start = (page - 1) * items;
			int end = start + 10;
			if (end >= articles.size()) {
				end = articles.size();
			}
			if (page <= 0) {
				page = 1;
			}
			if (start >= end) {
				System.out.printf("게시물은 %d페이지까지 존재합니다.\n", articles.size() / 10 + 1);

			}
			for (int i = start; i < end; i++) {
				System.out.printf("%d  / %s  / %s\n ", articles.get(i).id, articles.get(i).title, articles.get(i).body);

			}
		} 
		else if (command.startsWith("article detail")) {
			try {
				int inputid = Integer.parseInt(command.split(" ")[2]);

				System.out.println("== 게시물 정보 == ");
				System.out.printf("게시물 번호 : %d\n", articles.get(inputid).id);
				System.out.printf("게시물 제목 : %s\n", articles.get(inputid).body);
				System.out.printf("게시물 내용 : %s\n", articles.get(inputid).title);

			}

			catch (IndexOutOfBoundsException e) {
				System.out.println("해당하는 번호의 게시물이 존재하지 않습니다.");
			}
		} else if (command.startsWith("article delete")) {
			try {
				int inputid = Integer.parseInt(command.split(" ")[2]);
				articles.remove(inputid);
				System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputid);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("해당하는 번호의 게시물이 존재하지 않습니다.");
			}

		} else if (command.startsWith("article modify")) {
			try {
				int inputid = Integer.parseInt(command.split(" ")[2]);
				System.out.printf("== %d번 게시물 수정 ==\n", inputid);
				System.out.printf("변경할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("변경할 내용 : ");
				String body = sc.nextLine();
				articles.get(inputid).title = title;
				articles.get(inputid).body = body;
				System.out.printf("%d번 게시물이 수정되었습니다.", inputid);

			} catch (IndexOutOfBoundsException e) {
				System.out.println("해당하는 번호의 게시물이 존재하지 않습니다.");
			}
		} else if (command.startsWith("article search")) {
			String[] commandbits = command.split(" ");
			String inputbody = commandbits[2];
			List<Integer> ids = new ArrayList<>();
			int searchC = 0;

			int page = Integer.parseInt(commandbits[3]);
			if (page == 0) {
				page = 1;
			}
			int items = 10;
			int start = 0;
			start = (page - 1) * items;

			if (page <= 0) {
				page = 1;
			}
			System.out.printf("%s의 검색 결과 %d페이지 입니다.\n", inputbody, page);

			for (int i = 0; i < articles.size(); i++) {
				String getbody = articles.get(i).body;

				if (getbody.contains(inputbody)) {
					ids.add(i);
					searchC++;
				}

			}
			int end = start + 10;
			if (end >= ids.size()) {
				end = ids.size();
			}
			System.out.printf("%d건이 검색되었습니다.\n", searchC);
			System.out.println("번호  /  제목  /내용");
			for (int i = start; i < end; i++) {
				int index = ids.get(i);
				System.out.printf("%d  / %s /  %s\n", articles.get(index).id, articles.get(index).title,
						articles.get(index).body);
			}

		}

		
	}

}