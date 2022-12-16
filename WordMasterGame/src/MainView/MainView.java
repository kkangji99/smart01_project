package MainView;

import java.util.Scanner;

import DAO.DAO;

import DAO.Login;
import DAO.Resgister;
import VO.VO;

public class MainView {
	String msg;
	String userID;
	String userId, userPw, userName, userEmail;
	String fname, femail, fID, fpw, rpw;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Login log = new Login();
		Resgister reg = new Resgister();

		System.out.println("──────────────────────── * [ 단어를 맞춰 볼까..? ] * ─────────────────────────\n");
		Thread.sleep(800);
		System.out.println("*        *	*			*		*		*	*   *      	*	*      *\n");
		Thread.sleep(600);
		System.out.println("   *      *            *          *                 *    [왜 나한테..] *   *\n");
		Thread.sleep(600);
		System.out.println("       *    [이런 일이....]              *          *             *            *\n");
		Thread.sleep(600);
		System.out.println("*                   *     [왜 나만 가지고 그래..]                 *             *\n");
		Thread.sleep(600);
		System.out.println("        *                  *             *             [괴롭히지마...]    *\n");
		Thread.sleep(600);
		System.out.println("             *             [영어도...]             *             8            *\n");
		Thread.sleep(600);
		System.out.println("*              [너무 하기...]                *         [싫....어..]      *           *\n\n");
		Thread.sleep(600);
		while (true) {
			System.out.print("\n[1. 로그인]   [2. 회원가입]   [0. 종료] \n  >>> ");
			String menu = sc.next();
			switch (menu) {
			case "1":
				log.login();
				break;
			case "2":
				reg.resister();
				break;
			case "0":
				System.out.println("\n\n게임을 종료합니다.");
				System.exit(0);
				break;
			default:
				System.out.println("\n번호를 다시 확인해주세요.");
			}
		}
	} // main view close
}
