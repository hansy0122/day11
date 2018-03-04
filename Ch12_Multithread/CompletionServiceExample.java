package day11.Ch12_Multithread;

import java.util.*;
import java.util.concurrent.*;

public class CompletionServiceExample {
	public static void main(String ar[]){
		ExecutorService executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		//CompletionService 생성
		CompletionService<Integer> completionService=new ExecutorCompletionService<Integer>(executorService);
		
		//스레드 풀에게 작업처리요청
		System.out.println("[작업 처리 요청]");
		
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
		
		
			System.out.println("[처리 완료된 작업 확인]");
			//스레풀의 스레드에서 실행하도록함.
			executorService.submit(new Runnable(){
				@Override
				public void run(){
					while(true){
						try{
							//완료된 작업 가져오기
							Future<Integer> future=completionService.take();
							int value = future.get();
							System.out.println("처리결과="+value);
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
