package day11.Ch12_Multithread;

public class PriorityExample {
	/*��Ƽ ������ ���α׷������� ��������� ��ü�� �����ؼ� �۾��ؾ� �ϴ� ��찡 �ִ�.*/
	
	public static void main(String ar[]){
		for(int i=1;i<11;i++){
			Thread thread =new CalcThread("thread"+i);
			if(i !=10){
				thread.setPriority(Thread.MIN_PRIORITY);
			} else {
				thread.setPriority(Thread.MAX_PRIORITY);
			}
			thread.start();
		}
	}
}

 class CalcThread extends Thread{
	CalcThread(String name){
		setName(name);
	}
	
	public void run(){
		for(int i=0; i<Integer.MAX_VALUE; i++){
		}
		System.out.println(getName());
	}
}
 

