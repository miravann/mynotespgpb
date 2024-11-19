package com.example.mynotes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Notes")
public class Note {

    @PrimaryKey (autoGenerate = true)
    private int id;
    private String title;
    private String desc;
    private String date;

    public int getId(){return id; }
    public void setId(int id) {this.id = id; }

    public String getTitle(){return title; }
    public void setTitle(String title) {this.title = title; }

    public String getDesc(){ return desc;}
    public void setDesc(String desc) {this.desc = desc; }

    public String getDate(){return date;}
    public void setDate(String date) {this.date = date; }

    }



