package day11.Ch12_Multithread;

import java.awt.Toolkit;
//Thread 하위 클래스로 부터 생성. 
public class BeepPrintExample2 {
		public static void main(String ar[]){
			
			Thread thread =new Thread(){
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
			};
			thread.start();
			
			for(int i=0;i<5;i++){
				System.out.println("beeeeeeeeeeeep!");
				try{
					Thread.sleep(500);
				}catch(Exception e){}
			}
			
		}
}
