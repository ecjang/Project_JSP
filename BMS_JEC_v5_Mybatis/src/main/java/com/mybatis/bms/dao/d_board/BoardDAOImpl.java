package com.mybatis.bms.dao.d_board;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.mybatis.bms.dto.BoardDTO;
import com.mybatis.bms.dto.MemberDTO;


@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;

	
	@Override
	public int cnt_board(String kind) {
		System.out.println("    : cnt_board(kind) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.cnt_board(kind);
	}

	@Override
	public List<BoardDTO> get_boards(Map<String, Object> daoMap) {
		System.out.println("    : get_boards(daoMap) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.get_boards(daoMap);
	}

	@Override
	public BoardDTO get_board(int no) {
		System.out.println("    : get_board(num) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.get_board(no);
	}

	@Override
	public int add_viewcnt(int no) {
		System.out.println("    : add_viewcnt(num) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.add_viewcnt(no);
	}
	
	@Override
	public MemberDTO check_bym_num(int Mnum) {
		System.out.println("    : check_bym_num(Mnum) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.check_bym_num(Mnum);
	}

	
	@Override
	public int insert_board_01() {
		System.out.println("    : insert_board_01(dto) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.insert_board_01();
	}

	@Override
	public int insert_board_02( Map <String,Object> daoMap ) {
		System.out.println("    : insert_board_02(dto) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.insert_board_02( daoMap );
	}

	@Override
	public int insert_board_03(BoardDTO dto) {
		System.out.println("    : insert_board_03(dto) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.insert_board_03(dto);
	}
	

	@Override
	public int upadte_board(BoardDTO dto) {
		System.out.println("    : upadte_board(dto) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.upadte_board(dto);
	}

	
	@Override
	public BoardDTO delete_board_01(int num) {
		System.out.println("    : delete_board_01(num) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.delete_board_01(num);
	}

	
	@Override
	public List<BoardDTO> delete_board_02(Map<String, Object> daoMap) {
		System.out.println("    : delete_board_02(daoMap) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.delete_board_02( daoMap);
	}

	
	@Override
	public int delete_board_03(Map<String, Object> daoMap) {
		System.out.println("    : delete_board_03(daoMap) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.delete_board_03( daoMap);
	}

	
	@Override
	public int delete_board_04(int num) {
		System.out.println("    : delete_board_04(num) 매소드 실행");
		BoardDAO dao = sqlSession.getMapper(BoardDAO.class);
		return dao.delete_board_04(num);
	}
	
	
	
	
	
	
}
