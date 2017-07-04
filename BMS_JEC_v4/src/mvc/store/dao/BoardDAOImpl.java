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
	
	/* 1. �̱��� ���� -----------------------------------------------------*/
	private static BoardDAOImpl instance = new BoardDAOImpl();
	public static BoardDAOImpl getInstance(){
		return instance;
	}
	
	// ������ 	
	public BoardDAOImpl(){
		try{
			Context context = new InitialContext();
			data = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		}catch( Exception e ){e.printStackTrace();}
	}
		
		
	// getCount() : �Խ��� ���� ���ϱ�
	@Override
	public int getCount(String kind) {
		
		System.out.println("    : getConut(kind) �żҵ� ����");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String str = null;
		/*System.out.println("    : kind �� : " + kind);*/
		
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
			
			System.out.println("    : �Խù� ���� : " + cnt);
			
			
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
	
	
	// getArticles() : �Խ��� ���� ��������
	@Override
	public ArrayList<BoardDTO> getArticles(int start, int end , String kind) {
	
		System.out.println("    : getArticles(start, end, kind) �żҵ� ����");
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
		
		System.out.println("    : kind �� : " + kind);
		
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
	
	
	// getArticles() : �Խ��� ���� �������� (�ѹ���)
	@Override
	public BoardDTO getArticles(int no) {
		System.out.println("    : getArticles() �޼ҵ� ����");
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
				
				System.out.println("    : "+rs.getInt("NO")+"��° �Խù� ȣ�� �Ϸ�" );
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
	
	
	// addview() : ��ȸ�� ����
	public void addview(int no){
		
		System.out.println("    : addview() �޼ҵ� ����");
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
	
	// pwCheck() : ���̵�� ��й�ȣ Ȯ��
	@Override
	public int pwCheck(int Mnum, String ps) {
		
		System.out.println("    : pwCheck() �޼ҵ� ����");
		Connection conn = null; 
		PreparedStatement pstmt=null; 
		ResultSet rs=null;
		int cnt = 0;
		/*
		System.out.println("����� �Ѿ� ���ǰ�1? : " + Mnum);
		System.out.println("����� �Ѿ� ���ǰ�2? : " + ps);
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
					System.out.println("    : ��й�ȣ�� ��ġ cnt=1");
					cnt = 1;
				}
				
			} else {
				System.out.println("    : ��й�ȣ�� ����ġ cnt=0");
				
			}
			
			System.out.println("    : ��ġ�ϴ� ���� ���� cnt=0");
				
			
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
	

	// idCheck() : ���̵�� ��й�ȣ Ȯ��
	@Override
	public int idCheck(int Mnum) {
		
		System.out.println("    : idCheck() �޼ҵ� ����");
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
				System.out.println("    : ȸ����ȣ ��ġ cnt=1");
				cnt = 1;
				
			} else {
				System.out.println("    : ȸ����ȣ ����ġ cnt=0");
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
	
	
	// upadte() : ������ �Խ��Ǹ� ���
	@Override
	public int upadte(BoardDTO dto) {
		
		System.out.println("    : upadte() �޼ҵ� ���� ");
		
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
			System.out.println("  ���� : " + dto.getTITLE());
			System.out.println("  ���� : " + dto.getCONTENT());
			System.out.println("  �з� : " + dto.getKIND());
			System.out.println("  ��ȣ : " + dto.getNO());
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
				System.out.println("  Ȯ�� ���� : " + rs.getString("TITLE"));
				System.out.println("  Ȯ�� ���� : " + rs.getString("CONTENT"));
				System.out.println("  Ȯ�� �з� : " + rs.getString("KIND"));
				System.out.println("  Ȯ�� ��ȣ : " + rs.getInt("NO"));
			}
			*/
			
			System.out.println("    : �Խ��� ���� �Է� �Ϸ�, cnt="+cnt);
			
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
		
		System.out.println("    : delete() �żҵ� ���� ");
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
			
			if ( rs.next() ){	// ��� ���� ���� Ȯ��
				
				System.out.println("    : ��ۿ��� Ȯ��");
				
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
				
				
				if( rs.next() ){	// ����� ������ ���� �Ұ� (cnt=-1)
					cnt = -1;
					System.out.println("    : �������, cnt�� : " + cnt);
					
				} else {	// ����� ���� ���
					System.out.println("    : ��۾���, update, delete ����");
				
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
					System.out.println("    : cnt �� : " + cnt );
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

	
	
	// insert() : �ۼ��� �� ���ε�
	@Override
	public int insert(BoardDTO dto) {

		System.out.println("    : insert() �޼ҵ� ����");
		
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
			
			if( no == 0 ){ // ������ ���
				System.out.println("    : ���۷� �Է� ");
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
				
				
			} else {	// ����� ���
				System.out.println("    : ��۷� �Է� ");	
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
				System.out.println("    : ref_step�� : " + ref_step );
				System.out.println("    : ref_level�� : " + ref_level );
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
			
			System.out.println("    : cnt �� : " + cnt);
			
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
		
		System.out.println("    : kindCheck() �޼ҵ� ����");
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
				System.out.println("    : kind �� : " + kind);
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
