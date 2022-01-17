package highClassJava;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04_ListSort {
   public static void main(String[] args) {

      List<Member> memList = new ArrayList<Member>();

      memList.add(new Member(1, "홍길동", "010-1111-1111"));
      memList.add(new Member(5, "변학도", "010-1111-2222"));
      memList.add(new Member(9, "성춘향", "010-1111-3333"));
      memList.add(new Member(3, "이순신", "010-1111-4444"));
      memList.add(new Member(6, "강감찬", "010-1111-5555"));
      memList.add(new Member(2, "일지매", "010-1111-6666"));

      System.out.println("정렬 전 : ");
      for (Member member : memList) {
         System.out.println(member);
      }
      Collections.sort(memList); // 정렬하기

      System.out.println("이름의 오름차순으로 정렬 : ");
      for (Member member : memList) {
         System.out.println(member);
      }

      // 외부 정렬 기준을 이용한 정렬하기
      Collections.sort(memList, new SortNumDesc());
      System.out.println("번호 내림차순으로 정렬 : ");
      for (Member member : memList) {
         System.out.println(member);
      }
   }
}

class Member implements Comparable<Member> {
   private int num; // 번호
   private String name; // 이름
   private String tel; // 전화번호

   public Member(int num, String name, String tel) {
      super();
      this.num = num;
      this.name = name;
      this.tel = tel;
   }

   public int getNum() {
      return num;
   }

   public void setNum(int num) {
      this.num = num;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getTel() {
      return tel;
   }

   public void setTel(String tel) {
      this.tel = tel;
   }

   @Override
   public String toString() {
      return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
   }

   @Override
   public int compareTo(Member mem) {
      // TODO Auto-generated method stub
      return getName().compareTo(mem.getName());
   }
}

// Member의 번호(num) 의 내림차순으로 정렬하기
class SortNumDesc implements Comparator<Member> {

   @Override
   public int compare(Member mem1, Member mem2) {
      if (mem1.getNum() > mem2.getNum()) {
         return -1; // 오름차순이면 양수, 내림차순 정렬을 하기 위해 -1 리턴
      } else if (mem1.getNum() == mem2.getNum()) {
         return 0;
      } else {
         return 1;
      }
   }

}