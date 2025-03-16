package com.test;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


// ● 오늘 날짜 관련 클래스 CalcDate

// 1. getToday()
//    - private 으로 설정한 today에 접근하기 위한 getter
// 2. setToday()
//    - 오늘 날짜 today를 세팅하는 메소드
//      (이때, 오늘 날짜는 "20240101"의 식)
// 3. splitDate(String date)
//    - 문자열로 저장된 date("20240101" 형식)를 매개변수로 받아,
//    - "2024년 01월 01일" 형식의 문자열로 반환하는 메소드
// 4. betwDates1(String date1, String date2)
//    - 문자열로 저장된 date
class CalcDate
{
	//--------------------------- 속성 ----------------------------
	private String today;
	
	
	//-------------------------- 메소드 ---------------------------
	// getter
	public String getToday()
	{
		setToday();				//-- today 에 값이 담기지 않아 나던 NullPointerException 해결!
		return today;
	}
	
	// 오늘 날짜를 세팅하는 메소드
	public void setToday()
	{
		// Calendar 클래스 기반 인스턴스 생성
		Calendar cal = Calendar.getInstance();
		
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH);
		int d = cal.get(Calendar.DATE);
		
		today = String.format("%04d%02d%02d", y, m+1, d);
		//System.out.printf("오늘은 %d년 %d월 %d일 입니다.\n", y, m+1, d);
		
	}//end setToday()
	
	// "20241231"의 식으로 저장된 날짜를 "%s년 %s월 %s일"로 만드는 메소드
	public String splitDate(String date)
	{
		String[] hd = date.split("");		//-- 권장하지 않는 방식이라고 함.
		String y = "";
		String m = "";
		String d = "";
		
		for (int i=0; i<4; i++)
		{
			y += hd[i];		//-- 쪼개둔 숫자를 연도로 결합
		}
		
		for (int i=0; i<2; i++)
		{
			m += hd[i+4];	//-- 쪼개둔 숫자를 월로 결합
			d += hd[i+6];	//-- 쪼개둔 숫자를 일로 결합
		}
		
		String result = String.format("%s년 %s월 %s일", y,m,d);
		
		return result;
		
	}
	
	
	// 두 날짜 사이의 차이를 세는 메소드 (주말 포함)
	public int betwDates1(String date1, String date2) throws ParseException
	{
		// SimpleDateFormat 객체를 생성하고 포맷 형식을 설정
		String dateFormatType = "yyyyMMdd";		//-- dataFormat으로 사용될 예정
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatType);
		
		// 시작일과 종료일 문자열을 Date 객체로 변환
		Date dDate1 = simpleDateFormat.parse(date1);
		Date dDate2 = simpleDateFormat.parse(date2);
		
		// DATE1과 DATE2의 차이 (밀리초 단위로 계산)
		long diffDateSec1 = dDate2.getTime() - dDate1.getTime();		//-- 날짜 간 차이(하루, 이틀...) = 마지막날 - 시작날
		//-- Date.getTime()
		//   Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
		//   ** int로 하면 에러! long 타입 필요.(아마 밀리초..? 때문인듯)
		
		// 밀리초 단위의 차이를 일수로 변환
		long diffDateDay1 = diffDateSec1 / 86400000L;
		
		int result = (int)diffDateDay1;
		
		return result;
	}//end betwDates1()
	
	
	// 두 날짜 사이의 평일만 세는 메소드 (주말 제외)
	public int betwDates2(String date1, String date2) throws ParseException
	{
		// SimpleDateFormat 객체를 생성하고 포맷 형식을 설정
		String dateFormatType = "yyyyMMdd";		//-- dataFormat으로 사용될 예정
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatType);
		
		// 시작일과 종료일 문자열을 Date 객체로 변환
		Date dDate1 = simpleDateFormat.parse(date1);
		Date dDate2 = simpleDateFormat.parse(date2);
		
		// 테스트(확인) - 객체의 자료형 확인
		//System.out.println(dateStartClass.getClass().getName());
		//--==>> java.util.Date

		// DATE1과 DATE2의 차이 (밀리초 단위로 계산)
		long diffDateSec1 = dDate2.getTime() - dDate1.getTime();		//-- 날짜 간 차이(하루, 이틀...) = 마지막날 - 시작날
		//-- Date.getTime()
		//   Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
		//   ** int로 하면 에러! long 타입 필요.(아마 밀리초..? 때문인듯)
		
		// 밀리초 단위의 차이를 일수로 변환
		long diffDateDay1 = diffDateSec1 / 86400000L;
		
		// 주말 카운트를 위한 변수 weekend
		int weekend1 = ((int)diffDateDay1+2)/7;	//-- 우리 수업 종료일이 수요일, 주말 세기 위해 금요일로 숫자 맞춰줌(diffDateDay+3)
												//   이를 7로 나눈 몫이 곧, 시작일과 종료일 사이 주말의 수..!
														//** 편의를 위해 diffDateDay1을 int형으로 강제 형 변환!

		// 수업시작일과 종료일 사이 실 수업 일
		int result = ((int)diffDateDay1-weekend1*2);	//** 편의를 위해 diffDateDay1을 int형으로 강제 형 변환!
		
		return result;
	}//end betwDates2()
	
}//end CalcDate