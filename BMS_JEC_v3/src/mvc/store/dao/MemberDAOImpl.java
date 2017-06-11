package mvc.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.store.dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {

	DataSource dataSource; //컨넥션 객체를 보관

	private static MemberDAOImpl instance = new MemberDAOImpl();
	public static MemberDAOImpl getInsatance() {
		return instance;
	}
	
	
	// 생성자 	
	public MemberDAOImpl(){
		try{
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
			
		}catch( Exception e ){e.printStackTrace();}
	}
	
	// 명수 구하기
	@Override
	public int num() {
		System.out.println("    : num() 메소드 실행");
		
		int n = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = dataSource.getConnection();
			
			String sql = "SELECT COUNT(*) FROM MEMBER";
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()){
				n = rs.getInt(1);
			}
			
		}catch( Exception e ){
			e.printStackTrace();
		}finally{
			try{
				if( conn!=null ) conn.close();	
				if( pstmt!=null ) pstmt.close();	
				if( rs!=null ) rs.close();	
			}catch( Exception e ){
				e.printStackTrace();
			}
		}
		
		return n;
	}
	
	// 중복 확인 부분
	@Override
	public int idCheck(String strid) {
		
		System.out.println("    : idCheck메소드 실행");
		
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("    : idcheck 메소드의  id값 : " + strid);
		
		try{
			conn = dataSource.getConnection();		// 커넥션에 연결
			
			String sql = "SELECT * FROM MEMBER WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strid);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){ cnt = 1;
			} else { cnt = 0;}
			
		} catch(SQLException e){	e.printStackTrace();
		} finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(SQLException e2){
				e2.printStackTrace();
			}
		}
		System.out.println("    : idcheck의 cnt값 : " + cnt);
		return cnt;
	}
	
	// 회원 가입 등록
	@Override
	public int mem_insert(MemberDTO dto) {
		System.out.println("  -> mem_insert(dto) 메소드 실행");
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = dataSource.getConnection();
			String sql = "INSERT INTO MEMBER "
					+ "(M_NUM, ID, PW, NAME, JUMIN1, jumin2, HP, EMAIL, REG_DATE, KIND)"
					+ "VALUES(mem_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getJumin1());
			pstmt.setString(5, dto.getJumin2());
			pstmt.setString(6, dto.getHp());
			pstmt.setString(7, dto.getEmail());
			pstmt.setTimestamp(8, dto.getReg_date());
			pstmt.setInt(9, 2);
			
			cnt = pstmt.executeUpdate();
			System.out.println(" - cnt : " + cnt +"[ 1 입력완료, 0 입력오류 ]" );
			
			if (cnt>0) System.out.println(" - 입력이 정상적으로 완료 되었습니다.");
			else System.out.println(" - 입력도중 오류가 발생했습니다.");
			
			/*---------------------------------------------------*/
			/*
			String sql2 = "SELECT * FROM MEMBER WHERE id=?";
			pstmt.close();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, dto.getId());
			rs=pstmt.executeQuery();
			
			while( rs.next() ){
				System.out.println("  - 테스트 : insert된 값을 출력 ");
				System.out.println(
					"  --> 아이디 : " 	+ rs.getString("ID") 		+ "\n"+
					"  --> 비번 : " 	+ rs.getString("PW") 	+ "\n"+
					"  --> 이름 : " 	+ rs.getString("Name") 		+ "\n"+
					"  --> 주민1 : " 	+ rs.getString("jumin1") 	+ "\n"+
					"  --> 주민2 : " 	+ rs.getString("jumin2") 	+ "\n"+
					"  --> 연락처 : " 	+ rs.getString("HP") 		+ "\n"+
					"  --> 이메일 : " 	+ rs.getString("EMAIL") 	+ "\n"+
					"  --> 가입일 : " 	+ rs.getString("REG_DATE") 	+ "\n"
				);
				
			}
			*/
			
			
		}catch( Exception e ){
			e.printStackTrace();
		} finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			}catch( Exception e){
				e.printStackTrace();;
			}
		}
		
		return cnt;
	}
	
	// LoginProHandler에서 요청 : 아이디와 비밀번호가 db에 있는지 체크
	// cnt 값을 리턴 ( 1: 아이디, 패스워드 일치, 0 : 패스워드가 불일치, -1:아이디,패스워드 둘다 불일치 )
	@Override
	public int check(String id, String pwd) {
		
		System.out.println("    : check(id,pwd)매소드 실행");
		
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = dataSource.getConnection();
			
			String sql = "SELECT * FROM MEMBER WHERE id=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()){
				
				System.out.println("    : 아이디가 일치합니다.");
				
				String memid = rs.getString(2);
				String mempw = rs.getString(3);
				
				if ( mempw.equals(pwd) ){ 
					System.out.println("    : 비밀번호가 일치합니다."); 
					cnt=1; 
					
				} else { 
					cnt=-1; 
					System.out.println("    : 비밀번호가 일치하지 않습니다.");  }
				
			} else { 
				System.out.println("    : 아이디가 일치하지 않습니다."); 
				cnt=0; 
			}	

			
		}catch( Exception e ){
			e.printStackTrace();
		}finally{
			try{
				if( conn!=null ) conn.close();	
				if( pstmt!=null ) pstmt.close();	
				if( rs!=null ) rs.close();	
			}catch( Exception e ){
				e.printStackTrace();
			}
			
		}
		
		return cnt;	
	}

	// 회원탈퇴,  DeleteProHandler에서 요청
	@Override
	public int delete(String strid) {
		
		System.out.println("    : delete(id) 매소드 실행");
		
		int cnt=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn=dataSource.getConnection();
			String sql="DELETE MEMBER WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strid);
			pstmt.executeQuery();
			cnt = pstmt.executeUpdate();
			
			if( cnt <= 0 ){
				System.out.println("    : 정상적으로 회원정보가 삭제");
				cnt=1;
			}else{
				System.out.println("    : 삭제도중 오류가 발생");
				cnt=0;
			} 
			
		}catch( Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(conn!=null)	conn.close();
				if(pstmt!=null)	pstmt.close();
			}catch(Exception e2){ 
				e2.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	// 회원정보 조회,  ModifyViewHandler에서 요청
	@Override
	public MemberDTO getMember(String strid) {
		
		System.out.println("    : getMember() 매소드를 실행");
		
		int cnt=0;
		Connection conn=null;; PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		// 바구니를 생성
		MemberDTO dto = new MemberDTO();
		
		try{
			conn = dataSource.getConnection();
			String sql="SELECT * FROM MEMBER WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strid);
			rs=pstmt.executeQuery();
			
			// rs를 읽어서 바구니에 담는다.
			
			while( rs.next() ){
				dto.setId( rs.getString("ID") );
				dto.setPasswd( rs.getString("PW"));
				dto.setName( rs.getString("NAME"));
				dto.setJumin1( rs.getString("JUMIN1"));
				dto.setJumin2( rs.getString("JUMIN2"));
				dto.setHp( rs.getString("HP"));
				dto.setEmail( rs.getString("EMAIL"));
				dto.setReg_date( rs.getTimestamp("REG_DATE"));
			}
			/*
			System.out.println("  - 테스트 쿼리값 출력 : " + 
				dto.getName() +" / "+ dto.getPasswd() +" / "+ dto.getName() +"\n  - "+
				dto.getJumin1() +" / "+ dto.getJumin2() +" / "+ dto.getHp() +"\n  - "+
				dto.getEmail() +" / "+ dto.getReg_date());
			*/

		}catch( SQLException e){ e.printStackTrace();
		}finally{
			try{
				if( conn!=null ) conn.close();
				if( pstmt!=null ) pstmt.close();
				if( rs!=null ) rs.close();
			}catch( Exception e1){ e1.printStackTrace(); }
		}
		
		return dto;
	}
	
	// 회원정보 수정
	@Override
	public int update(MemberDTO dto) {
		
		System.out.println("    : update(dto) 매소드 실행");
		
		int cnt = 0; Connection conn = null; 
		PreparedStatement pstmt=null; ResultSet rs = null; 	
		
		try{
			
			conn = dataSource.getConnection();
			String sql = "UPDATE MEMBER SET PW=?, HP=?, EMAIL=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPasswd());
			pstmt.setString(2, dto.getHp());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getId());
			
			cnt = pstmt.executeUpdate();
			System.out.println("    : cnt의 값 : " + cnt);
			pstmt.close();
			
			if(cnt>0) {
				System.out.println("    : 정상적으로 수정완료");
				sql = "SELECT * FROM MEMBER WHERE id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getId());
				rs = pstmt.executeQuery();
				
				while( rs.next() ){
					System.out.println("    : update된 값을 출력 ");
					System.out.println(
						"    : 아이디 : " 	+ rs.getString("ID") 		+ "\n"+
						"    : 비번 : " 		+ rs.getString("PW") 	+ "\n"+
						"    : 이메일 : " 	+ rs.getString("EMAIL") 	+ "\n"+
						"    : 연락처 : " 	+ rs.getString("HP") 		+ "\n");
				}
				
			}else System.out.println("    : 수정중 오류 발생");
			
			
			
			
		} catch (SQLException e){ e.printStackTrace();
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			}catch(SQLException e){ e.printStackTrace();}
		}
		return cnt;
	}

	
	// 회원종류 판별
	@Override
	public int kind(String id) {
		
		System.out.println("    : kind(id)매소드 실행");
		
		int memkind = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = dataSource.getConnection();
			
			String sql = "SELECT KIND FROM MEMBER WHERE id=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()){
				memkind = rs.getInt("KIND");
			}
			
		}catch( Exception e ){
			e.printStackTrace();
		}finally{
			try{
				if( conn!=null ) conn.close();	
				if( pstmt!=null ) pstmt.close();	
				if( rs!=null ) rs.close();	
			}catch( Exception e ){
				e.printStackTrace();
			}
			
		}
		
		return memkind;	
		
	}

	// 회원번호 구하기
	@Override
	public int m_num(String id) {
		
		System.out.println("    : m_num(id)매소드 실행");
		
		int m_num = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = dataSource.getConnection();
			
			String sql = "SELECT M_NUM FROM MEMBER WHERE id=?";
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()){
				m_num = rs.getInt("M_NUM");
			}
			
		}catch( Exception e ){
			e.printStackTrace();
		}finally{
			try{
				if( conn!=null ) conn.close();	
				if( pstmt!=null ) pstmt.close();	
				if( rs!=null ) rs.close();	
			}catch( Exception e ){
				e.printStackTrace();
			}
			
		}
		
		return m_num;	
	}

}
