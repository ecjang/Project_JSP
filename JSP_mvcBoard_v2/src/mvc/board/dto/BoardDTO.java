package mvc.board.dto;

import java.sql.Timestamp;

public class BoardDTO {
	
	// ���� ����
	private int num, readCnt, ref, ref_step, ref_level;		// �۹�ȣ, �׷���̵�, �׷콺��, �׷췹��
	private String writer, passwd, subject, content, ip;	// �۰�, ���, ����, ���� , ip
	private Timestamp reg_date;								// �ۼ���
	
	
	
	@Override
	public String toString() {
		return " num=" + num + "\n readCnt=" + readCnt + "\n ref=" + ref + "\n ref_step=" + ref_step
				+ "\n ref_level=" + ref_level + "\n writer=" + writer + "\n passwd=" + passwd + "\n subject=" + subject
				+ "\n content=" + content + "\n ip=" + ip + "\n reg_date=" + reg_date + "]";
	}

	// get,set ����
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getRef_step() {
		return ref_step;
	}

	public void setRef_step(int ref_step) {
		this.ref_step = ref_step;
	}

	public int getRef_level() {
		return ref_level;
	}

	public void setRef_level(int ref_level) {
		this.ref_level = ref_level;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String password) {
		this.passwd = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
	
	 
}
