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
	Date to = simpleDateFormat.parse(endDate);
	
	// 테스트 (확인)
	//System.out.println("strDate: " + strDate);
	//System.out.println("from: " + from);
	
	
	// 시작일과 종료일의 차이를 밀리초 단위로 계산합니다.
	long diffDateSec = to.getTime() - from.getTime();		//-- 날짜 간 차이(하루, 이틀...) = 마지막날 - 시작날

	// 밀리초 단위의 차이를 일수로 변환합니다.
	long diffDateDay = diffDateSec / 86400000L;	
	
	// 테스트 (확인) - 수업 시작일 ~ 수업 종료일
	System.out.println("우리 수업 시작일은 " + strDate + "입니다.");
	System.out.println("우리 수업 종료일은 " + endDate + "입니다.");
	System.out.println("시작일과 종료일 간 차이는 " + diffDateDay + "일 입니다.");		//-- 전체 기간에서 주말이랑 쉬는날이랑 공휴일 빼줘야 함@!@@@@@@@@@@
	
	// 주말 카운트를 위한 변수 weekend
	int weekend = ((int)diffDateDay+3)/7;	//-- 종료일이 화요일, 주말 카운트 위해 금요일로 숫자 맞춰줌(diffDateDay+3)
											//   이를 7로 나눈 몫이 곧, 시작일과 종료일 사이 주말의 수..!
	
	// 테스트 (확인) - 수업 시작일 ~ 수업 종료일 사이 주말
	System.out.println("시작일과 종료일 사이 주말은 " + weekend + "번 입니다.");
	

	}
}