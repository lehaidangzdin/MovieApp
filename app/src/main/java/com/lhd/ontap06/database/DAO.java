package com.lhd.ontap06.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.lhd.ontap06.model.db.History;

import java.util.List;

@Dao
public interface DAO {
    @Insert
    void insert(History... history);

    @Query("DELETE FROM History WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM History ORDER BY id DESC")
    List<History> getAll();
}
