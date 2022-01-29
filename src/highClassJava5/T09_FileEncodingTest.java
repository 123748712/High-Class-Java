package highClassJava5;

// 문자 변환 보조 스트림 예제
public class T09_FileEncodingTest {
	public static void main(String[] args) {
		// InputStreamReader객체는 파일의 인코딩 방식을 지정할 수 있다.

		// 형식) new InputStreamReader(바이트기반스트림객체, 인코딩방식)

		// 인코딩 방식
		// 한글인코딩 방식은 UTF-8 과 EUC-KR방식 두가지가 있다.
		// 원래 한글윈도우는 CP949방식을 사용했는데,
		// 윈도우를 개발한 마이크로소프트에서 EUC-KR방식에서 확장하였기 때문에 MS949라고도 부른다.
		// 한글 Window의 메모장에서 이야기하는 ANSI인코딩이란, CP949(Code Page 949)를 말한다.
	}
}
