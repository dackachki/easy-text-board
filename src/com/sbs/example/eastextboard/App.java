package com.sbs.example.eastextboard;

import java.text.SimpleDateFormat;

import java.util.*;

import com.sbs.example.eastextboard.Controller.ArticleController;
import com.sbs.example.eastextboard.Controller.MemberController;

public class App {

	public void run() {
		Scanner sc = new Scanner(System.in);
		

		MemberController memberController = new MemberController();
		ArticleController articleController = new ArticleController();
		
		while (true) {
			System.out.printf("명령어 입력 : ");
			String command = sc.nextLine();
			
			if (command.equals("exit")) {
				break;
			} else if (command.startsWith("article ")) {
				articleController.run(sc, command);
			} else if (command.startsWith("member ")) {
				memberController.run(sc, command);

			} else {
				System.out.println("명령어가 잘못 입력되었습니다.");
			}

		}
		sc.close();
	}

}