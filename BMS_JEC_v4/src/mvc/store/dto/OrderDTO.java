package mvc.store.dto;

import java.sql.Timestamp;

/**
 * @author yht
 *
 */
public class OrderDTO {
	
	private int o_num;		// �ֹ� ��ȣ
	private int b_num;		// ���� ��ȣ
	private int m_num;		// ȸ�� ��ȣ
	private int quan;		// �ֹ� ����
	private int price;		// �ֹ� ����
	private Timestamp reg_date;	// �ֹ���
	private String state;	// �ֹ� ����
	
	private String b_title;
	private String m_id;
	
	
	
	
	
	
	//---------------------------------//
	
	
	public int getO_num() {
		return o_num;
	}

	public void setO_num(int o_num) {
		this.o_num = o_num;
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

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	
	
	

}
