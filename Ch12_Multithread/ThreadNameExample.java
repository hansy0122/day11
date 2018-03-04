package day11.Ch12_Multithread;

public class ThreadNameExample {
	public static void main(String ar[]) {
		Thread mainThread = Thread.currentThread();
		System.out.println("Thread name=" + mainThread.getName());

		Thread ThreadA = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(getName() + "�� ����� ����");
				}
			}
		};
		ThreadA.setName("ThreadA");
		ThreadA.start();
		
		Thread ThreadB = new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println(getName() + "�� ����� ����");
				}
			}
		};
		ThreadB.setName("ThreadB");
		ThreadB.start();
	}
}
