package day11.Ch12_Multithread;

public class InterruptedExample {
	public static void main(String ar[]) {
		Thread thread = new PrintThread2();
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		
		thread.interrupt();
	}
}

class PrintThread2 extends Thread{
	public void run(){
		try{
			while(true){
				System.out.println("now operating ~");
				Thread.sleep(1);
			}
		}catch(InterruptedException e){}
		System.out.println("order resource" );
		System.out.println("end Thread");
	}
}