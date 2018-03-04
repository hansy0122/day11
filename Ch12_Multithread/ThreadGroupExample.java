package day11.Ch12_Multithread;

public class ThreadGroupExample {
	public static void main(String ar[]) {
		ThreadGroup myGroup = new ThreadGroup("myGroup");
		WorkThread workThreadA = new WorkThread(myGroup, "workThreadA");
		WorkThread workThreadB = new WorkThread(myGroup, "workThreadB");

		workThreadA.start();
		workThreadB.start();
		System.out.println("[main ThreadGroup�� list() �޼ҵ� ��� ����]");
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		mainGroup.list();
		System.out.println();

		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		System.out.println("[myGroup ThreadGroup�� interrpted() method ȣ��");
		myGroup.interrupt();
	}
}

// �̷������� �����ڸ� ���� ����Ҽ��� �ֱ�!!
class WorkThread extends Thread {
	public WorkThread(ThreadGroup threadGroup, String threadName) {
		super(threadGroup, threadName);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000); // interruptedException�� �ߵ���Ű�� ����
			} catch (InterruptedException e) {
				System.out.println(getName() + " interreupted");
				break;
			}
		}
		System.out.println(getName() + " end");
	}
}