package com.mybatis.bms.dao.b_member;

import org.apache.ibatis.session.SqlSession;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.mybatis.bms.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public MemberDTO check_login( Map<String,Object> daoMap) {
		System.out.println("    : check_login(id,pwd)매소드 실행");
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.check_login(daoMap);
	}

	@Override
	public int cnt_member() {
		System.out.println("    : cnt_member()매소드 실행");
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.cnt_member();
	}
	
	
	@Override
	public int check_id(String id) {
		System.out.println("    : check_id(id)매소드 실행");
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.check_id(id);
	}

	
	@Override
	public int insert_member(MemberDTO dto) {
		System.out.println("    : insert_member(dto)매소드 실행");
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.insert_member(dto);
	}

	
	@Override
	public int delete_member(String id) {
		System.out.println("    : delete_member(id)매소드 실행");
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.delete_member(id);
	}

	
	@Override
	public int get_member(String id) {
		System.out.println("    : get_member(id)매소드 실행");
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.get_member(id);
	}

	@Override
	public int update_member(MemberDTO dto) {
		System.out.println("    : update_member(dto)매소드 실행");
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.update_member(dto);
	}

	
	@Override
	public MemberDTO check_login_bymnum(int Mnum) {
		System.out.println("    : check_login_bymnum(m_num)매소드 실행");
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		return dao.check_login_bymnum(Mnum);
	}
	
	
	
	
}
