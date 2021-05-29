package com.wq.AnnotationAndReflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 通过反射操作泛型
 */
public class ReflectTest07 {

    // 1.参数里有泛型
    public void test01(Map<String,User> map, List<User> userList){
        System.out.println("test01");
    }
    // 2.返回值有泛型
    public Map<String,User> test02(){
        System.out.println("test02");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        // 1.1获得方法
        Method method = ReflectTest07.class.getMethod("test01",Map.class,List.class);
        // 1.2获得所有泛型类型的参数
        Type[] genericParamTypes = method.getGenericParameterTypes();
        for(Type gpt: genericParamTypes){
            System.out.println(gpt);
            //java.util.Map<java.lang.String, com.wq.AnnotationAndReflection.User>
            //java.util.List<com.wq.AnnotationAndReflection.User>
            //分解出泛型里实际包含的所有类型
            if(gpt instanceof ParameterizedType){
                // 泛型里所包含的所有类型
                Type[] actualTypeArguments = ((ParameterizedType)gpt).getActualTypeArguments();
                for (Type ata:actualTypeArguments){
                    System.out.println(ata);
                }
            }
        }

        // 2.1 获得方法
        Method method2 = ReflectTest07.class.getMethod("test02",null);
        // 2.2 得到返回值类型的泛型
        Type genericParamTypes2 = method2.getGenericReturnType();
        if(genericParamTypes2 instanceof ParameterizedType){
            // 分解出泛型里所包含的所有类型
            Type[] actualTypeArguments = ((ParameterizedType)genericParamTypes2).getActualTypeArguments();
            for (Type ata:actualTypeArguments){
                System.out.println(ata);
            }
        }
    }
}
