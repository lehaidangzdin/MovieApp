package com.lhd.ontap06.model.modelzip;

public class ModelZip3<T, T1, T2> {
    private T res;
    private T1 res1;
    private T2 res2;

    public ModelZip3() {
    }

    public ModelZip3(T res, T1 res1, T2 res2) {
        this.res = res;
        this.res1 = res1;
        this.res2 = res2;
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

    public T2 getRes2() {
        return res2;
    }

    public void setRes2(T2 res2) {
        this.res2 = res2;
    }


}
