package com.sbs.example.eastextboard.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.sbs.example.eastextboard.Container.Container;
import com.sbs.example.eastextboard.dto.Article;
import com.sbs.example.eastextboard.dto.Member;
import com.sbs.example.eastextboard.service.ArticleService;
import com.sbs.example.eastextboard.*;

import java.util.*;

public class ArticleController extends Controller {
	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}

	public void run(Scanner sc, String command) {

		if (command.equals("article add")) {
			if (Container.session.isLogined()) {
				System.out.println("== 게시물 생성 ==");
				System.out.printf("제목 입력 : ");
				String title = sc.nextLine();
				System.out.printf("내용 입력 : ");
				String body = sc.nextLine();
				int id = articleService.add(Container.session.writerindex, title, body);
				System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

			} else if (Container.session.isLogined() == false) {
				System.out.println("회원만 게시물을 작성할 수 있습니다.");
				return;
			}
		} else if (command.startsWith("article list")) {

			int page = Integer.parseInt(command.split(" ")[2]);
			if (page == 0) {
				System.out.println("페이지 번호는 1 이상입니다.");
			}
			System.out.println("== 게시물 리스트 ==");
			System.out.printf("== %d 페이지 입니다.==\n", page);
			System.out.println("번호  /  제목  / 내용 / 생성일자 / 작성자  ");
			int items = 10;
			int start = 0;
			start = (page - 1) * items;
			int end = start + 10;
			if (end >= articleService.getArticlesize()) {
				end = articleService.getArticlesize();
			}
			if (page <= 0) {
				page = 1;
			}
			if (start >= end) {
				System.out.printf("게시물은 %d페이지까지 존재합니다.\n", articleService.getArticlesize() / 10 + 1);

			}
			for (int i = start; i < end; i++) {
				System.out.printf("%d  / %s  / %s  / %s  / %s  \n", articleService.getarticles().get(i).id, articleService.getarticles().get(i).title,
						articleService.getarticles().get(i).body, articleService.getarticles().get(i).date,articleService.getarticles().get(i).writer);

			}
		} else if (command.startsWith("article detail")) {
			try {
				if (Container.session.isLogined()) {
					int inputid = Integer.parseInt(command.split(" ")[2]) - 1;

					System.out.println("== 게시물 정보 == ");
					System.out.printf("게시물 번호 : %d\n", articleService.getarticles().get(inputid).id);
					System.out.printf("게시물 제목 : %s\n", articleService.getarticles().get(inputid).body);
					System.out.printf("게시물 내용 : %s\n", articleService.getarticles().get(inputid).title);
					System.out.printf("게시물 작성일 : %s\n", articleService.getarticles().get(inputid).date);
					System.out.printf("게시물 작성자 : %s\n", articleService.getarticles().get(inputid).writer);
				} else if (Container.session.isLogined() == false) {
					System.out.println("회원만 게시물을 작성할 수 있습니다.");
					return;
				}
			}

			catch (IndexOutOfBoundsException e) {
				System.out.println("해당하는 번호의 게시물이 존재하지 않습니다.");
			}
		} else if (command.startsWith("article delete")) {
			try {
				if (Container.session.isLogined()) {
					int inputid = Integer.parseInt(command.split(" ")[2]);
					articleService.getarticles().remove(inputid);

					System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputid);
				} else if (Container.session.isLogined() == false) {
					System.out.println("회원만 게시물을 작성할 수 있습니다.");
					return;
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("해당하는 번호의 게시물이 존재하지 않습니다.");
			}

		} else if (command.startsWith("article modify")) {
			try {
				if (Container.session.isLogined()) {
					int inputid = Integer.parseInt(command.split(" ")[2]);
					System.out.printf("== %d번 게시물 수정 ==\n", inputid);
					System.out.printf("변경할 제목 : ");
					String title = sc.nextLine();
					System.out.printf("변경할 내용 : ");
					String body = sc.nextLine();
					articleService.getarticles().get(inputid).title = title;
					articleService.getarticles().get(inputid).body = body;
					System.out.printf("%d번 게시물이 수정되었습니다.", inputid);
				}
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

			for (int i = 0; i < articleService.getArticlesize(); i++) {
				String getbody = articleService.getarticles().get(i).body;

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
				System.out.printf("%d  / %s /  %s\n", articleService.getarticles().get(index).id, articleService.getarticles().get(index).title,
						articleService.getarticles().get(index).body);
			}

		}

	}

}