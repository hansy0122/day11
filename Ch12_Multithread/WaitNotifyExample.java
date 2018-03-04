package day11.Ch12_Multithread;

public class WaitNotifyExample {
	public static void main(String args[]){
		WorkObject sharedObject=new WorkObject();
		ThreadAA threadA=new ThreadAA(sharedObject);
		ThreadBB threadB=new ThreadBB(sharedObject);
		
		threadA.start();
		threadB.start();
	}
}

// 공유객체를 위한 class
class WorkObject {
	//항상 동기화 메소드 혹은 동기화 블록 내에서만 notify와 wait가 가능하다. 
	public synchronized void methodA() {
		System.out.println("ThreadAA's MethodA operates!");
		notify();
		try{
			wait();
		}catch(InterruptedException e){}
	}

	public synchronized void methodB() {
		System.out.println("ThreadBB's MethodB operates!");
		notify();
		try{
			wait();
		}catch(InterruptedException e){}
	}
}

class ThreadAA extends Thread{
	private WorkObject workobject;
	
	public ThreadAA(WorkObject workObject){
		this.workobject=workObject;
	}
	
	@Override
	public void run(){
		for(int i=0;i<10;i++){
			workobject.methodA();
		}
	}
}

class ThreadBB extends Thread{
	private WorkObject workobject;
	
	public ThreadBB(WorkObject workObject){
		this.workobject=workObject;
	}
	
	@Override
	public void run(){
		for(int i=0;i<10;i++){
			workobject.methodB();
		}
	}
}