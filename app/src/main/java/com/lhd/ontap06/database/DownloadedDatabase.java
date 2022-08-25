//package com.lhd.ontap06.database;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//@Database(entities = {Downloaded.class}, version = 1, exportSchema = false)
//public abstract class DownloadedDatabase extends RoomDatabase {
//
//    public static final String DATABASE_NAME = "Downloaded.db";
//    private static DownloadedDatabase instance;
//
//    public static synchronized DownloadedDatabase getInstance(Context context) {
//        if (instance == null) {
//            instance = Room.databaseBuilder(context.getApplicationContext(), DownloadedDatabase.class, DATABASE_NAME)
//                    .allowMainThreadQueries()
//                    .build();
//        }
//        return instance;
//    }
//
//    public abstract DAO daoDB();
//}
