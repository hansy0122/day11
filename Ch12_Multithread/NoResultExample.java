package day11.Ch12_Multithread;

import java.util.concurrent.*;

public class NoResultExample {
	public static void main(String ar[]) {
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		System.out.println("작업 처리 요청");
		Runnable runnable = new Runnable() {
			public void run() {
				int sum = 0;
				for (int i = 0; i < 11; i++) {
					sum += i;
				}
				System.out.println("처리 결과 =" + sum);
			}
		};
		Future future = executorService.submit(runnable);

		//mainThread에서 실행 future.get()을 실행함으로써 blocking으로 인해 코드실행이 멈추는 것을 막음. 
		/*스레드 풀내에 새로운 thead를 이용하여 future.get()을 실행할수도있음.
		 * executorService.submit(new Runnable(){
		@Override
		public void run(){

		try {
			future.get();
			System.out.println("작업 처리 완료");
		} catch (Exception e) {
			System.err.println("작업중 에러" + e.getMessage());
		}
		}
		}
		*/
		try {
			future.get();
			System.out.println("작업 처리 완료");
		} catch (Exception e) {
			System.err.println("작업중 에러" + e.getMessage());
		}
		executorService.shutdown();
	}
}
