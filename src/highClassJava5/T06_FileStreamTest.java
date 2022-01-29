package highClassJava5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// 파일 출력 예제
public class T06_FileStreamTest {
	public static void main(String[] args) throws IOException {

		// 파일에 출력하기
		FileOutputStream fos = new FileOutputStream(new File("D:/D_Other/out.txt"));

		for (char ch = 'a'; ch <= 'z'; ch++) {
			fos.write(ch);
		}
		fos.close();
		
		FileInputStream fis = new FileInputStream("D:/D_Other/out.txt");
		
		int data;
		
		while((data = fis.read()) != -1) {
			System.out.print((char) data);
		}
	}
}                         