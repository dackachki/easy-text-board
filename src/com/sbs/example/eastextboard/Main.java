package com.sbs.example.eastextboard;

import java.util.*;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int id = 1;
		int pre_id = 0;

		int article1_id = 0;
		String article1_title = "";
		String article1_body = "";

		int article2_id = 0;
		String article2_title = "";
		String article2_body = "";

		while (true) {

			System.out.printf("명령어 :");

			String command = scanner.nextLine();

			if (command.equals("article add")) {
				System.out.printf("타이틀 : ");
				String title = scanner.nextLine();

				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				System.out.println("== 게시물 정보 == ");
				System.out.println("번호 : " + id);
				System.out.println("제목 : " + title);
				System.out.println("내용 : " + body);

				if (id == 1) {
					 article1_id = id;
					 article1_title = title;
					 article1_body = body;
				}

				else if (id == 2) {
					 article2_id = id;
					 article2_title = title;
					 article2_body = body;
				}
				id++;
				pre_id++;

			} else if (command.equals("article list")) {
				if (pre_id == 0) {
					System.out.println("게시물이 없습니다");
				} else {
					if( pre_id >= 1) {
						System.out.printf("%d / %s  %s \n",article1_id,article1_title,article1_body);
					}
					if( pre_id >= 2) {
						System.out.printf("%d / %s / %s \n ",article2_id,article2_title,article2_body);
					}
				}
			}
			else if (command.equals("article detail 1")) {
				System.out.println("== 게시물 상세 ==");
				System.out.printf("번호 :  %s\n",article1_id);
				System.out.printf("제목 :  %s\n",article1_title);
				System.out.printf("내용 :  %s\n",article1_body);
			}
				else if (command.equals("article detail 2")) {
					System.out.println("== 게시물 상세 ==");
					System.out.printf("번호 :  %s\n",article2_id);
					System.out.printf("제목 :  %s\n",article2_title);
					System.out.printf("내용 :  %s\n",article2_body);
			
			}
				
			else if (command.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			}
		}
	
		scanner.close();
	
	}
}