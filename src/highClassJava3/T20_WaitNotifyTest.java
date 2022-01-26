package kr.or.ddit.basic;

public class T20_WaitNotifyTest {
	public static void main(String[] args) {
		DataBox dataBox = new DataBox();
		
		ProducerThread pth = new ProducerThread(dataBox);
		ConsumerThread cth = new ConsumerThread(dataBox);
		
		pth.start();
		cth.start();
	}
}

// 데이터를 공용으로 사용하는 클래스
class DataBox {
	private String data; // 데이터를 담기 위한 저장공간

	// data가 null이 아닐때 data값을 반환하는 메서드
	public synchronized String getData() {
		if (data == null) {
			try {
				wait(); // 데이터가 없으면 대기
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 데이터가 null이 아니면 아래 코드를 실행한다.
		String returnData = data;
		System.out.println("읽어온 데이터 : " + returnData);
		data = null; // null 로 초기화를 헤줬기 때문에 위의 if문에서 현재 실행중인 스레드는 wait() 상태가 된다.
		System.out.println(Thread.currentThread().getName() + "notify() 호출");
		notify();

		return returnData;
	}

	// data가 null일 경우에만 자료를 세팅하는 메서드
	public synchronized void setData(String data) {
		if (this.data != null) {
			try {
				wait(); // 데이터가 있으면 대기.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.data = data;
		System.out.println("세팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName() + "notify() 호출");
		notify();
	}
}

// 데이터를 세팅만 하는 스레드
class ProducerThread extends Thread {
	private DataBox dataBox;

	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");
		this.dataBox = dataBox;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			String data = "data-" + i; // data-i 데이터를 10번 생성
			System.out.println("dataBox.setData(" + data + ") 호출");
			dataBox.setData(data);
		}
	}
}

// 데이터를 읽어오는 스레드
class ConsumerThread extends Thread {
	private DataBox dataBox;

	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			String data = dataBox.getData();
			System.out.println("dataBox.getData() : " + data);
		}
	}
}