package com.wq.javashizhan.chapter012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class lambdaEXP {
	// 2. ��   ����ʽ�ӿ�  ��Ϊ�����Ĳ��� ִ��ĳ����Ϊ
	public static String processFile(lambdaEXPInterface lei) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
			return lei.process(br);
		}
		// return "";
	}
	public static void main(String[] args) throws IOException {
		// 3. ʹ��lambda���ʽ ʵ�ֺ���ʽ�ӿڵľ������  ����Ϊ����������   processFile ������processFile���ø���Ϊ
		String readTwoLine = processFile((BufferedReader br)-> br.readLine() + " " + br.readLine()); 
		// 3. ʹ��lambda���ʽ ʵ�ֺ���ʽ�ӿڵľ������  ����Ϊ����������   processFile ������processFile���ø���Ϊ
		String readOneLine = processFile((BufferedReader br)-> br.readLine()); 
		System.out.println(readTwoLine);
		System.out.println(readOneLine);
	}
}
