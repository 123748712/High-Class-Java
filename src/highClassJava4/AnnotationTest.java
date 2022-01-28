package highClassJava4;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		System.out.println("static 변수값 : " + PrintAnnotation.id); // PrintAnnotation 에서 필드에 선언한 id 값을 가져온다.

		Method[] declaredMethods = Service.class.getDeclaredMethods();

		for (Method m : declaredMethods) {
			Annotation[] annos = m.getDeclaredAnnotations();

			for (Annotation anno : annos) {
				if (anno.annotationType().getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnn = (PrintAnnotation) anno;
					for (int i = 0; i < printAnn.count(); i++) {
						System.out.print(printAnn.value());
					}
				}
			}
			System.out.println();
			m.invoke(new Service());
		}
	}
}