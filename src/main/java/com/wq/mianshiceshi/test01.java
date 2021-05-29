package com.wq.mianshiceshi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 1.声明用于测试的类
class DemoTest {
    private int num;
    DemoTest() {
    }
    DemoTest(int n) {
        this.num=n;
    }

}
public class test01 {
    private static char c;
    public static void main(String[] args) {
    /*
        // hashcode equal 引用传递等例子
        DemoTest d1 = new DemoTest(); // d1的hashcode：381259350
        DemoTest d2 = new DemoTest(); // d2的hashcode：664223387
        DemoTest d3 = d1;             // d3的hashcode：381259350
        DemoTest d4 = new DemoTest(11);// d4的hashcode：824909230
        DemoTest d5 = new DemoTest(11);// d5的hashcode：122883338
        DemoTest d6 = d4;                 // d6的hashcode：824909230
        System.out.println("d1和d2相等吗："+d1.equals(d2)); // false
        System.out.println("d1和d3相等吗："+d1.equals(d3)); // true
        System.out.println("d4和d5相等吗："+d4.equals(d5)); // false
        System.out.println("d4和d6相等吗："+d4.equals(d6)); // true

        // 2. String 是不可修改的例子 StringBuffer
        StringBuffer str = new StringBuffer("hello");
        System.out.println(str.hashCode());
        str = str.replace(0,1, "a");
        System.out.println(str.hashCode());
        System.out.println(str);
        int a;
        // 3.c定义为私有静态char型 默认值
        System.out.println("1"+"\u0000"+"2");

        // 4. 带标签的for循环
        inner:
        for( int i = 0 ; i<3 ;i++ ){
            System.out.println("i="+i);
            for( int j =0 ; j<5; j++ ){
                if( j ==  3 )
                    // continue inner;
                    break inner;
                System.out.println("j="+j);
            }
        }

     */
        // 5. 正则表达式
        String str = "成都市(成华区)(武侯区)(高新区)";
        Pattern p = Pattern.compile(".*?(?=\\()");
        Matcher m = p.matcher(str);
        if(m.find()){
            System.out.println(m.group(1));
        }

    }
}
