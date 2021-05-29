package com.wq.lunwen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class zhuyilijisuan {
    public static void main(String[] args) {
        List<Double> dList = new ArrayList<>();
        Random r = new Random(42);
        double sum = 0;
        for(int i=0; i<5; i++){
            double ran1 = r.nextDouble();
            sum += Math.exp(ran1);
            dList.add(Math.exp(ran1));
        }
        for(int i=0;i<5;i++){
            dList.set(i, dList.get(i)/sum);
            System.out.println(dList.get(i));
        }

    }
}
