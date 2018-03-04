package day11.Ch12_Multithread;

public class JoinExample {
	public static void main(String ar[]){
		SumThread sumthread=new SumThread();
		sumthread.setSum(1);
		
		sumthread.run();
		//SumThread가 끝난 후에 mainThread가 작동함
		try{
			sumthread.join();
		}catch(Exception e){}
		
		System.out.println("1+(1~100)="+sumthread.getSum());
	}
}

class SumThread extends Thread{
	private long sum;
	
	public long getSum(){
		return sum;
	}
	
	public void setSum(long sum){
		this.sum=sum;
	}
	
	public void run(){
		for(int i=0;i<=100;i++){
			sum+=i;
		}
	}
	
}