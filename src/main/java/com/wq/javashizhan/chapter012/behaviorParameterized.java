package com.wq.javashizhan.chapter012;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class behaviorParameterized {

	public static List<Apple> filterGreenApple(List<Apple> inventory){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory) {
			if(Color.GREEN.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}
	
	
	public static  List<Apple> filterApples(List<Apple> inventory, FilterApple fa){
		List<Apple> result = new ArrayList<>();
		for(Apple apple: inventory) {
			if(fa.test(apple)) {
				//System.out.println(apple.toString());
				result.add(apple);
			}
		}
		return result;
	}
	
	/* �Ѿ����ƻ�������� ����� T ���� */
	public static <T> List<T> FilterAppleAbstract(List<T> inventory, FilterAppleAbstract<T> fa){
		List<T> result = new ArrayList<>();
		for(T t: inventory) {
			if(fa.test(t)) {
				//System.out.println(apple.toString());
				result.add(t);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		/* ����ƻ��������� */
		List<Apple> inventory = Arrays.asList(new Apple(80, Color.GREEN),
											new Apple(155, Color.GREEN),
											new Apple(120, Color.RED));
		/* ʹ�������� ���˳���������150��ƻ�� */
		List<Apple> heavyApples = filterApples(inventory,new FilterApple() {
			public Boolean test(Apple apple) {
				return apple.getWeight()>150;
			}
		});
		for(Apple a : heavyApples) {
			System.out.println(a);
		}
		/* ʹ����һ�������� ���˳���ɫΪ��ɫ��ƻ�� */
		List<Apple> redApples = filterApples(inventory,new FilterApple() {
			public Boolean test(Apple apple) {
				return Color.RED.equals(apple.getColor());
			}
		});
		for(Apple a : redApples) {
			System.out.println(a);
		}
		/* ʹ��lambda���ʽ ��д�ӿ�ʵ���� */
		List<Apple> heavyAndGreen = filterApples(inventory, (Apple apple)-> apple.getColor().equals(Color.GREEN)&&apple.getWeight()%2==0);
		for(Apple a : heavyAndGreen) {
			System.out.println(a);
		}
		
	}
}
