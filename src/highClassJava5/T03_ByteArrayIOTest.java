package highClassJava5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03_ByteArrayIOTest {
	public static void main(String[] args) throws IOException {

		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

//		// ArrayCopy를 이용한 배엷 복사 방법
//
//		outSrc = new byte[inSrc.length]; // 공간 확보
//		System.arraycopy(inSrc, 0, outSrc, 0, inSrc.length);
//
//		System.out.println("outSrc => " + Arrays.toString(outSrc));

		// 스트림 선언 및 객체 생성
		ByteArrayInputStream bais = null; // 스트림 선언
		bais = new ByteArrayInputStream(inSrc); // 객체생성

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int data; // 읽어온 데이터를 저장할 변수

		// read() 메서드 => byte단위로 자료를 읽어와 int형으로 반환한다.
		// 더이상 읽어올 자료가 없으면 -1을 반환한다.
		while ((data = bais.read()) != -1) {
			baos.write(data);
		}
		outSrc = baos.toByteArray();

		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));

		// 사용한 스트림객체 닫기
		bais.close();
		baos.close();
	}
}