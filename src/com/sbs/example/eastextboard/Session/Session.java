package com.sbs.example.eastextboard.Session;

import com.sbs.example.eastextboard.dto.Board;

public class Session {

public int loginedMemberId;
	
	public boolean isLogined() {
		return loginedMemberId != 0;
	}
	
	public int writerindex;
	public String writerid;
	public Board presentBoard;
	 
	
}
