package com.sbs.example.eastextboard;

import java.text.SimpleDateFormat;
import java.util.*;

public class App {
	List<Article> articles = new ArrayList<Article>();
	int article_index = 1;
	List<Member> members = new ArrayList<Member>();
	int member_index = 0;
	String input_name = "";
	String currentid = "";
	String currentname = "";

	public void run() {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 32; i++) {
			articles.add(new Article(i, "title" + i, "body" + i));
		}
		while (true) {
			System.out.printf("명령어 : ");
			String command = sc.nextLine();

			if (command.equals("article add")) {
				System.out.println("== 게시물 생성 ==");
				System.out.printf("제목 입력 : ");
				String title = sc.nextLine();
				System.out.printf("내용 입력 : ");
				String body = sc.nextLine();
				articles.add(new Article(article_index, title, body));
				article_index++;

			} else if (command.startsWith("article list")) {
				int page = Integer.parseInt(command.split(" ")[2]);
				System.out.println("== 게시물 리스트 ==");
				System.out.printf("== %d 페이지 입니다.==\n", page);
				System.out.println("번호  /  제목  / 내용 / 생성일자 ");
				int items = 10;
				int start = 0;
				start = (page - 1) * items;
				int end = start +10;
				if (end >=articles.size()) {
					end = articles.size();
				}
				if (page <= 0) {
					page = 1;
				}
				if (start >= end) {
					System.out.printf("게시물은 %d페이지까지 존재합니다.\n",articles.size() / 10 +1);
				
				}
				for (int i = start; i < end; i++) {
					System.out.printf("%d  / %s  / %s /  %s\n ", articles.get(i).id, articles.get(i).title,
							articles.get(i).body,articles.get(i).regDate);

				}
			} else if (command.startsWith("article detail")) {
				try {
					int inputid = Integer.parseInt(command.split(" ")[2]);

					System.out.println("== 게시물 정보 == ");
					System.out.printf("게시물 번호 : %d\n", articles.get(inputid).id);
					System.out.printf("게시물 제목 : %s\n", articles.get(inputid).body);
					System.out.printf("게시물 내용 : %s\n", articles.get(inputid).title);
					System.out.printf("게시물 내용 : %s\n", articles.get(inputid).regDate);
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
			} else if (command.equals("exit")) {
				System.out.println("프로그램 종료");
				break;
			} else if (command.equals("member reg")) {

				System.out.printf("성함을 입력하세요 : ");
				String name = sc.nextLine();
				System.out.printf("사용할 id을 입력하세요 : ");
				String id = sc.nextLine();
				System.out.printf("비밀 번호를 입력하세요 :");
				String password = sc.nextLine();
				members.add(new Member(member_index, name, id, password));

			} else if (command.equals("member list")) {
				System.out.println("아이디 / 비밀번호");
				for (int i = 0; i < members.size(); i++) {

					System.out.printf("%s   / %s\n", members.get(i).name, members.get(i).password);

				}
			} else if (command.equals("member login")) {
				String loginId = "";

				int loginIdTryCount = 0;
				int loginIdMaxTryCount = 3;
				boolean loginIdMatched = false;

				while (true) {
					if(currentname != "") {
						System.out.println("로그인중인 아이디가 있습니다.");
						break;
					}
					System.out.printf("로그인아이디를 입력하세요 : ");
					loginId = sc.nextLine().trim();
					
					if (loginId.length() == 0) {
						System.out.println("로그인아이디를 입력해주세요.");
						loginIdTryCount++;
						continue;
					}

					for (Member member : members) {
						if (member.id.equals(loginId)) {
							loginIdMatched = true;
						
						}
					}

					if (loginIdMatched) {
						break;
					}

					System.out.println("존재하지 않는 회원 아이디 입니다.");
					loginIdTryCount++;

					if (loginIdTryCount >= loginIdMaxTryCount) {
						System.out.println("로그인아이디를 확인 후 다시 시도해주세요.");
						break;
					}
					if (loginIdMatched) {
						break;
					}

				}

				if (loginIdMatched == false) {
					continue;
				}
				

				/*
				 * System.out.printf("아이디를 입력하세요 : "); String name = sc.nextLine();
				 * 
				 * for (int i = 0; i < memberlist.size(); i++) { String getname =
				 * memberlist.get(i).name; if (name.equals(getname)) { input_name =
				 * memberlist.get(i).name; continue; } if (name != getname) { if (true) {
				 * System.out.println("해당하는 이름이 존재하지 않습니다."); break;
				 * 
				 * }
				 * 
				 * } break; }
				 */

				while (true) {
					System.out.printf("비밀번호를 입력하세요 : ");
					String passwd = sc.nextLine().trim();
					int loginpswdTryCount = 0;
					int loginpswdMaxTryCount = 3;
					boolean loginpswdMatched = false;

					for (int i = 0; i < members.size(); i++) {
						String lookingf = members.get(i).id; // 맴버리스트 이름 넣기

						if (lookingf.equals(loginId)) {
							String checkpw = members.get(i).password;

							if (passwd.equals(checkpw)) {
								loginpswdMatched = true;
								System.out.printf("%s님 환영합니다.\n", lookingf);
								currentid = members.get(i).id;
								currentname = members.get(i).name;

							}
							
							
							if (loginpswdMatched == false) {

								System.out.println("비밀번호가 맞지 않습니다.");
								loginpswdTryCount++;
							}

							if (loginpswdTryCount >= loginpswdMaxTryCount) {
								System.out.printf("비밀번호가 %d회 틀렸습니다.확인 후 다시 시도하세요", loginpswdMaxTryCount);
								break;
							}

						}
					}
					if (loginpswdMatched) {
						break;
					}
					
				}
			}

			else if (command.equals("member whoami")) {
				System.out.println("== 현재 사용자 정보 ==");
				System.out.printf("사용자 이름 :%s\n", currentname);
				System.out.printf("사용자 ID : %s\n", currentid);

			} else if (command.equals("member logout")) {
				System.out.printf("%s님 로그아웃 되었습니다.\n", currentid);
				currentid = "";
				currentname = "";
			}
			else {
				System.out.println("잘못된 명령어 입니다.");
			}
		}

		sc.close();
	}

}