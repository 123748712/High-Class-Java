package highClassJava5;
/*
 * 객체 입출력 스트림 예제(직렬화와 역직렬화)
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class T15_ObjectStreamTest {
	public static void main(String[] args) {
		
		//Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "경기");
		Member mem3 = new Member("이몽룡", 40, "강원");
		Member mem4 = new Member("성춘향", 10, "전북");
		
		ObjectOutputStream oos = null;
		
		try {
			//객체 파일에 저장하기
			
			//출력용스트림 객체 생성
			oos = new ObjectOutputStream(
					new BufferedOutputStream(///buffer로 성능향상
					new FileOutputStream("d:/D_Other/memObj.bin")));///무조건 bin에 저장할 필요는 없음
			
			//쓰기작업
			oos.writeObject(mem1);//직렬화
			oos.writeObject(mem2);//직렬화
			oos.writeObject(mem3);//직렬화
			oos.writeObject(mem4);//직렬화
			
			System.out.println("쓰기 작업 완료");
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}finally{///finally처리까지 해주는게 안전함(그동안 수업에는 가독성때문에 안쓴거임)
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/////////////////////////////////////////////////////////////
		
		
		ObjectInputStream ois = null;
		
		try {
			//저장한 객체를 읽어와 출력하기
			
			//입력용 스트림 객체 생성
			ois = new ObjectInputStream(
					new BufferedInputStream(
					new FileInputStream("d:/D_Other/memObj.bin")));
			
			Object obj = null;
			
			while ((obj = ois.readObject()) != null) {
				//읽어온 데이터를 원래의 객체형으로 변환 후 사용한다.
				Member mem = (Member) obj;
				
				System.out.println("이름: " + mem.getName());
				System.out.println("나이: " + mem.getAge());
				System.out.println("주소: " + mem.getAddr());
				System.out.println("--------------------------");
				
			}
		} catch (IOException ex) {
			//ex.printStackTrace();
			System.out.println("출력 작업 끝...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				ois.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}		}
	}
}


class Member implements Serializable{
	//자바는 Serializable(뜻:직렬가능한, 연속적으로) 인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있음.
	//구현하지 않으면 직렬화 작업시 NotSerializableException 예외 발생함.
	//(IO작업시 무조건 Serializable이 일어나기 때문에 IO작업할 객체라면 객체가 Serializable했는지 확인하기 위해 implements해줘야함--안하면 예외발생)
	
	/*
	 * transient => 직렬화가 되지 않을 멤버 변수에 지정한다.
	 * 			(* static 필드도 직렬화가 되지 않는다.)
	 * 직렬화가 되지 않는 멤버변수는 기본값으로 저장된다.
	 * (참조형 변수: null, 숫자형 변수: 0)
	 */
	private String name;
	private transient int age;///transient일시적인--직렬화X(데이터 저장이 안됨)
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}
