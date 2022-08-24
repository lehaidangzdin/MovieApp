package com.lhd.ontap06.model.modelzip;

public class ModelZip4<T, T2, T3, T4> {
    private T res1;
    private T2 res2;
    private T3 res3;
    private T4 res4;

    public ModelZip4(T res1, T2 res2, T3 res3, T4 res4) {
        this.res1 = res1;
        this.res2 = res2;
        this.res3 = res3;
        this.res4 = res4;
    }

    public T getRes1() {
        return res1;
    }

    public void setRes1(T res1) {
        this.res1 = res1;
    }

    public T2 getRes2() {
        return res2;
    }

    public void setRes2(T2 res2) {
        this.res2 = res2;
    }

    public T3 getRes3() {
        return res3;
    }

    public void setRes3(T3 res3) {
        this.res3 = res3;
    }

    public T4 getRes4() {
        return res4;
    }

    public void setRes4(T4 res4) {
        this.res4 = res4;
    }
}
