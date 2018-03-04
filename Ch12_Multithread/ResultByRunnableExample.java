package day11.Ch12_Multithread;

import java.util.*;
import java.util.concurrent.*;

public class ResultByRunnableExample {
	public static void main(String ar[]) {
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		System.out.println("[�۾� ó�� ��û]");
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
		
		Result result = new Result(); // �� ���� �۾� ó���� ��û
		Runnable task1 = new Task(result);
		Runnable task2 = new Task(result);
		Future<Result> future1 = executorService.submit(task1, result);
		Future<Result> future2 = executorService.submit(task2, result);
		System.out.println("ó�� ���1:" +result.accumValue);
		try {
			result = future1.get();
			System.out.println("ó�� ���2:" +result.accumValue); // 55 Ȥ�� 110�� ���ü��ִµ� 55�� ������ task1�� ��������� ����̰� 110�� ������ task2�� ���� ����� ����̴�.
			result = future2.get();
			System.out.println("ó�� ���3:" +result.accumValue);
			System.out.println("�۾� ó�� �Ϸ�");
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���࿹�� �߻���:" + e.getMessage());
		}
		executorService.shutdown();
	}
}

class Result {
	// ó�� ����� �����ϴ� ResultŬ����. ���� ��ü�� ����
	int accumValue;

	synchronized void addValue(int value) {
		accumValue += value;
	}
}