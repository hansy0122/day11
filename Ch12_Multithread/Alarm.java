package day11.Ch12_Multithread;

import java.awt.Toolkit;
import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

//���� extends Thread�� ���� ���߻���� �Ұ����ϹǷ� �ٸ� ������ ���� ��ӹ޴� ���� �� ����.

public class Alarm {
	public static void main(String ar[]) throws IOException {
		// �ð踦 �켱 ������.
		// ���� �����̿�
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int x;
		System.out.println("���Ͻô� ����� ġ�ÿ� 1.�˶����� 2.Ÿ�̸�");
		x = Integer.parseInt(in.readLine());

		if (x == 1) {

			System.out.println("�˶��� �︮�� �� �ð��� ġ����");
			int h = Integer.parseInt(in.readLine());
			System.out.println("�˶��� �︮�� �� ���� ġ����");
			int m = Integer.parseInt(in.readLine());
			System.out.println("�˶��� �︮�� �� �ʸ� ġ����");
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
		setMessage("��� �ؾ���");
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