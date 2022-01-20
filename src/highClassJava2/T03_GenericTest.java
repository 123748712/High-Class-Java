package highClassJava2;

public class T03_GenericTest {

   // 제너릭 클래스를 만드는 방법

   // class 클래스명<제너릭타입글자> {
   //       제너릭타입글자 변수명; => 변수 선언에 제너릭을 사용할 경우

   //       제너릭타입글자 메서드명() { => 반환값이 있는 메서드에서 사용
   //       return 값;
   //       }
   //    }

   // 제너릭타입 글자
   // T => Type
   // K => Key
   // V => Value
   // E => Element(자료구조에 들어가는 항목을 나타낼때 사용)

   public static void main(String[] args) {

      NonGenericClass ng1 = new NonGenericClass();
      ng1.setVal("가나다라");

      NonGenericClass ng2 = new NonGenericClass();
      ng2.setVal(100);      
      // NonGenericClass 는 Object 객체이기 때문에 Integer, String 전부 set 가능

      String rtnNg1 = (String) ng1.getVal();
      System.out.println("문자열 반환값 rtnNg1 => " + rtnNg1);

      int irtnNg2 = (int) ng2.getVal();
      System.out.println("정수 반환값 irtnNg2 => " + irtnNg2);
      System.out.println();      
      // Object 타입이기 때문에 casting 해야함.

      MyGeneric<String> mg1 = new MyGeneric<>(); // mg1 => String 객체
      MyGeneric<Integer> mg2 = new MyGeneric<>();// mg2 => Integer 객체

      mg1.setVal("우리나라");
      mg2.setVal(500);

      rtnNg1 = mg1.getVal();
      irtnNg2 = mg2.getVal();

      System.out.println("제너릭 문자열 반환값 : " + rtnNg1);
      System.out.println("제너릭 정수형 반환값 : " + irtnNg2);
   }
}

class NonGenericClass {
   private Object val;

   public Object getVal() {
      return val;
   }

   public void setVal(Object val) {
      this.val = val;
   }
}

class MyGeneric<T> { // Type형 (String, Integer ...)
   private T val;

   public T getVal() {
      return val;
   }

   public void setVal(T val) {
      this.val = val;
   }
}