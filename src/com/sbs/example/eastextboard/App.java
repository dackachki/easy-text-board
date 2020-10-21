package com.sbs.example.eastextboard;


import java.util.Scanner;

public class App {
	Article[] articles = new Article[3];
	int lastArticleId = 0;
	int articlesSize = 0;

	int articlesSize() {
		return articlesSize;
	}

	Article getArticle(int id) {
		int index = getIndexById(id);
		
		if ( index == -1 ) {
			return null;
		}
		
		return articles[index];
	}

	public void run() {
		Scanner sc = new Scanner(System.in);

		int maxArticlesCount = articles.length;

		while (true) {

			System.out.printf("명령어 : ");
			String command = sc.nextLine();

			if (command.equals("system exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else if (command.equals("article add")) {
				System.out.println("== 게시물 등록 ==");

				if (articlesSize() >= maxArticlesCount) {
					System.out.println("더 이상 생성할 수 없습니다.");
					continue;
				}

				int id = lastArticleId + 1;
				String title;
				String body;

				lastArticleId = id;

				System.out.printf("제목 : ");
				title = sc.nextLine();
				System.out.printf("내용 : ");
				body = sc.nextLine();

				Article article = new Article();

				article.id = id;
				article.title = title;
				article.body = body;

				System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

				articles[articlesSize] = article;

				articlesSize++;
			} else if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");

				if (articlesSize() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				System.out.println("번호 / 제목");

				for (int i = 0; i < articlesSize(); i++) {
					Article article = articles[i];

					System.out.printf("%d / %s / %s\n", article.id, article.title,article.body);
				}
			} else if (command.startsWith("article detail ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 상세 ==");

				Article article = getArticle(inputedId);

				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}

				System.out.printf("번호 : %d\n", article.id);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
			} else if (command.startsWith("article delete ")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 삭제 ==");

				Article article = getArticle(inputedId);
				
				if (article == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", inputedId);
					continue;
				}

				remove(inputedId);

				System.out.printf("%d번 게시물이 삭제되었습니다.\n", inputedId);
			}
			else if(command.startsWith("article modify")) {
				int inputedId = Integer.parseInt(command.split(" ")[2]);
				String PreT = "";
				String PreB = "";
				
				System.out.println("수정할 제목 : ");
				String ChangedT = sc.nextLine();
				System.out.println("수정할 내용 : ");
				String ChangedB = sc.nextLine();
				getArticle(inputedId).title = ChangedT;
				getArticle(inputedId).body = ChangedB;
				System.out.printf("제목 %s 가/이 %s로 변경되었습니다.\n",PreT,ChangedT);
				System.out.printf("내용 %s 가/이 %s로 변경되었습니다.\n",PreB,ChangedB);
			}
			else if (command.startsWith("article search")) {
				String LookingF=(command.split(" ")[2]);
			}
		}

		sc.close();
	}

	private void remove(int id) {
		int index = getIndexById(id);

		if (index == -1) {
			return;
		}

		for (int i = index + 1; i < articlesSize(); i++) {
			articles[i - 1] = articles[i];
		}

		articlesSize--;
	}

	private int getIndexById(int id) {
		for (int i = 0; i < articlesSize(); i++) {
			if (articles[i].id == id) {
				return i;
			}
		}

		return -1;
	}
}
