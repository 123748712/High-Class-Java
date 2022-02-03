package highClassJava5;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class T14_PrintStreamTest {
	public static void main(String[] args) throws IOException {

		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print2.txt");

		// PrintStream은 모든 자료형을 출력할 수 있는 기능을 제공하는 OutputStream의 서브클래스이다.
		// PrintStream은 IOException을 발생시키지 않는다.
		// println 및 print등 메서드 호출시마다 autoflush기능이 제공됨
		PrintStream out = new PrintStream(fos); // 콘솔로 출력하려면 매개변수에 System.out 을 넣어준다.
		out.print("안녕하세요. PrintStream입니다.\n");
		out.println("안녕하세요. PrintStream입니다2.");
		out.println("안녕하세요. PrintStream입니다3.");
		out.println(out); // 객체 출력
		out.println(3.14);

		out.close();

		// PrintStream은 데이터를 문자로 출력하는 기능을 수행함. (System.out)
		// 향상된 기능의 PrintWriter가 추가되었지만 계속 사용됨

		// PrintWriter가 PrintStream보다 다양한 언어의 문자를 처리하는데 적합하다.
		// 둘다 기본적으로 autoflush 기능이 꺼져있음.

		PrintWriter pw = new PrintWriter(fos2);
		pw.print("안녕하세요. PrintWriter입니다.\n");
		pw.println("안녕하세요. PrintWriter입니다.2");
		pw.println("안녕하세요. PrintWriter입니다.3");
		pw.println(out);
		
		pw.close();
	}
}
