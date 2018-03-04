package day11.Ch12_Multithread;

public class StopFlagExample {
	public static void main(String ar[]) {
		PrintThread1 printThread = new PrintThread1();
		printThread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		printThread.setStop(true);

	}
}

class PrintThread1 extends Thread {
	private boolean stop;

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public void run() {
		while (!stop) {
			System.out.println("now operating");
		}
		System.out.println("order resource");
		System.out.println("end");
	}
}
