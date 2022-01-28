package highClassJava4.reflection;

// Java Reflection
// 1. 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메서드, 생성자에 대한 정보를 가져오거나 수정할 수 있고,
// 새로운 객체를 생성하거나 메서드를 실행할 수 있다.(컴파일 시점에 해당 정보를 알 수 없는 경우에 유영하게 사용할 수 있다.)
// 2. Reflection API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공된다.
// 3. java.lang.Class의 주요 메서드
// 	- getName(), getSuperclass(), getInterfaces(), getModifiers() 등
// 4. java.lang.reflect 패키지의 주요 클래스
//	- Field, Method, Constructor, Modifier 등

// Class 오브젝트 생성하기
public class T01_ClassObjectCreationTest {
	public static void main(String[] args) throws ClassNotFoundException {
		// 첫번째 방법 : Class.forName() 메서드 이용
		Class<?> klass = Class.forName("kr.or.ddit.basic.reflection.T01_ClassObjectCreationTest");
		// 클래스가 있다면 정상적으로 진행되지만, 없다면 thorows로 던진 에러가 발생

		// 두번째 방법 : getClass() 메서드 이용
		T01_ClassObjectCreationTest obj = new T01_ClassObjectCreationTest();

		klass = obj.getClass();

		// 세번째 방법 : .class 이용
		klass = T01_ClassObjectCreationTest.class;

	}
}
