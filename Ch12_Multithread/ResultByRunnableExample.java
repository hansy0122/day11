package day11.Ch12_Multithread;

import java.util.*;
import java.util.concurrent.*;

public class ResultByRunnableExample {
	public static void main(String ar[]) {
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		System.out.println("[작업 처리 요청]");
		class Task implements Runnable {
			Result result;

			Task(Result result) {
				this.result = result;
			}

			@Override
			public void run() {
				int sum = 0;
				for (int i = 1; i < 11; i++) {
					sum += i;
				}
				result.addValue(sum);
			}
		}
		
		Result result = new Result(); // 두 가지 작업 처리를 요청
		Runnable task1 = new Task(result);
		Runnable task2 = new Task(result);
		Future<Result> future1 = executorService.submit(task1, result);
		Future<Result> future2 = executorService.submit(task2, result);
		System.out.println("처리 결과1:" +result.accumValue);
		try {
			result = future1.get();
			System.out.println("처리 결과2:" +result.accumValue); // 55 혹은 110이 나올수있는데 55가 나오면 task1이 먼저실행된 경우이고 110이 나오면 task2가 먼저 실행된 경우이다.
			result = future2.get();
			System.out.println("처리 결과3:" +result.accumValue);
			System.out.println("작업 처리 완료");
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("실행예외 발생함:" + e.getMessage());
		}
		executorService.shutdown();
	}
}

class Result {
	// 처리 결과를 저장하는 Result클래스. 공유 객체로 사용됨
	int accumValue;

	synchronized void addValue(int value) {
		accumValue += value;
	}
}