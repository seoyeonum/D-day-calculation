
// ■ BufferedReader 사용하여 입력값에 따른 수업 진행률 구하기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

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
	String todayDate;
	String endDate = "20250422";
	
	// 연산 및 처리
	System.out.print("오늘 날짜를 입력하세요(yyyyMMdd): ");
	todayDate = br.readLine();				//-- 아직 문자열

	// SimpleDateFormat 객체를 생성하고 포맷 형식을 설정합니다.
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatType);

	// 시작일과 종료일 문자열을 Date 객체로 변환합니다.
	Date from = simpleDateFormat.parse(strDate);
	Date today = simpleDateFormat.parse(todayDate);
	Date to = simpleDateFormat.parse(endDate);
	
	// 시작일과 종료일의 차이를 밀리초 단위로 계산합니다.
	long diffDateSec1 = to.getTime() - from.getTime();		//-- 날짜 간 차이(하루, 이틀...) = 마지막날 - 시작날

	// 밀리초 단위의 차이를 일수로 변환합니다.
	long diffDateDay1 = diffDateSec1 / 86400000L;	
	
	// 테스트 (확인) - 수업 시작일 ~ 수업 종료일
	System.out.println("우리 수업 시작일은 " + strDate + "입니다.");
	System.out.println("우리 수업 종료일은 " + endDate + "입니다.");
	System.out.println("시작일과 종료일 간 차이는 " + diffDateDay1 + "일 입니다.");		//-- 전체 기간에서 주말이랑 쉬는날이랑 공휴일 빼줘야 함@!@@@@@@@@@@
	
	// 주말 카운트를 위한 변수 weekend
	int weekend1 = ((int)diffDateDay1+3)/7;	//-- 종료일이 화요일, 주말 카운트 위해 금요일로 숫자 맞춰줌(diffDateDay+3)
											//   이를 7로 나눈 몫이 곧, 시작일과 종료일 사이 주말의 수..!
	
	// 테스트 (확인) - 수업 시작일 ~ 수업 종료일 사이 주말
	System.out.println("시작일과 종료일 사이 주말은 " + weekend1 + "번 입니다.");
	
	// 크리스마스 디데이 계산----------------------------------------------------------------------
	
	String xmasDate = "20241225";
	Date xmas = simpleDateFormat.parse(xmasDate);
	
	// 오늘과 종료일의 차이를 밀리초 단위로 계산합니다.
	long diffXToTodaySec = xmas.getTime() - today.getTime();		//-- 날짜 간 차이(하루, 이틀...) = 마지막날 - 시작날

	// 밀리초 단위의 차이를 일수로 변환합니다.
	long diffXToTodayDay = diffXToTodaySec / 86400000L;	
	
	// 오늘 ~ 크리스마스 디데이 출력
	System.out.println();
	System.out.println("    [ 크리스마스 디데이 ]    ");
	System.out.println("오늘은 " + todayDate + "입니다.");
	System.out.println("크리스마스는 " + xmasDate + "입니다.");
	System.out.println("오늘은 크리스마스 D-" + diffXToTodayDay + " 입니다.");		//-- 전체 기간에서 주말이랑 쉬는날이랑 공휴일 빼줘야 함@!@@@@@@@@@@
	
	
	// ---------------------------------------------------------------------- 크리스마스 디데이 계산 
	
	
	// 주말과 공휴일을 빼보자!! -------------------------------------------------------------------
	
	// 오늘과 수업 종료일의 차이를 밀리초 단위로 계산
	long diffDateSec2 = to.getTime() - today.getTime();		//-- 날짜 간 차이(하루, 이틀...) = 마지막날 - 시작날

	// 밀리초 단위의 차이를 일수로 변환합니다.
	long diffDateDay2 = diffDateSec2 / 86400000L;
	
	
	// 테스트 (확인) - 오늘 ~ 수업 종료일
	System.out.println();
	System.out.println("    [ 실 수업일 디데이 ]    ");
	System.out.println("오늘 날짜는 " + todayDate + "입니다.");
	System.out.println("우리 수업 종료일은 " + endDate + "입니다.");
	System.out.println("오늘과 종료일 간 차이는 " +diffDateDay2 + "일 입니다.");		//-- 전체 기간에서 주말이랑 쉬는날이랑 공휴일 빼줘야 함@!@@@@@@@@@@
	
	// 주말 카운트를 위한 변수 weekend
	int weekend2 = ((int)diffDateDay2+3)/7;	//-- 종료일이 화요일, 주말 카운트 위해 금요일로 숫자 맞춰줌(diffDateDay+3)
	//										//   이를 7로 나눈 몫이 곧, 시작일과 종료일 사이 주말의 수..!
	
	// 테스트 (확인) - 수업 시작일 ~ 수업 종료일 사이 주말
	System.out.println();
	System.out.println("시작일과 종료일 사이 주말은 " + weekend2 + "번 입니다.");
	
	//** 크리스마스(1일) + 연말연초(2일) + 설연휴(4일) + 삼일절(1일) = 8일은 수기로 빼줌
	long weekdays = (diffDateDay2-weekend2*2-8);

	// 앞으로 남은 수업일 수
	System.out.println("앞으로 남은 수업일 수는 " + weekdays + "일 입니다.");
	
	// 현재 수업 진행률
	// 실 수업일수 140일
	double progress2 = (140-weekdays)/140.0*100;		//-- 백분율을 『*100』 해줘야

	System.out.printf("F 강의실 과정 진행률: %.2f%%\n", progress2);
	
	// ------------------------------------------------------------------- 주말과 공휴일을 빼보자!!
	}
}

// 실행 결과
/*

오늘 날짜를 입력하세요(yyyyMMdd): 20241031
우리 수업 시작일은 20240923입니다.
우리 수업 종료일은 20250422입니다.
시작일과 종료일 간 차이는 211일 입니다.
시작일과 종료일 사이 주말은 30번 입니다.

    [ 크리스마스 디데이 ]    
오늘은 20241031입니다.
크리스마스는 20241225입니다.
오늘은 크리스마스 D-55 입니다.

    [ 실 수업일 디데이 ]    
오늘 날짜는 20241031입니다.
우리 수업 종료일은 20250422입니다.
오늘과 종료일 간 차이는 173일 입니다.

시작일과 종료일 사이 주말은 25번 입니다.
앞으로 남은 수업일 수는 115일 입니다.
F 강의실 과정 진행률: 17.86%

*/
