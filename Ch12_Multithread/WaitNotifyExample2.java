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
		// data�� null �̸� �Һ��� �����带 �Ͻ����� ���·� ����
		if (this.data == null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		String returnValue = data;
		System.out.println("ConsummerThread�� ���� ������:" + returnValue);
		data = null;
		notify();
		return returnValue;
	}

	public synchronized void setData(String data) {
		//data�� null�� �ƴϸ� ������ �����带 �Ͻ� ���� ���·� ����.
		if (this.data != null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		this.data=data;
		System.out.println("ProducerTread�� ������ ������:" + data);
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