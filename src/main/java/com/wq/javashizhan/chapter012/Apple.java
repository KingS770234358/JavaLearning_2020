package com.wq.javashizhan.chapter012;

public class Apple {

	Color color;
	Integer weight;
	public Apple(int weight, Color color){
		this.color = color;
		this.weight = weight;
	}
	Color getColor() {
		return color;
	}
	Integer getWeight() {
		return this.weight;
	}
	public String toString() {
		return this.color.toString()+" "+this.weight;
	}

}
