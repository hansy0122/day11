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

// ������ü�� ���� class
class WorkObject {
	//�׻� ����ȭ �޼ҵ� Ȥ�� ����ȭ ��� �������� notify�� wait�� �����ϴ�. 
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