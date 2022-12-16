package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import VO.VO;
import DAO.DAO;

public class Resgister {
	DAO dao = new DAO();
	VO vo = new VO();
	private Connection con = null;
	private Statement st = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private int userNo;

	Scanner sc = new Scanner(System.in);
	String userId, userPw, userName, userEmail;
	String tmp = null;
	Boolean result = false;
	public Connection dbConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			con = DriverManager.getConnection(url, "vocagame", "voca");
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public VO resister() throws Exception {
		try {
			con = dbConnect();
			System.out.println("\n  [회원가입]\n\t\t\t[9. 뒤로가기]");

			setuserId();
			if (userId.equals("9")) {
				return vo;
			}
			setuserPw();
			if (userPw.equals("9") || tmp.equals("9")) {
				return vo;
			}
			setuserName();
			if (userName.equals("9")) {
				return vo;
			}
			setuserEmail();
			if (userEmail.equals("9")) {
				return vo;
			}
//			setuserId();
//			setuserPw();
//			setuserName();
//			setuserEmail();

			resisterUser(userNo, userId, userPw, userName, userEmail);
			System.out.println("\n회원가입이 완료되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pst.close();
			con.close();
		}
		return vo;

	}// join end

	public void setuserId() {
		loop: while (true) {
			while (true) {
				System.out.print("\n  아이디 (2-10자,공백X,한글X) \n >>> ");
				userId = sc.nextLine();
				if (userId.equals("9")) {
					return;
				} else if (stringCheck(userId, 2, 10) || userId.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
					System.out.println("\n양식이 잘못되었습니다. 다시 입력해주세요.");
					continue;
				}
				break;
			} // while end
			try {
				String msg = "select * from userinfo";
				rs = st.executeQuery(msg);
				while (rs.next() == true) {
					String tmpid = rs.getString("userid");
					if (userId.equals(tmpid)) {
						System.out.println("\n이미 있는 ID입니다.");
						continue loop;
					} // if end
				} // while end
				System.out.println("\n아이디로 사용가능합니다.");
				break;
			} catch (Exception e) {
				System.out.println("error" + e);
			}
		} // while end
	}// setID end

	// 아이디 입력

	// 비밀번호입력
	public void setuserPw() {
		while (true) {
			loop: while (true) {
				System.out.print("\n  비밀번호 (8-15자,공백X,한글X) \n >>> ");
				userPw = sc.nextLine();
				if (userPw.equals("9")) {
					return;
				} else if (stringCheck(userPw, 8, 15) || userPw.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
					System.out.println("\n양식이 잘못되었습니다. 다시 입력해주세요.");
					continue;
				}
				break loop;
			} // while end
			System.out.print("\n  비밀번호 재확인 \n >>> ");
			tmp = sc.nextLine();
			if (tmp.equals("9")) {
				return;
			} else if (userPw.equals(tmp)) {
				System.out.println("\n비밀번호가 일치합니다.");
				break;
			} else {
				System.out.println("\n비밀번호가 일치하지 않습니다.");
				continue;
			} // if end
		} // while end
	}// setPw end
		// 이름 설정

	public void setuserName() {
		while (true) {
			System.out.print("\n  이름 (2-10자,공백X) \n >>> ");
			userName = sc.nextLine();
			if (userName.equals("9")) {
				return;
			} else if (stringCheck(userName, 2, 10)) {
				System.out.println("\n양식이 잘못되었습니다. 다시 입력해주세요.");
				continue;
			}
			break;
		} // while end
	}// setName end

	public void setuserEmail() {
		while (true) {
			System.out.print("\n  email (12-25자,공백X,한글X) \n >>> ");
			userEmail = sc.nextLine();
			if (userEmail.equals("9")) {
				return;
			} else if (stringCheck(userEmail, 12, 25) || emailCheck(userEmail)
					|| userEmail.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
				System.out.println("\n양식이 잘못되었습니다. 다시 입력해주세요.");
				continue;
			}
			break;
		} // while end
	}// setEmail end

	public boolean stringCheck(String string) {
		boolean check = string == null || string.isEmpty() || string.indexOf(" ") != -1;
		if (check) {
			System.out.println("\n양식이 잘못되었습니다. 다시 입력해주세요.");
		}
		return check;
	}// stringCheck end

	public boolean stringCheck(String string, int minLength, int maxLength) {
		boolean check = string == null || string.isEmpty() || string.indexOf(" ") != -1 || string.length() < minLength
				|| string.length() > maxLength;
		return check;
	}// stringCheck end

	// 이메일에크 @있는지,@뒤에 '.'이 있는지 @앞에 1글자이상 있는지, @뒤에 5글자이상 있는지
	public boolean emailCheck(String email) {
		boolean check = true;
		if (email.contains("@")) {
			int index = email.indexOf("@");
			String front = email.substring(0, index);
			int frontLen = front.length();
			String last = email.substring(index + 1);
			int lastLen = last.length();
			check = !last.contains(".") || frontLen < 1 || lastLen < 5;
		}
		return check;
	}// emailCheck end

	public boolean resisterUser(int userNo, String userId, String userPw, String userName, String userEmail) {
		try {
			con = dbConnect();
			String msg = "INSERT INTO USERINFO(userno, userid, userpw, username, useremail) VALUES(seq.nextval, ?, ?, ?, ?)";
			
			pst = con.prepareStatement(msg);
			pst.setString(1, userId);
			pst.setString(2, userPw);
			pst.setString(3, userName);
			pst.setString(4, userEmail);
			
			int cnt = pst.executeUpdate();
			System.out.println(cnt);
			if (cnt > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.close(rs);
			dao.close(pst);
			dao.close(con);
		}
		return result;

	}// insertMember end
}// register Class END
