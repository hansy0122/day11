package day11.Ch12_Multithread;

import java.awt.Toolkit;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

//원래 extends Thread는 비추 다중상속이 불가능하므로 다른 유용한 것을 상속받는 것이 더 좋음.

public class Alarm {
	public static void main(String ar[]) throws IOException {
		// 시계를 우선 구현함.
		// 비프 사운드이용
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int x;
		System.out.println("원하시는 기능을 치시오 1.알람설정 2.타이머");
		x = Integer.parseInt(in.readLine());

		if (x == 1) {

			System.out.println("알람을 울리게 할 시간을 치세요");
			int h = Integer.parseInt(in.readLine());
			System.out.println("알람을 울리게 할 분을 치세요");
			int m = Integer.parseInt(in.readLine());
			System.out.println("알람을 울리게 할 초를 치세요");
			int s = Integer.parseInt(in.readLine());

			LocalDateTime inputTime = LocalDateTime.of(2016, 11, 19, h, m, s);
			
			while (true) {
				LocalDateTime now = LocalDateTime.now();
				if (inputTime.equals(now)) {

					System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
					Sound sound = new Sound();
					sound.start();
					break;
				}
			}
		} else if (x == 2) {
			Timer timer = new Timer();
			timer.start();
		}

	}
}

class Timer extends Thread {
	long second;
	private String message;

	void setSecond(long second) {
		this.second = second * 1000;
	}

	void setMessage(String s) {
		this.message = s;
	}

	public void run() {
		setSecond(3);
		setMessage("출근 해야지");
		try {
			Thread.sleep(second);
		} catch (Exception e) {
		}
		System.out.println(message);
		Sound sound = new Sound();
		sound.run();
	}
}

class Sound extends Thread {
	Toolkit tk = Toolkit.getDefaultToolkit();

	public void run() {
		while (true) {
			tk.beep();
			try {
				Thread.sleep(4000);
			} catch (Exception e) {
			}
		}
	}
}