package highClassJava5;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// 성능향상을 위한 보조스트림 예제
// 바이트기반의 Buffered스트림 사용 예제
public class T11_BufferedIOTest {
	public static void main(String[] args) throws IOException {

		// 입출력의 성능 향상을 위한 보조스트림
		// 버퍼의 크기를 지정하지 않으면 기본적으로 8192byte(8kb)로 설정된다.
		// 버퍼크기가 5byte인 스트림 객체 생성
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:/D_Other/bufferTest.txt"), 5);

		for (int i = '1'; i <= '9'; i++) {
			System.out.println("i = " + i);
			bos.write(i); // 숫자 자체를 문자로 저장하기
			// buffer에 5byte가 모두 저장된 후 Write 메서드가 실행되어 총 2번 진행된다. (효율성)
		}
		bos.flush();
		// 작업을 종료하기 전에 남아있는 데이터를 모두 출력시킨다.(close시 자동 호출됨)
		// buffer내에 데이터가 모두 저장되있지 않아도 출력시키기 위함

		bos.close();

		System.out.println("작업 끝...");
	}
}
