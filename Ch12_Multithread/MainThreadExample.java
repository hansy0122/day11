package day11.Ch12_Multithread;

	// 공유된 객체를 사용하는 Thread는 이렇게 Data가 의도하지않게 변경되는 오류가 발생할수 있다.

public class MainThreadExample {
	public static void main(String ar[]){
		Calculator calculator=new Calculator();
		
		User1 user1=new User1();
		user1.setClaculator(calculator);
		user1.start();
		
		User2 user2=new User2();
		user2.setClaculator(calculator);
		user2.start();
	}

}

	class Calculator {
		private int memory; 
		public int getMemoty(){
			return memory;
		}
		
		public void setMemory(int memory){
			this.memory=memory;
			try{
				Thread.sleep(3000);
			}catch(InterruptedException e){}
			System.out.println(Thread.currentThread().getName()+":"+this.memory);
		}
	}
	
	class User1 extends Thread{
		private Calculator calculator;
		public void setClaculator(Calculator calculator){
			this.setName("User1");
			this.calculator=calculator;
		}
		public void run(){
			calculator.setMemory(100);
		}
	}
	
	class User2 extends Thread{
		private Calculator calculator;
		public void setClaculator(Calculator calculator){
			this.setName("User2");
			this.calculator=calculator;
		}
		public void run(){
			calculator.setMemory(500);
		}
	}