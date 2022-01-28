package highClassJava4;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Annotation
// 프로그램 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨 것(JDK1.5부터 지원)
// 주석처럼 프로그래밍 언어에 영향을 미치지 않으면서도 다른 프로그램에세 유용한 정보를 제공함.

// 종류
// 1. 표준 에너테이션 
// 2. 메타 애너테이션 (애너테이션을 위한 애너테이션, 즉 에너테이션을 정의할 때 사용하는 애너테이션)

// 애너테이션 타입 정의 방법

// @interface 애너티이션 이름 {
//	요소 타입 타입요소이름(); // 반환값이 있고 매개변수는 없는 추상메서드 모양

// 애너테이션 요소의 규칙
// 1. 요토타입은 기본형, String, enum, annotation, class만 허용
// 2. () 안에 매개변수를 선언할 수 없다.
// 3. 예외를 선언할 수 없다.
// 4. 요소의 타입에 타입 매개변수(지네릭타입문자)를 사용할 수 없다.

@Target(ElementType.METHOD) // 적용 대상
@Retention(RetentionPolicy.RUNTIME) // 유지 기간
// 메타 애너테이션 METHOD에 적용시키고 RUNTIME 까지 유지시키겠다.
public @interface PrintAnnotation {
	int id = 100; // 상수 선언  => static final int id = 100;

	String value() default "-"; // 기본값 '-'
	
	int count() default 20; // 기본값 20
}
