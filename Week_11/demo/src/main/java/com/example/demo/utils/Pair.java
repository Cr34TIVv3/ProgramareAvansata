package com.example.demo.utils;

import com.example.demo.models.Person;

public class Pair implements Comparable{
    private Person key;
    private Integer value;

    @Override
    public int compareTo(Object o) {
        Pair p = (Pair)o;
        return p.getValue()-value;
    }



    public Pair(Person key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public int compareTo(Pair o) {
        return o.getValue() - value;
    }

    public Person getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
