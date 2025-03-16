package com.test;

//실행하면 자동으로 D-DAY 산출
import java.util.Calendar; // Scanner도 util 상자!

import java.text.ParseException;		//** Signals that an error has been reached unexpectedly while parsing.
import java.text.SimpleDateFormat;
import java.util.Date;

//import java.time.LocalDate;

class Sist
{
	//-------------------------------속성---------------------------
	private String startClass = "20240923";
	private String lastClass = "20250423";
	private String today;
	
	// 훈련과정 휴강일 모음
	// 고려해야 하는 휴일(yyyyMMdd)
		// - 개천절연휴	(241002 241003 241004); 3일
		// - 한글날		(241009); 1일
		// - 임시 휴강  (241106); 1일
		// - 크리스마스 (241225); 1일
		// - 신정연휴   (241231 250101); 2일
		// - 설연휴     (250127 250128 250129 250130)
		// - 삼일절     (250301); 1일
		//==> 총 13일
	
	private String[] holiday = {"20241002", "20241003", "20241004", "20241009"
							, "20241106", "20241225", "20241231", "20250101"
							, "20250127", "20250128", "20250129", "20250130"
							, "20250301"};		//==> 총 13일
						
	//-------------------------------메소드--------------------------
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
	public int betwDates1(String date1, String date2) throws ParseException
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

		// DATE1과 DATE2의 차이 (밀리초 단위로 계산)
		long diffDateSec1 = dDate2.getTime() - dDate1.getTime();		//-- 날짜 간 차이(하루, 이틀...) = 마지막날 - 시작날
		//-- Date.getTime()
		//   Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
		//   ** int로 하면 에러! long 타입 필요.(아마 밀리초..? 때문인듯)
		
		// 밀리초 단위의 차이를 일수로 변환
		long diffDateDay1 = diffDateSec1 / 86400000L;
		
		// 주말 카운트를 위한 변수 weekend
		int weekend1 = ((int)diffDateDay1+2)/7;	//-- 우리 수업 종료일이 수요일, 주말 카운트 위해 금요일로 숫자 맞춰줌(diffDateDay+3)
												//   이를 7로 나눈 몫이 곧, 시작일과 종료일 사이 주말의 수..!
														//** 편의를 위해 diffDateDay1을 int형으로 강제 형 변환!

		// 수업시작일과 종료일 사이 실 수업 일
		int result = ((int)diffDateDay1-weekend1*2);	//** 편의를 위해 diffDateDay1을 int형으로 강제 형 변환!
		
		return result;
	}
	
	// 두 날짜 간 차이(평일주말다포함 카운트) 구하는 메소드2
	public int betwDates2(String date1, String date2) throws ParseException
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

		// DATE1과 DATE2의 차이 (밀리초 단위로 계산)
		long diffDateSec1 = dDate2.getTime() - dDate1.getTime();		//-- 날짜 간 차이(하루, 이틀...) = 마지막날 - 시작날
		//-- Date.getTime()
		//   Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
		//   ** int로 하면 에러! long 타입 필요.(아마 밀리초..? 때문인듯)
		
		// 밀리초 단위의 차이를 일수로 변환
		long diffDateDay1 = diffDateSec1 / 86400000L;
		
		int result = (int)diffDateDay1;
		
		return result;
	}
	
	// 평일 중 휴일을 카운트하는 메소드
	
	public int leftHoliday()
	{
		int sum = 0;
		String td = getToday();
		
		for (int i=holiday.length-1; i>=0; i--)
		{
			if (td.compareTo(holiday[i])<0)
			sum += 1;
		}
		
		
		return sum;
	}
	
	
	
	
	// 수업 진행률 구하는 메소드(출력까지!)
	public void calProgress(int x, int y)
	{
		double progress = (double)x/y * 100;
		System.out.printf("수업 진행률 : %.5f%%\n", progress);
	}
	
	// 가장 가까운 휴일을 반환하는 메소드
	public void latelyHoliday() throws ParseException
	{
		String latelyHoliday = "";
		String td = getToday();
		
		// 가장 가까운 휴일 찾기
		for (int i=0; i<holiday.length; i++)
		{
			if (td.compareTo(holiday[i])<0)
			{
				latelyHoliday = holiday[i];
				break;
			}
		}
		
		// 만약 latelyHoliday 가 빈 문자열이라면...
		if (latelyHoliday.isEmpty())
		{
			System.out.println("** 다음 휴일 없음 **");
			return;
		}
		
		// 뽑아낸 휴일이 현재 "20241231"의 식으로 저장되어있으니,
		// 출력문에 쓰기 위해 쪼개보자.
		String[] hd = latelyHoliday.split("");		//-- 권장하지 않는 방식이라고 함.
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
		
		System.out.printf("다음 휴일은 %s년 %s월 %s일입니다.\n", y,m,d);
		int dday = betwDates2(getToday(), latelyHoliday);
		System.out.printf("해당 휴일까지 D-%d 입니다.",dday);
	}
	
}