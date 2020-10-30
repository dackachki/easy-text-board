package com.sbs.example.eastextboard.Container;

import com.sbs.example.eastextboard.Session.Session;

public class Container {
	public static Session session;

	static {
		session = new Session();
	}

}
