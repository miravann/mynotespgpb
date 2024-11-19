package com.example.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class AddNote extends AppCompatActivity {
    private EditText editTextTitle, editTextDescription, editTextDate;
    private NoteDatabase database;
    private int noteId = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note);

        editTextTitle = findViewById(R.id.edt_title);
        editTextDescription = findViewById(R.id.edt_description);
        editTextDate = findViewById(R.id.edt_date);
        Button buttonAddNote = findViewById(R.id.buttonAdd);
        Button buttonUpdateNote = findViewById(R.id.buttonUpdate);

        database = NoteDatabase.getInstance(this);

        Intent intent = getIntent();
        if (intent.hasExtra("note_id")) {
            setTitle("edit my note");
            noteId = intent.getIntExtra("note_id", -1);
            editTextTitle.setText(intent.getStringExtra("note_title"));
            editTextDescription.setText(intent.getStringExtra("note_description"));
            editTextDate.setText(intent.getStringExtra("note_date"));
        } else {
            setTitle("add my note");
            String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            editTextDate.setText(currentDate);
        }
        buttonAddNote.setOnClickListener(view -> saveNote());
        buttonUpdateNote.setOnClickListener(view -> updateNote());
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String date = editTextDate.getText().toString();

        if (title.trim().isEmpty() || description.trim().isEmpty() || date.trim().isEmpty()) {
            Toast.makeText(this, "Kolom Masih Kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Note note = new Note();
        note.setTitle(title);
        note.setDesc(description);
        note.setDate(date);

        if (noteId == -1) {
            database.noteDao().insert(note);
            Toast.makeText(this, "Note Ditambahkan", Toast.LENGTH_SHORT).show();
        }
        setResult(RESULT_OK);
        finish();
    }

    private void updateNote() {
        if (noteId == -1) {
            Toast.makeText(this, "Note Tidak Dapat Di Update", Toast.LENGTH_SHORT).show();
            return;
        }
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String date = editTextDate.getText().toString();

        if (title.trim().isEmpty() || description.trim().isEmpty() || date.trim().isEmpty()) {
            Toast.makeText(this, "Kolom Masih Kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        Note note = new Note();
        note.setTitle(title);
        note.setId(noteId);
        note.setDesc(description);
        note.setDate(date);

            database.noteDao().update(note);
            Toast.makeText(this, "Note Telah Di Update", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

}