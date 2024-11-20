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
	
	//아이디 중복 체크
	public MemberDTO selectInfo(String user_id) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member "
				+ " WHERE user_id=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, user_id);
			//쿼리 적용
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setUser_id(rs.getString(user_id));
				dto.setUser_name(rs.getString(2));
				dto.setPassword(rs.getString(3));
				dto.setNickname(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setPhone_number(rs.getString(6));
				dto.setGender(rs.getString(7));
				dto.setAddress(rs.getString(8));
				dto.setJoin_date(rs.getString(9));
			}
			
		} catch (Exception e) {
			System.out.println("정보를 불어오기 에러");
			e.printStackTrace();
		}
		return dto;
	}
	
	
	//로그인 인증
	public MemberDTO login(String user_id, String password) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member "
				+" WHERE user_id=? and password=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, user_id);
			psmt.setString(2, password);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_name(rs.getString(2));
				dto.setPassword(rs.getString("password"));
				dto.setNickname(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setPhone_number(rs.getString(6));
				dto.setGender(rs.getString(7));
				dto.setAddress(rs.getString(8));
				dto.setJoin_date(rs.getString(9));
			}
			
		} catch (Exception e) {
			System.out.println("로그인 인증 실패");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	
	// 중복체크
	public boolean check(String type, String value) {
        boolean available = false;
        // DB 연결 설정
        String query = "SELECT COUNT(*) "
        		+ " FROM member WHERE " + type + " = ?";
        try{
        	psmt = con.prepareStatement(query);
            psmt.setString(1, value);
            rs = psmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                available = (count == 0); // 중복되지 않으면 사용 가능
            }
        } catch (Exception e) {
        	System.out.println("중복체크중 에러");
            e.printStackTrace();
        }
        return available;
    }
}
