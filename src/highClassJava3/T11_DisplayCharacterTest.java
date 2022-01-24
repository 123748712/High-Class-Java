package highClassJava3;

public class T11_DisplayCharacterTest {
   static String strRank = "";

   public static void main(String[] args) {
      DisplayCharacter[] disChars = new DisplayCharacter[] {
            new DisplayCharacter("홍길동"),
            new DisplayCharacter("일지매"),
            new DisplayCharacter("변학도")
            };

      for (int i = 0; i < disChars.length; i++) {
         disChars[i].start();
      }
      for (DisplayCharacter dc : disChars) {
         try {
            dc.join();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println("경기 끝...");
      System.out.println("===================");
      System.out.println();
      System.out.println("=== 경기결과 ===");
      System.out.println("순위 : " + strRank);
   }
}

class DisplayCharacter extends Thread {
   private String name;

   public DisplayCharacter(String name) {
      this.name = name;
   }

   @Override
   public void run() {
      for (char ch = 'A'; ch <= 'Z'; ch++) {
         System.out.println(name + "의 출력 문자 : " + ch);
         try {
            Thread.sleep((int) (Math.random() * 301 + 200)); // sleep() 메서드의 값을 200~500 사이의 난수로 처리하기
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println(name + "출력 끝...");
      T11_DisplayCharacterTest.strRank += name + " "; // strRank에 (name + " ") 가 누적된다.
   }
}