package mvc.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	public ArrayList<BookDTO> booksearch(String str, int start, int end) {

		System.out.println("    : booksearch() �żҵ� ����");
		
		ArrayList<BookDTO> dtos = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = data.getConnection();
			
			String sql = "SELECT * " + 
							"FROM ( SELECT B_NUM, TITLE, SUBTITLE, AUTHOR, QUAN, " + 
							"PRICE, REG_DATE, KIND, STATE, rowNum rNum " + 
						"FROM ( SELECT * FROM BOOK " +
							" WHERE TITLE LIKE '%"+str+"%' " + 
							" ORDER BY B_NUM " +
							 	") " +
							") " +
						"WHERE rNum >= ? AND rNum <= ? ";
			
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
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

	
	// ���� ���� ���� : ��ٱ��Ͽ� �߰� 
	@Override
	public int bookstate( BookDTO dto) {
		
		System.out.println("    : bookstate() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		int in_cont=0;
		
		try{
			
			conn = data.getConnection();
			
			// �켱 ��� �ľ� ����
			
			String sql="SELECT QUAN FROM BOOK WHERE B_NUM=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getB_num());
			rs = pstmt.executeQuery();
	

			if( rs.next() ){
				
				if( rs.getInt("QUAN") >= dto.getQuan() ){
					
					// �ֹ� ���� ��ŭ ��� ����
					sql = null;
					sql="UPDATE BOOK SET QUAN=(QUAN-?) WHERE B_NUM=?";
					
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, dto.getQuan() );
					pstmt.setInt(2, dto.getB_num() );
					rs.close();
					rs = pstmt.executeQuery();
					
					if(rs.next()){
						cnt = 1;	// �ֹ� ���� ���� �Ϸ�
					} else {
						cnt = 2;	// ���� �� ����
					}
				} else {
					cnt = 3;	// �������� �ֹ��������� ����
				}
				
			} else {	
				cnt = 0 ;	// ��� ����  
			} //  rs.next() 	
			
			
			
			
			if (cnt==1){
				
				// �ֹ� ���� ��ŭ ���� ���¸� ������ �߰�
				sql = null;
				sql="INSERT INTO CART ( C_NUM , B_NUM, M_NUM, ORDERNUM, PRICE, REG_DATE, STATE )" + 
					" VALUES ( BOOK_SEQ.NEXTVAL ,?,?,?,?,?,?)";
					
				pstmt.close();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt		(1, dto.getB_num()		);
				pstmt.setInt		(2, dto.getM_num()		);
				pstmt.setInt		(3, dto.getQuan()		);
				pstmt.setInt		(4, dto.getPrice()		);
				pstmt.setTimestamp	(5, dto.getReg_date()	);
				pstmt.setString		(6, dto.getState()		);	
					
				in_cont = pstmt.executeUpdate();
				
				
				if(in_cont == 1){
					System.out.println("    : ��ϿϷ�");
				}else{
					System.out.println("    : ���� �߻�");
					cnt=4;
				} 
			}
				
		
			/*
			sql = null;
			sql="INSERT INTO BOOK ( B_NUM, M_NUM, TITLE, SUBTITLE, " +
					"AUTHOR, QUAN, PRICE, REG_DATE, KIND, STATE )" + 
					" VALUES (?,?,?,?,?,?,?,?,?,?)";
				 
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt		(1, dto.getB_num()		);
				pstmt.setInt		(2, dto.getM_num()		);
				pstmt.setString		(3, dto.getTitle()		);
				pstmt.setString		(4, dto.getSubtitle()	);
				pstmt.setString		(5, dto.getAuthor()		);
				pstmt.setInt		(6, dto.getQuan()		);
				pstmt.setInt		(7, dto.getPrice()		);
				pstmt.setTimestamp	(8, dto.getReg_date()	);
				pstmt.setString		(9, dto.getKind()		);
				pstmt.setString		(10, dto.getState()		);
				
				cnt = pstmt.executeUpdate();
			*/
			
			
			/*System.out.println("    : cnt�� : " + cnt);*/
			
		} catch( SQLException e1 ){ e1.printStackTrace(); 
		} finally {
			
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch(  SQLException e2 )
				{ e2.printStackTrace(); }
			
		}
		
		return cnt;
	}

	
	// īƮ���̺� ��� �ҷ�����
	@Override
	public ArrayList<BookDTO> getcart(int start, int end) {
		
		System.out.println("    : getcart(start, end) �żҵ� ����");
		System.out.println("    : start : " + start) ;
		System.out.println("    : end : " + end);
		
		ArrayList<BookDTO> carts = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = data.getConnection();
			
			/*	������
			"SELECT C.B_NUM, B.TITLE, C.M_NUM, M.ID , C.ORDERNUM , C.PRICE , " +
			"C.REG_DATE , C.STATE , rowNum " +
			"FROM CART C INNER JOIN MEMBER M " +
			"ON C.M_NUM = M.M_NUM INNER JOIN BOOK B "+
			"ON C.B_NUM = B.B_NUM " +
			"WHERE rowNum >= ? AND rowNum <= ? "; 
			*/

			String sql = 
				
				"SELECT * " +
				"FROM ( SELECT C.C_NUM , C.B_NUM, B.TITLE , C.M_NUM, M.ID , C.ORDERNUM , " +
				"C.PRICE , C.REG_DATE , C.STATE , rowNum rnum " +
				"FROM CART C INNER JOIN MEMBER M " +
				"ON C.M_NUM = M.M_NUM INNER JOIN BOOK B "+
				"ON C.B_NUM = B.B_NUM ) " +
				"WHERE rnum BETWEEN ? AND ? "; 
			
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					carts = new ArrayList<>(end-start+1);
					
					do {
						BookDTO dto = new BookDTO();
						
						/*----------------------------------------------------------*/
						/*
						System.out.println( rs.getInt("B_NUM") );
						System.out.println( rs.getString("TITLE") );
						System.out.println( rs.getInt("M_NUM") );
						System.out.println( rs.getString("ID") );
						*/
						/*----------------------------------------------------------*/
						
						
						dto.setC_num(rs.getInt("C_NUM"));			// ��ٱ��� ��ȣ
						dto.setB_num(rs.getInt("B_NUM"));			// ������ȣ
						dto.setTitle(rs.getString("TITLE")); 		// ��������
						dto.setM_num(rs.getInt("M_NUM"));			// ȸ����ȣ
						dto.setId(rs.getString("ID")); 				// ȸ��ID
						dto.setOrdernum(rs.getInt("ORDERNUM"));		// �ֹ�����	
						dto.setPrice(rs.getInt("PRICE"));			// ���� ����
						dto.setReg_date(rs.getTimestamp("REG_DATE"));// �����
						dto.setState(rs.getString("STATE"));		// ��������
						
						
						/*----------------------------------------------------------*/
						
						/*System.out.println(rs.getString("B_NUM")+"\n" );*/	// �׽�Ʈ��
						
						carts.add(dto);
					
					} while(rs.next());
					
				System.out.println("    : ������ �ε� ����");
				
			} else {
				System.out.println("    : ������ �ε� �� ���� �߻�");
			}
				
				// �׽�Ʈ�� 
				/*
				sql = "SELECT * FROM CART";
				pstmt.close();
				pstmt=conn.prepareStatement(sql);
				rs.close();
				rs = pstmt.executeQuery();
				
				if (rs.next()){
					System.out.println("���� ����");
				}else {
					System.out.println("���� ����");
				}
				*/
				
				
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
				if( rs != null ) rs.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return carts;
	}
	
	// īƮ ��� ��������
	public int getCount_cart(){
		System.out.println("    : getCount_cart() �żҵ� ����");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try{
			conn = data.getConnection();
			String sql = "SELECT COUNT(*) FROM CART WHERE STATE='CART'";
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) cnt=rs.getInt("COUNT(*)");
			System.out.println("    : ��� cnt�� : " + cnt + "��");
			
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

	
	
	// ��ٱ��� ���� ���� 
	@Override
	public int cart_ordernum(int c_num, int ordernum) {
		
		System.out.println("    : cart_ordernum() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try{
			conn = data.getConnection();
			String sql = "UPDATE CART SET ORDERNUM=? WHERE C_NUM=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, ordernum);
			pstmt.setInt(2, c_num);
			cnt = pstmt.executeUpdate();
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return cnt;
	}

	
	
	// ��ٱ��� ���ϸ�� ����
	@Override
	public int cart_orderdel(int c_num) {

		System.out.println("    : cart_orderdel() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try{
			conn = data.getConnection();
			String sql = "DELETE CART WHERE C_NUM=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			cnt = pstmt.executeUpdate();
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return cnt;
	}
	
	// ��ٱ��� ���ϸ�� �ֹ�������� ����
	@Override
	public int cart_orderconfirm(int c_num, int ordernum) {
		return 0;
	}

	
	// ��ٱ��� ���ϴ� �������� �ľ�
	@Override
	public int cart_quan( int b_num  ){
		
		System.out.println("    : cart_quan() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
				
		try{
			conn = data.getConnection();
			
			BookDTO dto = new BookDTO();

			// �켱 ��� ������� �ľ�����
			
			String sql = "SELECT QUAN FROM BOOK WHERE B_NUM=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				cnt = rs.getInt("QUAN");
				System.out.println("    : ������ : " + rs.getInt("QUAN"));
			} else { cnt = 0; }
			
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return cnt;
	}


	// ��ٱ��� ���� ���� �ҷ�����
	@Override
	public BookDTO cart_input(int c_num , int ordernum) {
		
		System.out.println("    : cart_input() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		
		
		BookDTO dto = new BookDTO();
				
		try{
			conn = data.getConnection();
			
			String sql = "SELECT * FROM CART WHERE C_NUM=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println("    : ��ٱ��� ���� ���� �Ϸ�");
				
				dto.setB_num( rs.getInt("B_NUM"));
				dto.setM_num( rs.getInt("M_NUM"));
				dto.setQuan(ordernum);
				dto.setPrice( rs.getInt("PRICE"));
				dto.setReg_date( new Timestamp(System.currentTimeMillis()));
				dto.setState("ORDER");
				
				System.out.println("dto.getM_num : " + dto.getM_num());
				System.out.println("dto.getPrice : " + dto.getPrice());
				
				System.out.println("    : dto ���� ���� �Ϸ�");
				
				
			} else {
				System.out.println("    : ���� ������ ���� �߻�");
			} 
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return dto;
	}

	
	// �ֹ� ���̺� ���
	@Override
	public int cart_output(BookDTO dto) {
		
		System.out.println("    : cart_output() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
				
		try{
			conn = data.getConnection();
			
			String sql = "INSERT INTO BOOK_ORDER "	+
						"( O_NUM , B_NUM , M_NUM , QUAN , PRICE )" +
						"VALUES( CART_SEQ.NEXTVAL,?,?,?,?) ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getB_num());
			pstmt.setInt(2, dto.getM_num());
			pstmt.setInt(3, dto.getQuan());
			pstmt.setInt(4, dto.getPrice());
			
			cnt = pstmt.executeUpdate();
			
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return cnt;
	}

	
	// īƮ ��� ��ü �ҷ�����
	@Override
	public ArrayList<BookDTO> cart_getcart(int id) {
	System.out.println("    : cart_output() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<BookDTO> dtos = null;
		
		ResultSet rs = null;
				
		try{
			conn = data.getConnection();

			String sql = "SELECT B_NUM , M_NUM , " + 
					" ORDERNUM , PRICE  " +
					" FROM CART " +
					" WHERE M_NUM=? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, id );
			rs = pstmt.executeQuery();
			
			if( rs.next()){
				
				dtos = new ArrayList<>();
				
				do{
				
					BookDTO dto = new BookDTO();
					
					dto.setB_num( rs.getInt("B_NUM") );
					dto.setM_num( rs.getInt("M_NUM") );
					dto.setQuan( rs.getInt("ORDERNUM") );
					dto.setPrice( rs.getInt("PRICE") );
					
					dtos.add(dto);
					
				} while( rs.next() );
			}
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return dtos;
	}

	
	// īƮ ��� �迭�� �ֹ� ���̺� ����
	@Override
	public int cart_moveorder(ArrayList<BookDTO> dtos) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt =0;
				
		try{
			conn = data.getConnection();
			
			for (int i=0 ; i<dtos.size() ; i++){
			
				BookDTO dto = dtos.get(i);
				
				String sql = "INSERT INTO BOOK_ORDER " +
							"( O_NUM , B_NUM , M_NUM , QUAN , PRICE )" +
							" VALUES( ORDER_SEQ.NEXTVAL , ?,?,?,? )";
				
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getB_num() );
				pstmt.setInt(2, dto.getM_num() );
				pstmt.setInt(3, dto.getQuan() );
				pstmt.setInt(4, dto.getPrice() );
				
				cnt = pstmt.executeUpdate();
				
			}
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return cnt;
	}

	
	// �ֹ����̺�� �ű�� īƮ ���̺� id �˻��� ���� + ��ٱ��� ��ü ����
	@Override
	public int cart_afterdel(int m_num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt =0;
				
		try{
			conn = data.getConnection();
				
			String sql = "DELETE FROM CART WHERE M_NUM=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, m_num );
			
			cnt = pstmt.executeUpdate();
				
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return cnt;
	}

	
	
	
	// �ֹ����̺� ���� ��������
	@Override
	public ArrayList<BookDTO> getorder(int start, int end) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookDTO> dtos = null;
				
		try{
			conn = data.getConnection();

			String sql = 
					"SELECT * " + 
					"FROM ( SELECT O_NUM, M_NUM, B_NUM, QUAN , PRICE , " +
								"REG_DATE, STATE , ROWNUM rNum " +
								
							"FROM ( SELECT * FROM BOOK_ORDER " +
									" ORDER BY O_NUM DESC " +
								 ") " +
							") " +
					"WHERE rNum >= ? AND rNum <= ? ";
				
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start );
			pstmt.setInt(2, end );
			rs = pstmt.executeQuery();
			
			if( rs.next()){
				
				dtos = new ArrayList<>();
				
				do{
				
					BookDTO dto = new BookDTO();
					
					dto.setO_num( rs.getInt("O_NUM") );
					dto.setB_num( rs.getInt("B_NUM") );
					dto.setM_num( rs.getInt("M_NUM") );
					dto.setQuan( rs.getInt("QUAN") );
					dto.setPrice( rs.getInt("PRICE") );
					dto.setState( rs.getString("STATE"));
					dto.setReg_date( rs.getTimestamp("REG_DATE"));
					
					
					dtos.add(dto);
					
				} while( rs.next() );
			}
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return dtos;
	}

	// �ֹ� ���̺� ��� �ҷ�����
	@Override
	public int getOrderCount() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int cnt=0;
				
		try{
			conn = data.getConnection();
				
			String sql = "SELECT COUNT(*) FROM BOOK_ORDER";
			
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
				
			if( rs.next()){
				
				cnt = rs.getInt(1);
				
			}
			
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return cnt;

		
	}

	
	// �ֹ� ���̺� ���� ��ȭ�ϱ�
	@Override
	public int changeState(int o_num, String state) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt=0;
				
		try{
			conn = data.getConnection();
				
			String sql = "UPDATE BOOK_ORDER SET STATE=? WHERE O_NUM=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, state);
			pstmt.setInt(2, o_num);
			cnt = pstmt.executeUpdate();
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return cnt;
		
	}

	
	// �������!
	@Override
	public int sum() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sum = 0;
				
		try{
			conn = data.getConnection();
				
			String sql = "SELECT price, sum(quan) as squan FROM BOOK_ORDER group by price"; 
			
			pstmt=conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				do{
					sum = (rs.getInt("price") * rs.getInt("squan")) + sum;
				}while(rs.next());
			}
			
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return sum;
		
	}
	
	
	
	
	
}

		
	

	


