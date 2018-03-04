package day11.Ch12_Multithread;

public class YieldExample {
	public static void main(String ar[]){
		ThreadA threadA=new ThreadA();
		ThreadB threadB=new ThreadB();
		threadA.start();
		threadB.start();
		try{Thread.sleep(3000);}catch(Exception e){}
		threadA.work=false;
		try{Thread.sleep(3000);}catch(Exception e){}
		threadA.work=true;
		try{Thread.sleep(3000);}catch(Exception e){}
		threadA.stop=true;
		threadB.stop=true;
	
}
}

class ThreadA extends Thread{
	public boolean stop =false;
	public boolean work =true;
	
	public void run(){
		while(!stop){
			if(work){
				System.out.println("threadA 작업 내용");
			}else{
				Thread.yield();
			}
		}
		System.out.println("Thread terminated");
	}
}
class ThreadB extends Thread{
	public boolean stop =false;
	public boolean work =true;
	
	public void run(){
		while(!stop){
			if(work){
				System.out.println("threadB 작업 내용");
			}else{
				Thread.yield();
			}
		}
		System.out.println("Thread terminated");
	}
}