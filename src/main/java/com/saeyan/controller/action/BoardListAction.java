package com.saeyan.controller.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

import util.BoardPage;

public class BoardListAction implements Action {

	@Override
	public void excute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardList.jsp";
		BoardDAO bDao=BoardDAO.getInstance(); // dao 객체생성
		
		// 페이징 처리 ***********************************************
		Map<String, Object> map =new HashMap<String, Object>();
		
		String searchField=request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
        if (searchWord != null) {
            // 쿼리스트링으로 전달받은 매개변수 중 검색어가 있다면 map에 저장
            map.put("searchField", searchField);
            map.put("searchWord", searchWord);
        }
        int totalCount = bDao.selectCount(map);  // 게시물 개수

        /* 페이지 처리 start */
       
        int pageSize = 10; // 페이지당 글수
        int blockPage = 5; // 목록 아랫쪽  페이지번호 수

        // 현재 페이지 확인
        int pageNum = 1;  // 기본값
        String pageTemp = request.getParameter("pageNum");
        if (pageTemp != null && !pageTemp.equals(""))
            pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정

        // 목록에 출력할 게시물 범위 계산
        int start = (pageNum - 1) * pageSize + 1;  // 첫 게시물 번호
        int end = pageNum * pageSize; // 마지막 게시물 번호
        map.put("start", start);
        map.put("end", end);
        /* 페이지 처리 end */

        // 뷰에 전달할 매개변수 추가
        String pagingString="";
        if(searchWord!=null) {//검색하는 경우
        	pagingString = BoardPage.pagingStr(totalCount, pageSize,
        			blockPage, pageNum, "/Board/BoardServlet?command=board_list&searchField="+searchField+"&searchWord="+searchWord);  // 바로가기 영역 HTML 문자열
        }else {//검색하지 않는 경우
        	pagingString = BoardPage.pagingStr(totalCount, pageSize,
        			blockPage, pageNum, "/Board/BoardServlet?command=board_list");  // 바로가기 영역 HTML 문자열
        }
        map.put("pagingString", pagingString);
        map.put("totalCount", totalCount);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);

        // 전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드        
        request.setAttribute("map", map);
		/* 페이징 처리.끝 */
		
        List<BoardVO> boardList=bDao.selectListPage(map); // 목록조회
		request.setAttribute("boardList", boardList); // view에 전달할 데이터
		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		dispatcher.forward(request, response); // 목록페이지로 이동.주소변경없음
		
	}

}
