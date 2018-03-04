package day11.Ch12_Multithread;

import java.awt.Toolkit;

public class Make_Runnable {
public static void main(String ar[]){
	Beep beep=new Beep();
	Thread thread=new Thread(beep);
	thread.start();
}
}

class Beep implements Runnable{
	public void run(){
		Toolkit tk=Toolkit.getDefaultToolkit();
		tk.beep();
	}
}
