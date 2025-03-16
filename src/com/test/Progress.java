package com.test;

import java.text.ParseException;

public class Progress
{
	public static void main(String[] args) throws ParseException	//** 없으면 에러!
	{
		Sist ob = new Sist(); 
		ob.setToday();
		
		System.out.println("-----------------------------");
		
		final int hdBtwClass = 13;					//-- 수업 과정 중 전체 휴일 수는 13일로 고정.
		int hdBtwTodayAndLast = ob.leftHoliday();	//-- 앞으로 남은 휴일 수
		
		int period1 = ob.betwDates1(ob.getStartClass(), ob.getLastClass());
		int y = period1 -hdBtwClass +1;				//-- +1 해야 날짜가 맞아진다.(위에서는 날짜간 차이를 구한 것이기 때문)
		System.out.println("전체 수업일수 : " + y + "일");
		
		//System.out.println(hdBtwTodayAndLast);
		
		int period2 = ob.betwDates1(ob.getStartClass(), ob.getToday());
		int x = (period2 -(hdBtwClass -hdBtwTodayAndLast) +1);	//-- +1 해야 날짜가 맞아진다.
		System.out.println("누적 수업일수 : " + x + "일");
		
		ob.calProgress(x,y);
		
		//System.out.println(LocalDate.now());
		
		System.out.println("-----------------------------");
		
		// 다음 휴일까지 남은 일자 알려주기
		ob.latelyHoliday();
		System.out.println();	// 개행
	}
}

// 실행 결과
/*

오늘은 2025년 1월 15일 입니다.
-----------------------------
전체 수업일수 : 140일
누적 수업일수 : 75일
수업 진행률 : 53.57143%
-----------------------------
다음 휴일은 2025년 01월 27일입니다.
해당 휴일까지 D-12 입니다.


*/
