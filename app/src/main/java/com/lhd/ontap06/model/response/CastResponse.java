package com.lhd.ontap06.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lhd.ontap06.model.movieModel.Cast;

import java.util.List;

public class CastResponse {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

}
