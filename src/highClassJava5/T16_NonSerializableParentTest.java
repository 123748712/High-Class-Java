package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T16_NonSerializableParentTest {
/*
 * 부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
 * 부모객체의 필드값 처리방법
 * 
 * 1. 부모클래스가 Serializable 인터페이스를 구현하도록 해야한다.
 * 2. 자식클래스에 writeObject()와 readObject()메서드를 이용하여
 * 부모객체의 필드값을 처리할 수 있도록 직접 구현한다.	
 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/nonSerializableTest.bin"));
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		oos.writeObject(child);//직렬화
		oos.flush();//생략가능
		oos.close();
		
		ObjectInputStream ois = new ObjectInputStream(
									new FileInputStream("d:/D_Other/nonSerializableTest.bin"));
		
		Child child2 = (Child)ois.readObject();//역직렬화
		System.out.println("parentName : " + child2.getParentName());///부모는 직렬화하지 않았기 때문에 값이 나오지 않는다.
		System.out.println("childName : "+ child2.getChildName());
		
		ois.close();
	}

}

// Serializable을 구현하지 않은 부모클래스  ///자식클래스가 IO작업을 하고싶지 않을 수 있기 때문에 부모클래스는 하지 않음.
class Parent{
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}

//Serializable을 구현한 자식 클래스
class Child extends Parent implements Serializable {
	
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	
	///부모 직렬화 하지 않았을때 데이터를 나오게 하고싶을때--수동으로 저장하고 읽어오기
	/*
	 * 직렬화 될때 자동으로 호출됨.
	 * (접근체한자가 private이 아니면 자동으로 호출되지 않음)
	 */
	private void writeObject(ObjectOutputStream out) throws IOException{
		out.writeUTF(getParentName());//수동으로 저장함. //wirteUTF-문자열로 저장
		out.defaultWriteObject();//기본작업
	}
	/*
	 * 역직렬화 될때 자동으로 호출됨.
	 * (접근제한자가 private이 아니면 자동으로 호출되지않음.)
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		setParentName(in.readUTF()); //수동으로 읽어오기
		in.defaultReadObject();
	}
	
	
	
	
		
}
