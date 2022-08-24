package com.lhd.ontap06.model.modelzip;

public class ModelZip2<T, T1> {
    private T res;
    private T1 res1;

    public ModelZip2(T res, T1 res1) {
        this.res = res;
        this.res1 = res1;
    }

    public ModelZip2() {
    }

    public T getRes() {
        return res;
    }

    public void setRes(T res) {
        this.res = res;
    }

    public T1 getRes1() {
        return res1;
    }

    public void setRes1(T1 res1) {
        this.res1 = res1;
    }
}
