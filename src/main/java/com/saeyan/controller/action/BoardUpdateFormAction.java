package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardUpdateFormAction implements Action{

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정화면으로
		String url="/board/boardUpdate.jsp";
		String num=request.getParameter("num");
		BoardDAO bDao=BoardDAO.getInstance();
		BoardVO bVo=bDao.selectOneBoarrdByNum(num); // 글 상세내용
		request.setAttribute("board", bVo); // board 라는 이름으로 vo 전달
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
