package com.sbs.example.eastextboard;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
		
		int id = 1;
	
		while(true){
			
			
				
			System.out.printf("명령어 :");
		
		String command = scanner.nextLine();
		
		
		
		if (command.equals("article list")) {
			System.out.println("==게시판 리스트==");

		}
		else if (command.equals("aaa")) {
			System.out.println("==게시판 업로드==");
			

			String title;
			String body;
			
			
			System.out.printf("제목 : ");
			title = scanner.nextLine();
			System.out.printf("내용 : ");
			body = scanner.nextLine();
			


		
			System.out.printf("게시물 번호 :%d\n",id);
			System.out.println("게시물 제목 : "+title);
			System.out.println("게시물 내용 : "+body);
			id ++;
			
			
		}
		else if (command.equals("exit")){
			
			break;
		}
		else {
			System.out.println("wrong");

		}
		}
		scanner.close();
	
		
}
}