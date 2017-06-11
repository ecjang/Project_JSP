package mvc.store.dto;

import java.sql.Timestamp;

public class BoardDTO {

	private int M_NUM; 
	private int NO; 
	private String TITLE; 
	private String WRITER; 
	private String CONTENT; 
	private Timestamp REG_DATE ;
	private int VIEWS ;
	private int REF ;
	private int REF_STEP ;
	private int REF_LEVEL;
	private String KIND ;
	private String IP;
	
	
	public int getM_NUM() {
		return M_NUM;
	}
	public void setM_NUM(int m_NUM) {
		M_NUM = m_NUM;
	}
	public int getNO() {
		return NO;
	}
	public void setNO(int nO) {
		NO = nO;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getWRITER() {
		return WRITER;
	}
	public void setWRITER(String wRITER) {
		WRITER = wRITER;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public Timestamp getREG_DATE() {
		return REG_DATE;
	}
	public void setREG_DATE(Timestamp rEG_DATE) {
		REG_DATE = rEG_DATE;
	}
	public int getVIEWS() {
		return VIEWS;
	}
	public void setVIEWS(int vIEWS) {
		VIEWS = vIEWS;
	}
	public int getREF() {
		return REF;
	}
	public void setREF(int rEF) {
		REF = rEF;
	}
	public int getREF_STEP() {
		return REF_STEP;
	}
	public void setREF_STEP(int REF_STEP) {
		REF_STEP = REF_STEP;
	}
	public int getREF_LEVEL() {
		return REF_LEVEL;
	}
	public void setREF_LEVEL(int REF_LEVEL) {
		REF_LEVEL = REF_LEVEL;
	}
	public String getKIND() {
		return KIND;
	}
	public void setKIND(String kIND) {
		KIND = kIND;
	}

	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	
	
	
	@Override
	public String toString() {
		return "BoardDTO [M_NUM=" + M_NUM + ", NO=" + NO + ", TITLE=" + TITLE + ", WRITER=" + WRITER + ", CONTENT="
				+ CONTENT + ", REG_DATE=" + REG_DATE + ", VIEWS=" + VIEWS + ", REF=" + REF + ", REF_STEP=" + REF_STEP
				+ REF_LEVEL + ", KIND=" + KIND + "]";
	}
	
	
}
