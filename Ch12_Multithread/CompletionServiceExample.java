package day11.Ch12_Multithread;

import java.util.*;
import java.util.concurrent.*;

public class CompletionServiceExample {
	public static void main(String ar[]){
		ExecutorService executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		//CompletionService ����
		CompletionService<Integer> completionService=new ExecutorCompletionService<Integer>(executorService);
		
		//������ Ǯ���� �۾�ó����û
		System.out.println("[�۾� ó�� ��û]");
		
			completionService.submit(new Callable<Integer>(){
				@Override
				public Integer call() throws Exception{
					int sum=0;
					for(int i=1;i<=10;i++){
						sum+=i;
					}
					return sum;
				}
			});
			completionService.submit(new Callable<Integer>(){
				@Override
				public Integer call() throws Exception{
					int sum=0;
					for(int i=1;i<=11;i++){
						sum+=i;
					}
					return sum;
				}
			});
			completionService.submit(new Callable<Integer>(){
				@Override
				public Integer call() throws Exception{
					int sum=0;
					for(int i=1;i<=12;i++){
						sum+=i;
					}
					return sum;
				}
			});
		
		
			System.out.println("[ó�� �Ϸ�� �۾� Ȯ��]");
			//����Ǯ�� �����忡�� �����ϵ�����.
			executorService.submit(new Runnable(){
				@Override
				public void run(){
					while(true){
						try{
							//�Ϸ�� �۾� ��������
							Future<Integer> future=completionService.take();
							int value = future.get();
							System.out.println("ó�����="+value);
						}catch(Exception e){break;}
					}
				}
			});
			
			
			try{
				Thread.sleep(3000);
			}catch(Exception e){
				
			}
			executorService.shutdownNow();
			
	}
}
