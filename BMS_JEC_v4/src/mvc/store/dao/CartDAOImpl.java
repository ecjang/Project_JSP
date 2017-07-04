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
	

	// 도서 상태 변경 : 장바구니에 추가 
	@Override
	public int bookstate( BookDTO dto) {
		
		System.out.println("    : bookstate() 매소드 실행");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		int in_cont=0;
		
		try{
			
			conn = data.getConnection();
			
			// 우선 재고 파악 부터
			
			String sql="SELECT QUAN FROM BOOK WHERE B_NUM=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getB_num());
			rs = pstmt.executeQuery();
	

			if( rs.next() ){
				
				if( rs.getInt("QUAN") >= dto.getQuan() ){
					
					// 주문 수량 만큼 재고 삭제
					sql = null;
					sql="UPDATE BOOK SET QUAN=(QUAN-?) WHERE B_NUM=?";
					
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, dto.getQuan() );
					pstmt.setInt(2, dto.getB_num() );
					rs.close();
					rs = pstmt.executeQuery();
					
					if(rs.next()){
						cnt = 1;	// 주문 수량 삭제 완료
					} else {
						cnt = 2;	// 삭제 중 오류
					}
				} else {
					cnt = 3;	// 재고수량이 주문수량보다 적음
				}
				
			} else {	
				cnt = 0 ;	// 재고 없음  
			} //  rs.next() 	
			
			
			
			
			if (cnt==1){
				
				// 주문 수량 만큼 도서 상태를 변경후 추가
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
					System.out.println("    : 등록완료");
				}else{
					System.out.println("    : 오류 발생");
					cnt=4;		// 카트에 입력중 오류 발생
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
			
			
			/*System.out.println("    : cnt값 : " + cnt);*/
			
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


	// 카트테이블 목록 불러오기
	@Override
	public ArrayList<CartDTO> getcart(int start, int end) {
		
		System.out.println("    : getcart(start, end) 매소드 실행");
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
			

			
			/*	수정전
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
						
						
						dto.setC_num(rs.getInt("C_NUM"));			// 장바구니 번호
						dto.setB_num(rs.getInt("B_NUM"));			// 도서번호
						dto.setM_num(rs.getInt("M_NUM"));			// 회원번호
						dto.setPrice(rs.getInt("PRICE"));			// 도서 가격
						dto.setQuan(rs.getInt("QUAN"));				// 주문수량 	
						dto.setReg_date(rs.getTimestamp("REG_DATE"));// 등록일
						dto.setState(rs.getString("STATE"));		// 도서상태
							
						// 추가 멤버 변수 
						dto.setTitle(rs.getString("TITLE")); 		// 도서제목
						dto.setId(rs.getString("ID")); 				// 회원ID
						dto.setBookquan(rs.getInt("B_QUAN"));		// 도서 재고 
						
						
						
						
						
						/*----------------------------------------------------------*/
						
						/*System.out.println(rs.getString("B_NUM")+"\n" );*/	// 테스트용
						
						carts.add(dto);
					
					} while(rs.next());
					
				System.out.println("    : 데이터 로딩 성공");
				
			} else {
				System.out.println("    : 데이터 로딩 중 오류 발생");
			}
				
				// 테스트용 
				/*
				sql = "SELECT * FROM CART";
				pstmt.close();
				pstmt=conn.prepareStatement(sql);
				rs.close();
				rs = pstmt.executeQuery();
				
				if (rs.next()){
					System.out.println("값이 들어갔음");
				}else {
					System.out.println("값이 업음");
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
	
	// 장바구니 목록 가져오기
	public int getCount_cart(){
		System.out.println("    : getCount_cart() 매소드 실행");
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
			System.out.println("    : 목록 cnt값 : " + cnt + "개");
			
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

	
	// 장바구니 수량 조절 : 주문 수량 감소 , 재고 증가
	@Override
	public int cartquanMinus(int c_num, int quan_abs) {
		
		System.out.println("    : cartquanModify() 매소드 실행");
		
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
					
					System.out.println("    : 장바구니 수량 변경 완료");
					
				} else {
					cnt = 0;
					System.out.println("    : 장바구니 수량 변경 중 오류 발생");	
				}
				
			} else {
				System.out.println("    : 도서 재고에서 주문 수량 추가중 오류 발생");
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


	// 장바구니 수량 조절 : 주문 수량 증가 , 재고 감소
	@Override
	public int cartquanPlus(int c_num, int quan_abs) {
		
		System.out.println("    : cartquanModify() 매소드 실행");
		
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
					
					System.out.println("    : 장바구니 수량 변경 완료");
					
				} else {
					cnt = 0;
					System.out.println("    : 장바구니 수량 변경 중 오류 발생");	
				}
				
			} else {
				System.out.println("    : 도서 재고에서 주문 수량 제외중 오류 발생");
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

	
	// 장바구니 단일목록 삭제
	@Override
	public int cartdel(int c_num) {

		System.out.println("    : cartdel() 매소드 실행");
		
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
	
	
	// 장바구니 단일목록 주문목록으로 변경
	@Override
	public int cart_orderconfirm(int c_num, int ordernum) {
		return 0;
	}
	
	
	// 카트 재고 불러오기 
	public int cartquanCnt(int c_num){
	System.out.println("    : cartquanCnt() 매소드 실행");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
				
		try{
			conn = data.getConnection();
			
			// 우선 재고가 충분한지 파악하자
			
			String sql = "SELECT QUAN FROM CART WHERE C_NUM=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, c_num);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				cnt = rs.getInt("QUAN");
				System.out.println("    : 주문수량 : " + rs.getInt("QUAN"));
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
	
	
	// 장바구니 원하는 서적수량 파악
	@Override
	public int bookquanCnt( int b_num  ){
		
		System.out.println("    : cart_quan() 매소드 실행");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
				
		try{
			conn = data.getConnection();
			
			// 우선 재고가 충분한지 파악하자
			
			String sql = "SELECT QUAN FROM BOOK WHERE B_NUM=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			rs = pstmt.executeQuery();
			
			if( rs.next() ){
				cnt = rs.getInt("QUAN");
				System.out.println("    : 재고수량 : " + rs.getInt("QUAN"));
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


	// 장바구니 서적 내용 불러오기
	@Override
	public CartDTO cart_getCart(int c_num , int ordernum) {
		
		System.out.println("    : cart_getCart() 매소드 실행");
		
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
				System.out.println("    : 장바구니 수량 변경 완료");
				
				dto.setB_num( rs.getInt("B_NUM"));
				dto.setM_num( rs.getInt("M_NUM"));
				dto.setQuan(ordernum);
				dto.setPrice( rs.getInt("PRICE"));
				dto.setReg_date( new Timestamp(System.currentTimeMillis()));
				dto.setState("ORDER");
				
				System.out.println("    : 주문한 회원 번호 : " + dto.getM_num());
				System.out.println("    : 주문 서적 가격 : " + dto.getPrice());
				
				System.out.println("    : dto 내용 변경 완료");
				
				
			} else {
				System.out.println("    : 구량 변경중 오류 발생");
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
	
	
	// 주문 테이블로 이동
	@Override
	public int cart_putOrder(CartDTO dto){

		System.out.println("    : cart_putOrder() 매소드 실행");
		
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
				System.out.println("    : 선택한 도서가 주문테이블로 이동 완료");
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


	// 카트 목록 전체 불러오기
	@Override
	public ArrayList<CartDTO> cart_getall(int m_num) {
	System.out.println("    : cart_getall() 매소드 실행");
		
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

	
	// 카트 목록 배열을 주문 테이블에 삽입
	@Override
	public int cart_moveorder( ArrayList<CartDTO> carts ) {
		
		System.out.println("    : cart_moveorder() 매소드 실행");
		
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

	
	// 주문테이블로 옮기고 카트 테이블 id 검색후 삭제 + 장바구니 전체 비우기
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
