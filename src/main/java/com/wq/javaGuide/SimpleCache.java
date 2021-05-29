package com.wq.javaGuide;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleCache<K, V> extends LinkedHashMap<K, V> {
    private static final int MAX_NODE_NUM = 100; // 维护的最大数量

    private int limit;

    public SimpleCache(){// 无参构造函数 直接以最大维护数量为限
        this(MAX_NODE_NUM);
    }
    public SimpleCache(int limit){
        super(limit, 0.75f, true);  // 【查】按访问顺序维护双向链表 把最新访问的元素放在队尾
        this.limit = limit;
    }
    // 增
    public V save(K key, V value){
        return put(key, value);
    }
    // 查
    public V getOne(K key){
        return get(key);
    }
    // 存在？
    public boolean exist(K key){
        return containsKey(key);
    }

    /**
     * 判断节点数是否超限
     * @param eldest
     * @return 超限返回 true，否则返回 false
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > limit; // 【增】 当超过界限的时候 删除最少访问的元素
    }
}
