package highClassJava3;

import java.util.concurrent.locks.Lock;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

// 은행의 입출금을 스레드로 처리하는 예제 (Lock객체를 이용한 동기화)
public class T17_LockAccountTest {

   // Lock 기능을 제공하는 클래스

   // ReentrantLock : Read 및 Write 구분없이 사용하기 위한 락 클래스
   // Synchronized를 이용한 동기화 처리보다 부가적인 기능을 제공함
   // ex) Fairness 설정 등 => 가장 오래 기다린 스레드가 가장 먼저 락을 획득하게 함

   // ReentrantReadWriteLock : Read 및 Write락을 구분하여 사용가능함
   // 재귀적으로 여러번 Read나 Write락을 획득할 수 있다. (재진입 가능)
   // 여러 스레드가 동시에 read작업은 가능하지만, write작업은 단지 하나의 스레드만 가능함(exclusive)
   // => Write 보다 Read 위주의 작업이 많이 발생하는 경우에 사용하면 Throughput이 좋아진다.

   public static void main(String[] args) {

   }
}

// 입출금을 담당하는 클래스(공유객체)
class LockAccount {
   private int balance; // 잔액

   // Lock 객체 생성 => 되도록이면 private final 로 만든다.
   private final Lock lock;

   public LockAccount(Lock lock) {
      this.lock = lock;
   }

   public int getBalance() {
      return balance;
   }

   // 입금 메서드
   public void deposit(int money) {
      // Lock객체의 lock() 메서드가 동기화 시작이고, unlock() 메서드가 동기화의 끝을 나타낸다.
      // lock() 메서드로 동기화를 설정한 곳에셔는 반드시 unlock() 메서드로 해제해 주어야 한다.
      lock.lock(); // 락 설정 (락 획득하기 전까지 BLOCKED 됨)
      balance += money;
      lock.unlock();
   }

   // 출금 메서드(출금성공 : true, 출금실패 : false)
   public boolean withdraw(int money) {
      boolean chk = false;

      if (lock.tryLock()) { // 락 획득 시도(성공 ㅣ true, 실패 " false)
         System.out.println(Thread.currentThread().getName() + " : 락 획득 성공 !");

         // try - catch 블럭을 사용할 경우에는 unlock() 메서드 호출은 finally 블럭에서 한다.

         
         
         try {
            if (balance >= money) {
               for (int i = 1; i <= 100000000; i++) {
               }
               balance -= money;
               System.out.println("메서드 안에서 balance = " + getBalance());
               chk = true;
            }
         } catch (Exception e) {
            chk = false;
         } finally {
            lock.unlock();
         }
      } else {
         System.out.println(Thread.currentThread().getName() + " : 락 획득 실패 !");
      }
      return chk;
   }
}

class BankThread2 extends Thread {
   private LockAccount lAcc;

   public BankThread2(LockAccount lAcc) {
      this.lAcc = lAcc;
   }

   @Override
   public void run() {
      boolean result = lAcc.withdraw(6000);
      System.out.println("스레드 안에서 result : " + result + ", balance = " + lAcc.getBalance());
   }
}