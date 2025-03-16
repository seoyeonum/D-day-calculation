package com.test;

import java.text.ParseException;

public class Progress
{
	public static void main(String[] args) throws ParseException	//** 없으면 에러!
	{
		Sist ob = new Sist();
		ob.setToday();
		//int hd = ob.holiday();
		
		int p1 = ob.betwDates(ob.getStartClass(), ob.getLastClass());
		System.out.println("전체 수업일수 : " + (p1 -13 +1) + "일");
		//** 개천절연휴(3일) + 한글날(1일) + 임시(1일) + 크리스마스(1일) + 신정연휴(2일) + 설연휴(4일) + 삼일절(1일)
		//    = 전체 13일은 수기로 빼줌
		//** +1 해야 날짜가 맞아진다.(위에서는 날짜간 차이를 구한 것이기 때문)
		
		int p2 = ob.betwDates(ob.getStartClass(), ob.getToday());
		System.out.println("누적 수업일수 : " + (p2 -4 +1) + "일");
		//** 개천절연휴(3일) + 한글날(1일) = 4일은 수기로 빼줌
		//** +1 해야 날짜가 맞아진다.(위에서는 날짜간 차이를 구한 것이기 때문)
		
		ob.calProgress((p2 -4 +1), (p1 -13 +1));
	}
}

// 실행 결과
/*

오늘은 2024년 11월 5일 입니다.
전체 수업일수 : 140일
누적 수업일수 : 28일
수업 진행률 : 20.00000%
계속하려면 아무 키나 누르십시오 . . .

*/
