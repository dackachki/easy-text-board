package com.sbs.example.eastextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.printf("명령어 입력:");
			String command = scanner.nextLine();

			if (command.equals("article list")) {
				System.out.println("== 게시물 리스트 ==");
			} else if (command.equals("article add")) {
				System.out.println("== 게시물 작성 ==");
			} else if (command.equals("break")) {
				break;
			} else {
				System.out.println("잘못된 입력");
			}
		}
			scanner.close();

		}

	

}
