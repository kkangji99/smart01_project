package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import VO.VO;

public class DAO {
	VO vo = new VO();
	int totalCount;
	int answerCount;
	double answerRate;
	private static String userId, userPw, userName, userEmail;
	private int userNo;
	int userScore;
	String type, word, kor;
	String w_Word, w_Kor;
	boolean result;

	private int wordNo;

	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	String msg;
	ArrayList<VO> list = new ArrayList<VO>();

	public DAO() {
		this.dbConnect();
	}

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

	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(PreparedStatement pst) {
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void select(String userId) throws Exception {
		con = dbConnect();
		msg = "select * from userinfo where userid = '" + userId + "'";
		rs = st.executeQuery(msg);
		while (rs.next()) {

			vo.getUserNo();
			vo.getUserEmail();
			vo.getUserEmail();
			vo.getUserScore();
			vo.getTotalCount();
			vo.getAnswerCount();
			vo.getAnswerRate();
		}
	}

	public ArrayList<VO> questionSelect(String type2) {
		ArrayList<VO> list = new ArrayList<VO>();
		try {

			con = dbConnect();
			String sql = "SELECT *FROM(SELECT * FROM voca where type like ? ORDER BY dbms_random.value)";

//           String sql = "SELECT *FROM(SELECT * FROM voca where type like ? ORDER BY dbms_random.value)WHERE rownum <= 10";
			pst = con.prepareStatement(sql);
			pst.setString(1, type2);

			rs = null;
			rs = pst.executeQuery();
			while (rs.next()) {
				wordNo = rs.getInt("wordno");
				type = rs.getString("type");
				word = rs.getString("word");
				kor = rs.getString("kor");
				vo = new VO(wordNo, type, word, kor);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pst);
			close(con);
		}
		return list;

	}

	public boolean insertStd(String w_Word, String w_Kor, int userNo) throws SQLException {
		con = dbConnect();

		String sql = "insert into note2(w_Word,w_Kor,userNo) values(?,?,?)";// ? : 바인드 변수로 들어감
		// 3. SQL작성
		
	// 4. 바인드 변수 채우기 / 여기 인덱스는 1부터 시작함
		try {
			pst = con.prepareStatement(sql);

			pst.setString(1, w_Word);
			pst.setString(2, w_Kor);
			pst.setInt(3, userNo);
//			boolean res = false;
//			while(rs.next()) {
//				w_Word = vo.getW_Word();
//				w_Kor = vo.getW_Kor();
//				userNo = vo.getUserNo();
//				vo = new VO(w_Word, w_Kor, userNo);
////				res = true;
//			}

			int cnt = pst.executeUpdate();
			if (cnt > 0) {
				result = true;
			} else {
				result = false;
			}
//			if (res == false) {
//				vo = new VO();
//			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// 아래부터 닫아준다.
			close(rs);
			close(pst);
			close(con);
		}

		return result;

	}

	// rankview DAO
	public ArrayList<VO> rankSelect() {
		
		try {

			con = dbConnect();
			String sql = "SELECT * FROM USERINFO order by USERSCORE desc";

			pst = con.prepareStatement(sql);

			rs = null;
			rs = pst.executeQuery();
			while (rs.next()) {
				userId = rs.getString("USERID");
				userScore = rs.getInt("USERSCORE");

				vo = new VO(userId, userScore);
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pst);
			close(con);
		}
		return list;

	}

//	public VO selectedwords(int userNo) {
	public ArrayList<VO> selectedwords(){
	ArrayList<VO> list = new ArrayList<VO>();
	try {
			dbConnect();
			String sql = "select w_Word, w_Kor from note2 where userno = ? ";
			pst = con.prepareStatement(sql);
			pst.setInt(1, userNo);

//			boolean res = false;
			rs = null;
			rs = pst.executeQuery();
			
			while (rs.next()) {
				
				w_Word = rs.getString(1);
				w_Kor = rs.getString(2);
				vo = new VO(w_Word, w_Kor);
	            list.add(vo);
//				res = true;
			}
//			if (res == false) {
//				vo = new VO();
//			}
//				pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pst);
			close(con);

		}
		return list;
	}

	public VO deleteWord(String dword) {
		try {
			dbConnect();
			String sql = "delete from note2 where userNo= ? and w_word = ? ";
			pst = con.prepareStatement(sql);
//			pst.setInt(1, userNo);
			pst.setString(1, dword);

			int res = pst.executeUpdate();
			if (res > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return vo;
	}
	public boolean updateScore(String userId,  int userScore) 
	{
		dbConnect();

		// 3. SQL작성
		String sql = "update userinfo set userscore = ? where userid = ?";// ? : 바인드 변수로 들어감

		// 4. 바인드 변수 채우기 / 여기 인덱스는 1부터 시작함
		try {
			pst = con.prepareStatement(sql);
	           
			pst.setInt(1, userScore);
			pst.setString(2, userId);
			
			System.out.println("sql확인/"+sql);
			System.out.println("매개변수 확인"+userId+"/"+userScore);
			
			
			int cnt = pst.executeUpdate();
			if (cnt > 0) {
				result = true;
			} else {
				result = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// 아래부터 닫아준다.
			close(pst);
			close(con);
		}
		
		return result;
		
	}
	public VO selectScore(String userId,int userScore) 
	{
		dbConnect();

		// 3. SQL작성

		// 4. 바인드 변수 채우기 / 여기 인덱스는 1부터 시작함
		try {
			String sql = "select userscore from userinfo where userid = ?";// ? : 바인드 변수로 들어감
			pst = con.prepareStatement(sql);
			
			pst.setString(1, userId);

			// 4. SQL 실행처리
			// executeQuery => select
			boolean isList = false;
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				userScore = rs.getInt("userscore");

	            vo = new VO(userId,userScore);

				isList = true;
			}
			if(isList==false)
			{
				vo = new VO();
			}

	         
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// 아래부터 닫아준다.
			close(pst);
			close(con);
		}
		
		return (VO) vo;
		
	}

}