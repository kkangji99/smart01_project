package VO;

public class VO {
	private static int userNo; // 유저테이블 변수, 외부 접근 불가 private
	private static String userId; // 유저번호, 아이디, 비밀번호, 이름, 이메일, 점수
	private static String userPw; // 스코어는 프라이빗 안주려다 유저 테이블이라 그냥 줌
	private static String userName;
	private static String userEmail;
	private static int userScore;
	private static int wordNo;



	public VO(int wordNo, String type, String word, String kor) {
		this.wordNo = wordNo;
		this.type = type;
		this.word = word;
		this.kor = kor;
	}
	

	public VO(String w_Word, String w_Kor, int userNo) {
		this.userNo = userNo;
		this.w_Word = w_Word;
		this.w_Kor = w_Kor;
	}


	int totalCount;
	int answerCount;
	int answerRate; // 노트 테이블, 총 풀이문제 개수, 대답 수, 정답률
	String w_Word;
	String w_Kor; // 틀린문제 영어, 한국어

	String type;
	String word;
	String kor; // 사전 변수
	
	

	public VO(String w_Word, String w_Kor) {
		this.w_Word = w_Word;
		this.w_Kor = w_Kor;
	}

	public VO(String userId, int userScore) {
	      
	      this.userId = userId;
	      this.userScore = userScore;
	   }
	
	public VO(String userId, String userName, String w_Word) {
	
		this.userId = userId;
		this.userName = userName;
		this.w_Word = w_Word;
	}

	public VO(String userId, String userPw, String userName, int userScore) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userScore = userScore;
	}

	// 유저 변수 접근을 위한 함수
	public VO(int userNo, String userId, String userPw, String userName, String userEmail) {
		this.userNo = userNo;
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userEmail = userEmail;
	}

	// 애도 마찬가지, 애는 아이디 생성때나 수정할때 쓰면 될듯..?
	public VO(String userId, String userPw, String userName, String userEmail) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userEmail = userEmail;
	}

	// 난 아직도 이아이는 왜 만드는지 모른다...아시는분 알려주셈...
	public VO() {
	}

	public int getUserNo() {
		return userNo;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public int getUserScore() {
		return userScore;
	}

	public int getWordNo() {
		return wordNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public int getAnswerRate() {
		return answerRate;
	}

	public String getW_Word() {
		return w_Word;
	}

	public String getW_Kor() {
		return w_Kor;
	}

	public String getType() {
		return type;
	}

	public String getWord() {
		return word;
	}

	public String getKor() {
		return kor;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	public void setWordNo(int wordNo) {
		this.wordNo = wordNo;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}

	public void setAnswerRate(int answerRate) {
		this.answerRate = answerRate;
	}

	public void setW_Word(String w_Word) {
		this.w_Word = w_Word;
	}

	public void setW_Kor(String w_Kor) {
		this.w_Kor = w_Kor;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public void setKor(String kor) {
		this.kor = kor;
	}

	@Override
	public String toString() {
		return "VO [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userEmail=" + userEmail + ", userScore=" + userScore + ", wordNo=" + wordNo + ", totalCount="
				+ totalCount + ", answerCount=" + answerCount + ", answerRate=" + answerRate + ", w_Word=" + w_Word
				+ ", w_Kor=" + w_Kor + ", type=" + type + ", word=" + word + ", kor=" + kor + "]";
	}

	
}
