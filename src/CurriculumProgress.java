
// ■ BufferedReader 사용하여 입력값에 따른 수업 진행률 구하기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Calendar; // Scanner도 util 상자!


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CurriculumProgress
{
	public static void main(String[] args) throws IOException, ParseException
	{
	
	// BuffedReader 클래스 기반의 인스턴스 생성
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	// 변수 선언 및 초기화
	String dateFormatType = "yyyyMMdd";
	String strDate = "20240923";
	String todayDate= "20241010";
	String endDate = "20250422";
	
	// 연산 및 처리
	System.out.print("오늘 날짜를 입력하세요(yyyyMMdd): ");
	todayDate = br.readLine();				//-- 아직 문자열

	// SimpleDateFormat 객체를 생성하고 포맷 형식을 설정합니다.
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatType);

	// 시작일과 종료일 문자열을 Date 객체로 변환합니다.
	Date from = simpleDateFormat.parse(strDate);
	Date mid = simpleDateFormat.parse(todayDate);
	Date to = simpleDateFormat.parse(endDate);
	
	//테스트(확인)
	//System.out.println("strDate: " + strDate);
	//System.out.println("from: " + from);
	
	
	// 두 날짜의 차이를 밀리초 단위로 계산합니다.
	long diffDate = to.getTime() - from.getTime();		//-- 날짜 간 차이(하루, 이틀...) = 마지막날 - 시작날

	// 밀리초 단위의 차이를 일수로 변환합니다.
	long period = diffDate / 86400000L;	
	// 결과를 출력합니다.
	System.out.println(period);							//-- 전체 기간에서 주말이랑 쉬는날이랑 공휴일 빼줘야 함@!@@@@@@@@@@

	}
}

// 실행 결과
/*

오늘 날짜를 입력하세요(yyyyMMdd): 20241015
211


*/