package highClassJava5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

// 문자변환보조스트림 예제(OutputStreamWriter)
public class T10_FileEncodingTest {
	public static void main(String[] args) throws IOException {

		// OutputStreamWriter => OutputStream(바이트기반스트림)을 Writer(문자기반의 출력용 객체)로 변환하는 객체
		// => 이 객체도 출력할 때 '인코딩 방식'을 지정해서 출력할 수 있다.

		// 키보드로 입력한 내용을 파일로 저장하는데
		// out_utf8.txt 파일은 'UTF-8'인코딩 방식으로
		// out_ansi.txt 파일은 'ms949'인코딩 방식으로 지정한다.

		InputStreamReader isr = new InputStreamReader(System.in);

		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");

		// OutputStreamWriter는 바이트 출력 스트림에 연결되어 문자 출력 스트림인 Writer로 변환시키는 보조스트림

		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "utf-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "ms949");

		int data;
		System.out.println("아무거나 입력하세요.");

		while ((data = isr.read()) != -1) {
			osw1.write(data);
			osw2.write(data);
		}

		System.out.println("작업 완료...");
		isr.close();
		osw1.close();
		osw2.close();
	}
}
