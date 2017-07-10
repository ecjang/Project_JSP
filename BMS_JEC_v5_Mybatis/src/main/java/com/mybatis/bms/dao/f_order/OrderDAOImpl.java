package com.mybatis.bms.dao.f_order;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mybatis.bms.dao.e_cart.CartDAO;
import com.mybatis.bms.dto.BookDTO;
import com.mybatis.bms.dto.OrderDTO;



@Repository
public class OrderDAOImpl implements OrderDAO {
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public int change_orderstate_01(BookDTO dto) {
		System.out.println("    : change_orderstate_01(BookDTO DTO) 매소드 실행");
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.change_orderstate_01(dto);
	}

	@Override
	public int change_orderstate_02(BookDTO dto) {
		System.out.println("    : change_orderstate_02(BookDTO DTO) 매소드 실행");
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.change_orderstate_02(dto);
	}

	@Override
	public int change_orderstate_03(BookDTO dto) {
		System.out.println("    : change_orderstate_03(BookDTO DTO) 매소드 실행");
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.change_orderstate_03(dto);
	}
	

	@Override
	public int cnt_order() {
		System.out.println("    : cnt_order() 매소드 실행");
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.cnt_order();
	}

	@Override
	public List<OrderDTO> get_orders( Map<String, Object> daoMap ) {
		System.out.println("    : get_orders() 매소드 실행");
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.get_orders(daoMap);
	}

	@Override
	public int sum_order ( ){
		System.out.println("    : sum_order() 매소드 실행");
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.sum_order();
	}




	@Override
	public int input_order_01(int o_num) {
		System.out.println("    : input_order_01(o_num) 메소드 실행 ");
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.input_order_01(o_num);
	}


	@Override
	public int input_order_02(Map<String, Object> daoMap) {
		System.out.println("    : input_order_02(daoMap) 메소드 실행 ");
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.input_order_02(daoMap);
	}


	@Override
	public int input_order_03( int o_num) {
		System.out.println("    : input_order_03(daoMap) 메소드 실행 ");
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.input_order_03(o_num);
	}
	
	@Override
	public int input_order_04(Map<String, Object> daoMap) {
		System.out.println("    : input_order_04(daoMap) 메소드 실행 ");
		OrderDAO dao = sqlSession.getMapper(OrderDAO.class);
		return dao.input_order_04(daoMap);
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
