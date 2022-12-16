package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import VO.VO;
import oracle.net.aso.a;

public class Note {

	public static void main() {
			Scanner sc = new Scanner(System.in);

			String w_Word = null;
			String w_Kor = null;
			boolean res = false;
//			ArrayList<VO> arr = null;
			Connection con = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			DAO dao = null;
//			ArrayList<VO> vo = new VO();

			System.out.print("User No.: ");
			int userNo = sc.nextInt();
			System.out.println("_____________NOTES_____________");
			// show the lists of mistakes
			
			dao = new DAO();
			ArrayList<VO> arr = dao.selectedwords();
			//System.out.println(arr.get(userNo));

			if (arr != null) {
				System.out.println("_____________Lists_____________");
				for ( int i = 0; i <arr.size(); i++) {
				System.out.print(arr.get(i).getW_Word() + "\t");
				System.out.print(arr.get(i).getW_Kor() + "\t");
				System.out.println();
				}
			} else {
				System.out.println("None!");
			}
			Game gm = new Game();
			gm.ModeChoiceView();
	}
}
//			System.out.println("[1]delete [2]back");
//			int a = sc.nextInt();
//			if (a == 1) {
//				System.out.println("pick a word to delete: ");
//				String dword = sc.next();
//				dao = new DAO();
//				arr = dao.deleteWord(dword);
//				int result = 0;
//				if (result == 1) {
//					System.out.println("success!");
//				} else {
//					System.out.println("failed!");
//				}
//			}
//			Game gm = new Game();

		


