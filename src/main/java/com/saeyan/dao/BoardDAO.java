package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.saeyan.dto.BoardVO;

import util.DBManager;

public class BoardDAO {
	// Singleton Pattern
	
	private BoardDAO() {}	// private 생성자
	
	private static BoardDAO instance=new BoardDAO();
			// private static instance 생성
	public static BoardDAO getInstance() {
		// 외부에서는 BoardDAO.getInstance() 형식으로 사용
		return instance;	// instance 리턴
	}
	
	// 전체 글 수
	public int selectCount (Map<String, Object> map) {
		int totalCount=0;
		String sql= "select count(*) from board";
		if (map.get("searchWord")!=null) {
			sql+="where " + map.get("searchField")+" "
					+" like '%" + map.get("searchWord")+"%'";
		}
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				totalCount=rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return totalCount;
	}
	
	// 검색 조건에 맞는 게시물 목록을 반환합니다. ( 페이징 기능 지원 )
	public List<BoardVO> selectListPage(Map<String,Object> map) {        
        String sql = "select * "
        			+ "from ( "
                     + "    select t.*, rownum rnum from ( "
                     + "        select * from board ";

        if (map.get("searchWord") != null)
        {
            sql += " where " + map.get("searchField")
                   + " like '%" + map.get("searchWord") + "%' ";
        }

        sql += "        order by num desc "
               + "    ) t "
               + " 	where rownum < ?) "
               + "where rnum >= ?";
        
        
        
        List<BoardVO> list = new ArrayList<BoardVO>();
        Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
        try {
        	conn=DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, map.get("end").toString());
            pstmt.setString(2, map.get("start").toString());
            rs = pstmt.executeQuery();
            
            while (rs.next()) {                
            	BoardVO bVo=new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				list.add(bVo);               
            }
        }
        catch (Exception e) {
            System.out.println("게시물 조회 중 예외 발생");
            e.printStackTrace();
        }finally {
        	DBManager.close(conn, pstmt, rs);
        }
        return list;
    }
	
	// 목록. >> 페이징 처리 안할때
//	public List<BoardVO> selectAllBoards(){
//		String sql = "select * from board order by num desc";
//		List<BoardVO> list = new ArrayList<BoardVO>();
//		Connection conn=null;
//		Statement stmt=null;
//		ResultSet rs=null;
//		try {
//			conn=DBManager.getConnection();
//			stmt=conn.createStatement();
//			rs=stmt.executeQuery(sql);
//			while(rs.next()) {
//				BoardVO bVo=new BoardVO();
//				bVo.setNum(rs.getInt("num"));
//				bVo.setName(rs.getString("name"));
//				bVo.setEmail(rs.getString("email"));
//				bVo.setPass(rs.getString("pass"));
//				bVo.setTitle(rs.getString("title"));
//				bVo.setContent(rs.getString("content"));
//				bVo.setReadcount(rs.getInt("readcount"));
//				bVo.setWritedate(rs.getTimestamp("writedate"));
//				list.add(bVo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, stmt, rs);
//		}
//		return list;
//	}
//	
	// 등록
	public void insertBoard(BoardVO bVo) {
			String sql="insert into board(num,name,email,pass,title,content)"
					+" values(board_seq.nextval,?,?,?,?,?)";
			Connection conn=null;
			PreparedStatement pstmt=null;
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bVo.getName());
				pstmt.setString(2, bVo.getEmail());
				pstmt.setString(3, bVo.getPass());
				pstmt.setString(4, bVo.getTitle());
				pstmt.setString(5, bVo.getContent());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	
	// 조회수 증가
	public void updateReadCount(String num) {
		String sql="update board set readcount=readcount+1 where num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 글보기
	public BoardVO selectOneBoarrdByNum(String num) {
		String sql="select * from board where num=?";
		BoardVO bVo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bVo=new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setPass(rs.getString("pass"));
				bVo.setEmail(rs.getString("email"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				bVo.setReadcount(rs.getInt("readcount"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bVo;
	}
	
	// 수정
	public void updateBoard(BoardVO bVo) {
		String sql="update board set name=?,email=?,pass=?,title=?,content=?"
				+ "where num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.setInt(6, bVo.getNum());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 삭제
	public void deleteBoard(String num) {
		String sql="delete from board where num=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=DBManager.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
}
