package com.example.architecture_components;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


//Marks the class as a Data Access Object.
//Data Access Objects are the main classes where you define your database interactions.
// They can include a variety of query methods.
@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNOtes();
}

//The class marked with @Dao should either be an interface or an abstract class.
// At compile time, Room will generate an implementation of this class when it is referenced by a Database.
