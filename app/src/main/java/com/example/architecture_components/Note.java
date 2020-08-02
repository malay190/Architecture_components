package com.example.architecture_components;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Marks a class as an entity. This class will have a mapping SQLite table in the database.
@Entity(tableName = "note_table") //sets the name of the table to note_table
public class Note {

    @PrimaryKey(autoGenerate = true) //generate unique id for each note. Each entity must have at least 1 field annotated with PrimaryKey.
    // You can also use primaryKeys() attribute to define the primary key.
    private int id;
    private String title;
    private String description;
    private int priority;

    public void setId(int id) {
        this.id = id;
    }

    //Each entity must either have a no-arg constructor or a constructor whose parameters match fields (based on type and name).
    // Constructor does not have to receive all fields as parameters but if a field is not passed into the constructor,
    // it should either be public or have a public setter.
    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
