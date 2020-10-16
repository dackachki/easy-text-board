package com.sbs.example.eastextboard;

import java.util.Scanner;

public class App {
	

	public void run() {
		Scanner scanner = new Scanner(System.in);

		Article[] art = new Article[11];
		for (int num = 0; num < art.length; num++) {
			art[num] = new Article();
		}
		int i = 1;
		int MaxArticleCount = 11;

		while (true) {
			System.out.printf("명령어 입력 : ");
			String command = scanner.nextLine();

			if (command.equals("article add")) {
				if (i == MaxArticleCount) {
					System.out.println("==게시물을 더 이상 등록 할 수 없습니다. ==");

				} else {
					System.out.printf("제목 : ");
					art[i].title = scanner.nextLine();
					System.out.printf("내용 : ");
					art[i].body = scanner.nextLine();
					art[i].id = i;
					System.out.println("== 생성된 게시물 정보 ==");
					System.out.printf("게시물 번호 : %d\n", art[i].id);
					System.out.printf("게시물 제목 : %s\n", art[i].title);
					System.out.printf("게시물 내용 : %s\n", art[i].body);
					i++;
				}

			} else if (command.equals("article list")) {
				System.out.println("== 전체 게시물 리스트 ==");
				System.out.println("번호 / 제목");
				for (int j = 1; j <= i - 1; j++) {

					System.out.printf("%d /%s\n", art[j].id, art[j].title);

				}

			} else if (command.startsWith("article detail")) {
				String[] commandbits = command.split(" ");
				int defid = Integer.parseInt(commandbits[2]);

				if (defid <= 0 || art[defid].id == 0) {
					System.out.println("==게시글 정보 ==");
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", defid);
					continue;
				} else if (defid > 10) {
					System.out.println("게시물 범위 초과");
				}
				System.out.println("==게시글 정보 ==");
				System.out.printf("게시물 번호 : %d\n게시물 제목: %s\n 게시물 내용: %s\n", art[defid].id, art[defid].title,
						art[defid].body);

			} else if (command.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else {
				System.out.println("명령어가 잘못 입력되었습니다.");
			}

		}

		scanner.close();

	}

}
