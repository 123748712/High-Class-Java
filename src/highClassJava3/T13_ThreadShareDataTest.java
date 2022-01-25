package highClassJava3;

public class T13_ThreadShareDataTest {
   // 스레드에서 데이터를 공통으로 사용하는 방법

   // 공통으로 사용할 데이터를 클래스로 정의한다.
   // 공통으로 사용할 클래스의 인스턴스를 만든다.
   // 이 인스턴스를 각각의 스레드에 넘겨준다.
   // 각각의 스레드는 이 인스턴스의 참조값을 지정한 변수를 이용하여 공통으로 데이ㅓ를 사용한다.

   // 원주율을 계산하는 스레드가 있고, 계산된 원주율을 출력하는 스레드가 있다.
   // 원주율을 계산한 후 이 값을 출력하는 프로그램을 작성하시오.
   // 이때 원주율을 저장하는 객체가 필요하다.

   public static void main(String[] args) {
      ShareData sd = new ShareData();

      Thread tr1 = new CalcPIThread(sd);
//      Thread tr1 = new CalcPIThread(new ShareData()); 같은 값을 바라보지 못하고 다른값으로 되기 때문에 사용하면 안된다.
      Thread tr2 = new PrintPIThread(sd);

      tr1.start();
      tr2.start();
   }
}

// 원주율을 관리하는 클래스(공통으로 사용할 클래스)
class ShareData {
   public double result; // 원주율이 저장될 변수

   // volatile => 선언된 변수를 컴파일러의 최적화 대상에서 제외시킨다.
   // 즉, 값이 변경되는 즉시 변수에 적용시킨다. (최적화는 낮아지지만, 정확도는 올라감)
   // 다중 스레드에서 하나의 변수가 완벽하게 한번에 작동되도록 보장하는 키워드 (일종의 동기화) 


   volatile public boolean isOk = false; // 원주율 계산이 완료되었는지를 나타내는 변수
}

// 원주율을 계산하는 스레드 클래스
class CalcPIThread extends Thread {
   private ShareData sd;

   public CalcPIThread(ShareData sd) {
      this.sd = sd;
   }

   @Override
   public void run() {
      // 원주율 (1/1 - 1/3 + 1/5 - 1/7 + 1/9 ...) * 4
      //         0       1     2       3     4 => 2로 나눈 몫
      //         1      3     5      7     9 => 분모

      double sum = 0.0; // 누적시키기 위한 sum 값 0.0 초기화

      for (int i = 1; i <= 1500000000; i += 2) { // 무한히 계속 구해야하기 때문에 i 의 값을 높게 작성, i+=2 를 했기 때문에 1 => 3 => 5 => ...
         if (((i / 2) % 2) == 0) { // 2로 나눈 몫이 짝수이면...
            sum += (1.0 / i);
         } else {
            sum -= (1.0 / i);
         }
      }
      sd.result = sum * 4; // 계산된 원주율을 공통객체에 저장
      sd.isOk = true; // for문 계산이 완료되었음을 나타냄
   }
}

class PrintPIThread extends Thread {
   private ShareData sd;

   public PrintPIThread(ShareData sd) {
      this.sd = sd;
   }

   @Override
   public void run() {
      while (true) {
         // 원주율 계산이 완료될 때 까지 기다린다.
         if (sd.isOk) {
            break;
         }
      }
      System.out.println();
      System.out.println("계산된 원주율 : " + sd.result); // for문의 i 값이 높아지면 높아질수록 값은 더 정확해진다.
      System.out.println("      PI : " + Math.PI);
   }
}