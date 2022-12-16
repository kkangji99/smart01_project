package DAO;

import java.util.ArrayList;

import VO.VO;

public class RankView {
	public static void RankView() {
	    

	      DAO dao = null;
	      VO vo = null;
	      dao = new DAO();
	      boolean result = false;

//	      String[] answer = new String[4];
	      ArrayList<VO> arr = dao.rankSelect(); //여기가문제 
//	      System.out.println(arr.get());
//	      System.out.println(arr.get(2));
//	      System.out.println(arr.get(3));
//	      System.out.println(arr.get(4));
//	      System.out.println(arr.get(5));
	      
	      System.out.println(
		            "____________________________________________________________________________________________________\r\n"
		                  + "   <MASTERS RANKING>\r\n"
		                  + "____________________________________________________________________________________________________\r\n"
		                  + "순위      ID   \r\n"
		                  + "____________________________________________________________________________________________________\r\n"
		                  + "");

		      // 순위 ID 넣기

	      for (int i = 0; i < arr.size(); i++) {
//	    	  System.out.println(arr.get(i));
	    	  System.out.println((i+1)+"위 \t"+arr.get(i).getUserId()+"\t"+arr.get(i).getUserScore());
//	          System.out.println((i+1)+"위 \t"+arr.get(i).getUserId()+"\t"+arr.get(i).getUserScore());
	       }

	      // 랭크 연산
	      System.out.println(
	            "____________________________________________________________________________________________________\r\n"
	                  + "");
	      Game gm = new Game();
			gm.ModeChoiceView();

	   }

}
