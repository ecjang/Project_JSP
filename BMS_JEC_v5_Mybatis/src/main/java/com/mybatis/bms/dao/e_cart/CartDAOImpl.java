package com.mybatis.bms.dao.e_cart;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mybatis.bms.dto.BookDTO;
import com.mybatis.bms.dto.CartDTO;


@Repository
public class CartDAOImpl implements CartDAO {
	
	@Inject
	private SqlSession sqlSession;



	@Override
	public int insert_cart_01(BookDTO dto) {
		System.out.println("    : insert_cart_01() 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.insert_cart_01(dto);
	}


	@Override
	public int insert_cart_02(BookDTO dto) {
		System.out.println("    : insert_cart_02() 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.insert_cart_02(dto);
	}


	@Override
	public int insert_cart_03(BookDTO dto) {
		System.out.println("    : insert_cart_03() 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.insert_cart_03(dto);
	}


	@Override
	public int cnt_cart() {
		System.out.println("    : cnt_cart() 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.cnt_cart();
	}


	@Override
	public List<CartDTO> get_carts(Map<String, Object> daoMap) {
		System.out.println("    : get_carts(daoMap) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.get_carts(daoMap);
	}

	
	@Override
	public int cnt_cartquan(int c_num) {
		System.out.println("    : cnt_cartquan(c_num) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao. cnt_cartquan(c_num);
	}
	

	@Override
	public int cnt_bookquan(int b_num) {
		System.out.println("    : cnt_bookquan(b_num) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.cnt_bookquan(b_num);
	}
	

	@Override
	public int quanminus_cart_01(Map<String, Object> daoMap) {
		System.out.println("    : quanminus_cart_01(daoMap) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.quanminus_cart_01(daoMap);
	}


	@Override
	public int quanminus_cart_02(Map<String, Object> daoMap) {
		System.out.println("    : quanminus_cart_02(daoMap) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.quanminus_cart_02(daoMap);
	}
	

	@Override
	public int quanplus_cart_01(Map<String, Object> daoMap) {
		System.out.println("    : quanplus_cart_01(daoMap) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.quanplus_cart_01(daoMap);
	}


	@Override
	public int quanplus_cart_02( Map<String, Object> daoMap) {
		System.out.println("    : quanplus_cart_02(daoMap) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.quanplus_cart_02(daoMap);
	}


	@Override
	public int delete_cart_byc_num( int c_num ) {
		System.out.println("    : delete_cart_byc_num(c_num) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.delete_cart_byc_num(c_num);
	}

	
	@Override
	public CartDTO get_cart(Map<String, Object> daoMap) {
		System.out.println("    : get_cart(daoMap) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.get_cart(daoMap);
	}


	
	@Override
	public int insert_order(CartDTO dto) {
		System.out.println("    : insert_order(dto) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.insert_order(dto);
	}


	@Override
	public List<CartDTO> getall_cart(int m_num) {
		System.out.println("    : getall_cart(m_num) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.getall_cart(m_num);
	}
	


	@Override
	public int allinsert_order( CartDTO dto ) {
		System.out.println("    : allinsert_order(carts) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.allinsert_order(dto);
	}


	@Override
	public int delete_cart_bym_num(int m_num) {
		System.out.println("    : delete_cart_bym_num(m_num) 메소드 실행 ");
		CartDAO dao = sqlSession.getMapper(CartDAO.class);
		return dao.delete_cart_bym_num(m_num);
	}
	
	

	
}
