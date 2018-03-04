package day11.Ch12_Multithread;

import java.util.*;

public class ThreadInfoExample {
	public static void main(String ar[]){
		AutoSaveThread autosaveThread=new AutoSaveThread();
		autosaveThread.setName("AUTOSAVETHREAD");
		autosaveThread.setDaemon(true);
		autosaveThread.start();
		
		Map<Thread, StackTraceElement[]> map=Thread.getAllStackTraces();
		Set<Thread> threads =map.keySet();
		for(Thread thread:threads){
			System.out.println("name: "+ thread.getName()+((thread.isDaemon()?"(Daemon)" :"(Main)")));
			System.out.println("\t"+ "소속그룹: "+ thread.getThreadGroup().getName());
			System.out.println();
		}
		}
}
