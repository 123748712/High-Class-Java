package highClassJava;

import java.io.ObjectInputStream.GetField;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HotelManagement {
	public static void main(String[] args) {
		HashMap<Integer, String> guestList = new HashMap<Integer, String>();

//      문제)
//
//      호텔 운영을 관리하는 프로그램 작성.(Map이용)
//       - 키값은 방번호 
//       
//      실행 예시)
//
//      **************************
//      호텔 문을 열었습니다.
//      **************************
//
//      *******************************************
//      어떤 업무를 하시겠습니까?
//      1.체크인  2.체크아웃 3.객실상태 4.업무종료
//      *******************************************
//      메뉴선택 => 1 <-- 입력
//
//      어느방에 체크인 하시겠습니까?
//      방번호 입력 => 101 <-- 입력
//
//      누구를 체크인 하시겠습니까?
//      이름 입력 => 홍길동 <-- 입력
//      체크인 되었습니다.
//
//      *******************************************
//      어떤 업무를 하시겠습니까?
//      1.체크인  2.체크아웃 3.객실상태 4.업무종료
//      *******************************************
//      메뉴선택 => 1 <-- 입력
//
//      어느방에 체크인 하시겠습니까?
//      방번호 입력 => 102 <-- 입력
//
//      누구를 체크인 하시겠습니까?
//      이름 입력 => 성춘향 <-- 입력
//      체크인 되었습니다
//
//      *******************************************
//      어떤 업무를 하시겠습니까?
//      1.체크인  2.체크아웃 3.객실상태 4.업무종료
//         *******************************************
//      메뉴선택 => 3 <-- 입력
//
//      방번호 : 102, 투숙객 : 성춘향
//      방번호 : 101, 투숙객 : 홍길동
//
//      *******************************************
//      어떤 업무를 하시겠습니까?
//      1.체크인  2.체크아웃 3.객실상태 4.업무종료
//      *******************************************
//      메뉴선택 => 2 <-- 입력
//
//      어느방을 체크아웃 하시겠습니까?
//      방번호 입력 => 101 <-- 입력
//      체크아웃 되었습니다.
//
//      *******************************************
//      어떤 업무를 하시겠습니까?
//      1.체크인  2.체크아웃 3.객실상태 4.업무종료
//      *******************************************
//      메뉴선택 => 1 <-- 입력
//
//      어느방에 체크인 하시겠습니까?
//      방번호 입력 => 102 <-- 입력
//
//      누구를 체크인 하시겠습니까?
//      이름 입력 => 허준 <-- 입력
//      102방에는 이미 사람이 있습니다.
//
//      *******************************************
//      어떤 업무를 하시겠습니까?
//      1.체크인  2.체크아웃 3.객실상태 4.업무종료
//      *******************************************
//      메뉴선택 => 2 <-- 입력
//
//      어느방을 체크아웃 하시겠습니까?
//      방번호 입력 => 101 <-- 입력
//      101방에는 체크인한 사람이 없습니다.
//
//      *******************************************
//      어떤 업무를 하시겠습니까?
//      1.체크인  2.체크아웃 3.객실상태 4.업무종료
//      *******************************************
//      메뉴선택 => 3 <-- 입력
//
//      방번호 : 102, 투숙객 : 성춘향
//
//      *******************************************
//      어떤 업무를 하시겠습니까?
//      1.체크인  2.체크아웃 3.객실상태 4.업무종료
//      *******************************************
//      메뉴선택 => 4 <-- 입력
//
//      **************************
//      호텔 문을 닫았습니다.
//      **************************
//
//      
		boolean run = true;
			Scanner scanner = new Scanner(System.in);
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
				break;
			case 2:
				System.out.println("어느 방을 체크아웃 하시겠습니까 ?");
				System.out.print("방번호 입력 > ");
				roomNum = Integer.parseInt(scanner.nextLine());

				if (guestList.get(roomNum) == null) {
					System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
					break;
				} else {
					guestList.remove(roomNum);
					System.out.println("체크아웃 되었습니다.");
				}
				break;
			case 3:
				Set<Integer> keySet = guestList.keySet();
				if (keySet.size() == 0) {
					System.out.println("객실이 모두 비어있습니다.");
				} else {
					for(Integer key : keySet) {
						System.out.println(key + ", 투숙객 : " + guestList.get(key));
					}
				}

				break;
			case 4:
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				run = false;
				break;
			default:
				break;
			}
		}
	}
}