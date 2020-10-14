package com.sbs.example.eastextboard;

import java.util.*;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Article[] art = new Article[10];
		art[0] = new Article();
		art[1] = new Article();
		art[2] = new Article();
		art[3] = new Article();
		art[4] = new Article();
		art[5] = new Article();
		art[6] = new Article();
		art[7] = new Article();
		art[8] = new Article();
		art[9] = new Article();

		int i = 0;
		int id = 1;
		while (true) {
			art[i] = new Article();

			System.out.printf("명령어 :");

			String command = scanner.nextLine();

			if (command.equals("article add")) {

				System.out.printf("타이틀 : ");
				String title = scanner.nextLine();
				art[i].title = title;
				System.out.printf("내용 : ");
				String body = scanner.nextLine();
				art[i].body = body;
				art[i].id = id;
				System.out.println("== 게시물이 생성됨 ==");
				System.out.printf("%d번", art[i].id);
				System.out.printf("제목 : %s\n", art[i].title);
				System.out.printf("내용 : %s\n", art[i].body);
				i++;
				id++;
			} else if (command.equals("article list")) {
				for (int j = 0; j < art.length; j++) {
					System.out.println("Index / Title / Body");
					System.out.printf("%d번", art[j].id);
					System.out.printf("     %s", art[j].title);
					System.out.printf("       %s\n", art[j].body);
				}
			} else if (command.equals("exit")) {
				break;
			} else {
				System.out.println("잘못된 명령어 입니다.");
			}

		}
		scanner.close();

	}

}
