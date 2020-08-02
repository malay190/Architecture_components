package com.example.architecture_components;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//Marks a class as a RoomDatabase.
//The class should be an abstract class and extend RoomDatabase.
//You can receive an implementation of the class via Room.databaseBuilder or Room.inMemoryDatabaseBuilder.
@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //A singleton is a design pattern that restricts the instantiation of a class to only one instance.
    // Notable uses include controlling concurrency and creating a central point of access for an application to access its data store.
    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    //Synchronization in Java guarantees that no two threads can execute a
    // synchronized method which requires the same lock simultaneously or concurrently.
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance == null){

            //Once a database is built, you should keep a reference to it and re-use it.
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()//Allows Room to destructively recreate database tables
                    // if Migrations that would migrate old database schemas to the latest schema version are not found.
                    // When the database version on the device does not match the latest schema version, Room runs necessary
                    // Migrations on the database.
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void,Void>{
        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase database) {
            noteDao = database.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("title 1", "Description 1", 1));
            noteDao.insert(new Note("title 2", "Description 2", 2));
            noteDao.insert(new Note("title 3", "Description 3", 3));

            return null;
        }
    }
}