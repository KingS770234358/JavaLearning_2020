package com.wq.javashizhan.chapter03;

public class Apple {
    private Color color;
    private Integer weight;
    public Apple(Color c, Integer w){
        this.color = c;
        this.weight = w;
    }
    public Color getColor() {
        return color;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color=" + color +
                ", weight=" + weight +
                '}';
    }
}
