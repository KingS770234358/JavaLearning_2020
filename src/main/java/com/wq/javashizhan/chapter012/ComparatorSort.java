package com.wq.javashizhan.chapter012;

import java.awt.Button;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

/*
 * ��������T
 * public interface Comparator<T>{
 * 		int compare(T o1, T o2);
 * }
 * 
 * ��Ҫʵ�� compare ����
 * 
 * public interface Runnable{
 * 		void run();
 * }
 * ��Ҫʵ��run����
 * 
 * ����ֵ����T
 * public interface callable<V>{
 * 		V call();
 * }
 * 
 * */
public class ComparatorSort {
	public static void main(String[] args) throws Exception {
		/* ����ƻ��������� */
		List<Apple> inventory = Arrays.asList(new Apple(80, Color.GREEN),
											new Apple(155, Color.GREEN),
											new Apple(120, Color.RED));
		
		/* 1.ʹ��������ʵ��compare�ӿ� */
		inventory.sort(new Comparator<Apple>() {
			public int compare(Apple a1, Apple a2) {
				return a1.getWeight().compareTo(a2.getWeight());
			}
		});
		for(Apple a: inventory) {
			System.out.println(a);
		}
		System.out.println("================================================");
		/* ��lambda���ʽʵ�� */
		inventory.sort((Apple a1, Apple a2) -> a2.getWeight().compareTo(a1.getWeight()));
		for(Apple a: inventory) {
			System.out.println(a);
		}
		
		/* 2.ʹ��lambda���ʽ�����߳� */
		/*
			Thread t = new Thread(new Runnable(){
				public void run(){
					System.out.println("hello");
				}
			});
		*/
		Thread t = new Thread(    ()->System.out.println("Hello world")   );
		t.start();
		
		/* 3.ʹ��lambda���ʽʵ��callable�ӿ� �����߳�ִ�н�� 
		 * 
		 * 		ExecutorService es = Executors.newCachedThreadPool();
		 *		Future<String> threadName = es.submit(new Callable<String>() {
		 * 			@Override
		 *			public String call() throws Exception{
		 *				return Thread.currentThread().getName();
		 *			}
		 *		});	
		 * */
		ExecutorService es = Executors.newCachedThreadPool();
		Future<String> threadName = es.submit(()->Thread.currentThread().getName());
		System.out.println(threadName.get());
		
		/* 4.GUI�¼�����  Ҫʹ��javafx��*/
		/*
		Button button = new Button("Send");  // ������ ��ʼ�� ����
		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				label.setText("Send!");
			}
		});
		*/		
		
	}
}
