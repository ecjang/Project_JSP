package mvc.store.dao;

public class test {
	public static void main(String[] args) {
		System.out.println("�׽�Ʈ �Դϴ�.");
		
		String str = "�׽�Ʈ �Դϴ�.";
		
		System.out.println(
		"SELECT * " + 
		"FROM ( SELECT M_NUM, NO, TITLE, WRITER, CONTENT, " + 
						"REG_DATE, VIEWS, REF, REF_STEP, " + 
						"REF_LEVLE, KIND, IP, rowNum rNum " + 
				"FROM ( SELECT * FROM BOARD " 
						+ str +
						" ORDER BY M_NUM DESC " +
					 ") " +
				") " +
		"WHERE rNum >= ? AND rNum <= ? "
				);
		
	}
}
