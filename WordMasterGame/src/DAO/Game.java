package DAO;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import VO.VO;

public class Game {
//	RankView rv = new RankView();

	public static void main() {

		ModeChoiceView();

	}

	public static String TypeCheck(int input)// 난이도 체크 함수
	{
		String type = null;

		switch (input) {
		// 이지
		case 1:
			type = "E";
			break;
		// 노말
		case 2:
			type = "N";
			break;
		// 하드
		case 3:
			type = "H";
			break;

		}

		return type;
	}

	public static int TypeCheckForSCore(int type)// 난이도 체크 함수
	{
		int typeScore = 0;

		switch (type) {
		// 이지
		case 1:
			typeScore = 10;
			break;
		// 노말
		case 2:
			typeScore = 30;
			break;
		// 하드
		case 3:
			typeScore = 50;
			break;

		default:
			break;
		}

		return typeScore;
	}

	   public static void GameEndView(int totalScore)// 종료 시 출력되는 뷰
	   {
		   Scanner sc = new Scanner(System.in);
		

		
	      System.out.println(
	            "\n\n____________________________________________________________________________________________________\r\n"
	                  + "   \n\n게임 종료!\r\n" + "");
	      System.out.println("총 점수 : " + totalScore);
	      System.out.println(
	            "____________________________________________________________________________________________________\r\n"
	                  + " ____ \r\n" + "||0 ||\r\n" + "||__|| 확인\r\n" + "|/__\\| \r\n" + "\r\n" + " ____ \r\n"
	                  + "||9 ||\r\n" + "||__|| 종료\r\n" + "|/__\\| \r\n" + "\r\n"
	                  + "____________________________________________________________________________________________________");
	   
	      	DAO dao= null;
			VO vo = new VO();
			VO ansVo = null;

			dao = new DAO();
			
			boolean result = false;
			
			Login login = new Login();
			Resgister res = new Resgister();
			
			
			//userid가져옴			

			vo = dao.selectScore(login.sendId, totalScore);
			
			if(vo.getUserScore()<totalScore)
			{
				result = dao.updateScore(login.sendId,totalScore);
			}
			
			
			
			if(result==true)
			{
				System.out.println("BestScore 업데이트!");
			}
			else
			{
//				System.out.println("점수 수정 실패");
			}
	      
	      

	      
		  while(true) { 
		   try {
			   int input = sc.nextInt();
				   if(input==0)//확인
				   {
					   ModeChoiceView();  
				   }
				   else if(input==9)//종료
				   {
					   System.out.println("프로그램을 종료합니다.");
					   //프로그램 종료 코드
						System.exit(0);
						
						
				   }
				   else//다른 숫자 키 입력 받았을 때
				   {
		               System.out.println("키를 다시 입력 해 주세요");
		               System.out.println(
		                       "____________________________________________________________________________________________________");
		               
					   
				   }break;
			   }
		   catch(Exception e)
		   {
	           System.out.println("키를 다시 입력 해 주세요");
	           System.out.println(
	                   "____________________________________________________________________________________________________");
	           
		    	  sc = new Scanner(System.in);
		   }
		  }

	   }

