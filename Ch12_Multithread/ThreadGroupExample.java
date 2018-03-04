package day11.Ch12_Multithread;

public class ThreadGroupExample {
	public static void main(String ar[]) {
		ThreadGroup myGroup = new ThreadGroup("myGroup");
		WorkThread workThreadA = new WorkThread(myGroup, "workThreadA");
		WorkThread workThreadB = new WorkThread(myGroup, "workThreadB");

		workThreadA.start();
		workThreadB.start();
		System.out.println("[main ThreadGroup의 list() 메소드 출력 내용]");
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		mainGroup.list();
		System.out.println();

		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		System.out.println("[myGroup ThreadGroup의 interrpted() method 호출");
		myGroup.interrupt();
	}
}

// 이런식으로 생성자를 만들어서 사용할수도 있군!!
class WorkThread extends Thread {
	public WorkThread(ThreadGroup threadGroup, String threadName) {
		super(threadGroup, threadName);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000); // interruptedException을 발동시키기 위함
			} catch (InterruptedException e) {
				System.out.println(getName() + " interreupted");
				break;
			}
		}
		System.out.println(getName() + " end");
	}
}