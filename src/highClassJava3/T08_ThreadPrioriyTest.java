package highClassJava3;

public class T08_ThreadPrioriyTest {
   public static void main(String[] args) {
      System.out.println("최대 우선순위 : " + Thread.MAX_PRIORITY);
      System.out.println("보통 우선순위 : " + Thread.NORM_PRIORITY);
      System.out.println("최소 우선순위 : " + Thread.MIN_PRIORITY);
      // 프로세스에 엄청난 영향을 줄만큼 우선순위를 설정하는건 아니다.
      
      Thread th1 = new ThreadTest1();
      Thread th2 = new ThreadTest1();
      Thread th3 = new ThreadTest1();
      Thread th4 = new ThreadTest1();
      Thread th5 = new ThreadTest1();
      
      Thread th6 = new ThreadTest2();
      
      // 우선순위는 start() 메서드를 호출하기 전에 설정해야 한다.
      th1.setPriority(1);
      th2.setPriority(1);
      th3.setPriority(1);
      th4.setPriority(1);
      th5.setPriority(1);
      th6.setPriority(10);
      
      System.out.println("th1 의 우선순위 : " + th1.getPriority());
      System.out.println("th2 의 우선순위 : " + th2.getPriority());
      System.out.println("th3 의 우선순위 : " + th3.getPriority());
      System.out.println("th4 의 우선순위 : " + th4.getPriority());
      System.out.println("th5 의 우선순위 : " + th5.getPriority());
      System.out.println("th6 의 우선순위 : " + th6.getPriority());
      
      th1.start();
      th2.start();
      th3.start();
      th4.start();
      th5.start();
      th6.start();
      
      try {
         th1.join();
         th2.join();
         th3.join();
         th4.join();
         th5.join();
         th6.join();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
}
// 대문자를 출력하는 스레드
class ThreadTest1 extends Thread {
   @Override
   public void run() {
      for(char ch = 'A'; ch<='Z'; ch++) {
         System.out.println(ch);
         
         // 아무것도 하지 않는 반복문(시간떼우기)
         for(int i =0 ; i<=1000000000; i++) {}
      }
   }
}
// 소문자를 출력하는 스레드
class ThreadTest2 extends Thread {
   @Override
   public void run() {
      for(char ch = 'a'; ch<='z'; ch++) {
         System.out.println(ch);
         
         // 아무것도 하지 않는 반복문(시간떼우기)
         for(int i =0 ; i<=1000000000; i++) {}
      }
   }
}