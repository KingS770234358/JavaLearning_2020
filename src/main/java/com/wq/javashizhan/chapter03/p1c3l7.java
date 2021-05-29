package com.wq.javashizhan.chapter03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
// 静态导入 一个类中的某个方法
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/*
* 使用 行为参数化、匿名类、Lambda表达式、方法引用 实现对苹果数组的排序
* List 的 sort 方法
* void sort(Comparator<? super E> c)
* 这是典型的 策略模式 的应用
* */
public class p1c3l7 {

    /* 方式1: Comparator接口具体的实现类 */
    public static class AppleComparator implements Comparator<Apple>{ // 这里用一个参数泛型
        public int compare(Apple a1, Apple a2){
            return a1.getWeight().compareTo(a2.getWeight()); // 从小到大
        }
    }
    public static List<Apple> filterApple(List<Apple> apples, Predicate<Apple> p){
        List<Apple> ret = new ArrayList<Apple>();
        for(Apple a : apples){
            if(p.test(a)) {
                ret.add(a);
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(Color.GREEN, 80),
                new Apple(Color.GREEN, 155),
                new Apple(Color.RED, 120));
        /* 方式1: 直接在sort函数中传入具体的接口实现类 */
        System.out.println("======方式1: 直接在sort函数中传入具体的接口实现类=======");
        inventory.sort(new AppleComparator());
        for(Apple a : inventory){
            System.out.println(a);
        }

        System.out.println("=========用匿名类 实现 sort方法需要的 Comparator 接口实现类======");
        /* 方式2: 用匿名类 实现 sort方法需要的 Comparator 接口实现类*/
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a2.getWeight().compareTo(a1.getWeight()); // 从大到小
            }
        });
        for(Apple a : inventory){
            System.out.println(a);
        }
        System.out.println("======用lambda表达式 实现 sort方法需要的 Comparator 接口实现类=====");
        /* 方式3: 用lambda表达式 实现 sort方法需要的 Comparator 接口实现类*/
//        inventory.sort((Apple a1, Apple a2)
//                ->a1.getWeight().compareTo(a2.getWeight())); // 再从小到大
        inventory.sort((a1, a2)
                ->a1.getWeight().compareTo(a2.getWeight())); // 再从小到大 这里使用lambda根据上下文进行类型推断的特性
        for(Apple a : inventory){
            System.out.println(a);
        }
        System.out.println("===方式4: 用lambda表达式 实现 sort方法需要的Comparator.comparing静态方法 从大到小========");
        /* 方式4: 用lambda表达式 实现 sort方法需要的 Comparable键值
        *  需要使用 Comparator.comparing静态方法 接收一个 Function 来获得Comparable键值
        * */
        inventory.sort(Comparator.comparing(apple -> -apple.getWeight())); // 以Apple的重量作为排序键值 从小到大还是从大到小需要设置
        for(Apple a : inventory){
            System.out.println(a);
        }
        System.out.println("=======方式5: 使用 方法引用的方式 引用Apple类的 getWeight 方法进行排序 从小到大=======");
        /* 方式5: 使用 方法引用的方式 引用Apple类的 getWeight 方法进行排序 从小到大*/
        // 使用静态导入的方法 需要在import 中 静态导入类的方法comparing
        inventory.sort(comparing(Apple::getWeight)); // 以Apple的重量作为排序键值 从小到大还是从大到小需要设置
        for(Apple a : inventory){
            System.out.println(a);
        }
        System.out.println("=============1. 比较器复合============");
        /*
        * lambda表达式的复合
        * 多个表达式可以使用 and | or等逻辑符号进行连接
        * 一个表达式的结果可以作为另一个表达式的输入 f(g(x))
        * */
        /* 比较器复合 */
        List<Apple> inventory2 = Arrays.asList(new Apple(Color.GREEN, 80),
                new Apple(Color.GREEN, 155),
                new Apple(Color.RED, 120),
                new Apple(Color.RED, 155));
        inventory2.sort(comparing(Apple::getWeight).reversed() // 将返回的结果进行逆序 链式编程
                .thenComparing(Apple::getColor) // 这里是当第一条比较规则结果为 相等 的时候 会使用的规则
        );
        for(Apple a : inventory2){
            System.out.println(a);
        }
        System.out.println("=============2. 谓词复合============");
        /**
         * 谓词复合 Predicate
         * and or negate 与或非
         * */
        List<Apple> ret = new ArrayList<Apple>();
        Predicate<Apple> pGreen = (apple) -> apple.getColor()==Color.GREEN;
        ret = filterApple(inventory, pGreen);
        for(Apple r : ret){
            System.out.println("绿色的苹果: " + r);
        }
        Predicate<Apple> pNotGreen = pGreen.negate(); // 链式编程 产生现有条件的 非
        ret = filterApple(inventory, pNotGreen);
        for(Apple r : ret){
            System.out.println("不是绿色的苹果: " + r);
        }
        Predicate<Apple> pGreenAndHeavy = pGreen.and(apple -> apple.getWeight()>150); // 链式编程 与 上另一个条件
        ret = filterApple(inventory, pGreenAndHeavy);
        for(Apple r : ret){
            System.out.println("绿色而且重量不低于150的苹果: " + r);
        }
        Predicate<Apple> pGreenOrLight = pGreen.or(apple -> apple.getWeight()==120); // 链式编程 或 上另一个条件
        ret = filterApple(inventory, pGreenOrLight);
        for(Apple r : ret){
            System.out.println("绿色或者重量不低于120的苹果: " + r);
        }
        /*
        * 函数复合 Function
        * 可以使用  f.andThen(g) 使Function f 的执行结果作为Function g的参数 继续执行产生结果
        * 使用     f.compose(g) 使Function g的执行结果作为Function f的参数 继续执行产生结果
        * */
        Function<Integer, Integer> f = (x) -> x+1;
        Function<Integer, Integer> g = (x) -> x*x;
        Function<Integer, Integer> h1 = f.andThen(g);
        Function<Integer, Integer> h2 = f.compose(g);
        System.out.println(h1.apply(5));
        System.out.println(h2.apply(5));
        // 具体例子： [流水线] 处理字符串
        Function<String, String> ah = p1c3l7::addHeader;
        Function<String, String> af = p1c3l7::addFooter;
        Function<String, String> cp = p1c3l7::checkSpelling;
        Function<String, String> allOp = cp.andThen(ah).andThen(af);
        System.out.println(allOp.apply("wo tai nan le"));

        // 对给定的线性函数在区间内进行积分
        DoubleFunction<Double> lineFunc = p1c3l7::LineFunc;  // DoubleFunction 就是返回Double类型 所有只需要指明参数类型Doiuble
        System.out.println(integer(lineFunc, 3.0, 7.0));

    }

    public static String addHeader(String text){
        return "From Mike, Maria and Kangkang\n" + text;
    }
    public static String addFooter(String text){
        return text + "\nKind regards";
    }
    public static String checkSpelling(String text){
        return text.replaceAll("a","456");
    }
    public static Double LineFunc(Double x){
        return x + 10;
    }
    public static Double integer(DoubleFunction<Double> lineFunc, Double a, Double b){
        //         上底加下底乘高除二
        return (lineFunc.apply(a) + lineFunc.apply(b)) * (b-a) / 2;
    }
}
