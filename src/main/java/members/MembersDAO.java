package members;

import common.DBConnPool;

public class MembersDAO extends DBConnPool{
	// 생성자 생량
	
	//회원가입 등록
	public int insertMember(MembersDTO dto) {
		int result = 0;
		
		// 쿼리문 작성
		String query = "INSERT INTO members "
				+ " (id,userName,password,nickname, "
				+ " email,phonNumber,gender,address) "
				+ " VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			psmt = con.prepareStatement(query);
		} catch (Exception e) {
			System.out.println("회원 등록 실패");
			e.printStackTrace();
		}
		
		return result;
	}
}
