package com.wq.javashizhan.chapter03;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import javax.naming.spi.DirStateFactory.Result;

/*
 * 测试 java.util.function 包中常见的函数式接口 
 * Predicate
 * Consumer
 * Function
 * */
public class commonFunctionalInterface {
	/*
	 * Predicate 
	 * 提供test函数
	 * 接受 泛型 参数
	 * 返回Boolean值
	 * */
	/* 过滤出以star开头的字符串 */
	public static <T> List<T> filter(List<T> str, Predicate p){
		List<T> ret = new ArrayList<T>();
		for(T t: str) {
			if(p.test(t)) {
				ret.add(t);
			}
		}
		return ret;
	}
	
	/*
	 * Consumer
	 * 提供accept函数
	 * 接受 泛型 参数
	 * 无返回值 用于访问对象 执行一些操作
	 * */
	public static <T> void addFilter(List<T> ints, Consumer<T> c){
		for(T t: ints) {
			c.accept(t); // 接口抽象操作
		}
	}
	/*
	 * Function
	 * 提供apply函数
	 * 接受 泛型 参数 T
	 * 返回 泛型 参数 R
	 * 
	 * */
	public static <T, R> List<R> addFilter2(List<T> ints, Function<T, R> f){
		List<R> ret = new ArrayList<R>();
		for(T t: ints) {
			ret.add(f.apply(t)); // 抽象接口操作
		}
		return ret;
	}
	public static void main(String[] args) {
		List<String> str = Arrays.asList("start","star","ss","asrr");	
		// 用lambda表达式创建一个函数式接口的实现
		Predicate<String> p = (String s)-> s.startsWith("star");
		List<String> ret = filter(str, p);
		// List<String> ret = filter(str, (String s) -> s.startsWith("star") );
		for(String r: ret) {
			System.out.println(r);
		}
		
		List<Integer> ints = Arrays.asList( 1, 3, 4, 8);
		List<String> strs = Arrays.asList( "sadsad", "sss", "s", "sdfff");
		addFilter(ints, (Integer a)-> 
			// a = a + 1; // 目前只能做访问 不能对ints做修改
			System.out.println(a)
		);
		System.out.println("=========下面将字符串映射成字符串的长度===========");
		List<Integer> ints2 = addFilter2(ints, (Integer a)->{
			return a+1;
		});
		List<Integer> ints3 = addFilter2(strs, a -> {  // 这里【隐式推断】 a的类型为String类型
			return a.length();
		});
		for(Integer i: ints3) {
			System.out.println(i);
		}
		ToIntFunction<String> stringToInt = Integer::parseInt;//(String s)-> Integer.parseInt(s);
		Integer sti = stringToInt.applyAsInt("123456"); // 要调用 函数式接口中 对应的方法进行使用
		System.out.println(sti);
		
	}
}
