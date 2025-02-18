package com.example.mynotes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;
@Dao
public interface NoteDao {
    @Insert
    void insert (Note note);

    @Update
    void update (Note note);

    @Delete
    void delete (Note note);

    @Query("SELECT*FROM Notes")
    List<Note> getAllNotes();
}
