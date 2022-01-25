package highClassJava3;

public class T15_SyncThreadTest {
   public static void main(String[] args) {

      ShareObject sObj = new ShareObject(); // 공유객체

      WorkerThread th1 = new WorkerThread("1번스레드", sObj);
      WorkerThread th2 = new WorkerThread("2번스레드", sObj);

      th1.start();
      th2.start();
   }
}
// 공통으로 사용할 객체
class ShareObject {
   private int sum = 0;

   // 동기화 하는 방법 1 => 메서드 자체에 동기화 설정하기
//   synchronized public void add() { // synchronized 키워드를 통해 동기화 설정 
   public void add() {   
   for (int i = 0; i < 100000000; i++) {} // 시간 떼우기
   // 동기화 하는 방법 2 => 동기화 코드 블럭을 설정해 그 부분은 하나의 스레드만 실행되게끔 하는 동기화
   // 동기화를 하지 않으면 안할수록 좋다. but, 해야할 상황에선 동기화 할 부분만 최소화하여 사용하는게 좋다.
   synchronized (this) {
      int n = sum;
      n += 10;
      sum = n;
      System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
      }
   }
}

// 작업을 수행하는 스레드
class WorkerThread extends Thread {
   private ShareObject sObj;

   public WorkerThread(String name, ShareObject sObj) {
      super(name); // 스레드에 이름을 달아주기 위함
      this.sObj = sObj;
   }

   @Override
   public void run() {
      for (int i = 1; i <= 10; i++) {
         synchronized (sObj) { // sObj 에 대해서 동기화를 하겠다.
         sObj.add();
         }
      }
   }
}