	public static void TypeChoiceView()// 난이도 선택 뷰 함수//플레이 대부분이 여기에
	{
		System.out.println(
				"____________________________________________________________________________________________________\r\n"
						+ "         난이도를 선택하세요      \r\n"
						+ "____________________________________________________________________________________________________\r\n"
						+ " ____ \r\n" + "||1 ||\r\n" + "||__|| EASY\r\n" + "|/__\\|\r\n" + "\r\n" + " ____ \r\n"
						+ "||2 ||\r\n" + "||__|| NORMAL\r\n" + "|/__\\|\r\n" + "\r\n" + " ____ \r\n" + "||3 ||\r\n"
						+ "||__|| HARD\r\n" + "|/__\\|\r\n"
						+ "____________________________________________________________________________________________________");

		DAO dao = null;
		VO vo = null;
		VO ansVo = null;

		dao = new DAO();

		boolean result = false;

		String[] answer = new String[4];
		ArrayList<VO> arr = null;

		// 문제번호 카운팅
		int quesCount = 0;

		// 총점 계산 스코어
		int score = 0;

		// 타입별 더해지는 점수가 달라진다
		int typeScore = 0;

		// 콤보 점수용 스코어
		int bonusScore = 0;
		int bonusCount = 0;

		// 목숨 3개
		int life = 3;

		Random rnd = new Random();

		// 문제 랜덤 변수
		int rndNum = 0;

		int ansRndNum = 0;

		// 스캐너
		Scanner sc = new Scanner(System.in);

		// 입력값 위한 변수
		int typeInput = 0;

		// 정해진 난이도입력값 말고 다른 걸로 하면 체크해준다.
		boolean typeCheck = false;

		// 난이도 별 문제 가져옴
		while (typeCheck != true) {
			try {
				typeInput = sc.nextInt();

				if (typeInput == 1 || typeInput == 2 || typeInput == 3) {

					arr = dao.questionSelect(TypeCheck(typeInput));
					//
					// 점수 스위치 다시 만들어야 함
					typeScore = TypeCheckForSCore(typeInput);

					typeCheck = true;

				} else {
					System.out.println("키를 다시 입력 해 주세요");
					System.out.println(
							"____________________________________________________________________________________________________");
					typeCheck = false;
				}

			} catch (Exception e) {
				System.out.println("키를 다시 입력 해 주세요");
				System.out.println(
						"____________________________________________________________________________________________________");

				sc = new Scanner(System.in);

			}
		}

		// 목숨이 0이상이고 타입이 정상적으로 입력이 되었을 때
		while (life > 0 && typeCheck == true) {
			// 문제 번호 증감
			quesCount++;

			// 문제 출력 뷰
			System.out.println(
					"____________________________________________________________________________________________________");
			System.out.println(quesCount + "번 문제\t\t\t" + "점수 : " + score + "\t\t\t목숨 : " + life);
			System.out.println(
					"____________________________________________________________________________________________________");

			rndNum = rnd.nextInt(arr.size() - 1) + 0;
			vo = arr.get(rndNum);
			System.out.println("\n\t" + vo.getWord() + "\n\n");

			// 답안이 중복이 아니라면 배열에 넣어라

			for (int i = 0; i < 4; i++) {
				ansRndNum = rnd.nextInt(arr.size() - 1) + 0;
				ansVo = arr.get(ansRndNum);
				answer[i] = ansVo.getKor();

				for (int j = 0; j < i; j++) {
					if (answer[i] == answer[j]) {
						i--;
					}
				}

			}

			// 문제의 답을 포함하고 랜덤으로 위치부여, 나머지 3개는 랜덤
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				if (answer[i] == vo.getKor()) {
					cnt++;
				}
			}
			if (cnt <= 0) {
				int fourRnd = rnd.nextInt(3) + 1;
				answer[fourRnd] = vo.getKor();

			}
			// 4지선다형 답안들 출력
			for (int i = 0; i < 4; i++) {
				System.out.print("[" + (i + 1) + "]" + answer[i] + "\t\t");

			}

			// 정답 / 오답 판단

			System.out.println();
			System.out.println(
					"____________________________________________________________________________________________________");

			System.out.println("답을 선택하세요");
			while (true) {
				try {
					int input = sc.nextInt();

					if (answer[input - 1] == vo.getKor())// (입력값) == (정답)이면
					{
						System.out.println(
								"____________________________________________________________________________________________________\r\n"
										+ "\n   정답입니다!\r");
						// 연속으로 몇문제 맞췄는지
						bonusCount++;

						// 타입별 점수 증감
						score += typeScore;

						// 콤보
						// 연속으로 맞추면 보너스 점수 증감해서 스코어 증감
						if (bonusCount >= 2 && bonusCount <= 5) {
							bonusScore++;
							System.out.println("   " + (bonusCount - 1) + "COMBO!");
						}
						// 5개 이상 연속으로 맞추면 보너스 점수 5점씩해서 스코어 증감
						if (bonusCount > 5) {
							bonusScore = 5;
							System.out.println("   대단해요!\t" + (bonusCount - 1) + "COMBO!");

						}
						System.out.println(
								"____________________________________________________________________________________________________\n\n");

					} else// 오답이면 목숨차감 및 보너스 점수 초기화
					{
						life--;
						System.out.println(
								"____________________________________________________________________________________________________\r\n"
										+ "\n   오답입니다!\r");

						bonusCount = 0;
						bonusScore = 0;

						// 정답이 뭔지 표시해줌
						System.out.println("   정답 :  " + vo.getWord() + " - " + vo.getKor());

						// 오답노트 자리
						result = dao.insertStd(vo.getWord(), vo.getKor(), vo.getUserNo());

						if (!result) {
							System.out.println("성공");
						} else {
							System.out.println("실패");
						}

					}
					break;

				} catch (Exception e) {
					System.out.println();
					System.out.println("키를 다시 입력 해 주세요");
					System.out.println(
							"____________________________________________________________________________________________________");
					sc = new Scanner(System.in);

				}
			}

			// 최종 점수는 스코어 + 토탈 보너스 점수이다.
			score += bonusScore;

			// 목숨이 다 닳면 종료 창 출력
			if (life <= 0) {
				// 종료할 때 최종스코어를 출력한다.
				GameEndView(score);
			}

		}

	}

	public static void ModeChoiceView()// 모드선택뷰
	{
		System.out.println(
				"____________________________________________________________________________________________________\r\n"
						+ "         모드를 선택하세요      \r\n"
						+ "____________________________________________________________________________________________________\r\n"
						+ " ____ \r\n" + "||1 ||\r\n" + "||__|| PLAY\r\n" + "|/__\\| 		 	\r\n" + "\r\n"
						+ " ____ \r\n" + "||2 ||\r\n" + "||__|| RANK\r\n" + "|/__\\|\r\n" + "\r\n" + " ____ \r\n"
						+ "||3 ||\r\n" + "||__|| NOTE\r\n" + "|/__\\|\r\n" + "\r\n" + " ____ \r\n" + "||9 ||\r\n"
						+ "||__|| 종료\r\n" + "|/__\\|\r\n" + "\r\n"

						+ "____________________________________________________________________________________________________");

		Scanner sc = new Scanner(System.in);

		while (true) {
			try {
				int input = sc.nextInt();
				if (input == 1)// 확인
				{
					TypeChoiceView();
				} else if (input == 2)// 종료
				{
					RankView rk = new RankView();// 랭크 뷰 삽입
					rk.RankView();

				} else if (input == 3) {
					// 노트인풋 삽입
					NoteView();
				}

				else if (input == 9)// 종료
				{
					System.out.println("프로그램을 종료합니다.");
					// 프로그램 종료 코드
					System.exit(0);

				} else// 다른 숫자 키 입력 받았을 때
				{
					System.out.println("키를 다시 입력 해 주세요");
					System.out.println(
							"____________________________________________________________________________________________________");

				}
				break;

			} catch (Exception e) {
				System.out.println("키를 다시 입력 해 주세요");
				System.out.println(
						"____________________________________________________________________________________________________");

				sc = new Scanner(System.in);
			}

		}

	}

