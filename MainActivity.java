package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteAdapter adapter;
    private NoteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        database = NoteDatabase.getInstance(this);
        loadNotes();

        FloatingActionButton buttonAddNote = findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddNote.class);
            startActivityForResult(intent, 1);
        });
        adapter.setOnItemClickListener(note -> {
            Intent intent = new Intent(MainActivity.this, AddNote.class);
            intent.putExtra("note_id", note.getId());
            intent.putExtra("note_title", note.getTitle());
            intent.putExtra("note_description", note.getDesc());
            intent.putExtra("note_date", note.getDate());
            startActivityForResult(intent, 2);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode== RESULT_OK){
            loadNotes();
        }
    }
    private void loadNotes(){
        List<Note> notes = database.noteDao().getAllNotes();
        adapter.setNotes(notes);
    }
}