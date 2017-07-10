package com.mybatis.bms.dao.c_book;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mybatis.bms.dto.BookDTO;


@Repository
public class BookDAOImpl implements BookDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	
	@Override
	public int cnt_book() {
		System.out.println("    : cnt_book() �żҵ� ����");
		BookDAO dao = sqlSession.getMapper(BookDAO.class);
		return dao.cnt_book(); 
	}

	@Override
	public ArrayList<BookDTO> get_books( Map<String,Object> daoMap  ) {
		System.out.println("    : get_books() �żҵ� ����");
		BookDAO dao = sqlSession.getMapper( BookDAO.class);
		return dao.get_books(daoMap);
	}

	@Override
	public BookDTO getbook(int b_num) {
		System.out.println("    : getbook() �żҵ� ����");
		BookDAO dao = sqlSession.getMapper( BookDAO.class);
		return dao.getbook(b_num);
	}

	@Override
	public int delete_book(int b_num) {
		System.out.println("    : delete_book(b_num) �żҵ� ����");
		BookDAO dao = sqlSession.getMapper( BookDAO.class);
		return dao.delete_book(b_num);
	}

	
	@Override
	public int insert_book(BookDTO dto) {
		System.out.println("    : insert_book(dto) �żҵ� ����");
		BookDAO dao = sqlSession.getMapper( BookDAO.class);
		return dao.insert_book(dto);
	}

	

	@Override
	public int upadte_book(BookDTO dto) {
		System.out.println("    : upadte_book(dto) �żҵ� ����");
		BookDAO dao = sqlSession.getMapper( BookDAO.class);
		return dao.upadte_book(dto);
	}


	
	
	
	
	
}
