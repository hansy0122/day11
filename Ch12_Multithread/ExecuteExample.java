package day11.Ch12_Multithread;

import java.util.concurrent.*;

public class ExecuteExample {
	public static void main(String ar[]) throws Exception{
		//최대 스레드 수가 2개인 스레드 풀 생성
		ExecutorService executorService=Executors.newFixedThreadPool(2);
		for(int i=0;i<10;i++){
			Runnable runnable =new Runnable(){
				public void run(){
					//스레드 총 개수 및 작업 스레드 이름 출력
					//ThreadPoolExcecutor 가 자녀 ExecutorService가 부모. 
					//Executors.newFixedThreadPool()은 자체적으로 ThreadPollExecutor을 return함. 그러므로 강제 형변환이 가능하고 
					//강제 형변환을 통해서 자녀의 method를 사용가능함.
					ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
					int poolSize = threadPoolExecutor.getPoolSize();
					String threadName =Thread.currentThread().getName();
					System.out.println("총 스레드 갯수"+ poolSize);
					System.out.println("스레드 이름"+threadName);
					//예외 발생시킴 
					int value =Integer.parseInt("삼");
					
				}
			};
			//작업처리요청
			//executor은 예외발생시 thraed를 죽이고  새로운 thread를 생서함.
			//executorService.execute(runnable);
			//submit은 예외발생시 스레드는 종료되지않고 다음작업을 위해서 재사용됨. submit을 사용하는것이 생성오버헤더를 줄이기위해서 좋음
			executorService.submit(runnable);
			Thread.sleep(10);
			
		}
		executorService.shutdown();
	}
}
