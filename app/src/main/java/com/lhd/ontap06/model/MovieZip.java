package com.lhd.ontap06.model;

public class MovieZip<T> {
    private T t;

    public MovieZip(T t) {
        this.t = t;
    }

    public MovieZip() {
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
