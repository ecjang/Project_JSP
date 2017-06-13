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
	
	/* 1. �̱��� ���� -----------------------------------------------------*/
	private static BookDAOIpml instance = new BookDAOIpml();
	public static BookDAOIpml getInstance(){
		return instance;
	}
	
	// ������ 	
	public BookDAOIpml(){
		try{
			Context context = new InitialContext();
			data = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch( Exception e ){e.printStackTrace();}
	}
		
		
	// getCount() : ���� ���� ���ϱ�
	@Override
	public int getCount() {
		
		System.out.println("    : getConut() �żҵ� ����");
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
			System.out.println("    : ���� ���� cnt�� : " + cnt + "��");
			
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
	
	
	// getArticles() : ���� ���� ��������
	@Override
	public ArrayList<BookDTO> getArticles(int start, int end) {
	
		System.out.println("    : getArticles(start, end) �żҵ� ����");
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
				System.out.println("    : ������ �ε� ����");
			} else {
				System.out.println("    : ������ �ε� �� ���� �߻�");
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
	
	
	// getArticles() : ���� ���� �������� (�ѹ���)
	@Override
	public BookDTO getArticles(int b_num) {
		System.out.println("    : getArticles() �޼ҵ� ����");
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
				
				System.out.println("    : dto ���� �� �� �ҷ����� �Ϸ�");
				
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
	
	
	// insert() : ���� �߰�
	@Override
	public int book_insert(BookDTO dto) {

		System.out.println("    : insert() �żҵ� ����");
		
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
	
	
	// pwCheck() : ���̵�� ��й�ȣ Ȯ��
	@Override
	public int pwCheck(int b_num, String ps) {
		
		System.out.println("    : pwCheck() �޼ҵ� ����");
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
					System.out.println("    : ��й�ȣ�� ��ġ cnt=1");
					cnt = 1;
				}
			} else {
				System.out.println("    : ��й�ȣ�� ����ġ cnt=0");
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
	
	
	// upadte() : ������ ������ ���
	@Override
	public int upadte(BookDTO dto) {
		
		System.out.println("    : upadte() �޼ҵ� ���� ");
		
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
			
			System.out.println("    : ���� ���� �Է� �Ϸ�, cnt="+cnt);
			
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
	
	// delete() : ���� ����
	@Override
	public int delete(int b_num) {
		
		System.out.println("    : delete() �޼ҵ� ����");
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

	// searchCnt : �˻� ��
		@Override
		public int searchCnt(String str) {
			
			System.out.println("    : searchCnt() �żҵ� ����");
			System.out.println("    : str �� : " +str);
			
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
				System.out.println("    : �˻� ����  : " + cnt + "��");
				
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

	
	// search() : ���� �˻�
	@Override
	public ArrayList<BookDTO> booksearch(String str) {

		System.out.println("    : booksearch() �żҵ� ����");
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
				System.out.println("    : ������ �ε� ����");
			} else {
				System.out.println("    : ������ �ε� �� ���� �߻�");
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
