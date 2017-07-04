package mvc.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.store.dto.BoardDTO;

public class BoardDAOImpl implements BoardDAO {
	
	DataSource data;
	
	/* 1. 싱글톤 생성 -----------------------------------------------------*/
	private static BoardDAOImpl instance = new BoardDAOImpl();
	public static BoardDAOImpl getInstance(){
		return instance;
	}
	
	// 생성자 	
	public BoardDAOImpl(){
		try{
			Context context = new InitialContext();
			data = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch( Exception e ){e.printStackTrace();}
	}
		
		
	// getCount() : 게시판 개수 구하기
	@Override
	public int getCount(String kind) {
		
		System.out.println("    : getConut(kind) 매소드 실행");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String str = null;
		/*System.out.println("    : kind 값 : " + kind);*/
		
		if ( kind.equals("NOTICE")) 	str = "WHERE KIND ='NOTICE' " ;
		else if (kind.equals("QnA")) 	str = "WHERE KIND ='QnA' ";
		else if (kind.equals("FAQ")) 	str = "WHERE KIND ='FAQ' ";
		else if (kind.equals("EVENT")) 	str = "WHERE KIND ='EVENT' ";
		else   str = "WHERE KIND IN ( 'NOTICE', 'QnA', 'FAQ', 'EVENT','ALL' )";
		
		
		try{
			conn = data.getConnection();
			String sql = "SELECT COUNT(*) FROM BOARD " + str;
			
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) cnt=rs.getInt("COUNT(*)");
			
			System.out.println("    : 게시물 개수 : " + cnt);
			
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return cnt;
	}
	
	
	// getArticles() : 게시판 정보 가져오기
	@Override
	public ArrayList<BoardDTO> getArticles(int start, int end , String kind) {
	
		System.out.println("    : getArticles(start, end, kind) 매소드 실행");
		ArrayList<BoardDTO> dtos = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String str= null;
		
		if ( kind.equals("NOTICE")) 	str = "WHERE KIND ='NOTICE' " ;
		else if (kind.equals("QnA")) 	str = "WHERE KIND ='QnA' ";
		else if (kind.equals("FAQ")) 	str = "WHERE KIND ='FAQ' ";
		else if (kind.equals("EVENT")) 	str = "WHERE KIND ='EVENT' ";
		else  str = "WHERE KIND IN ( 'NOTICE', 'QnA', 'FAQ', 'EVENT','ALL' )";
		
		System.out.println("    : kind 값 : " + kind);
		
		try{
			conn = data.getConnection();
			String sql = 
			"SELECT * " + 
			"FROM ( SELECT M_NUM, NO, TITLE, WRITER, CONTENT, " + 
							"REG_DATE, VIEWS, REF, REF_STEP, " + 
							"REF_LEVEL, KIND, IP, rowNum rNum " + 
					"FROM ( SELECT * FROM BOARD " +
							str +
							"  ORDER BY REF DESC, REF_STEP ASC  " +
						 ") " +
					") " +
			"WHERE rNum >= ? AND rNum <= ? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dtos = new ArrayList<>(end-start+1);
				
				do{
					BoardDTO dto = new BoardDTO();
	
					dto.setM_NUM( rs.getInt("M_NUM")  );
					dto.setNO( rs.getInt("NO") );
					dto.setTITLE( rs.getString("TITLE") );
					dto.setWRITER( rs.getString("WRITER") );
					dto.setCONTENT(rs.getString("CONTENT"));
					dto.setREG_DATE(rs.getTimestamp("REG_DATE"));
					dto.setVIEWS(rs.getInt("VIEWS") );
					dto.setREF(rs.getInt("REF"));
					dto.setREF_STEP(rs.getInt("REF_STEP"));
					dto.setREF_LEVEL(rs.getInt("REF_LEVEL"));
					dto.setKIND(rs.getString("KIND"));
					dto.setIP(rs.getString("IP"));
					dto.setREF_LEVEL(rs.getInt("REF_LEVEL"));
					dto.setREF_STEP(rs.getInt("REF_STEP"));
					
					dtos.add(dto);
					
				}while(rs.next());
				System.out.println("    : 데이터 로딩 성공");
				
				
			} else {
				System.out.println("    : 데이터 로딩 중 오류 발생");
			}
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return dtos;
	}
	
	
	// getArticles() : 게시판 정보 가져오기 (넘버로)
	@Override
	public BoardDTO getArticles(int no) {
		System.out.println("    : getArticles() 메소드 실행");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		
		try{
			conn = data.getConnection();
			String sql = " SELECT * FROM BOARD WHERE NO=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new BoardDTO();
				
				dto.setM_NUM( rs.getInt("M_NUM")  );
				dto.setNO( rs.getInt("NO") );
				dto.setTITLE( rs.getString("TITLE") );
				dto.setWRITER( rs.getString("WRITER") );
				dto.setCONTENT(rs.getString("CONTENT"));
				dto.setREG_DATE(rs.getTimestamp("REG_DATE"));
				dto.setVIEWS(rs.getInt("VIEWS"));
				dto.setREF(rs.getInt("REF"));
				dto.setREF_STEP(rs.getInt("REF_STEP"));
				dto.setREF_LEVEL(rs.getInt("REF_LEVEL"));
				dto.setKIND(rs.getString("KIND"));
				dto.setIP(rs.getString("IP"));
				
				System.out.println("    : "+rs.getInt("NO")+"번째 게시물 호출 완료" );
			}
			
		} catch( SQLException e ){
			e.printStackTrace();
		} finally{
			
			try{ 
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch(SQLException e ){
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	
	// addview() : 조회수 증가
	public void addview(int no){
		
		System.out.println("    : addview() 메소드 실행");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		
		try{
			conn = data.getConnection();
			String sql = " UPDATE BOARD SET VIEWS=VIEWS+1 WHERE NO=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			rs = pstmt.executeQuery();
			
			
		} catch( SQLException e ){
			e.printStackTrace();
		} finally{
			
			try{ 
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch(SQLException e ){
				e.printStackTrace();
			}
		}
	}
	
	// pwCheck() : 아이디와 비밀번호 확인
	@Override
	public int pwCheck(int Mnum, String ps) {
		
		System.out.println("    : pwCheck() 메소드 실행");
		Connection conn = null; 
		PreparedStatement pstmt=null; 
		ResultSet rs=null;
		int cnt = 0;
		/*
		System.out.println("제대로 넘어 간건가1? : " + Mnum);
		System.out.println("제대로 넘어 간건가2? : " + ps);
		*/
		try{
			
			conn = data.getConnection();
			
			String sql = "SELECT * FROM MEMBER WHERE M_NUM=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Mnum);
			rs = pstmt.executeQuery();
			
			
			
			System.out.println("    : Mnum : " + Mnum);
			System.out.println("    : ps : " + ps);
			
			
			if(rs.next()){
				
				if( rs.getString("PW").equals(ps) ){
					System.out.println("    : 비밀번호가 일치 cnt=1");
					cnt = 1;
				}
				
			} else {
				System.out.println("    : 비밀번호가 불일치 cnt=0");
				
			}
			
			System.out.println("    : 일치하는 값이 없음 cnt=0");
				
			
		} catch( SQLException e){
			e.printStackTrace();
		} finally {
			
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch( SQLException e1  ){
				e1.printStackTrace();
			}
		} 
		
		return cnt;
	}
	

	// idCheck() : 아이디와 비밀번호 확인
	@Override
	public int idCheck(int Mnum) {
		
		System.out.println("    : idCheck() 메소드 실행");
		Connection conn = null; 
		PreparedStatement pstmt=null; 
		ResultSet rs=null;
		int cnt = 0;

		
		try{
			
			conn = data.getConnection();
			
			String sql = "SELECT * FROM MEMBER WHERE M_NUM=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Mnum);
			rs = pstmt.executeQuery();

			if(rs.next()){
				System.out.println("    : 회원번호 일치 cnt=1");
				cnt = 1;
				
			} else {
				System.out.println("    : 회원번호 불일치 cnt=0");
			}
				
			
		} catch( SQLException e){
			e.printStackTrace();
		} finally {
			
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch( SQLException e1  ){
				e1.printStackTrace();
			}
		} 
		
		return cnt;
	}
	
	
	// upadte() : 수정한 게시판를 등록
	@Override
	public int upadte(BoardDTO dto) {
		
		System.out.println("    : upadte() 메소드 실행 ");
		
		Connection conn = null; 
		PreparedStatement pstmt=null; 
		ResultSet rs = null;
		int cnt = 0;
		
		try{
			conn = data.getConnection();
			String sql = "UPDATE BOARD SET " + 
					"TITLE=?, CONTENT=?, KIND=? " +
					"WHERE NO=? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(	1, dto.getTITLE()	);
			pstmt.setString(	2, dto.getCONTENT()	);
			pstmt.setString(	3, dto.getKIND()	);
			pstmt.setInt(		4, dto.getNO()		);
			
			cnt = pstmt.executeUpdate();
			
			/*
			System.out.println("  제목 : " + dto.getTITLE());
			System.out.println("  내용 : " + dto.getCONTENT());
			System.out.println("  분류 : " + dto.getKIND());
			System.out.println("  번호 : " + dto.getNO());
			*/
			
			/*-------------------------------------------------*/
			
			/*
			sql=null;
			sql = "SELECT * FROM BOARD WHERE NO=?";
			
			pstmt.close();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNO());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println("  확인 제목 : " + rs.getString("TITLE"));
				System.out.println("  확인 내용 : " + rs.getString("CONTENT"));
				System.out.println("  확인 분류 : " + rs.getString("KIND"));
				System.out.println("  확인 번호 : " + rs.getInt("NO"));
			}
			*/
			
			System.out.println("    : 게시판 정보 입력 완료, cnt="+cnt);
			
		} catch( SQLException e){
			e.printStackTrace();
		} finally {
			
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
				
			} catch( SQLException e1  ){
				e1.printStackTrace();
			}
		} 
		
		return cnt;
	}
	
	
	@Override
	public int delete(int no) {
		
		System.out.println("    : delete() 매소드 실행 ");
		int cnt = 0;
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			
			conn = data.getConnection();
			
			sql="SELECT * FROM BOARD WHERE NO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ){	// 답글 존재 여부 확인
				
				System.out.println("    : 답글여부 확인");
				
				int ref = rs.getInt("ref");
				int ref_step = rs.getInt("ref_step");
				int ref_level = rs.getInt("ref_level");
				
				sql="SELECT * FROM BOARD " + 
					"WHERE REF=? AND REF_STEP=?+1 AND REF_LEVEL>? " ;
				
				pstmt.close();
				pstmt= conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, ref_step);
				pstmt.setInt(3, ref_level);
				rs.close();
				rs=pstmt.executeQuery();
				
				
				if( rs.next() ){	// 답글이 있으면 삭제 불가 (cnt=-1)
					cnt = -1;
					System.out.println("    : 답글존재, cnt값 : " + cnt);
					
				} else {	// 답글이 없는 경우
					System.out.println("    : 답글없음, update, delete 실행");
				
					sql="UPDATE BOARD SET REF_STEP=REF_STEP-1 " +
						"WHERE REF = ? AND REF_STEP > ? ";
					
					pstmt.close();
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, ref);
					pstmt.setInt(2, ref_step);
					pstmt.executeUpdate();
					
					
					sql="DELETE BOARD WHERE NO=? ";
					
					pstmt.close();
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, no);
					cnt = pstmt.executeUpdate();
					System.out.println("    : cnt 값 : " + cnt );
				}
			}
			
		} catch( SQLException e){
			e.printStackTrace();
		} finally {
			
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				
			} catch( SQLException e1  ){
				e1.printStackTrace();
			}
		} 
		
		return cnt;
	}

	
	
	// insert() : 작성한 글 업로드
	@Override
	public int insert(BoardDTO dto) {

		System.out.println("    : insert() 메소드 실행");
		
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = data.getConnection();
			
			int no = dto.getNO();
			int ref	= dto.getREF();
			int ref_step = dto.getREF_STEP();
			int ref_level = dto.getREF_LEVEL();
			
			/*
			System.out.println("    : no : " + no);
			System.out.println("    : ref : " + ref);
			System.out.println("    : ref_step : " + ref_step);
			System.out.println("    : ref_level : " + ref_level);
			*/
			
			String sql = null;
			
			if( no == 0 ){ // 새글일 경우
				System.out.println("    : 새글로 입력 ");
				sql="SELECT MAX(no) FROM BOARD";
				pstmt=conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if( rs.next() ){	
					ref = rs.getInt(1) + 1;
				} else {	
					ref = 1;
				}
				
				ref_step=0; 
				ref_level=0;
				
				
			} else {	// 답글인 경우
				System.out.println("    : 답글로 입력 ");	
				sql="UPDATE BOARD SET " + 
					"REF_STEP=REF_STEP+1 " + 
					"WHERE REF = ? AND REF_STEP > ? " ;	
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, ref_step);
				pstmt.executeUpdate();
				ref_step++; 
				ref_level++;
				
				/*
				System.out.println("    : ref_step값 : " + ref_step );
				System.out.println("    : ref_level값 : " + ref_level );
				*/
				
			}
			
			sql="INSERT INTO BOARD (NO, M_NUM, TITLE, WRITER, CONTENT, " +
				"REG_DATE, VIEWS, REF, REF_STEP, REF_LEVEL, KIND, IP) " +
				" VALUES (BOARD_SEQ.NEXTVAL,?,?,?,?,?,0,?,?,?,?,?) ";
			 
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setInt(1, dto.getM_NUM());
			pstmt.setString(2, dto.getTITLE());
			pstmt.setString(3, dto.getWRITER());
			pstmt.setString(4, dto.getCONTENT());
			pstmt.setTimestamp(5, dto.getREG_DATE());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, ref_step);
			pstmt.setInt(8, ref_level);
			pstmt.setString(9, dto.getKIND());
			pstmt.setString(10, dto.getIP());
			
			cnt = pstmt.executeUpdate();
			
			System.out.println("    : cnt 값 : " + cnt);
			
		} catch( SQLException e ){
			e.printStackTrace();
		} finally {
			try{
				if( conn!=null ) conn.close();
				if( pstmt!=null ) pstmt.close();
				if( rs!=null ) rs.close();
			} catch( SQLException e1 ){
				e1.printStackTrace();
			}
		}
		
		return cnt;
	}
	
	
	public String kindCheck(int no){
		
		System.out.println("    : kindCheck() 메소드 실행");
		Connection conn = null; 
		PreparedStatement pstmt=null; 
		ResultSet rs=null;
		String kind = "NOTICE";
		
		try{
			
			conn = data.getConnection();
			
			String sql = "SELECT KIND FROM BOARD WHERE NO=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if(rs.next()){
				kind = rs.getString("KIND");
				System.out.println("    : kind 값 : " + kind);
			} 
				
			
		} catch( SQLException e){
			e.printStackTrace();
		} finally {
			
			try{
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch( SQLException e1  ){
				e1.printStackTrace();
			}
		} 
		
		return kind;
	}

	
	
}
