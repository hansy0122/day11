package day11.Ch12_Multithread;

public class ThreadStateExample {
	public static void main(String ar[]){
		StatePrintThread statePrintThread =new StatePrintThread(new TargetThread());
		statePrintThread.run();
	}
}

	class TargetThread extends Thread{
		public void run(){
			for(long i=0;i<1000000000;i++){}
			
			try{
				Thread.sleep(1500);
			}catch(Exception e){}
			
			for(long i=0;i<1000000000;i++){}
		}
	}
	class StatePrintThread extends Thread{
		private Thread targetThread;
		
		public StatePrintThread(Thread targetThread){
			this.targetThread=targetThread;
		}
		
		public void run(){
			while(true){
				Thread.State state=targetThread.getState();
				System.out.println("targetThread state: "+ state);
				
				if(state== Thread.State.NEW){
					targetThread.start();
				}
				
				if(state==Thread.State.TERMINATED){
					break;
				}
				try{
					Thread.sleep(500);
				}catch(Exception e){}
			}
		}
		
		
	}
