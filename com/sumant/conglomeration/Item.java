package com.sumant.conglomeration;

import java.util.Objects;

public class Item {
    //To identify the item, by it's number
    int itemNumber;

    //Weight is in Kilo Grams and Grams.
    double weight;

    //Cost is in Euros.
    double cost;


    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "Item{" +
                "itemNumber=" + itemNumber +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item product = (Item) o;
        return itemNumber == product.itemNumber &&
                Double.compare(product.weight, weight) == 0 &&
                Double.compare(product.cost, cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemNumber, weight, cost);
    }
}
