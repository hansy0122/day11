package day11.Ch12_Multithread;

import java.nio.channels.CompletionHandler;
import java.text.NumberFormat;
import java.util.concurrent.*;

public class CallbackExample {
	private ExecutorService executorService;
	
	public CallbackExample(){
		executorService =Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	//�ݹ� �޼ҵ带 ���� CompletionHAndler ��ü ����
	private CompletionHandler<Integer,Void> callback= new CompletionHandler<Integer,Void> (){
		//�۾��� ����ó�� �Ϸ������� ȣ��Ǵ� callback�޼ҵ�
		@Override
		public void completed(Integer result, Void attachment){
			System.out.println("completed() ����: "+result);
		}
		//���ܹ߻��� ȣ��Ǵ� �ݹ� �޼ҵ�
		@Override 
		public void failed(Throwable exc, Void attachment){
			System.out.println("failed() ����:"+exc.toString());
		}
	};
	
	public void doWork(final String x, final String y){
		Runnable task= new Runnable(){
			@Override
			public void run(){
				try{
					int intX= Integer.parseInt(x);
					int intY= Integer.parseInt(y);
					int result= intX+intY;
					callback.completed(result, null);
				}catch(NumberFormatException e){
					callback.failed(e, null);
				}
			}
		};
		executorService.submit(task);
	}
	public void finish(){
		executorService.shutdown();
	}
	
	public static void main(String ar[]){
		CallbackExample example =new CallbackExample();
		example.doWork("3", "3");
		example.doWork("3", "��");
		example.finish();
	}
	
	
}
