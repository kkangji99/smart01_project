package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import VO.VO;

public class Login {
	//로그인 클래스에서 
	public static String sendId;//로그인 한 현재 아이디

	//
	private Connection con = null;
	private Statement st = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	String sql;
	int userNo;
	Scanner sc = new Scanner(System.in);
	String userId, userPw, userName, userEmail;
	private String userID;
	String userPW;
	String fname, femail, fID, fpsw, rpsw;
	int userScore;
	DAO dao = new DAO();
	VO vo = new VO();
	Game gm = new Game();

	public VO login() throws Exception {
		try {
			con = dao.dbConnect();
			while (true) {
				System.out.println("\n  [로그인]");
				System.out.print("\n  ID>>> ");
				userID = sc.nextLine();
				System.out.print("  Password >>> ");
				userPW = sc.nextLine();
				sql = "select * from userinfo where userId = ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, userID);
				rs = pst.executeQuery();
				while(rs.next()) {
					userNo = rs.getInt("userNo");
					userId = rs.getString("userId");
					userPw = rs.getString("userPw");
					userName = rs.getString("userName");
					userScore = rs.getInt("userScore");
					vo = new VO(userId, userPw, userName, userScore);
//					System.out.println(vo.toString());
				}
				
//				System.out.println("\n===================================================================\n");
//				System.out.println("userId"+userId);
//				System.out.println("userID"+userID);
//				System.out.println("userPw"+userPw);
//				System.out.println("userPW"+userPW);
//				System.out.println("\n===================================================================\n");
				if (userId.equals(userID) && userPw.equals(userPW)) {
					sendId = userID;
					System.out.println(userId);
					dao.select(userId);
					System.out.println(userNo);
					System.out.println("\n===================================================================\n");
					System.out.println("\t" + "҉ ٩(๑>ω<๑)۶҉      단어 맞추기 게임  ꉂ (๑¯ਊ¯)σ \n");
					Game.main();
					System.out.println(userName + "님 환영합니다!");
					System.out.println("\n===================================================================\n");

					return vo;
				} else {
					System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.\n");
					return vo;				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pst.close();
			con.close();
		}
			return vo;
	}
}
