package com.example.higherorlower;

public class Country {

    int pop;
    String name;
    int size;

    Country(String name, int pop, int size){
        this.pop = pop;
        this.name = name;
        this.size = size;
    }

    public int getCountryPop(){
        return pop;
    }

    public int getCountrySize(){
        return size;
    }

    public String getCountryName(){
        return name;
    }
}
