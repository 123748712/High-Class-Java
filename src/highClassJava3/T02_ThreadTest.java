package highClassJava3;

public class T02_ThreadTest {
   public static void main(String[] args) {
      // 멀티 스레드 프로그래밍 방식

      // 방법 1 : Thread클래스를 상속한 class의 인스턴스를 생성한 후 인스턴스의 start() 메서드르 호출한다.

      MyThread1 th1 = new MyThread1(); // thread 객체. run(), start() 메서드를 갖고있다.
      th1.start(); // main thread 가 th1 thread 를 실행시켜준다. 즉, mainThread 와 th1Thread 멀티 스레드 프로그램이다.
      
      // main Thread 가 MyThread 안에 있는 start() 메서드를 호출한다.
      // start() 메서드는 각각의 thread 를 호출하기 위한 메서드이다.
      // run() 메서드는 호출되었을때 실행될 코드만을 작성하는 메서드이다.

      // 방법 2 : Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후 인스턴스를 Thread객체의 인스턴스를 생성할 때
      // 생성자의 매개변수로 넘겨준다. 이때 생성된 Thread객체의 인스턴스의 start()메서드를 호출한다.
      // 자바는 다중상속이 불가능하여 Runnable 방법이 사용된다.

      Runnable r = new MyThread2();
      Thread th2 = new Thread(r);
      th2.start();

      // 방법 3 : 익명클래스를 이용하는 방법
      // Runnable 인터페이스를 구현한 익명클래스를 Thread 인스턴스를 생성할 때 매개변수로 넘겨준다.

      Thread th3 = new Thread(new Runnable() {

         @Override
         public void run() {
            for (int i = 1; i <= 200; i++) {
               System.out.print("@");

               try {
                  Thread.sleep(100);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
      });
      th3.start();
   }
}

class MyThread1 extends Thread {

   @Override
   public void run() { // 중요한 메서드
      for (int i = 1; i <= 200; i++) {
         System.out.print("*");

         try {
            Thread.sleep(100); // 100의 시간동안 작업을 잠시 멈춘다. (ms 단위를 사용함 1/1000초)
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
}

class MyThread2 implements Runnable { // void run() 메소드만을 갖고 있는 Runnable 객체

   @Override
   public void run() {
      for (int i = 1; i <= 200; i++) {
         System.out.print("$");

         try {
            Thread.sleep(100);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }
}