package com.sbs.example.eastextboard.Controller;

import com.sbs.example.eastextboard.dto.Member;
import com.sbs.example.eastextboard.service.MemberService;
import com.sbs.example.eastextboard.Container.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import com.sbs.example.eastextboard.Container.Container;
import com.sbs.example.eastextboard.Session.Session;

public class MemberController extends Controller {
	private MemberService memberService;

	
	public MemberController() {
		memberService = Container.memberService;
	}

	public void run(Scanner sc, String command) {

		if (command.equals("member reg")) {

			System.out.println("== 회원가입 ==");

			String loginId = "";
			String loginPw;
			String name;
			int regTryC = 0;
			int regMaxC = 3;

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
				} else if (validid(loginId) == false) {
					System.out.println("이미 사용중인 아이디 입니다.");
					regTryC++;
					continue;
				} else if (validid(loginId) == true) {
					break;
				}
			}

			System.out.printf("로그인비번 : ");
			loginPw = sc.nextLine();
			System.out.printf("이름 : ");
			name = sc.nextLine();

			int id = memberService.join(loginId, loginPw, name);

			System.out.printf("%d번 회원이 생성되었습니다.\n", id);

		}

		else if (command.equals("member list")) {
			System.out.println("아이디 / 비밀번호");
			for (int i = 0; i < memberService.size(); i++) {

				System.out.printf("%s   / %s\n", memberService.getMembers().get(i).id, memberService.getMembers().get(i).password);

			}
		} else if (command.equals("member login")) {
			String loginId = "";

			int loginIdTryCount = 0;
			int loginIdMaxTryCount = 3;
			boolean loginIdMatched = false;

			while (true) {
				if (memberService.currentname != "") {
					System.out.println("로그인중인 아이디가 있습니다.");
					return;
				}
				System.out.printf("로그인아이디를 입력하세요 : ");
				loginId = sc.nextLine().trim();

				if (loginId.length() == 0) {
					System.out.println("로그인아이디를 입력해주세요.");
					loginIdTryCount++;
					continue;
				}

				for (Member member : memberService.getMembers()) {
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
				String checkpw = "";

				for (int i = 0; i < memberService.size(); i++) {
					String lookingf = memberService.getMembers().get(i).id; // 맴버리스트 이름 넣기

					if (lookingf.equals(loginId)) {
						checkpw = memberService.getMembers().get(i).password;

						if (passwd.equals(checkpw)) {
							loginpswdMatched = true;
							System.out.printf("%s님 환영합니다.\n", lookingf);
							System.out.printf("가입일 : %s\n",memberService.getMembers().get(i).joineddate);

							memberService.currentid = memberService.getMembers().get(i).id;
							memberService.currentname =memberService.getMembers().get(i).name;

							Container.session.loginedMemberId = memberService.getMembers().get(i).index;
							Container.session.writerindex = i;

						}
						if (!passwd.equals(checkpw)) {

							System.out.println("비밀번호가 맞지 않습니다.");
							loginpswdTryCount++;
							System.out.printf("%d\n", loginpswdTryCount);

						}

					}

				}

				if (loginpswdTryCount >= loginpswdMaxTryCount) {
					System.out.printf("비밀번호가 %d회 틀렸습니다.확인 후 다시 시도하세요", loginpswdMaxTryCount);
					break;
				}
				if (loginpswdMatched) {
					break;
				}

			}
		}

		else if (command.equals("member whoami")) {
			if (Container.session.isLogined()) {
				System.out.println("== 현재 사용자 정보 ==");
				System.out.printf("사용자 이름 :%s\n", memberService.currentname);
				System.out.printf("사용자 ID : %s\n", memberService.currentid);
			} else if (Container.session.isLogined() == false) {
				System.out.println("로그인 후 다시 이용하세요");
				return;
			}
		} else if (command.equals("member logout")) {
			if (Container.session.isLogined()) {
				System.out.printf("%s님 로그아웃 되었습니다.\n", memberService.currentname);
				memberService.currentid = "";
				memberService.currentname = "";
				memberService.logined_member_index = 0;
				Container.session.loginedMemberId = 0;
				Container.session.writerindex = 0;

			} else if (Container.session.isLogined() == false) {
				System.out.println("로그인된 사용자가 없습니다.");
				return;
			}
		} else if (command.equals("member modify")) {
			if (Container.session.isLogined() == false) {
				System.out.println("로그인 후 다시 시도하세요");
			}
			System.out.printf("변경할 이름 입력 :");
			String changedname = sc.nextLine();
			System.out.printf("변경할 아이디 입력 :");
			String changedid = sc.nextLine();
			System.out.printf("변경할 비밀번호 입력 :");
			String changedpwd = sc.nextLine();
			memberService.getMembers().get(memberService.logined_member_index).name = changedname;
			memberService.getMembers().get(memberService.logined_member_index).id = changedid;
			memberService.getMembers().get(memberService.logined_member_index).password = changedpwd;
			System.out.println("회원 정보가 변경되었습니다");
		}
	}

	

	private boolean validid(String loginId) {
		return memberService.validid(loginId);
	}
}