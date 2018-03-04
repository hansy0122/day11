package day11.Ch12_Multithread;

//공유된 객체를 사용하는 Thread는 이렇게 Data가 의도하지않게 변경되는 오류가 발생할수 있다.
// 그래서 synchronized 시켜 동시에 여러개의 스레드가 하나의 객체를 동시에 이용하지 못하도록 막는다.

public class MainThreadExample_Synchronized {

	public static void main(String ar[]) {
		M_Calculator calculator = new M_Calculator();
		M_Calculator calculator2 = new M_Calculator();

		User11 user11 = new User11();
		user11.setClaculator(calculator);
		user11.start();

		User22 user22 = new User22();
		user22.setClaculator(calculator2);
		user22.start();
	}
	/**synchronized 키워드 대상
	 * 
	 * 1.한 클래스의 static 으로 공유되는 값 또는 객체를 여러 스래드에서 접근하여 변경하는 일이 발생한다면.(두번째의 예제)
	 * 
	 * 2.하나의 인스턴스의 공유되는 값 또는 객체를 여러 스레드에서 접근하여 변경하는 일이 발생한다면 (원래의 예제)
	 */
	 

}

class M_Calculator {
	private int memory;
	private static int value;

	public int getMemory() {
		return memory;
	}
	// 동기화--> 이 메소드는 한번에 하나의 스레드만 접근하여 사용이 가능하다.

	// 동기화 메소드
	/*
	 * public synchronized void setMemory(int memory) { this.memory = memory;
	 * try { Thread.sleep(3000); } catch (InterruptedException e) { }
	 * System.out.println(Thread.currentThread().getName() + ":" + this.memory);
	 * }
	 */

	/*
	 * 동기화블럭 속도면에서 동기화 메소드보다 우월함을 보임.
	 */
	public void setMemory(int memory) {
		synchronized (this) {
			this.memory = memory;
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			System.out.println(Thread.currentThread().getName() + ":" + this.memory);
		}
	}
	public static void setValue(int v) {
		synchronized (M_Calculator.class) {
			M_Calculator.value = v;
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			System.out.println(Thread.currentThread().getName() + ":" + M_Calculator.value);
		}
	}
	

}



class User11 extends Thread {
	private M_Calculator calculator;

	public void setClaculator(M_Calculator calculator) {
		this.setName("User1");
		this.calculator = calculator;
	}

	public void run() {
		calculator.setMemory(100);
		M_Calculator.setValue(300);
	}
}

class User22 extends Thread {
	private M_Calculator calculator;

	public void setClaculator(M_Calculator calculator) {
		this.setName("User2");
		this.calculator = calculator;
	}

	public void run() {
		calculator.setMemory(500);
		M_Calculator.setValue(100);
	}
}
