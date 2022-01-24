package highClassJava3;

public class T09_ThreadDaemonTtset {
   public static void main(String[] args) {

      Thread autosave = new AutoSaveThread();
      // 데몬스레드로 설정하기(start() 메서드 호출 전에 설정해야함)

      autosave.setDaemon(true); // DaemonThread는 일반 Thread가 종료되면 같이 자동 종료된다.
      autosave.start();

      try {
         for (int i = 1; i <= 20; i++) { // 작업 20까지 완료되면 Main Thread 는 종료된다.
            System.out.println("작업 " + i);
            Thread.sleep(1000);
         }
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
      System.out.println("메인 스레드 종료...");
   }
}

// 자동 저장하는 스레드 (3초에 한번씩 저장하기)
class AutoSaveThread extends Thread {
   public void save() {
      System.out.println("작업 내용을 저장합니다.");
   }

   @Override
   public void run() {
      while (true) { // 무한루프
         try {
            Thread.sleep(3000); // 3초에 한번씩 실행됨
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         save(); // 자동 저장 기능 호출
      }
   }
}