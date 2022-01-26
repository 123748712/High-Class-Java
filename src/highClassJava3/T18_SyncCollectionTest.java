package highClassJava3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T18_SyncCollectionTest {

	// Vector, Hashtable 등의 예전부터 존재하던 Collection 클래스들은 내부에 동기화 처리가 되어있다.
	// 그런데, 최근에 새로 구성된 Collection 클래스들은 동기화 처리가 되어있지 않다.
	// 그래서 동기화가 필요한 프로그램에서 이런 Collection클래스들을 사용하려면 동기화 처리를 한 후 사용한다.

	// 동기화 처리를 하지 않을 경우
	private static List<Integer> list1 = new ArrayList<>();

	// 동기화 처리를 한 경우
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	// 동기화 처리는 스레드가 하나씩 처리하기 때문에 효율도 떨어지고 느려져 최대한 하지 않는게 좋지만, 해야할 상황에선 해야하는게 맞다.

	public static void main(String[] args) {
		// 익명클래스로 스레드 구현
		Runnable r = new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10000; i++) {
//					list1.add(i);
					list2.add(i);
				}
			}
		};
		Thread[] ths = new Thread[] { new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r) };
		// 5개의 스레드가 정상작동을 했다면 list의 갯수는 5만개가 나와야한다.
		// 동기화가 되어있지 않아 서로의 영역을 침범해 예외가 굉장히 많이 발생함.

		long startTime = System.currentTimeMillis();

		for (Thread th : ths) {
			th.start();
		}
		for (Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();

		System.out.println("처리 시간(ms) : " + (endTime - startTime));
//		System.out.println("list1의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size()); // Collections 를 통해 동가화를 해주어 5만개가 정상출력된다.
	}
}