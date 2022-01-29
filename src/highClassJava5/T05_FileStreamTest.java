package highClassJava5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// 파일 읽기 예제
public class T05_FileStreamTest {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = null;

		fis = new FileInputStream("d:/D_Other/test2.txt");

		int data; // 읽어온 데이터를 저장할 변수

		while ((data = fis.read()) != -1) {
			// 읽어온 자료 출력하기
			System.out.print((char) data);
		}
		fis.close(); // 스트림 닫기
	}
}
