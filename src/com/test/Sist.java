
// ■ BufferedReader 사용하여 입력값에 따른 수업 진행률 구하기(클래스 설계 및 메소드 정의)

package com.test;

import java.util.Calendar; // Scanner도 util 상자!

import java.text.ParseException;		//** Signals that an error has been reached unexpectedly while parsing.
import java.text.SimpleDateFormat;
import java.util.Date;

class Sist
{
	private String startClass = "20240923";
	private String lastClass = "20250423";
	private String today;
	
	public String getStartClass()
	{
		return startClass;
	}
	
	public String getLastClass()
	{
		return lastClass;
	}
	
	public String getToday()
	{
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
		
		// 테스트 (확인)
		//System.out.printf("%d%d%d", y, m, d);
		today = String.format("%04d%02d%02d\n", y, m+1, d);
		
		// 테스트(확인)
		//System.out.printf("today : %s", today);
		System.out.printf("오늘은 %d년 %d월 %d일 입니다.\n", y, m+1, d);
	}
	
	
	// 두 날짜 간 차이(평일만 카운트) 구하는 메소드
	public int betwDates(String date1, String date2) throws ParseException
	{
		// SimpleDateFormat 객체를 생성하고 포맷 형식을 설정합니다.
		String dateFormatType = "yyyyMMdd";		//-- dataFormat으로 사용될 예정
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatType);
		
		// 시작일과 종료일 문자열을 Date 객체로 변환합니다.
		Date dDate1 = simpleDateFormat.parse(date1);
		Date dDate2 = simpleDateFormat.parse(date2);
		//Date dateToday = simpleDateFormat.parse(today);
		
		// 테스트(확인) - 객체의 자료형 확인
		//System.out.println(dateStartClass.getClass().getName());
		//--==>> java.util.Date

		// 수업시작일과 수업종료일의 차이 (밀리초 단위로 계산)
		long diffDateSec1 = dDate2.getTime() - dDate1.getTime();		//-- 날짜 간 차이(하루, 이틀...) = 마지막날 - 시작날
		//-- Date.getTime()
		//   Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
		//   ** int로 하면 에러! long 타입 필요.(아마 밀리초..? 때문인듯)
		
		// 밀리초 단위의 차이를 일수로 변환
		long diffDateDay1 = diffDateSec1 / 86400000L;
		
		// 주말 카운트를 위한 변수 weekend
		int weekend1 = ((int)diffDateDay1+3)/7;	//-- 우리 수업 종료일이 화요일, 주말 카운트 위해 금요일로 숫자 맞춰줌(diffDateDay+3)
												//   이를 7로 나눈 몫이 곧, 시작일과 종료일 사이 주말의 수..!
														//** 편의를 위해 diffDateDay1을 int형으로 강제 형 변환!

		// 수업시작일과 종료일 사이 실 수업 일
		int result = ((int)diffDateDay1-weekend1*2);	//** 편의를 위해 diffDateDay1을 int형으로 강제 형 변환!
		
		return result;
	}	
	
	// 수업 진행률 구하는 메소드(출력까지!)
	public void calProgress(int x, int y)
	{
		double progress = (double)x/y * 100;
		System.out.printf("수업 진행률 : %.5f%%\n", progress);
	}
	
	
}


