package com.wq.javashizhan.chapter08;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*
* Arrays.asList 和 List.of 创建的都是静态内部类
* AbstractList 的子类只有重写了 remove(int index) 才可以使用 removeIf() 方法
* 上面两者都没有实现 remove 方法
* */
public class CollectionDemo {
    public static void main(String[] args) {
        // 1.引入
        System.out.println("======Arrays.asList创建固定大小的小列表======");
        List<String> friends = Arrays.asList("Raphael"," Olivia","Thibaut");
        System.out.println(friends);
        // 只能set修改 不能add remove
        friends.set(0,"Mike");
        System.out.println(friends);
        /*
        try{
            friends.add("Raphael");// UnsupportedOperationException
        }catch (Exception e){
            e.printStackTrace();
        }*/
        System.out.println(friends);

        System.out.println("======Arrays.asList创建大小固定的小集合======");
        // Arrays.asList创建大小固定的小集合
        Set<String> friendsSet = new HashSet<String>(Arrays.asList("Raphael"," Olivia","Thibaut"));
        System.out.println(friendsSet);
        Set<String> friendsSet2 = Stream.of("Raphael"," Olivia","Thibaut")
                .collect(Collectors.toSet());
        System.out.println(friendsSet2);

        // 2. 具体api
        System.out.println("======List / Set======"); // 只读列表 不能增删改 安全
        // 创建List.of Set.of(元素不能重复)
        List<String> friends3 = new ArrayList<>(List.of("Raphael"," Olivia","Thibaut"));
        System.out.println(friends3);
        // 删除removeIf
        // 1.迭代删除
        /*
        for(String f: friends3){
            if(f.startsWith("R")){
                try{
                    System.out.println("迭代删除");
                    friends3.remove(f); // 这里不能删除  ConcurrentModificationException
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }*/
        // 2. 迭代器遍历 直接删除
        /*
        Iterator<String> iterator = friends3.iterator(); //ConcurrentModificationException
        for(;iterator.hasNext();){
            String s = iterator.next();
            if(s.startsWith("R")){
                try{
                    // friends3.remove(s); // iterator遍历 Collection对象删除
                    System.out.println("iterator删除");
                    iterator.remove(); // UnsupportedOperationException
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        */
        // 3. removeIf方法删除
        friends3.removeIf(s->s.startsWith("O"));
        System.out.println(friends3);

        // 替换所有
        // 可以使用流映射收集来实现相同的功能
        friends3.replaceAll(s-> s.substring(1)); // 流的映射是创建了一个新对象 而replaceAll是在原有的上面直接修改
        System.out.println(friends3);

        System.out.println("======Map======");
        // Map.of  Map.ofEntries  Map.entySet()
        Map<String,Integer> ages = Map.of("Mike",1,"Jason",2);
        Map<String,Integer> ages2 = Map.ofEntries(Map.entry("Mike",1),
                Map.entry("Jason",2));
        // for 遍历EntrySet()
        for(Map.Entry<String,Integer> entry: ages.entrySet()){
            System.out.println(entry.getKey()+" is "+entry.getValue()+" years old");
        }
        // forEach方法遍历
        ages.forEach((friendsName, friendsAge)-> System.out.println(friendsName+" is "+friendsAge+" years old"));
        // entry流进行排序 comparingByValue comparingByKey
        ages.entrySet().stream().sorted(Map.Entry.comparingByValue())
                // .forEach(System.out::println);
                .forEachOrdered(System.out::println);
        ages.entrySet().stream().sorted(Map.Entry.comparingByKey())
                // .forEach(System.out::println);
                .forEachOrdered(System.out::println);
        // getOrDefault
        Integer age = ages.getOrDefault("oo",100); // 设置 当传入的键不存在的时候 默认的取值
        System.out.println(age);
        Map<String, List<String>> friendsToMovies = new HashMap<>(Map.ofEntries(
                Map.entry("Kang", new ArrayList<String>(Arrays.asList("1","2","3"))),
                Map.entry("Maria", new ArrayList<String>(Arrays.asList("5","6","7"))),
                Map.entry("Jane", new ArrayList<String>(Arrays.asList("11","12","13")))
        ));
        // ==计算模式==
        // 如果 键 不存在 就新建加入
        friendsToMovies.computeIfAbsent("JOJO", name->new ArrayList<String>()).add("jjj");
        System.out.println(friendsToMovies);
        // 如果 键 存在 就计算替换
        friendsToMovies.computeIfPresent("Kang", (name, movies)-> movies).add("xxx");
        /*friendsToMovies.computeIfPresent("Kang", (name, movies)-> {
            movies.add("xxx");
            return movies;
        });*/
        System.out.println(friendsToMovies);
        // compute 必须确保 键 存在
        // friendsToMovies.compute("ioio", (name, movies)-> movies).add("ooo");
        System.out.println(friendsToMovies);

        // ==删除模式==
        // 要删除的键不存在 无变化
        // 要删除的键存在值不存在 无变化
        // 要删除的键值都存在 删除成功
        friendsToMovies.remove("JOJO", new ArrayList<>(Arrays.asList("jjj","sss")));
        friendsToMovies.remove("JOJO"); // 直接按键删除
        System.out.println(friendsToMovies);

        // replaceAll Replace
        friendsToMovies.replace("Kang",  new ArrayList<>(Arrays.asList("jjj","sss")));
        System.out.println(friendsToMovies);
    }
}
