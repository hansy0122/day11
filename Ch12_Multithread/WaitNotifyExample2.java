package day11.Ch12_Multithread;

public class WaitNotifyExample2 {
	public static void main(String ar[]){
		DataBox dataBox=new DataBox();
		
		ProducerThread producerThread=new ProducerThread(dataBox);
		ConsumerThread consumerThread=new ConsumerThread(dataBox);
		producerThread.start();
		consumerThread.start();
		
	}
}

class DataBox {
	private String data;

	public synchronized String getData() {
		// data가 null 이면 소비자 스레드를 일시정지 상태로 만듬
		if (this.data == null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		String returnValue = data;
		System.out.println("ConsummerThread가 읽은 데이터:" + returnValue);
		data = null;
		notify();
		return returnValue;
	}

	public synchronized void setData(String data) {
		//data가 null이 아니면 생산자 스레드를 일시 정지 상태로 만듬.
		if (this.data != null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		this.data=data;
		System.out.println("ProducerTread가 생성한 데이터:" + data);
		notify();
	}
}

class ProducerThread extends Thread{
	private DataBox dataBox;
	
	public ProducerThread(DataBox databox){
		this.dataBox=databox;
	}
	public void run(){
		for(int i=1;i<4;i++){
			String data="Data"+i;
			dataBox.setData(data);
		}
	}
}


class ConsumerThread extends Thread{
	private DataBox dataBox;
	
	public ConsumerThread(DataBox databox){
		this.dataBox=databox;
	}
	public void run(){
		for(int i=1;i<7;i++){
			String data=dataBox.getData();
		}
	}
}