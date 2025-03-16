package com.test;


//이번에는 수업 관련과 그렇지 않은 부분 클래스 분리하자.		-- 완료!

//실행하면 자동으로 D-DAY 산출

import java.text.ParseException;		//** Signals that an error has been reached unexpectedly while parsing.

//● 디데이(진행률) 연산 클래스
public class Progress
{
	public static void main(String[] args) throws ParseException	//** 없으면 에러!
	{
		Sist st = new Sist(); 
		CalcDate cd = new CalcDate();
		
		System.out.printf("오늘은 %s 입니다.\n", cd.splitDate(cd.getToday()) );
		System.out.println("-----------------------------");
		
		final int hdBtwClass = 13;					//-- 수업 과정 중 전체 휴일 수는 13일로 고정.
		int hdBtwTodayAndLast = st.leftHoliday();	//-- 앞으로 남은 휴일 수
		
		int period1 = cd.betwDates2(st.getStartClass(), st.getLastClass());
		int y = period1 -hdBtwClass +1;				//-- +1 해야 날짜가 맞아진다.(∵위에서는 날짜간 차이를 구한 것이기 때문)
		System.out.println("전체 수업일수 : " + y + "일");
		
		int period2 = cd.betwDates2(st.getStartClass(), cd.getToday());
		int x = (period2 -(hdBtwClass -hdBtwTodayAndLast) +1);	//-- +1 해야 날짜가 맞아진다.
		System.out.println("누적 수업일수 : " + x + "일");
		
		st.calProgress(x,y);
		
		//System.out.println(LocalDate.now());
		
		System.out.println("-----------------------------");
		
		// 다음 휴일까지 남은 일자 알려주기
		st.latelyHoliday();
		System.out.println();	// 개행
	}
}



// 실행 결과
/*

오늘은 2025년 03월 16일 입니다.
-----------------------------
전체 수업일수 : 140일
누적 수업일수 : 112일
수업 진행률 : 80.00000%
-----------------------------
** 다음 휴일 없음 **

*/
