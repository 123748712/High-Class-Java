package highClassJava3;

import java.awt.SystemColor;

// 은행의 입출금을 스레드로 처리하는 예제
// synchronized을 이용한 동기화 처리
public class T16_SyncAccountTest {
   public static void main(String[] args) {
      // 동기화를 하기 위해선 임계영역이 필요하고, 결국 공유객체가 필요하다.
      // 동기화는 최소화 !!!
      SyncAccount sAcc = new SyncAccount();
      sAcc.deposit(10000); // 입금
      
      BankThread bth1 = new BankThread(sAcc);
      BankThread bth2 = new BankThread(sAcc);
      // 동기화 처리를 하지 않으면 10000원의 금액을 동시에 탐색했고, 동시에 출금하기 떄문에  동시에 withdraw가 실행됨
      // 그래서 withdraw 메서드에 synchronized 키워드를 작성해, 한번에 한개의 스레드만 접근할 수 있도록 한다.
      
      bth1.start();
      bth2.start();
   }
}
// 은행의 입출금을 관리하는 클래스
class SyncAccount {
   private int balance; // 잔액이 저장될 변수

   synchronized public int getBalance() { // 동기화 영역에서 getBalance()를 호출하기 때문에 같이 synchronized 키워드를 적어준다.
      return balance;
   }
   
   // 입금 처리를 수행하는 메서드
   public void deposit(int money) {
      this.balance += money;
   }
   
   // 출금을 처리하는 메서드(출금 성공 : true, 출금 실패 : false)
   // 동기화 영역에서 호출하는 메서드도 동기화 처리를 해주어야 한다.
      public boolean withdraw(int money) {
   //   synchronized public boolean withdraw(int money) { 
      synchronized (this) {                         // 둘중 하나의 방법으로 사용 가능하다.
         if(balance >= money) {
         for(int i = 1; i <= 100000000; i++) {} // 시간떼우기
            balance -= money;
            System.out.println("메서드 안에서 balance = " + getBalance());
            return true; // 출금 성공
         } else {
            return false; // 출금 실패 (balance < money) 출금값이 현 잔액보다 크기 때문에 false 리턴
         }
      }
   }
}

// 은행 업무를 처리하는 스레드
class BankThread extends Thread {
   private SyncAccount sAcc;

   public BankThread(SyncAccount sAcc) {
      this.sAcc = sAcc;
   }
   
   @Override
   public void run() {
      boolean result = sAcc.withdraw(6000);
      System.out.println("스레드 안에서 result = " + result + ", balance = " + sAcc.getBalance());
   }
}