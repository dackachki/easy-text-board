package com.sbs.example.eastextboard;

import java.util.Scanner;

public class App {
	Article[] art = new Article[10];
	int MaxArticleCount = art.length;
	int artC = 1;
	int i = 1;

	public int getIndexN(int numb) {
		for (int i = 1; i < art.length; i++) {
			if (numb == art[i].id) {
				return art[i].id;
			}
		}
		return -1;

	}

	public void eraseByIndex(int IndexNum) {
		int Index = getIndexN(IndexNum);
		if (Index != -1) {
			remove(Index);
		} else {
			System.out.printf("%d번 게시물이 없습니다.\n", IndexNum);
		}

	}

	public void remove(int indexNum) {
		System.out.printf("== %d번 좌석 숫자 제거 ==\n", indexNum);

		for (int i = indexNum; i < MaxArticleCount - 1; i++) {
			art[i ] = art[i + 1];

		}
		artC--;

	}

	public void run() {
		Scanner scanner = new Scanner(System.in);

		for (int num = 0; num < art.length; num++) {
			art[num] = new Article();
		}

		while (true) {
			System.out.printf("명령어 입력 : ");
			String command = scanner.nextLine();

			if (command.equals("article add")) {
				if (i == MaxArticleCount) {
					System.out.println("== 게시물을 더 이상 등록 할 수 없습니다. ==");

				} else {
					System.out.printf("제목 : ");
					art[artC].title = scanner.nextLine();
					System.out.printf("내용 : ");
					art[artC].body = scanner.nextLine();
					art[artC].id = artC;
					System.out.println("== 생성된 게시물 정보 ==");
					System.out.printf("게시물 번호 : %d\n", art[artC].id);
					System.out.printf("게시물 제목 : %s\n", art[artC].title);
					System.out.printf("게시물 내용 : %s\n", art[artC].body);
					
					artC++;
				}

			} else if (command.equals("article list")) {
				System.out.println("== 전체 게시물 리스트 ==");
				System.out.println("번호 / 제목");
				for (int j = 1; j <= artC - 1; j++) {

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
			}

			else if (command.startsWith("article delete")) {
				String[] commandbits = command.split(" ");
				int defid = Integer.parseInt(commandbits[2]);
				if (defid < 0) {

					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", defid);

				}

				int len = art.length;
				for (int DIndex = defid; DIndex < len - 1; DIndex++) {

					art[DIndex] = art[DIndex + 1];

				}
				System.out.printf("%d번쨰 게시물이 삭제 되었습니다.\n", defid);
				artC--;
				i--;

			} else if (command.startsWith("article erase")) {
				String[] commandbits = command.split(" ");
				int defid = Integer.parseInt(commandbits[2]);
				eraseByIndex(defid);

			} else if (command.equals("index num")) {
				System.out.printf("Count :%d\n", artC);
				
			} else {
				System.out.println("명령어가 잘못 입력되었습니다.");
			}

		}

		scanner.close();

	}

}
