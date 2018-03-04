package day11.Ch12_Multithread;

public class InterruptedExample2 {
		public static void main(String ar[]) {
			Thread thread = new PrintThread3();
			thread.start();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			
			thread.interrupt();
		}
	}

	class PrintThread3 extends Thread{
		public void run(){
				while(true){
					System.out.println("now operating ~");
					if(Thread.interrupted()){
						break;
					}
				}
			
			System.out.println("order resource" );
			System.out.println("end Thread");
		}
	}

