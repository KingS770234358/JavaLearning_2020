package com.wq.javashizhan.chapter012;

public class lambdaPuzzle {
	public final int value = 4;
	public void doIt() {
		int value = 6;
		Runnable r = new Runnable() {
			public final int value = 5;
			public void run() {
				int value = 10;
				System.out.println(this.value);  // �����ӡ�� �� r ��value ���Դ�ӡ��5
			}
		};
		r.run();
	}
	public static void main(String[] args) {
		lambdaPuzzle lp = new lambdaPuzzle();
		lp.doIt();
	}

}
