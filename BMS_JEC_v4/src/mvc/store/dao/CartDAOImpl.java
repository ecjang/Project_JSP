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

import mvc.store.dto.BookDTO;
import mvc.store.dto.CartDTO;

public class CartDAOImpl implements CartDAO {
	
	
	DataSource data;
	
	private static CartDAOImpl instance = new CartDAOImpl();
	public static CartDAOImpl getInstance() {
		return instance;
	}
	
	public CartDAOImpl(){
		
		try{
			Context context = new InitialContext();
			data = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
			
		} catch( Exception e ){
			e.printStackTrace();
			
		}
		
	}
	
	
	/*------------------------------------------------------*/
	

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
				sql="INSERT INTO CART ( C_NUM , B_NUM, M_NUM, QUAN, PRICE, REG_DATE, STATE )" + 
					" VALUES ( CART_SEQ.NEXTVAL ,?,?,?,?,?,?)";
					
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
					cnt=4;		// īƮ�� �Է��� ���� �߻�
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
	public ArrayList<CartDTO> getcart(int start, int end) {
		
		System.out.println("    : getcart(start, end) �żҵ� ����");
		/*
		System.out.println("    : start : " + start) ;
		System.out.println("    : end : " + end);
		*/
		ArrayList<CartDTO> carts = null;
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
			/*
			"SELECT * " +
			"FROM ( SELECT C.C_NUM , C.B_NUM, B.TITLE , C.M_NUM, M.ID , C.QUAN , " +
			"C.PRICE , C.REG_DATE , C.STATE , B.QUAN , rowNum rnum " +
			"FROM CART C INNER JOIN MEMBER M " +
			"ON C.M_NUM = M.M_NUM INNER JOIN BOOK B "+
			"ON C.B_NUM = B.B_NUM ) " +
			"WHERE rnum BETWEEN ? AND ? "; 
			*/
			String sql = 
				"SELECT * " +
				"FROM ( SELECT C.C_NUM, C.B_NUM, C.M_NUM, B.TITLE, M.ID, C.QUAN, " +
				"C.PRICE, C.REG_DATE, C.STATE, B.QUAN AS B_QUAN, rowNum rnum " +
				"FROM CART C " +
				"JOIN MEMBER M ON C.M_NUM = M.M_NUM " +
				"JOIN BOOK B ON C.B_NUM = B.B_NUM ) " +
				"WHERE rnum BETWEEN ? AND ? ";
					
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					carts = new ArrayList<>(end-start+1);
					
					do {
						
			
						
						CartDTO dto = new CartDTO();
						
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
						dto.setM_num(rs.getInt("M_NUM"));			// ȸ����ȣ
						dto.setPrice(rs.getInt("PRICE"));			// ���� ����
						dto.setQuan(rs.getInt("QUAN"));				// �ֹ����� 	
						dto.setReg_date(rs.getTimestamp("REG_DATE"));// �����
						dto.setState(rs.getString("STATE"));		// ��������
							
						// �߰� ��� ���� 
						dto.setTitle(rs.getString("TITLE")); 		// ��������
						dto.setId(rs.getString("ID")); 				// ȸ��ID
						dto.setBookquan(rs.getInt("B_QUAN"));		// ���� ��� 
						
						
						
						
						
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
	
	// ��ٱ��� ��� ��������
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

	
	// ��ٱ��� ���� ���� : �ֹ� ���� ���� , ��� ����
	@Override
	public int cartquanMinus(int c_num, int quan_abs) {
		
		System.out.println("    : cartquanModify() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try{
			conn = data.getConnection();
			
			String sql = "UPDATE CART SET QUAN=(QUAN-?) WHERE C_NUM=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, quan_abs);
			pstmt.setInt(2, c_num);
			
			cnt = pstmt.executeUpdate();
			
			if( cnt > 0 ){
				
				if( quan_abs > 0 ){
					
					sql = null;
					sql = "UPDATE BOOK SET QUAN=(QUAN+?)" +
							"WHERE B_NUM = " +
							"( SELECT B_NUM " +
							"FROM BOOK B JOIN CART C " + 
							"USING(B_NUM) " +
							"WHERE C_NUM=? ) ";
					pstmt.close();
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, quan_abs);
					pstmt.setInt(2, c_num);
					cnt = pstmt.executeUpdate();
					
					System.out.println("    : ��ٱ��� ���� ���� �Ϸ�");
					
				} else {
					cnt = 0;
					System.out.println("    : ��ٱ��� ���� ���� �� ���� �߻�");	
				}
				
			} else {
				System.out.println("    : ���� ����� �ֹ� ���� �߰��� ���� �߻�");
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


	// ��ٱ��� ���� ���� : �ֹ� ���� ���� , ��� ����
	@Override
	public int cartquanPlus(int c_num, int quan_abs) {
		
		System.out.println("    : cartquanModify() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
		try{
			conn = data.getConnection();
			
			String sql = "UPDATE CART SET QUAN=(QUAN+?) WHERE C_NUM=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, quan_abs);
			pstmt.setInt(2, c_num);
			
			cnt = pstmt.executeUpdate();
			
			if( cnt > 0 ){
				
				if( quan_abs > 0 ){
					
					sql = null;
					sql = "UPDATE BOOK SET QUAN=(QUAN-?)" +
							"WHERE B_NUM = " +
							"( SELECT B_NUM " +
							"FROM BOOK B JOIN CART C " + 
							"USING(B_NUM) " +
							"WHERE C_NUM=? ) ";
					
					pstmt.close();
					pstmt=conn.prepareStatement(sql);
					pstmt.setInt(1, quan_abs);
					pstmt.setInt(2, c_num);
					cnt = pstmt.executeUpdate();
					
					System.out.println("    : ��ٱ��� ���� ���� �Ϸ�");
					
				} else {
					cnt = 0;
					System.out.println("    : ��ٱ��� ���� ���� �� ���� �߻�");	
				}
				
			} else {
				System.out.println("    : ���� ����� �ֹ� ���� ������ ���� �߻�");
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

	
	// ��ٱ��� ���ϸ�� ����
	@Override
	public int cartdel(int c_num) {

		System.out.println("    : cartdel() �żҵ� ����");
		
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
	
	
	// īƮ ��� �ҷ����� 
	public int cartquanCnt(int c_num){
	System.out.println("    : cartquanCnt() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
				
		try{
			conn = data.getConnection();
			
			// �켱 ��� ������� �ľ�����
			
			String sql = "SELECT QUAN FROM CART WHERE C_NUM=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				cnt = rs.getInt("QUAN");
				System.out.println("    : �ֹ����� : " + rs.getInt("QUAN"));
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
	
	
	// ��ٱ��� ���ϴ� �������� �ľ�
	@Override
	public int bookquanCnt( int b_num  ){
		
		System.out.println("    : cart_quan() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
				
		try{
			conn = data.getConnection();
			
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
	public CartDTO cart_getCart(int c_num , int ordernum) {
		
		System.out.println("    : cart_getCart() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		CartDTO dto = new CartDTO();
				
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
				
				System.out.println("    : �ֹ��� ȸ�� ��ȣ : " + dto.getM_num());
				System.out.println("    : �ֹ� ���� ���� : " + dto.getPrice());
				
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
	
	
	// �ֹ� ���̺�� �̵�
	@Override
	public int cart_putOrder(CartDTO dto){

		System.out.println("    : cart_putOrder() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int cnt = 0;
		
				
		try{
			conn = data.getConnection();
			
			String sql = "INSERT INTO BUY "	+
						"( O_NUM , B_NUM , M_NUM , QUAN , PRICE, REG_DATE, STATE  )" +
						"VALUES( BUY_SEQ.NEXTVAL,?,?,?,?,?,?) ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getB_num());
			pstmt.setInt(2, dto.getM_num());
			pstmt.setInt(3, dto.getQuan());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setTimestamp(5, dto.getReg_date());
			pstmt.setString(6, dto.getState());
			
			cnt = pstmt.executeUpdate();
			
			if ( cnt >0  ){
				System.out.println("    : ������ ������ �ֹ����̺�� �̵� �Ϸ�");
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


	// īƮ ��� ��ü �ҷ�����
	@Override
	public ArrayList<CartDTO> cart_getall(int m_num) {
	System.out.println("    : cart_getall() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList<CartDTO> carts = null;
		
		ResultSet rs = null;
				
		try{
			conn = data.getConnection();

			String sql = "SELECT B_NUM , M_NUM , " + 
					" QUAN , PRICE  " +
					" FROM CART " +
					" WHERE M_NUM=? ";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, m_num );
			rs = pstmt.executeQuery();
			
			if( rs.next()){
				
				carts = new ArrayList<>();
				
				do{
				
					CartDTO cart = new CartDTO();
					
					cart.setB_num( rs.getInt("B_NUM") );
					cart.setM_num( rs.getInt("M_NUM") );
					cart.setQuan( rs.getInt("QUAN") );
					cart.setPrice( rs.getInt("PRICE") );
					
					carts.add(cart);
					
				} while( rs.next() );
			}
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return carts;
	}

	
	// īƮ ��� �迭�� �ֹ� ���̺� ����
	@Override
	public int cart_moveorder( ArrayList<CartDTO> carts ) {
		
		System.out.println("    : cart_moveorder() �żҵ� ����");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt =0;
				
		try{
			conn = data.getConnection();
			
			for (int i=0 ; i<carts.size() ; i++){
			
				CartDTO dto = carts.get(i);
				
				String sql = "INSERT INTO BUY " +
							"( O_NUM , B_NUM , M_NUM , QUAN , PRICE )" +
							" VALUES( BUY_SEQ.NEXTVAL , ?,?,?,? )";
				
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
	

	
	
	
}
