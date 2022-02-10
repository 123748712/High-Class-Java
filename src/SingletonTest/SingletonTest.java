package SingletonTest;

public class SingletonTest {
	public static void main(String[] args) {
		
		// new 사용불가
//		MySingleton test1 = new MySingleton();
		
		MySingleton test2 = MySingleton.getInstance();
		test2.displayText();
		
		MySingleton test3 = MySingleton.getInstance();
		
		System.out.println("test2 => " + test2);
		System.out.println("test3 => " + test3);
	}
}