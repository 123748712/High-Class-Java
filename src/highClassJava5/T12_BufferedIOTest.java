package highClassJava5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// 성능향상을 위한 보조스트림 예제-2
// 문자기반의 Bufferd스트림 사용 예제
public class T12_BufferedIOTest {
	public static void main(String[] args) throws IOException {

		// 이클립스에서 만든 자바프로그램이 실행되는 기본 위치는 해당 '프로젝트폴더'가 기본 위치가 된다.

		FileReader fr = new FileReader("./src/kr/or/ddit/basic/T11_BufferedIOTest.java"); // 상대경로

//		int data;
//
//		while ((data = fr.read()) != -1) {
//			System.out.print((char) data);
//		}
//		fr.close();

		BufferedReader br = new BufferedReader(fr);

		String temp = "";
		for (int i = 1; (temp = br.readLine()) != null; i++) { // readLine() => 한줄씩 읽는다. 읽을 데이터가 없다면 null return
			System.out.printf("%4d : %s\n", i, temp);
		}
		br.close();
	}
}