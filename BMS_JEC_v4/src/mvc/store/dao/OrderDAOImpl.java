package mvc.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import mvc.store.dto.BookDTO;
import mvc.store.dto.CartDTO;
import mvc.store.dto.OrderDTO;

public class OrderDAOImpl implements OrderDAO {
	
	DataSource data;
	
	private static OrderDAOImpl instance = new OrderDAOImpl();
	public static OrderDAOImpl getInstance(){
		return instance;
	}
	
	public OrderDAOImpl(){
		
		try{
			
			Context context = new InitialContext();
			data = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		
		}catch( Exception e ){
			e.printStackTrace();
		}
		
	}
	
	//-----------------------------------------------//
	
	// 도서 주문하기 
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
				sql="INSERT INTO BUY ( O_NUM , B_NUM, M_NUM, QUAN, PRICE, REG_DATE, STATE )" + 
					" VALUES ( BUY_SEQ.NEXTVAL ,?,?,?,?,?,?)";
					
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
					cnt=4;		// 주문에 입력중 오류 발생
				} 
			}
				
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
	
	
	// 주문 테이블 목록 불러오기
	@Override
	public int getOrderCnt() {
			
			System.out.println("    : getOrderCnt() 매소드 실행");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs =null;
			int cnt=0;
					
			try{
				
				conn = data.getConnection();
					
				String sql = "SELECT COUNT(*) FROM BUY";
				
				pstmt=conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
					
				if( rs.next()){
					
					cnt = rs.getInt(1);
					
				}
				
				System.out.println("    : 주문 개수 cnt값 : " + cnt + "개");
				
			} catch ( SQLException e) { 
				e.printStackTrace();
				
			} finally {
				try{
					if( conn != null ) conn.close();
					if( pstmt != null ) pstmt.close();
					if( rs != null ) rs.close();
				} catch( SQLException e) { 
					e.printStackTrace(); }
			}

			return cnt;

			
		}
		
	
	// 주문테이블 정보 가져오기
	@Override
	public ArrayList<OrderDTO> getOrder(int start, int end) {
		
		System.out.println("    : getOrder() 매소드 실행");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<OrderDTO> orders = null;
				
		try{
			
			conn = data.getConnection();
			
			/*
			"SELECT * " + 
			"FROM ( SELECT O_NUM, M_NUM, B_NUM, QUAN , PRICE , " +
						"REG_DATE, STATE , ROWNUM rNum " +
						
					"FROM ( SELECT * FROM BUY " +
							" ORDER BY O_NUM DESC " +
						 ") " +
					") " +
			"WHERE rnum BETWEEN ? AND ? ";
			*/
			
			String sql = 
					" SELECT * " + 
					" FROM ( SELECT O_NUM, M.ID M_ID, O.M_NUM, B.TITLE B_TITLE, O.B_NUM, " +  
					"       O.QUAN, O.PRICE, O.REG_DATE, O.STATE , ROWNUM rNum " +
					"       FROM BUY O "  + 
					"       JOIN MEMBER M ON O.M_NUM = M.M_NUM "  + 
					"       JOIN BOOK B   ON O.B_NUM = B.B_NUM ) "  + 
					" WHERE rNum BETWEEN ? AND ? "  + 
					" ORDER BY O_NUM " ;
				
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, start );
			pstmt.setInt(2, end );
			rs = pstmt.executeQuery();
			
			if( rs.next()){
				
				orders = new ArrayList<>();
				
				do{
				
					OrderDTO order = new OrderDTO();
					
					order.setO_num( rs.getInt("O_NUM") );
					order.setB_title( rs.getString("B_TITLE") );
					order.setM_id( rs.getString("M_ID"));
					order.setQuan( rs.getInt("QUAN") );
					order.setPrice( rs.getInt("PRICE") );
					order.setState( rs.getString("STATE"));
					order.setReg_date( rs.getTimestamp("REG_DATE"));
					
					
					orders.add(order);
					
				} while( rs.next() );
				System.out.println("    : 데이터 로딩 성공");
			} else {
				System.out.println("    : 데이터 로딩 중 오류 발생");
			}
			
			
		} catch ( SQLException e) { e.printStackTrace();
			
		} finally {
			try{
				if( conn != null ) conn.close();
				if( pstmt != null ) pstmt.close();
			} catch( SQLException e) { e.printStackTrace(); }
		}

		return orders;
	}

	
	// 총 결제금액
	@Override
	public int orderSum() {
		
		System.out.println("    : orderSum() 매소드 실행");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sum = 0;
				
		try{
			conn = data.getConnection();
				
			String sql = "SELECT price, sum(quan) as squan FROM BUY group by price"; 
			
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
	
	
	// 주문 테이블 상태 변화하기
	@Override
	public int changeState(int o_num, String state) {
		
		System.out.println("    : changeState() 매소드 실행");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt=0;
		int quan = 0;
				
		try{
			conn = data.getConnection();
			String sql = null;
			
			
			// ORDER_CANCEL 에서 SALE로 변경시 도서목록에 되돌림
			if( state.equals("SALE") ){
				
				// 주문량 불러오기
				sql = null;
				sql = "SELECT QUAN FROM BUY WHERE O_NUM=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, o_num);
				rs = pstmt.executeQuery();
				
				
				if( rs.next() ){
					
					quan = rs.getInt("QUAN");
					System.out.println("    : 주문량 : " + quan);
					
					// 삭제된 수량 만큼 도서로 반환
					sql=null;
					sql=
						" UPDATE BOOK SET QUAN=( QUAN + ? ) WHERE B_NUM= " +
						" ( SELECT O.B_NUM FROM BUY O  " +
						" JOIN BOOK B1 ON B1.B_NUM = O.B_NUM " +
						" JOIN BOOK B2 ON B2.M_NUM = O.M_NUM " +
						" WHERE O.O_NUM=? " +
						" ) ";
					
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, quan);
					pstmt.setInt(2, o_num);
					
					cnt = pstmt.executeUpdate();
						
					
					if ( cnt > 0 ){
						
						System.out.println("    : 주문취소 완료");
						cnt = 1;

						// 주문에서 삭제
						sql = null;
						sql = "DELETE BUY WHERE O_NUM=? ";
						
						pstmt.close();
						pstmt = conn.prepareStatement(sql);
						pstmt.setInt(1, o_num);
						cnt = pstmt.executeUpdate();
							
						if ( cnt > 0 ){
							
							System.out.println("    : 주문삭제 완료");
							cnt = 1;
						
						} else {
							
							System.out.println("    : 주문삭제중 오류 발생");
							cnt= 2;
						}
						
					} else {
						System.out.println("    : 도서로 이동중 오류발생");
						cnt = 3;
					}
					
					
				} else {	// 재고 없음
					System.out.println("    : 주문량이 없음 ");
					cnt= 4;
				}
				
			
			// state 상태가 아닐 때
			} else {	
			
				
				sql = null;
				sql = "UPDATE BUY SET STATE=? WHERE O_NUM=?";
				
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, state);
				pstmt.setInt(2, o_num);
				cnt = pstmt.executeUpdate();
				
				if (cnt>0){
					System.out.println("    : 상태값 변경 완료");
					cnt = 1;
				
				} else {
					
					System.out.println("    : 상태값 변경 중 오류발생");
					cnt = 0;
				}
			
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
			
	
	}
