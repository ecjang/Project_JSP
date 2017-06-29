package mvc.store.dao;

public class test {
	public static void main(String[] args) {
		System.out.println("테스트 입니다.");
		
		String str = "테스트 입니다.";
		
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
