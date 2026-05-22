package com.pluralsight.models;

public class Drink {

    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    private Size size;
    private String flavor;

    public Drink(Size size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

}
