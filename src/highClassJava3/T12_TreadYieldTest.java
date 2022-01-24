package highClassJava3;

public class T12_TreadYieldTest {
   // Yield() 메서드
   // 1. 현재 실행 대기중인 동등한 우선순위 이상의 다른 스레드에게 실행 기회를 제공한다.
   // 2, 현재 실행중인 스레드의 상태를 Runnable 상태로 바꾼다. (Waiting 이나 Blocked 상태로 바뀌지 않는다.)
   // 3. yield() 메서드를 실행한다고 해서 현재 실행중인 스레드가 runnable 상태로 전이된다고 확신할 수 없다.
   public static void main(String[] args) {
      Thread th1 = new YieldThreadEx1();
      Thread th2 = new YieldThreadEx2();

      th1.start();
      th2.start();
   }
}

class YieldThreadEx1 extends Thread {
   public YieldThreadEx1() {
      super("YieldThreadEx1"); // 스레드 생성자에 super() 를 통해 이름을 세팅할 수 있다.
   }

   @Override
   public void run() {
      for (int i = 0; i < 10; i++) {
         System.out.println(Thread.currentThread().getName() + " : " + i); // currentThread() 스레드의 정보를 가져올 수 있다. getName() super()로 세팅한 이름 출력
         Thread.yield(); // 양보하기   `
      }
   }
}

class YieldThreadEx2 extends Thread {
   public YieldThreadEx2() {
      super("YieldThreadEx2");
   }

   @Override
   public void run() {
      for (int i = 0; i < 10; i++) {
         System.out.println(Thread.currentThread().getName() + " : " + i);
      }
   }
}