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

	DataSource dataSource; //���ؼ� ��ü�� ����

	private static MemberDAOImpl instance = new MemberDAOImpl();
	public static MemberDAOImpl getInsatance() {
		return instance;
	}
	
	
	// ������ 	
	public MemberDAOImpl(){
		try{
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
			
		}catch( Exception e ){e.printStackTrace();}
	}
	
	// ��� ���ϱ�
	@Override
	public int num() {
		System.out.println("    : num() �޼ҵ� ����");
		
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
	
	// �ߺ� Ȯ�� �κ�
	@Override
	public int idCheck(String strid) {
		
		System.out.println("    : idCheck�޼ҵ� ����");
		
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("    : idcheck �޼ҵ���  id�� : " + strid);
		
		try{
			conn = dataSource.getConnection();		// Ŀ�ؼǿ� ����
			
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
		System.out.println("    : idcheck�� cnt�� : " + cnt);
		return cnt;
	}
	
	// ȸ�� ���� ���
	@Override
	public int mem_insert(MemberDTO dto) {
		System.out.println("  -> mem_insert(dto) �޼ҵ� ����");
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
			System.out.println(" - cnt : " + cnt +"[ 1 �Է¿Ϸ�, 0 �Է¿��� ]" );
			
			if (cnt>0) System.out.println(" - �Է��� ���������� �Ϸ� �Ǿ����ϴ�.");
			else System.out.println(" - �Էµ��� ������ �߻��߽��ϴ�.");
			
			/*---------------------------------------------------*/
			/*
			String sql2 = "SELECT * FROM MEMBER WHERE id=?";
			pstmt.close();
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, dto.getId());
			rs=pstmt.executeQuery();
			
			while( rs.next() ){
				System.out.println("  - �׽�Ʈ : insert�� ���� ��� ");
				System.out.println(
					"  --> ���̵� : " 	+ rs.getString("ID") 		+ "\n"+
					"  --> ��� : " 	+ rs.getString("PW") 	+ "\n"+
					"  --> �̸� : " 	+ rs.getString("Name") 		+ "\n"+
					"  --> �ֹ�1 : " 	+ rs.getString("jumin1") 	+ "\n"+
					"  --> �ֹ�2 : " 	+ rs.getString("jumin2") 	+ "\n"+
					"  --> ����ó : " 	+ rs.getString("HP") 		+ "\n"+
					"  --> �̸��� : " 	+ rs.getString("EMAIL") 	+ "\n"+
					"  --> ������ : " 	+ rs.getString("REG_DATE") 	+ "\n"
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
	
	// LoginProHandler���� ��û : ���̵�� ��й�ȣ�� db�� �ִ��� üũ
	// cnt ���� ���� ( 1: ���̵�, �н����� ��ġ, 0 : �н����尡 ����ġ, -1:���̵�,�н����� �Ѵ� ����ġ )
	@Override
	public int check(String id, String pwd) {
		
		System.out.println("    : check(id,pwd)�żҵ� ����");
		
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
				
				System.out.println("    : ���̵� ��ġ�մϴ�.");
				
				String memid = rs.getString(2);
				String mempw = rs.getString(3);
				
				if ( mempw.equals(pwd) ){ 
					System.out.println("    : ��й�ȣ�� ��ġ�մϴ�."); 
					cnt=1; 
					
				} else { 
					cnt=-1; 
					System.out.println("    : ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");  }
				
			} else { 
				System.out.println("    : ���̵� ��ġ���� �ʽ��ϴ�."); 
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

	// ȸ��Ż��,  DeleteProHandler���� ��û
	@Override
	public int delete(String strid) {
		
		System.out.println("    : delete(id) �żҵ� ����");
		
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
				System.out.println("    : ���������� ȸ�������� ����");
				cnt=1;
			}else{
				System.out.println("    : �������� ������ �߻�");
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
	
	// ȸ������ ��ȸ,  ModifyViewHandler���� ��û
	@Override
	public MemberDTO getMember(String strid) {
		
		System.out.println("    : getMember() �żҵ带 ����");
		
		int cnt=0;
		Connection conn=null;; PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		// �ٱ��ϸ� ����
		MemberDTO dto = new MemberDTO();
		
		try{
			conn = dataSource.getConnection();
			String sql="SELECT * FROM MEMBER WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strid);
			rs=pstmt.executeQuery();
			
			// rs�� �о �ٱ��Ͽ� ��´�.
			
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
			System.out.println("  - �׽�Ʈ ������ ��� : " + 
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
	
	// ȸ������ ����
	@Override
	public int update(MemberDTO dto) {
		
		System.out.println("    : update(dto) �żҵ� ����");
		
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
			System.out.println("    : cnt�� �� : " + cnt);
			pstmt.close();
			
			if(cnt>0) {
				System.out.println("    : ���������� �����Ϸ�");
				sql = "SELECT * FROM MEMBER WHERE id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getId());
				rs = pstmt.executeQuery();
				
				while( rs.next() ){
					System.out.println("    : update�� ���� ��� ");
					System.out.println(
						"    : ���̵� : " 	+ rs.getString("ID") 		+ "\n"+
						"    : ��� : " 		+ rs.getString("PW") 	+ "\n"+
						"    : �̸��� : " 	+ rs.getString("EMAIL") 	+ "\n"+
						"    : ����ó : " 	+ rs.getString("HP") 		+ "\n");
				}
				
			}else System.out.println("    : ������ ���� �߻�");
			
			
			
			
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

	
	// ȸ������ �Ǻ�
	@Override
	public int kind(String id) {
		
		System.out.println("    : kind(id)�żҵ� ����");
		
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

	// ȸ����ȣ ���ϱ�
	@Override
	public int m_num(String id) {
		
		System.out.println("    : m_num(id)�żҵ� ����");
		
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
