package com.sbs.example.eastextboard.DAO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import com.sbs.example.eastextboard.dto.Member;

public class MemberDAO {
	private static List<Member> members;
	private int member_index;
	String input_name = "";
	public static String currentid = "";
	public static String currentname = "";
	public static int logined_member_index = 0;
	
	public static List<Member> getMembers() {
		return members;
	}	

	public MemberDAO() {
		member_index = 0;
		members = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			join("user" + i, "user" + i, "유저" + i);
		}
	}
	public int join(String regid, String password, String name) {

		Member member = new Member();

		member.index = member_index + 1;
		member.name = name;
		member.password = password;
		member.id = regid;
		DateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
		member.joineddate = format.format(Calendar.getInstance().getTime());

		members.add(member);
		member_index = member.index;

		return member.index;
	}
	public int getSize() {
		return members.size();
	}
	
	
	public boolean validid(String loginId) {
	for (Member member : getMembers()) {
		if (member.id.equals(loginId)) {
			return false;
		}
	}
	return true;
}
}
	



	