//	public static void LoginView() {
//
//		Login log = new Login();
//		Resgister reg = new Resgister();
//
//		Scanner sc = new Scanner(System.in);
//
//		while (true) {
//			System.out.print("\n[1. 로그인]   [2. 회원가입]   [3. 게임플레이]		0[0. 종료] \n  >>> ");
//			String menu = sc.next();
//			switch (menu) {
//			case "1":
//				try {
//					log.login();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
//			case "2":
//				try {
//					reg.resister();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				break;
//			case "3":
////					gm.main(args);
//				break;
//
//			case "0":
//				System.out.println("\n\n게임을 종료합니다.");
//				System.exit(0);
//				break;
//			default:
//				System.out.println("\n번호를 다시 확인해주세요.");
//			}
//		}
//	} // main view close



	public static void NoteView() {
		System.out.println(
				"____________________________________________________________________________________________________");

		// 유저이름 _ 님의 오답노트 입니다.

		System.out.println(
				"____________________________________________________________________________________________________\r\n"
						+ "단어				뜻		\r\n"
						+ "____________________________________________________________________________________________________");

		// 단어들이랑 뜻 출력
		Note.main();

		System.out.println(
				"____________________________________________________________________________________________________\r");

		System.out.println(" ____ \r\n" + "||0 ||\r\n" + "||__|| 삭제하기\r\n" + "|/__\\| 	");
		System.out.println(
				"____________________________________________________________________________________________________\r\n");

	}
}