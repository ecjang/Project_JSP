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
import mvc.store.dto.BookDTO;



public class BookDAOIpml implements BookDAO {
	
	DataSource data;
	
	/* 1. 싱글톤 생성 -----------------------------------------------------*/
	private static BookDAOIpml instance = new BookDAOIpml();
	public static BookDAOIpml getInstance(){
		return instance;
	}
	
	// 생성자 	
	public BookDAOIpml(){
		try{
			Context context = new InitialContext();
			data = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch( Exception e ){e.printStackTrace();}
	}
		
		
	// getCount() : 도서 개수 구하기
	@Override
	public int getCount() {
		
		System.out.println("    : getConut() 매소드 실행");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try{
			conn = data.getConnection();
			String sql = "SELECT COUNT(*) FROM BOOK";
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) cnt=rs.getInt("COUNT(*)");
			System.out.println("    : 도서 개수 cnt값 : " + cnt + "개");
			
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
	
	
	// getArticles() : 도서 정보 가져오기
	@Override
	public ArrayList<BookDTO> getArticles(int start, int end) {
	
		System.out.println("    : getArticles(start, end) 매소드 실행");
		ArrayList<BookDTO> dtos = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = data.getConnection();
			String sql = 
					"SELECT * " + 
							"FROM ( SELECT B_NUM, TITLE, SUBTITLE, AUTHOR, QUAN, " + 
											"PRICE, REG_DATE, KIND, STATE, rowNum rNum " + 
									"FROM ( SELECT * FROM BOOK " +
											" ORDER BY B_NUM DESC " +
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
						BookDTO dto = new BookDTO();
						
						dto.setB_num(rs.getInt("B_NUM"));
						dto.setTitle(rs.getString("TITLE"));
						dto.setSubtitle(rs.getString("SUBTITLE"));
						dto.setAuthor(rs.getString("AUTHOR"));
						dto.setQuan(rs.getInt("QUAN"));
						dto.setPrice(rs.getInt("PRICE"));
						dto.setReg_date(rs.getTimestamp("REG_DATE"));
						dto.setKind(rs.getString("KIND"));
						dto.setState(rs.getString("STATE"));
						
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
	
	
	// getArticles() : 도서 정보 가져오기 (넘버로)
	@Override
	public BookDTO getArticles(int b_num) {
		System.out.println("    : getArticles() 메소드 실행");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookDTO dto = null;
		
		try{
			conn = data.getConnection();
			String sql = " SELECT * FROM BOOK WHERE b_num=? ";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,b_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				dto = new BookDTO();
				
				dto.setB_num		( rs.getInt("B_NUM") );
				dto.setM_num		( rs.getInt("M_NUM") );
				dto.setTitle		( rs.getString("TITLE") );
				dto.setSubtitle		( rs.getString("SUBTITLE") ); 		
				dto.setAuthor		( rs.getString("AUTHOR") );
				dto.setQuan			( rs.getInt("QUAN") );
				dto.setPrice		( rs.getInt("PRICE") );
				dto.setReg_date		( rs.getTimestamp("REG_DATE") );
				dto.setKind			( rs.getString("KIND") );
				dto.setState		( rs.getString("STATE") );
				
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
	
	
	// insert() : 도서 추가
	@Override
	public int book_insert(BookDTO dto) {

		System.out.println("    : insert() 매소드 실행");
		
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = data.getConnection();
			
			String sql = null;
			
			sql="INSERT INTO BOOK ( B_NUM, M_NUM, TITLE, SUBTITLE, " +
				"AUTHOR, QUAN, PRICE, REG_DATE, KIND, STATE )" + 
				" VALUES (BOOK_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?)";
			 
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt		(1, dto.getM_num()		);
			pstmt.setString		(2, dto.getTitle()		);
			pstmt.setString		(3, dto.getSubtitle()	);
			pstmt.setString		(4, dto.getAuthor()		);
			pstmt.setInt		(5, dto.getQuan()		);
			pstmt.setInt		(6, dto.getPrice()		);
			pstmt.setTimestamp	(7, dto.getReg_date()	);
			pstmt.setString		(8, dto.getKind()		);
			pstmt.setString		(9, dto.getState()		);
			
			cnt = pstmt.executeUpdate();
			
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
	
	
	// pwCheck() : 아이디와 비밀번호 확인
	@Override
	public int pwCheck(int b_num, String ps) {
		
		System.out.println("    : pwCheck() 메소드 실행");
		Connection conn = null; 
		PreparedStatement pstmt=null; 
		ResultSet rs=null;
		int cnt = 0;
		
		try{
			
			conn = data.getConnection();
			
			String sql = "SELECT * FROM BOOK WHERE B_NUM=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
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
	
	
	// upadte() : 수정한 도서를 등록
	@Override
	public int upadte(BookDTO dto) {
		
		System.out.println("    : upadte() 메소드 실행 ");
		
		Connection conn = null; 
		PreparedStatement pstmt=null; 
		int cnt = 0;
		
		try{
			conn = data.getConnection();
			String sql = "UPDATE BOOK SET " + 
					"TITLE=?, SUBTITLE=?, AUTHOR=?, " +
					"PRICE=?, QUAN=?, KIND=? WHERE B_NUM=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getSubtitle());
			pstmt.setString(3, dto.getAuthor());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setInt(5, dto.getQuan());
			pstmt.setString(6, dto.getKind());
			pstmt.setInt(7, dto.getB_num());
			
			cnt = pstmt.executeUpdate();
			
			System.out.println("    : 도서 정보 입력 완료, cnt="+cnt);
			
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
	
	// delete() : 도서 삭제
	@Override
	public int delete(int b_num) {
		
		System.out.println("    : delete() 메소드 실행");
		int cnt = 0;
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			
			conn = data.getConnection();

			sql="DELETE BOOK WHERE B_NUM=? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			cnt = pstmt.executeUpdate();
			
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

	// searchCnt : 검색 량
		@Override
		public int searchCnt(String str) {
			
			System.out.println("    : searchCnt() 매소드 실행");
			System.out.println("    : str 값 : " +str);
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int cnt = 0;
			
			try{
				conn = data.getConnection();
				sql = "SELECT COUNT(B_NUM) FROM BOOK WHERE TITLE LIKE '%'||?||'%'";
				
				
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,  str);
				rs = pstmt.executeQuery();
				
				if(rs.next()) 
					cnt=rs.getInt("COUNT(B_NUM)");
				System.out.println("    : 검색 개수  : " + cnt + "개");
				
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

	
	// search() : 도서 검색
	@Override
	public ArrayList<BookDTO> booksearch(String str) {

		System.out.println("    : booksearch() 매소드 실행");
		ArrayList<BookDTO> dtos = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = data.getConnection();
			
			String sql = "SELECT * FROM BOOK WHERE title like '%"+str+"%'"; 
			
				pstmt=conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					dtos = new ArrayList<>();
					
					do{
						BookDTO dto = new BookDTO();
						
						dto.setB_num(rs.getInt("B_NUM"));
						dto.setTitle(rs.getString("TITLE"));
						dto.setSubtitle(rs.getString("SUBTITLE"));
						dto.setAuthor(rs.getString("AUTHOR"));
						dto.setQuan(rs.getInt("QUAN"));
						dto.setPrice(rs.getInt("PRICE"));
						dto.setReg_date(rs.getTimestamp("REG_DATE"));
						dto.setKind(rs.getString("KIND"));
						dto.setState(rs.getString("STATE"));
						
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

	
	
}
