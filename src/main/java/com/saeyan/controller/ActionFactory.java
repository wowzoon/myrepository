package com.saeyan.controller;

import com.saeyan.controller.action.Action;
import com.saeyan.controller.action.BoardCheckPassAction;
import com.saeyan.controller.action.BoardCheckPassFormAction;
import com.saeyan.controller.action.BoardDeleteAction;
import com.saeyan.controller.action.BoardListAction;
import com.saeyan.controller.action.BoardUpdateAction;
import com.saeyan.controller.action.BoardUpdateFormAction;
import com.saeyan.controller.action.BoardViewAction;
import com.saeyan.controller.action.BoardWriteAction;
import com.saeyan.controller.action.BoardWriteFormAction;

public class ActionFactory {
	// singleton pattern
	
	private static ActionFactory instance=new ActionFactory();
	private ActionFactory() {
		super();
	}
	public static ActionFactory getInstance() {
		return instance;
	};
	
	// command 별로 action 생성해서 리턴
	public Action getAction(String command) {
		Action action=null;
		System.out.println("ActionFactory : "+command);
		if(command.equals("board_list")) {
			action=new BoardListAction(); // 목록
		}else if(command.equals("board_write_form")) {
			action=new BoardWriteFormAction(); // 등록화면
		}else if(command.equals("board_write")) {
			action=new BoardWriteAction(); // 등록처리
		}else if(command.equals("board_view")) {
			action=new BoardViewAction(); // 글보기
		}else if(command.equals("board_check_pass_form")) {
			action=new BoardCheckPassFormAction(); // 비밀번호 입력화면
		}else if(command.equals("board_check_pass")) {
			action=new BoardCheckPassAction();
		}else if(command.equals("board_update_form")) {
			action=new BoardUpdateFormAction();
		}else if(command.equals("board_update")) {
			action=new BoardUpdateAction();
		}else if(command.equals("board_delet")) {
			action=new BoardDeleteAction();
		}
		return action;
	}
}
