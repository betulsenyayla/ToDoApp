package com.example.todoapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todoapp.entity.Yapilacaklar;

@Database(entities = {Yapilacaklar.class},version = 1)
public abstract class Veritabani extends RoomDatabase {
    public abstract YapilacaklarDao yapilacaklarDao();
    public static Veritabani INSTANCE;

    public static Veritabani veritabaniErisim(Context context) {
        if(INSTANCE == null) {
            synchronized (Veritabani.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Veritabani.class, "yapilacaklar.sqlite").createFromAsset("yapilacaklar.sqlite").build();
            }
        }
        return INSTANCE;
    }

}
