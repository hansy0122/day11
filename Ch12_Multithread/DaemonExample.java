package day11.Ch12_Multithread;

public class DaemonExample {
	public static void main(String ar[]){
		AutoSaveThread autosaveThread =new AutoSaveThread();
		autosaveThread.setDaemon(true);
		autosaveThread.start();
		try{
			Thread.sleep(3000);
			
		}catch(Exception e){}
		System.out.println("main Thread end");
	}
}

class AutoSaveThread extends Thread {
	public void save() {
		System.out.println("�۾� ������ ������.");
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
			save();
		}
	}
}