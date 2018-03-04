package day11.Ch12_Multithread;

import java.util.concurrent.*;

public class ResultByCallableExample {
	public static void main(String[] ar){
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	System.out.println("작업처리 요청");
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
	
	//blocking으로 인해 코드실행이 멈추는 것을 위해서 mainThrad에서 future.get()을 실행중.. 스레드 풀내의 새로운 thread나 새로운 thraed를 생성하여 실행하여도 무관.
	try{
		int sum= future.get();
		System.out.println("처리결과 "+sum);
		System.out.println("작업 처리 완료");
	}catch(Exception e){
		System.out.println("실행 예외 발생함"+ e.getMessage());
	}
	executorService.shutdown();
	}
}
