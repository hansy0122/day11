package day11.Ch12_Multithread;

import java.util.concurrent.*;

public class NoResultExample {
	public static void main(String ar[]) {
		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		System.out.println("�۾� ó�� ��û");
		Runnable runnable = new Runnable() {
			public void run() {
				int sum = 0;
				for (int i = 0; i < 11; i++) {
					sum += i;
				}
				System.out.println("ó�� ��� =" + sum);
			}
		};
		Future future = executorService.submit(runnable);

		//mainThread���� ���� future.get()�� ���������ν� blocking���� ���� �ڵ������ ���ߴ� ���� ����. 
		/*������ Ǯ���� ���ο� thead�� �̿��Ͽ� future.get()�� �����Ҽ�������.
		 * executorService.submit(new Runnable(){
		@Override
		public void run(){

		try {
			future.get();
			System.out.println("�۾� ó�� �Ϸ�");
		} catch (Exception e) {
			System.err.println("�۾��� ����" + e.getMessage());
		}
		}
		}
		*/
		try {
			future.get();
			System.out.println("�۾� ó�� �Ϸ�");
		} catch (Exception e) {
			System.err.println("�۾��� ����" + e.getMessage());
		}
		executorService.shutdown();
	}
}
