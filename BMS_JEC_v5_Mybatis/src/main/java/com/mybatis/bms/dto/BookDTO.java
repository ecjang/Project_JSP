package com.mybatis.bms.dto;

import java.sql.Timestamp;

public class BookDTO {
	
	// 변수 설정
	private int b_num;			// 도서 번호
	private int m_num;			// 회원 번호
	private String title;		// 제목
	private String subtitle;	// 부제
	private String author;		// 작가
	private int quan;			// 재고
	private int price;			// 가격
	private Timestamp reg_date;	// 입고일
	private String kind;		// 종류
	private String state;		// 상태
	
	// DB 속성명 외 변수
	private int ordernum;		// 주문번호
	private String id;			// 책을 구매한 회원 아이디
	private int c_num;			// 장바구니 번호
	private int o_num;			// 주문 번호 
	
	
	
	public int getO_num() {
		return o_num;
	}

	public void setO_num(int o_num) {
		this.o_num = o_num;
	}

	public int getC_num() {
		return c_num;
	}

	public void setC_num(int c_num) {
		this.c_num = c_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public void setB_num(int b_num) {
		this.b_num = b_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setQuan(int quan) {
		this.quan = quan;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getB_num() {
		return b_num;
	}

	public int getM_num() {
		return m_num;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getAuthor() {
		return author;
	}

	public int getQuan() {
		return quan;
	}

	public int getPrice() {
		return price;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public String getKind() {
		return kind;
	}

	public String getState() {
		return state;
	}

	@Override
	public String toString() {
		return "BookDTO [b_num=" + b_num + ", m_num=" + m_num + ", title=" + title + ", subtitle=" + subtitle
				+ ", author=" + author + ", quan=" + quan + ", price=" + price + ", reg_date=" + reg_date + ", kind="
				+ kind + ", state=" + state + "]";
	}
	
	
	
	
	
	
	

	
	 
}
