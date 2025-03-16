package com.test;

import java.text.ParseException;

// ● 우리 수업 관련 클래스 Sist
class Sist
{
	//--------------------------- 속성 ----------------------------
	private String startClass = "20240923";
	private String lastClass = "20250423";
	//private String today;
	
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
						
	//-------------------------- 메소드 ---------------------------
	// getter
	public String getStartClass()
	{
		return startClass;
	}
	
	public String getLastClass()
	{
		return lastClass;
	}
	

	// 평일 중 휴일을 세는 메소드
	public int leftHoliday()
	{
		CalcDate cd = new CalcDate();
		int sum = 0;
		String td = cd.getToday();
		
		// 테스트
		//System.out.println(td);		//-- 현재 null 이라 오류나는 중... → getToday() 에서 오늘 값 setting 하면서 문제 해결!
		
		
		for (int i=holiday.length-1; i>=0; i--)
		{
			if (td.compareTo(holiday[i])<0)
			sum += 1;
		}
		
		return sum;
	}//end leftHoliday()
	
	
	// 우리 수업 진행률 구하는 메소드
	public void calProgress(int x, int y)
	{
		double progress = (double)x/y * 100;
		System.out.printf("수업 진행률 : %.5f%%\n", progress);
	}
	
	// 가장 가까운 휴강일(주말 제외)을 반환하는 메소드
	public void latelyHoliday() throws ParseException
	{
		CalcDate cd = new CalcDate();
		String latelyHoliday = "";
		String td = cd.getToday();
		for (int i=0; i<holiday.length; i++)
		{
			if (td.compareTo(holiday[i])<0)
			{
				latelyHoliday = holiday[i];
				break;
			}
		}
		
		if (latelyHoliday.isEmpty())
		{
			System.out.println("** 다음 휴일 없음 **");
			return;
		}
		
		String result = cd.splitDate(latelyHoliday);
		System.out.printf("다음 휴일은 %s입니다.\n", result);
		
		int dday = cd.betwDates1(cd.getToday(), latelyHoliday);
		System.out.printf("해당 휴일까지 D-%d 입니다.",dday);
	}//end latelyHoliday()
	
	
}//end Sist