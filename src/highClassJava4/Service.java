package highClassJava4;

public class Service {

	@PrintAnnotation // 주석처럼 컴파일 에러나 변동이 일어나지 않는다. default(value = "-", count = 20)
	public void method1() {
		System.out.println("메서드1 에서 출력되었습니다.");
	}

	@PrintAnnotation("%") // default 값은 value 즉, value = "%" 와 동일
	public void method2() {
		System.out.println("메서드2 에서 출력되었습니다.");
	}

	@PrintAnnotation(value = "#", count = 25)
	public void method3() {
		System.out.println("메서드3 에서 출력되었습니다.");
	}
}