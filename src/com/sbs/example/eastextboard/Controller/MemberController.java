package com.sbs.example.eastextboard.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.eastextboard.Member;

public class MemberController extends Controller {

	private List<Member> members;
	private int member_index;
	String input_name = "";
	String currentid = "";
	String currentname = "";

	public MemberController() {
		member_index = 0;
		members = new ArrayList<>();
	}

	private int join(String regid, String name, String password) {

		Member member = new Member();

		member.index = member_index + 1;
		member.name = name;
		member.password = password;
		member.id = regid;
		;

		members.add(member);
		member_index = member.index;

		return member.index;
	}

	public void run(Scanner sc, String command) {
		if (command.equals("member reg")) {

			System.out.println("== 회원가입 ==");

			String loginId = "";
			String loginPw;
			String name;
			int regTryC = 0;
			int regMaxC = 3;
			boolean idpass = false;
			while (true) {
				if (regTryC >= regMaxC) {
					System.out.println("잠시 후 다시 시도하세요");
					break;
				}
				System.out.printf("로그인아이디 : ");
				loginId = sc.nextLine().trim();

				if (loginId.length() == 0) {
					regTryC++;
					continue;
				}
				else if(validid(loginId) == false) {
					System.out.println("이미 사용중인 아이디 입니다.");
					regTryC++;
					continue;
				}
					else if(validid(loginId) == true) {
					break;
				}
				}
				

				System.out.printf("로그인비번 : ");
				loginPw = sc.nextLine();
				System.out.printf("이름 : ");
				name = sc.nextLine();

				int id = join(loginId, loginPw, name);

				System.out.printf("%d번 회원이 생성되었습니다.\n", id);

			}
		

			else if (command.equals("member list")) {
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
				if (currentname != "") {
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

				if (loginIdMatched == false) {
					continue;
				}

			}

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
	}

	private boolean validid(String loginId) {
		for(Member member : members) {
			if(member.id.equals(loginId)) {
				return false;
			}
		}
		return true;
	}
}