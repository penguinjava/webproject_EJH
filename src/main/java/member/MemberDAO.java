package member;

import common.DBConnPool;

public class MemberDAO extends DBConnPool{
	// 생성자 생량
	
	//회원가입 등록
	public int insertMember(MemberDTO dto) {
		int result = 0;
		
		// 쿼리문 작성
		try {
		String query = "INSERT INTO member "
				+ " (user_id, user_name, password, "
				+ " nickname, email, phone_number, "
				+ " gender, address) "
				+ " VALUES(?,?,?,?,?,?,?,?)";
		
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getUser_id());
			psmt.setString(2, dto.getUser_name());
			psmt.setString(3, dto.getPassword());
			psmt.setString(4, dto.getNickname());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getPhone_number());
			psmt.setString(7, dto.getGender());
			psmt.setString(8, dto.getAddress());
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("회원 등록 실패");
			e.printStackTrace();
		}
		return result;
	}
}
