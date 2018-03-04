package day11.Ch12_Multithread;

//������ ��ü�� ����ϴ� Thread�� �̷��� Data�� �ǵ������ʰ� ����Ǵ� ������ �߻��Ҽ� �ִ�.
// �׷��� synchronized ���� ���ÿ� �������� �����尡 �ϳ��� ��ü�� ���ÿ� �̿����� ���ϵ��� ���´�.

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
	/**synchronized Ű���� ���
	 * 
	 * 1.�� Ŭ������ static ���� �����Ǵ� �� �Ǵ� ��ü�� ���� �����忡�� �����Ͽ� �����ϴ� ���� �߻��Ѵٸ�.(�ι�°�� ����)
	 * 
	 * 2.�ϳ��� �ν��Ͻ��� �����Ǵ� �� �Ǵ� ��ü�� ���� �����忡�� �����Ͽ� �����ϴ� ���� �߻��Ѵٸ� (������ ����)
	 */
	 

}

class M_Calculator {
	private int memory;
	private static int value;

	public int getMemory() {
		return memory;
	}
	// ����ȭ--> �� �޼ҵ�� �ѹ��� �ϳ��� �����常 �����Ͽ� ����� �����ϴ�.

	// ����ȭ �޼ҵ�
	/*
	 * public synchronized void setMemory(int memory) { this.memory = memory;
	 * try { Thread.sleep(3000); } catch (InterruptedException e) { }
	 * System.out.println(Thread.currentThread().getName() + ":" + this.memory);
	 * }
	 */

	/*
	 * ����ȭ�� �ӵ��鿡�� ����ȭ �޼ҵ庸�� ������� ����.
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
