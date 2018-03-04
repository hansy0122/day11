package day11.Ch12_Multithread;

import java.nio.channels.CompletionHandler;
import java.text.NumberFormat;
import java.util.concurrent.*;

public class CallbackExample {
	private ExecutorService executorService;
	
	public CallbackExample(){
		executorService =Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	}
	//콜백 메소드를 가진 CompletionHAndler 객체 생성
	private CompletionHandler<Integer,Void> callback= new CompletionHandler<Integer,Void> (){
		//작업을 정상처리 완료했을때 호출되는 callback메소드
		@Override
		public void completed(Integer result, Void attachment){
			System.out.println("completed() 실행: "+result);
		}
		//예외발생시 호출되는 콜백 메소드
		@Override 
		public void failed(Throwable exc, Void attachment){
			System.out.println("failed() 실행:"+exc.toString());
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
		example.doWork("3", "삼");
		example.finish();
	}
	
	
}
