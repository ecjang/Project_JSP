package mvc.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.board.dto.BoardDTO;


public class BoardDAOIpml implements BoardDAO {
	
	DataSource data;
	
	/* 1. 싱글톤 생성 -----------------------------------------------------*/
	private static BoardDAOIpml instance = new BoardDAOIpml();
	public static BoardDAOIpml getInstance(){
		return instance;
	}
	
	/* 2. 커넥션 풀 설정 -----------------------------------------------------*/
	public BoardDAOIpml(){
		
		
		try{
			Context context = new InitialContext();
			data = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch( Exception e ){ e.printStackTrace(); }
	}
	
	/* 3. getCount() : 글 개수 구하기 : List_01_Pro_Handler에서 호출 ------------*/
	@Override
	public int getCount() {

		System.out.println("    : BoardDAOIpml에서 getConut() 실행 : DAO");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try{
			conn = data.getConnection();
			String sql = "SELECT COUNT(*) FROM mvc_board";
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) cnt=rs.getInt("COUNT(*)");
			System.out.println("    : 게시물 개수 cnt값 : " + cnt + "개");
			
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
	
	/* 4. getArticles() : 게시글 정보 가져오기 : List_01_Pro_Handler에서 호출 ----*/
	@Override
	public ArrayList<BoardDTO> getArticles( int start , int end ) {
		
		System.out.println("    : BoardDAOIpml에서 getArticles() 실행 : DAO");
		ArrayList<BoardDTO> dtos = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			
			conn = data.getConnection();
			String sql = 
				"SELECT * " + 
				"FROM ( SELECT num, writer, passwd, subject, content, " + 
								"readCnt, ref, ref_step, ref_level, reg_date, ip, rowNum rNum " + 
						"FROM ( SELECT * FROM mvc_board " +
								" ORDER BY ref DESC, ref_step ASC " +
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
					
					dto.setNum( rs.getInt("num") );
					dto.setWriter( rs.getString("writer") );
					dto.setPasswd( rs.getString("passwd") );
					dto.setSubject( rs.getString( "subject" ) );
					dto.setContent( rs.getString("content"));
					dto.setReadCnt( rs.getInt("readCnt") );
					dto.setRef( rs.getInt("ref") );
					dto.setRef_step( rs.getInt("ref_step"));
					dto.setRef_level( rs.getInt("ref_level"));
					dto.setReg_date( rs.getTimestamp("reg_date"));
					dto.setIp( rs.getString("ip"));
					
					dtos.add(dto);
					
				}while(rs.next());
				System.out.println("    : 데이터를 로딩 성공");
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

	/* 5. getArticles() : 게시글 정보 가져오기 : ContentForm_01_Handler 에서 호출 ----*/
	@Override
	public BoardDTO getArticles(int num) {
		System.out.println("    : BoardDAOIpml에서 getAritcles() 실행 : DAO");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		
		try{
			conn = data.getConnection();
			String sql = " SELECT * FROM mvc_board WHERE num=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new BoardDTO();
				/*
				System.out.println(rs.getInt("num"));
				System.out.println( rs.getString("writer") );
				System.out.println( rs.getString("passwd") );
				System.out.println( rs.getString("subject") );
				System.out.println( rs.getString("content") );
				System.out.println( rs.getInt("readCnt") );
				System.out.println( rs.getInt("ref") );
				System.out.println( rs.getInt("ref_step") );
				System.out.println( rs.getInt("ref_level") );
				System.out.println( rs.getTimestamp("reg_date"));
				System.out.println( rs.getString("ip") );
				*/
				
				dto.setNum		( rs.getInt("num") );
				dto.setWriter	( rs.getString("writer") );
				dto.setPasswd	( rs.getString("passwd") );
				dto.setSubject	( rs.getString("subject") );
				dto.setContent	( rs.getString("content") );
				dto.setReadCnt	( rs.getInt("readCnt") );
				dto.setRef		( rs.getInt("ref") );
				dto.setRef_step	( rs.getInt("ref_step") );
				dto.setRef_level( rs.getInt("ref_level") );
				dto.setReg_date( rs.getTimestamp("reg_date"));
				dto.setIp		( rs.getString("ip") );
				
				System.out.println("    : dto 생성 및 값 불러오기 완료");
				
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
	
	/* 6. addReadCnt() : 조회수 증가 : ContentForm_01_Handler 에서 호출 ----*/
	@Override
	public void addReadCnt(int num) {
		System.out.println("    : BoardDAOIpml에서 addReadCnt() 실행 : DAO");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn=data.getConnection();
			String sql="UPDATE mvc_board SET readCnt=readCnt+1 WHERE num=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			sql="SELECT readCnt FROM mvc_board WHERE num=?";
			pstmt.close();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println("    : 조회수 값 : " + rs.getInt(1));
			}
			
			
		} catch ( SQLException e ){
			e.printStackTrace();
		} finally {
			try{ 
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch(SQLException e ){
				e.printStackTrace();
			}
		}
	}

	/* 7. pwCheck() : 비번 체크 : modify_02_View_Handler 에서 호출 ----*/
	@Override
	public int pwCheck(int num , String ps) {
		System.out.println("    : BoardDAOIpml에서 pwCheck() 실행 : DAO");
		
		System.out.println("    : num 값 " + num);
		System.out.println("    : ps 값 " + ps);
		
		Connection conn = null; 
		PreparedStatement pstmt=null; 
		ResultSet rs=null;
		int cnt = 0;
		
		try{
			
			conn = data.getConnection();
			
			String sql = "SELECT * FROM mvc_board WHERE num=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if(rs.next()){
				if( rs.getString("passwd").equals(ps) ){
					System.out.println("    : 비밀번호가 일치 cnt=1");
					cnt = 1;
				}
			} else {
				System.out.println("    : 비밀번호가 불일치 cnt=0");
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
	
	/* 8. upadte() : 수정정보 입력  : modify_03_Pro_Handler 에서 호출 ----*/
	@Override
	public int upadte(BoardDTO dto) {
		System.out.println("    : BoardDAOIpml에서 upadte() 실행 : DAO");
		Connection conn = null; 
		PreparedStatement pstmt=null; 
		int cnt = 0;
		
		try{
			conn = data.getConnection();
			String sql = "UPDATE mvc_board SET passwd=?, subject=?, content=? WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPasswd());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getNum());
			cnt = pstmt.executeUpdate();
			System.out.println("    : dto정보 입력 완료, cnt="+cnt);
			
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

	/* 9. insert() : 새글,댓글 등록  : Write_02_Pro_Handler 에서 호출 ----*/
	@Override
	public int insert(BoardDTO dto) {
		
		System.out.println("    : BoardDAOIpml에서 insert() 실행 : DAO");
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = data.getConnection();
			
			int num = dto.getNum();
			int ref	= dto.getRef();
			int ref_step = dto.getRef_step();
			int ref_level = dto.getRef_level();
			/*
			System.out.println("    : num : " + num);
			System.out.println("    : ref : " + ref);
			System.out.println("    : ref_step : " + ref_step);
			System.out.println("    : ref_level : " + ref_level);
			*/
			String sql = null;
			
			if( num == 0 ){ // 새글일 경우
				System.out.println("    : 새글로 입력 ");
				sql="SELECT MAX(num) FROM mvc_board";
				pstmt=conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if( rs.next() ){	// 첫글이 아닐 경우
					ref = rs.getInt(1) + 1;
				} else {	// 첫글인 경우
					ref = 1;
				}
				
				ref_step=0; 
				ref_level=0;
				
				
			} else {	// 답글인 경우
				System.out.println("    : 답글로 입력 ");	
				sql="UPDATE mvc_board SET " + 
					"ref_step=ref_step+1 " + 
					"WHERE ref = ? AND ref_step > ? " ;	
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
			
			sql="INSERT INTO mvc_board (num, writer, passwd, " +
				"subject, content, readCnt, ref, ref_step, ref_level, " +
				" reg_date, ip ) VALUES (board_seq.NEXTVAL,?,?,?,?,0,?,?,?,?,?)";
			 
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, ref );
			pstmt.setInt(6, ref_step );
			pstmt.setInt(7, ref_level );
			pstmt.setTimestamp(8, dto.getReg_date());
			pstmt.setString(9, dto.getIp());
			
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

	/* 10. delete() : 글 삭제  : Write_02_Pro_Handler 에서 호출 ----*/
	@Override
	public int delete(int num) {
		
		System.out.println("    : BoardDAOIpml에서 delete() 실행 : DAO");
		int cnt = 0;
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 우선 num 게시물 조회
		// ref_step이랑 ref_level 조회 -> 답글 있는지 없는지 분기
		// num 게시물 삭제
		
		try{
			
			conn = data.getConnection();
			
			sql="SELECT * FROM mvc_board WHERE num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if ( rs.next() ){	// 답글 존재 여부 확인
				System.out.println("    : 답글여부 확인");
				int ref = rs.getInt("ref");
				int ref_step = rs.getInt("ref_step");
				int ref_level = rs.getInt("ref_level");
				
				sql="SELECT * FROM mvc_board " + 
					"WHERE ref=? AND ref_step=?+1 AND ref_level>? " ;
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
					sql="UPDATE mvc_board SET ref_step=ref_step-1 " +
						"WHERE ref = ? AND ref_step > ? ";
					pstmt.close();
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, ref);
					pstmt.setInt(2, ref_step);
					pstmt.executeUpdate();
					
					sql="DELETE mvc_board WHERE num=? ";
					pstmt.close();
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, num);
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
	
	

	
}
