package highClassJava;
import java.util.LinkedList;

public class T02_StackQueueTest {
   public static void main(String[] args) {

      // Stack => 후입선출(LIFO)의 자료구조
      // Queue => 선입선출(FIFO)의 자료구조
      // Stack 과 Queue는 LinkedList 를 이용하여 사용할 수 있다.

      LinkedList<String> stack = new LinkedList<String>();

      // stack 방식을 위한 명령 자료 입력 > push(저장할 값)
      // 자료 출력 > pop() => 자료를 꺼내온 후 자료를 stack에서 삭제한다.

      stack.push("홍길동"); // 제일 마지막 데이터
      stack.push("일지매");
      stack.push("변학도");
      stack.push("강감찬"); // 제일 첫번째 데이터
      System.out.println("현재 stack값들 : " + stack);

      String data = stack.pop(); // data 라는 변수를 통해 마지막에 넣은 데이터를 꺼낸다.
      System.out.println("꺼내온 자료 : " + data);
      System.out.println("꺼내온 자료 : " + stack.pop()); // pop() 을 이용해 data 전에 넣은 데이터를 다시 꺼낸다.
      System.out.println("현재 stack값들 : " + stack);

      stack.push("성춘향");
      System.out.println("현재 stack값들 : " + stack);
      System.out.println("꺼내온 자료 : " + stack.pop());

      System.out.println("============================");
      System.out.println();

      LinkedList<String> queue = new LinkedList<String>();

      // Queue의 명령
      // 자료 입력 > offer(저장할 값)
      // 자료 출력 > poll() => 자료를 Queue에서 꺼내온 후 자료를 stack에서 삭제한다.

      queue.offer("홍길동");
      queue.offer("일지매");
      queue.offer("변학도");
      queue.offer("강감찬");
      System.out.println("현재 queue값들 : " + queue);

      String temp = queue.poll();
      System.out.println("꺼내온 자료 : " + temp);
      System.out.println("꺼내온 자료 : " + queue.poll());
      System.out.println("현재 queue의 값 : " + queue);

      if (queue.offer("성춘향")) {
         System.out.println("신규 등록자료 : 성춘향"); // 성춘향이 제일 마지막으로 넣은 데이터이기 때문에 맨 뒤에 출력된다.
      }
      System.out.println("현재 queue의 값 : " + queue);
      System.out.println("꺼내온 자료 : " + queue.poll());
   }
}