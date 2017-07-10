package com.mybatis.bms.dao.a_main;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mybatis.bms.dto.BookDTO;

@Repository
public class MainDAOImpl implements MainDAO {

	
	@Inject
	private SqlSession sqlSession;
	
	
	// 검색된 도서 개수
	@Override
	public int cnt_search(String str) {
		System.out.println("    : cnt_search() 매소드 실행");
		MainDAO dao = sqlSession.getMapper(MainDAO.class);
		int cnt = dao.cnt_search(str);
		return cnt;
		
	}

	// 신간 도서 조회(배열)
	@Override
	public List<BookDTO> get_newbooks(int cnt) {
		System.out.println("    : get_newbooks() 메소드 실행 ");
		List<BookDTO> vos = null;
		MainDAO dton = sqlSession.getMapper(MainDAO.class);
		vos = dton.get_newbooks(cnt);
		return vos;
	}

	
	// 검색된 도서 정보 조회(배열)
	@Override
	public List<BookDTO> get_searchbooks( Map<String,Object> daoMap ) {
		System.out.println("    : get_searchbooks() 메소드 실행 ");
		List<BookDTO> vos = null;
		MainDAO dao = sqlSession.getMapper(MainDAO.class);
		vos = dao.get_searchbooks(daoMap);
		return vos;
	}

	
	// 도서 정보 조회
	@Override
	public BookDTO get_book(int b_num) {
		System.out.println("    : get_book() 메소드 실행 ");
		BookDTO vo = null;
		MainDAO dao = sqlSession.getMapper(MainDAO.class);
		vo = dao.get_book(b_num);
		return vo;
	}

	
}
