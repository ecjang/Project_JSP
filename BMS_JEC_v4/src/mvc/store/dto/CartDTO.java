package mvc.store.dto;

import java.sql.Timestamp;

public class CartDTO {
	
	private int c_num;		// ��ٱ��� ��ȣ
	private int b_num;		// ���� ��ȣ
	private int m_num;		// ȸ�� ��ȣ
	private int quan;		// �ֹ���ȣ
	private int price;		// ����
	private String state;		// ���� ����
	private Timestamp reg_date;	// �ֹ�����
	
	private String id;			// ȸ�� ���̵�
	private String title;		// �����̸�
	private int bookquan;	// �������
	
	//---------------------------------//
	
	
	public int getC_num() {
		return c_num;
	}

	public void setC_num(int c_num) {
		this.c_num = c_num;
	}

	public int getB_num() {
		return b_num;
	}

	public void setB_num(int b_num) {
		this.b_num = b_num;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public int getQuan() {
		return quan;
	}

	public void setQuan(int quan) {
		this.quan = quan;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBookquan() {
		return bookquan;
	}

	public void setBookquan(int bookquan) {
		this.bookquan = bookquan;
	}
	
	
	
	

}
