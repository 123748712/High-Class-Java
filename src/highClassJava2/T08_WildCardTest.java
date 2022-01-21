package highClassJava2;

import java.util.ArrayList;
import java.util.List;

public class T08_WildCardTest {

   // 장바구니 항목조회를 위한 메서드(모든종류)
   public static void displayCartItemInfo(Cart<?> cart) { // 어떤 타입의 cart도 올 수 있다.

      System.out.println("= 음식류 장바구니 항목 리스트 =");
      for (Object obj : cart.getList()) {
         System.out.println(obj.toString());
      }
      System.out.println("==========================");
   }

   // 장바구니 항목 조회를 위한 메서드(음료나 그 하위)
   public static void displayCartItemInfo2(Cart<? extends Drink> cart) { // Drink 및 하위타입
      System.out.println("= 음료류 장바구니 항목 리스트 =");

      for (Object obj : cart.getList()) {
         System.out.println(obj.toString());
      }
      System.out.println("==========================");
   }
   
   // 장바구니 항목 조회를 위한 메서드(고기나 그 상위)
   public static void displayCartItemInfo3(Cart<? super Meat> cart) { // Meat 및 상위타입
      System.out.println("= 음료류 장바구니 항목 리스트 =");

      for (Object obj : cart.getList()) {
         System.out.println(obj.toString());
      }
      System.out.println("==========================");
   }
   public static void main(String[] args) {
      
      Cart<Food> foodCart = new Cart<>();
      foodCart.addItem(new Meat("돼지고기", 5000));
      foodCart.addItem(new Meat("소고기", 10000));
      foodCart.addItem(new Meat("오렌지쥬스", 1500));
      foodCart.addItem(new Meat("아메리카노", 1500));
      
      Cart<Meat> meatCart = new Cart<>();
      meatCart.addItem(new Meat("돼지고기", 5000));
      meatCart.addItem(new Meat("소고기", 10000));
      
      Cart<Drink> drinkCart = new Cart<>();
      
      drinkCart.addItem(new Juice("오렌지쥬스", 1500));
      drinkCart.addItem(new Coffee("아메리카노", 1500));
      
      displayCartItemInfo(foodCart);
      displayCartItemInfo(meatCart);
      displayCartItemInfo(drinkCart);

//      displayCartItemInfo2(foodCart);
//      displayCartItemInfo2(meatCart);
      displayCartItemInfo2(drinkCart);
      
      displayCartItemInfo3(foodCart);
      displayCartItemInfo3(meatCart);
//      displayCartItemInfo3(drinkCart);
      
   }
}

class Food {
   private String name;
   private int price;

   public Food(String name, int price) {
      super();
      this.name = name;
      this.price = price;
   }

   @Override
   public String toString() {
      return name + "(" + this.price + "원)";
   }
}

class Meat extends Food {

   public Meat(String name, int price) {
      super(name, price);
   }

}

class Drink extends Food {

   public Drink(String name, int price) {
      super(name, price);
   }
}

class Juice extends Drink {

   public Juice(String name, int price) {
      super(name, price);
   }
}

class Coffee extends Drink {

   public Coffee(String name, int price) {
      super(name, price);
   }
}

class Cart<T> { // Generic class
   private List<T> list = new ArrayList<>();

   public void addItem(T item) {
      list.add(item);
   }

   public List<T> getList() {
      return list;
   }
}