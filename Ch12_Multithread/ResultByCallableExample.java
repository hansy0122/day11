package day11.Ch12_Multithread;

import java.util.concurrent.*;

public class ResultByCallableExample {
	public static void main(String[] ar){
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	System.out.println("�۾�ó�� ��û");
	Callable<Integer> task =new Callable<Integer>(){
		public Integer call() throws Exception{
			int sum=0;
			for(int i=0;i<11;i++){
				sum+=i;
			}
			return sum;
		}
	};
	Future<Integer> future =executorService.submit(task);
	
	//blocking���� ���� �ڵ������ ���ߴ� ���� ���ؼ� mainThrad���� future.get()�� ������.. ������ Ǯ���� ���ο� thread�� ���ο� thraed�� �����Ͽ� �����Ͽ��� ����.
	try{
		int sum= future.get();
		System.out.println("ó����� "+sum);
		System.out.println("�۾� ó�� �Ϸ�");
	}catch(Exception e){
		System.out.println("���� ���� �߻���"+ e.getMessage());
	}
	executorService.shutdown();
	}
}
