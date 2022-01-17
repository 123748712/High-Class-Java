package highClassJava;
import java.util.ArrayList;
import java.util.List;

public class T01_ArrayListTest {
   public static void main(String[] args) {

      // List 특징
      // index 순서가 있다.
      // 중복값 저장 가능
      
      List list1 = new ArrayList<>();

      // add() 메서드를 사용해서 데이터를 추가한다.
      list1.add("aaa");
      list1.add("bbb");
      list1.add("111");
      list1.add('k');
      list1.add(true);
      list1.add(12.34); // default 는 double 이며, float 타입으로 선언하려 한다면 12.34f 를 해주면 된다.
      // 매개변수(파라미터)가 object 이기 때문에 자동으로 맞는 타입으로 변환 해준다.

      // size() => 데이터 개수
      System.out.println("size => " + list1.size());
      System.out.println("list1 => " + list1);

      // get() 으로 데이터 꺼내오기
      System.out.println("1번째 자료 : " + list1.get(1));

      // 데이터 끼워넣기도 같다. 넣고 싶은 index 번호, 넣고싶은 데이터를 넣게 되면 그 뒤의 index 는 자동으로 뒤로 밀린다.
      list1.add(0, "zzz");
      System.out.println("list1 => " + list1);

      // 데이터 변경하기 (set메서드)
      String temp = (String) list1.set(0, "YYY"); // 이전값을 return 하기 때문에 temp 에 이전값인 "zzz" 가 저장된다.
      System.out.println("temp => " + temp);
      System.out.println("list1 => " + list1);

      // 삭제하기도 같다. index 혹은 object 를 넣어도 된다.
      list1.remove(0);
      System.out.println("삭제 후 : " + list1);
      
      list1.remove(new Integer(111));
      // integer 값을 넣게 되면 자동으로 index로 인식하기 때문에 111번째 데이터를 지우려 한다.
      // integer 값을 지우려면 new Integer(integer) 를 사용한다.

      // 제너릭을 지정하여 선언할 수 있다.
      List<String> list2 = new ArrayList<String>(); // 제너릭에 타입을 선언하게 되면 다른타입을 작성할 수 없어 실수를 줄여준다.
      list2.add("AAA");
      list2.add("BBB");
      list2.add("CCC");
      list2.add("DDD");
      list2.add("EEE");

      for (int i = 0; i < list2.size(); i++) {
         System.out.println(i + " : " + list2.get(1));
      }
      System.out.println("======================================");
      for (String s : list2) {
         System.out.println(s);
      }
      System.out.println("======================================");

      // contains(비교객체) => 리스트에 '비교객체'가 있으면 true, 아니면 false 리턴함.

      System.out.println(list2.contains("DDD"));
      System.out.println(list2.contains("ZZZ"));

      // indexOf(비교객체) => 리스트에서 '비교객체'를 찾아 '비교객체'가 있는 index 값을 반환함.
      // 비교객체가 없으면 -1을 반환함.
      System.out.println("DDD의 index값 : " + list2.indexOf("DDD"));
      System.out.println("ZZZ의 index값 : " + list2.indexOf("ZZZ"));

      // toArray() => 리스트 안의 데이터들을 배열로 변환하여 반환함.
      // 기본적으로 object 형 배열로 반환한다.
      Object[] strArr = list2.toArray();
      System.out.println("배열의 개수 : " + strArr.length);

      // 리스트의 제너릭타입에 맞는 자료형의 배열로 변환하는 방법
      // 제너릭 타입의 0개짜리 배열을 생성해서 매개변수로 넣어준다. => 배열의 크기가 리스트 크기보다 작으면 리스트의 크기에 맞는 배열을 생성한다.
      // 형식 > toArray(new 재너릭타입[0] or toArray(new 재너릭타입[]{})
      String[] strArr2 = list2.toArray(new String[0]);
      // 매개변수에 적혀있는 타입으로 변환된다.

      // 리스트 삭제
      for (int i = 0; i < list2.size(); i++) {
         list2.remove(i);
      }
      // 값이 앞으로 당겨지기 때문에 값이 전부 지워지지 않는다.
      // 앞에서 뒤로 지우는게 아닌, 뒤에서 앞으로 지우면 전부 지워진다.
   }
}