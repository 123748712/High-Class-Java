package highClassJava4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class T03_LambdaTest {
	public static void main(String[] args) {

		List<String> list = new ArrayList<>();

		list.add("홍길동");
		list.add("이순신");
		list.add("강감찬");

		list.forEach(new Consumer<String>() {

			@Override
			public void accept(String name) {
				System.out.println(name);
			}
		});
		
		System.out.println("========================");
		list.forEach((name) -> System.out.println(name));;
		
		
	}
}
