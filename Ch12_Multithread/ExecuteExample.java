package day11.Ch12_Multithread;

import java.util.concurrent.*;

public class ExecuteExample {
	public static void main(String ar[]) throws Exception{
		//�ִ� ������ ���� 2���� ������ Ǯ ����
		ExecutorService executorService=Executors.newFixedThreadPool(2);
		for(int i=0;i<10;i++){
			Runnable runnable =new Runnable(){
				public void run(){
					//������ �� ���� �� �۾� ������ �̸� ���
					//ThreadPoolExcecutor �� �ڳ� ExecutorService�� �θ�. 
					//Executors.newFixedThreadPool()�� ��ü������ ThreadPollExecutor�� return��. �׷��Ƿ� ���� ����ȯ�� �����ϰ� 
					//���� ����ȯ�� ���ؼ� �ڳ��� method�� ��밡����.
					ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
					int poolSize = threadPoolExecutor.getPoolSize();
					String threadName =Thread.currentThread().getName();
					System.out.println("�� ������ ����"+ poolSize);
					System.out.println("������ �̸�"+threadName);
					//���� �߻���Ŵ 
					int value =Integer.parseInt("��");
					
				}
			};
			//�۾�ó����û
			//executor�� ���ܹ߻��� thraed�� ���̰�  ���ο� thread�� ������.
			//executorService.execute(runnable);
			//submit�� ���ܹ߻��� ������� ��������ʰ� �����۾��� ���ؼ� �����. submit�� ����ϴ°��� ������������� ���̱����ؼ� ����
			executorService.submit(runnable);
			Thread.sleep(10);
			
		}
		executorService.shutdown();
	}
}
