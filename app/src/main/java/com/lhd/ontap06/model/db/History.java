package com.lhd.ontap06.model.db;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.lhd.ontap06.BR;


@Entity(tableName = "History")
public class History extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;

    public History(){

    }

    public History(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

}
