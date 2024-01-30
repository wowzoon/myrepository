package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardViewAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상세보기
		String url="/board/boardView.jsp";
		String num=request.getParameter("num");
		BoardDAO bDao=BoardDAO.getInstance();
		bDao.updateReadCount(num); // 조회수 증가
		BoardVO bVO=bDao.selectOneBoarrdByNum(num); // 글 상세내용
		request.setAttribute("board", bVO); // board라는 이름으로 vo 전달
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
