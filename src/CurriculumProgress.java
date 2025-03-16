import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class CurriculumProgress
{
	public static void main(String[] args) throws IOException
	{
		
		// BuffedReader 클래스 기반의 인스턴스 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 변수 선언 및 초기화
		double progress;
		int day;
		
		// 연산 및 처리
		System.out.print("누적 수업일수 N를 입력하세요(N일차): ");
		day = Integer.parseInt(br.readLine());
		
		// 실 수업일수 140일
		progress = day/140.0*100;		//-- 백분율을 『*100』 해줘야
	
		//결과 출력
		//System.out.println("F 강의실 과정 진행률: " + progress);
		System.out.printf("F 강의실 과정 진행률: %.2f%%", progress);
		//-- 문자열 %를 문자열 내에 작성 시 앞에 % 붙이기
		//-- 따옴표의 경우 \(back slash) 붙여주기
		System.out.println();
		
	}
}

// 실행 결과
/*

누적 수업일수 N를 입력하세요(N일차): 5
F 강의실 과정 진행률: 3.57%

*/