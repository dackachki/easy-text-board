package com.sbs.example.eastextboard;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.*;

public class Article {
	public int id;
	public String title;
	public String body;
	public String regDate;
	
	Article(int id,String title,String body){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = format1.format(new Date());
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", body=" + body + ", regDate=" + regDate + "]";
	}
	

}
