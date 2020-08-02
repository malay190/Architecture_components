package com.example.architecture_components;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNOtes();
    }

    public void insert(Note note){
        new InsertNotesAsyncTask(noteDao).execute(note);  //created because Room don't allow database operation in the main thread.

    }

    public void update(Note note){
        new UpdateNotesAsyncTask(noteDao).execute(note);

    }
    public void delete(Note note){
        new DeleteNotesAsyncTask(noteDao).execute(note);

    }

    public void deleteAllNotes(){
        new DeleteAllNotesAsyncTask(noteDao).execute();

    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }


    //AsyncTask enables proper and easy use of the UI thread. This class allows you to perform background operations
    // and publish results on the UI thread without having to manipulate threads and/or handlers.
    // AsyncTasks should ideally be used for short operations (a few seconds at the most.)
    private static class InsertNotesAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao;

        public InsertNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNotesAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao;

        public UpdateNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNotesAsyncTask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao;

        public DeleteNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteDao noteDao;

        public DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
