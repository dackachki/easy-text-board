package com.sbs.example.eastextboard;

import java.text.SimpleDateFormat;
import java.util.*;

public class App {

	int articleL = 32;
	List<Article> list = new ArrayList<Article>();
	int article_index = 0;
	Scanner sc = new Scanner(System.in);

	public void run() {

		for (int i = 0; i < articleL; i++) {
			list.add(new Article(i, "title" + i, "body" + i));
		}
		while (true) {
			System.out.printf("명령어 입력 : ");

			String command = sc.nextLine();

			if (command.startsWith("article list")) {
				int pagenum = Integer.parseInt(command.split(" ")[2]);

				int itemsInPage = 10;
				int StartNum = articleL - 1;
				StartNum -= (pagenum - 1) * itemsInPage;
				int EndNum = StartNum - itemsInPage - 1;

				if (EndNum < 0) {
					EndNum = 0;
				}

				if (StartNum < 0) {
					System.out.printf("%d번 페이지는 존재하지 않습니다.", pagenum);
					continue;

				}
				for (int i = StartNum; i >= EndNum; i--) {
					System.out.printf("%d  /%s  /%s\n", list.get(i).id, list.get(i).title, list.get(i).body);
				}
			}

			else if (command.equals("exit")) {
				break;
			} else if (command.startsWith("article detail")) {
				String[] commandbits = command.split(" ");

				int number = 0;
				try {
					number = Integer.parseInt(commandbits[2]);

					System.out.println("번호 / 제목 / 내용");
					System.out.printf("%d /  %s  / %s\n", list.get(number).id, list.get(number).title,
							list.get(number).body);
				} catch (IndexOutOfBoundsException e) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.0부터 %d번 까지 입니다\n.", number, articleL);
				} catch (NumberFormatException e) {
					System.out.println("양의 정수를 입력하세요.");

				}
			}

			else if (command.startsWith("article delete")) {
				int input_index = 0;
				try {
					input_index = Integer.parseInt(command.split(" ")[2]);

					list.remove(input_index);
					articleL--;
					System.out.printf("%d번이 삭제 되었습니다.\n", input_index);
				} catch (IndexOutOfBoundsException e) {
					System.out.printf("%d번 게시물이 존재하지 않습니다.0부터 %d번 까지 입니다\n.", input_index, articleL);
				}

			} else if (command.startsWith("article modify")) {
					int input_index  =0;
				try {
					input_index = Integer.parseInt(command.split(" ")[2]);
				
				System.out.printf("== %d게시물 수정 ==\n", input_index);
				
				
				System.out.println("변경할 제목 입력 : ");
				String title = sc.nextLine();
				list.get(input_index).title = title;
				System.out.println("변경할 내용 입력 : ");
				String body = sc.nextLine();
				
				list.get(input_index).body = body;
				}
				 catch (IndexOutOfBoundsException e) {
						System.out.printf("%d번 게시물이 존재하지 않습니다.0부터 %d번 까지 입니다\n.", input_index, articleL);
					} catch (NumberFormatException e) {
						System.out.println("양의 정수를 입력하세요.");

					}
			} else if (command.startsWith("article search")) {
				String keywords = command.split(" ")[2];

				for (int i = 0; i < articleL; i++) {
					String getbody = list.get(i).body;
					if (getbody.contains(keywords)) {
						System.out.printf("%d  / %s   %s\n", list.get(i).id, list.get(i).title, list.get(i).body);

					}
				}

			}

			else {
				System.out.println("명령어가 올바르지 않습니다.");
			}

		}
		sc.close();
	}
}