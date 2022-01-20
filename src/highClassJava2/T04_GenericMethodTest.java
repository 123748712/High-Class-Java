package highClassJava2;

class Util {

   // 제너릭 메서드 : 파라미터 타입과 리턴타입으로 타입 파라미터를 가지는 메서드

   // 선언 방법 : 리턴타입 앞에 <> 기호를 추가하고 타입 파라미터를 기술 후 사용한다.
   // <T, R> R method(T t) // <T, R> => 제너릭 타입, R => 리턴타입, (T t) => 파라미터 타입

   public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {

      boolean KeyCompare = p1.getKey().equals(p2.getKey());

      boolean valueCompare = p1.getValue().equals(p2.getValue());

      return KeyCompare && valueCompare;
   }
}

// 멀티타입<K, V>을 가지는 제너릭 클래스
class Pair<K, V> {
   private K key;
   private V value;

   public Pair(K key, V value) {
      super();
      this.key = key;
      this.value = value;
   }

   public K getKey() {
      return key;
   }

   public void setKey(K key) {
      this.key = key;
   }

   public V getValue() {
      return value;
   }

   public void setValue(V value) {
      this.value = value;
   }

   // 키와 값 모두 출력하기 (일반메소드)
   public void displayAll(K key, V val) { // 제너릭 메소드로 만들기 위해선 public <K, V> void 가 되어야 한다.
      {
         System.out.println(key + " : " + val);

      }
   }
}

public class T04_GenericMethodTest {
   public static void main(String[] args) {

      Pair<Integer, String> p1 = new Pair<Integer, String>(1, "홍길동");
      Pair<Integer, String> p2 = new Pair<Integer, String>(2, "홍길동");

      boolean result1 = Util.<Integer, String>compare(p1, p2); // compare는 Generic Method 이기 때문에 <Integer, String>
                                                   // 타입이다.

      if (result1) {
         System.out.println("논리(의미)적으로 동일한 객체임");
      } else {
         System.out.println("논리(의미)적으로 동일한 객체가 아님");
      }

      Pair<String, String> p3 = new Pair<String, String>("001", "홍길동");
      Pair<String, String> p4 = new Pair<String, String>("002", "홍길동");

      boolean result2 = Util.compare(p3, p4); // 파라미터 객체 전부 String 이기 때문에 생략 가능

      if (result2) {
         System.out.println("논리(의미)적으로 동일한 객체임");
      } else {
         System.out.println("논리(의미)적으로 동일한 객체가 아님");
      }
      
      Pair<Integer, Integer> p5 = new Pair(180, 80);

      p5.displayAll(188, 88);
   }
}