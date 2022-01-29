package highClassJava5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// 문자기반 스트림을 이용한 파일 읽기
public class T08_FileReaderTest {
	public static void main(String[] args) throws IOException {

		FileReader fr = new FileReader("d:/D_Other/testChar.txt");

		int data;

		while ((data = fr.read()) != -1) {
			System.out.print((char) data);
		}
		fr.close();
	}
}
