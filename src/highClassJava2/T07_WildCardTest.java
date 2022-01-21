package highClassJava2;

import java.util.ArrayList;
import java.util.List;

// 상한 및 하한 제한 와일드 카드 예제

public class T07_WildCardTest {

   // 와일드카드(?) 는 제너릭 타입을 이용한 타입
   // 안전한 코드를 위해 사용되는 특별한 종류의 인수(Argument)로서, 변수선언, 객체생성 및 메서드 정의를 할때 사용된다.
   
   // <? extends T> => 와일드카드의 상한 제한. T와 그 자손들만 가능 (T => 부모)
   // <? super T>     => 와일드카드의 하한 제한. T와 그 조상들만 가능
   // <?>           => 모든 타입 가능 <? extends Object> 와 동일
   public void printMemberInfo(List<? extends Member> list) {
      // extends 키워드를 이용한 상한 제한(Upper Bounds) 예제
      // list 안에 객체는 반드시 Member 타입의 객체임을 보장 할 수 있다.
      
      for(Member member : list) {
         System.out.println(member);
      }
   }
   public void printMemberInfo2(List<? super Member> list) {
      // super 키워드를 이용한 하한 제한(Lower Bounds)
      // Member 타입의 변수를 이용하여 list로부터 객체를 꺼내올 수 없다.
      
//      for(Member member : list) {     // Member 타입을 명시했고, 그 하위타입이 출력될 수 없다.
//         System.out.println(member);
//      }
   }
   // 회원정보 등록
   public void registerMember(List<? extends Member> list) { // Member 포함 하위타입
      // Member 타입의 객체라고 항상 list에 추가할 수 있음을 보장해 주지 않는다.
      // Member타입 또는 Member를 상속한 그 어떤 타입을 의미하므로 아직 구체적인 타입이 정해져 있지 않았음.)
      
      Member m = new Member("홍길동", 33);
//      list.add(m); // Member 혹은 그 하위타입이기 때문에 add 할 수 없다. (하위타입일 가능성도 배제할 수 없다.)
   }
   public void registerMember2(List<? super Member> list) {
      // super 키워드를 이용한 하한 제한 예제
      // list는 Member타입의 객체를 포함한다는 것을 보장받는다.
      // (Member타입 또는 Member타입의 슈퍼 클래스 타입을 담은 리스트를 의미함.
      
      Member m = new Member("홍길동", 33);
      list.add(m); // Member타입 객체 추가 가능함. (Member 상위타입이기 때문에 가능하다.)
   }
   public static void main(String[] args) {
      T07_WildCardTest wt = new T07_WildCardTest();
      
      List<Member> memList = new ArrayList<Member>();
      
      wt.registerMember2(memList);
      wt.printMemberInfo(memList);
   }
}

// 회원정보
class Member {
   private String name;
   private int age;
   
   public Member(String name, int age) {
      super();
      this.name = name;
      this.age = age;
   }

   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public int getAge() {
      return age;
   }
   
   public void setAge(int age) {
      this.age = age;
   }
   
   @Override
   public String toString() {
      return "Member [name=" + name + ", age=" + age + "]";
   }
}