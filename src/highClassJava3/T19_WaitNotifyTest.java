package highClassJava3;

public class T19_WaitNotifyTest {

	// wait() 메서드 => 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별 존재)으로 이동시킨다.
	// => 대기실에서 기다리게 한다.

	// notify() 또는 notifyAll() 메서드 => Wait-Set영역에 있는 스레드를 깨워서 실행될 수 있도록 한다.
	// notify()는 하나, notifyAll()은 Wait-Set영역에 있는 전부를 깨운다.)
	// => 대기실에서 쉬고 있던 스레드중 랜덤으로 깨워 다시 일하게 시킨다.
	
	// wait()와 notify(), notifyAll() 메서드는 동기화 영역에서만 실행될 수 있고,
	// Object클래스에서 제공하는 메서드이다.

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();

		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
		// 서로가 번갈아가며 깨우고 대기하고 반복한다.
		// ThreadA 가 종료되어도, ThreadB는 계속 대기중이기 때문에 종료되지 않는다.
	}
}

// 공통으로 사용할 객체
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA()메서드 작업 중...");

		notify(); // 대기실에 있는 스레드가 있다면 깨운 후

		
		try {
			wait(); // 본인은 대기한다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void methodB() {
		System.out.println("methodB()메서드 작업 중...");

		notify();

		try { 
			wait(100); // 괄호 안에 숫자를 넣으면 그 시간이 지난 후 스스로 깨어난다.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ThreadA extends Thread {
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료");
	}
}

class ThreadB extends Thread {
	private WorkObject workObj;

	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료");
	}
}