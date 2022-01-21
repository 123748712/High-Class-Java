package highClassJava2;

import java.util.ArrayList;
import java.util.List;

public class T06_WildCardTest {
   public static void main(String[] args) {

      FruitBox<Fruit> fruitBox = new FruitBox();
      FruitBox<Apple> appleBox = new FruitBox();

      fruitBox.add(new Apple());
      fruitBox.add(new Grape());

      appleBox.add(new Apple());
      appleBox.add(new Apple());
      // appleBox.add(new Grape()); => Apple 타입으로만 담길 수 있게 선언했기 때문에 Grape 타입은 add 될 수 없다.
      
      Juicer.makeJoice(fruitBox);
      Juicer.makeJoice(appleBox);
   }
}

class Juicer {
   static <T> void makeJoice(FruitBox<T> box) {
      String FruitListStr = "";

      int cnt = 0;
      for (T f : box.getFruitList()) {
         if (cnt == 0) {
            FruitListStr += f;
         } else {
            FruitListStr += "," + f;
         }
         cnt++;
      }
      System.out.println(FruitListStr + "=> 쥬스 완성 !");
   }
}

class Fruit {
   private String name;

   public Fruit(String name) {
      super();
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return "과일(" + name + ")";
   }
}

class Apple extends Fruit {
   public Apple() {
      super("사과");
   }
}

class Grape extends Fruit {

   public Grape() {
      super("포도");
   }
}

// 과일 상자
class FruitBox<T> {
   private List<T> fruitList;

   public FruitBox() {
      fruitList = new ArrayList<>();
   }

   public List<T> getFruitList() {
      return fruitList;
   }

   public void add(T fruit) {
      fruitList.add(fruit);
   }
}