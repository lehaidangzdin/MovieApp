package com.lhd.ontap06.model.movieModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lhd.ontap06.constant.Constant;

public class ProductionCompanies {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("logo_path")
    @Expose
    private String logoPath;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("origin_country")
    @Expose
    private String originCountry;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogoPath() {
        return Constant.PATH_IMAGE + logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
}
