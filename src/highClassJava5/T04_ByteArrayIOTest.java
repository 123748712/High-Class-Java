package highClassJava5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {
	public static void main(String[] args) throws IOException {

		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		byte[] temp = new byte[4]; // 4byte씩 읽을 배열 생성

		
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

		// read(byte[] temp) 메서드 => temp배열 크기만큼 읽어와 temp 배열에 저장한다.
		// 더이상 읽어올 자료가 없으면 -1을 반환한다.
		while ((data = bais.read(temp)) != -1) {
			System.out.println("temp : " + Arrays.toString(temp));
			baos.write(temp, 0, data); // temp 배열을 index = 0 부터 data가 갖고 있는 index까지 읽어라.
		}
		outSrc = baos.toByteArray();

		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));

		// 사용한 스트림객체 닫기
		bais.close();
		baos.close();
	}
}