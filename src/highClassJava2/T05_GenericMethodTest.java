package highClassJava2;

class Util2 {
   public static <T extends Number> int compare(T t1, T t2) { // T 타입을 Number 타입으로 제한한다. (상속 extends 이용)

      double v1 = t1.doubleValue();
      double v2 = t2.doubleValue();

      return Double.compare(v1, v2);
   }
}

// 제한된 타입 파라미터(Bounded Parameter) 예제
public class T05_GenericMethodTest {
   public static void main(String[] args) {
      
      int result1 = Util2.compare(10, 20);
      System.out.println(result1); // 뒤의 값이 크기 때문에 -1 출력
      
      int result2 = Util2.<Number>compare(3.14, 3); // <Number> 생략 가능
      System.out.println(result2); // 앞의 값이 크기 때문에 1 출력
      
      // Util2.compare("C", "JAVA"); 컴파일 에러
      // 안전한, 정확한 코드가 가능하다.
   }
}