package day11.Ch12_Multithread;

import java.awt.Toolkit;
//Thread class肺 何磐 流立积己
public class BeepPrintExample {
	public static void main(String ar[]){
		
		Thread thread =new Thread(new Runnable(){
			@Override
			public void run(){
				Toolkit tk=Toolkit.getDefaultToolkit();
				for(int i=0;i<5;i++){
					
				
				tk.beep();
				try{
					Thread.sleep(1000);
				}catch(Exception e){}
				}
			}
		});
		thread.start();
		
		for(int i=0;i<5;i++){
			System.out.println("beeeeeeeeeeeep!");
			try{
				Thread.sleep(500);
			}catch(Exception e){}
		}
		
	}
}
