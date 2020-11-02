package com.sbs.example.eastextboard.service;

import java.util.List;

import com.sbs.example.eastextboard.Container.Container;
import com.sbs.example.eastextboard.DAO.MemberDAO;

import com.sbs.example.eastextboard.dto.Member;

public class MemberService {
	private MemberDAO memberDao;
	public String currentname = MemberDAO.currentname;
	public String currentid = MemberDAO.currentid;
	public int logined_member_index = MemberDAO.logined_member_index;
	
	public MemberService() {
		memberDao = Container.memberDAO;
	}

	public int join(String loginId, String loginPw, String name) {
			return memberDao.join(loginId, loginPw, name);
		
	}
	public int size() {
		return memberDao.getSize();
	}
	public List<Member> getMembers() {
		return MemberDAO.getMembers();
	}
	
	
	public boolean validid(String loginId) {
	return memberDao.validid(loginId);
	
}
}
	
