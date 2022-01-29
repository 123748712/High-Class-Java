package highClassJava5;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class T07_FileWriterTest {
	public static void main(String[] args) throws IOException {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기

		// 콘솔(표준 입출력장치)과 연결된 입력용 문자 스트림 생성
		// InputStreamReader => 바이트 기반 스트림을 문자 기반 스트림으로 변환해 주는 보조스트림
		InputStreamReader isr = new InputStreamReader(System.in);
		
		// 파일 출력용 문자기반 스트림
		FileWriter fw = new FileWriter("d:/D_Other/testChar.txt");
		
		int data;
		
		System.out.println("아무거나 입력하세요.");
		
		// 콘솔에 입력할 때 임력의 끝 표시는 Ctrl + Z키(End of File) 를 누른다.

		while((data = isr.read()) != -1) {
			fw.write(data);
		}
		System.out.println("작업 끝...");
		
		isr.close();
		fw.close();
	}
}