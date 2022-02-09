package highClassJava5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HotelManagement implements Serializable {
	private Scanner scanner;
	private HashMap<Integer, String> guestList;
	private boolean run;
	private FileOutputStream fos;
	private FileInputStream fis;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private File file;

	public HotelManagement() {
		scanner = new Scanner(System.in);
		guestList = new HashMap<Integer, String>();
		run = true;
		file = new File("d:/D_Other/hotel.bin");

	}

	public static void main(String[] args) throws IOException {
		HotelManagement hotel = new HotelManagement();

		hotel.start();
	}

	private void start() throws IOException { // 시작이 되었을때 close 할때 파일로 저장했던 key, value 값들을 불러와 다시 한번 hashMap에 저장한다.
		if (!file.createNewFile()) { // 파일이 없다면 생성 후 true 반환, 파일이 있다면 false 반환
			try {
				fis = new FileInputStream(file);

				ois = new ObjectInputStream(fis);

				Object obj = ois.readObject();

				ois.close();

				guestList = (HashMap<Integer, String>) obj;

				Iterator<Integer> it = guestList.keySet().iterator();

				while (it.hasNext()) {
					int key = it.next();

					String value = guestList.get(key);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");

		while (run) {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까 ?");
			System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴선택 > ");
			int menu = Integer.parseInt(scanner.nextLine());

			switch (menu) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				serchRoom();
				break;
			case 4:
				close();
				break;
			default:
				System.out.println("입력한 번호의 업무가 없습니다.");
				break;
			}
		}
	}

	private void close() {
		try { // close가 되었을때 지금까지 hashMap 에 저장된 key, value 들을 저장한다.
			fos = new FileOutputStream(file);

			oos = new ObjectOutputStream(fos);

			oos.writeObject(guestList);

			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
		run = false;

		scanner.close();
	}

	private void serchRoom() {
		Set<Integer> keySet = guestList.keySet();
		if (keySet.size() == 0) {
			System.out.println("객실이 모두 비어있습니다.");
		} else {
			for (Integer key : keySet) {
				System.out.println(key + ", 투숙객 : " + guestList.get(key));
			}
		}
	}
	
	private void checkOut() {
		System.out.println("어느 방을 체크아웃 하시겠습니까 ?");
		System.out.print("방번호 입력 > ");
		int roomNum = Integer.parseInt(scanner.nextLine());

		if (guestList.get(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
		} else {
			guestList.remove(roomNum);
			System.out.println("체크아웃 되었습니다.");
		}
	}

	private void checkIn() {
		System.out.println("어느 방에 체크인 하시겠습니까 ?");
		System.out.print("방번호 입력 > ");
		int roomNum = Integer.parseInt(scanner.nextLine());

		System.out.println("누구를 체크인 하시겠습니까 ?");
		System.out.print("이름 입력 > ");
		String name = scanner.nextLine();

		if (guestList.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		}
		guestList.put(roomNum, name);

	}
}