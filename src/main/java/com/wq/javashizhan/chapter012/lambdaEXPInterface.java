package com.wq.javashizhan.chapter012;

import java.io.BufferedReader;
import java.io.IOException;

/*
 * 1. ����һ������ʽ�ӿ�
 * */
@FunctionalInterface
public interface lambdaEXPInterface {
	String process(BufferedReader br) throws IOException;
}
