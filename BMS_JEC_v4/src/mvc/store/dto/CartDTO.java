package mvc.store.dto;

import java.sql.Timestamp;

public class CartDTO {
	
	private int c_num;		// 장바구니 번호
	private int b_num;		// 서적 번호
	private int m_num;		// 회원 번호
	private int quan;		// 주문번호
	private int price;		// 가격
	private String state;		// 도서 상태
	private Timestamp reg_date;	// 주문일자
	
	private String id;			// 회원 아이디
	private String title;		// 서적이름
	private int bookquan;	// 서적재고
	
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
