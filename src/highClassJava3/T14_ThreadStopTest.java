package highClassJava3;

public class T14_ThreadStopTest {
   // Thread의 stop()메서드를 호출하면 스레드가 바로 멈춘다.
   // 이때 사용하던 자원을 정리하지 못하고 종료되어서 나중에 실행되는 프로그램에 영향을 줄 수 있다.
   // 그래서 현재는 stop()메서드는 비추천으로 되어 있다.

   public static void main(String[] args) {
//      ThreadStopEx1 th = new ThreadStopEx1(); // Thread 타입으로 선언하면 ThreadStopEx1 메서드를 호출할 수 없기 때문에 ThreadStopEx1 타입으로 선언.
//      th.start();
//
//      try {
//         Thread.sleep(1000);
//      } catch (InterruptedException e) {
//         e.printStackTrace();
//      }
//      th.setStop(true);
//      // th.stop() 메서드는 갑자기 강제로 종료를 시키기 때문에 비추천하여 입력만 해도 가로줄이 생긴다.
   
      
      ThreadStopEx2 th2 = new ThreadStopEx2();
      th2.start();
      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      th2.interrupt(); // interrupt 걸기
   }
}

class ThreadStopEx1 extends Thread {
   private boolean stop;

   public void setStop(boolean stop) {
      this.stop = stop;
   }

   @Override
   public void run() {
      while (!stop) {
         System.out.println("스레드 처리 중...");
      }
      System.out.println("자원 정리 중...");
      System.out.println("실행 종료");
   }
}

// interrupt() 이용한 스레드 멈추는 방법
class ThreadStopEx2 extends Thread {
   @Override
   public void run() {
      // 방법 1 => sleep()이나 join() 메서드 등을 사용했을 때 interrupt() 메서드를 호출하면 InterruptedException이 발생한다.
//      try {
//         while(true) {
//         System.out.println("스레드 처리중...");
//         Thread.sleep(1); // interrupt 를 걸어주면 catch 로 이동하며 예외가 발생한다.
//         }
//      } catch (InterruptedException e) {
//         e.printStackTrace();
//      }
      
      // 방법2 => interrupt() 메서드가 호출되었는지 검사하기
      while(true) {
         System.out.println("스레드 처리 중...");
         
         // 검사방법 1 => 스레드의 인스턴스 객체용 메서드를 이용하는 방법
//         if(this.isInterrupted()) {
//            System.out.println("인스턴스용 isInterrupted()");
//            break;
         
         
         // 검사방법 2 => 스레드의 정적 메서드를 이용하는 방법
         if(Thread.interrupted()) {
            System.out.println("정적 메서드 interrupted()");
            break;
         }
      }
   System.out.println("자원 정리 중...");
   System.out.println("실행 종료");
   }
}