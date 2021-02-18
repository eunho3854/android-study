package com.view.myapplication3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

// stack 생성
public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNoteList;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 전체 인플레이트

        List<Note> notes = new ArrayList<>();

        notes.add(new Note(1, "Ali Connors \nBrunch this weekend?", "I`ll be in your neighborhood doing errands...", "15m"));
        notes.add(new Note(2, "me, Scott, Jennifer \nSummer BBQ", "I`ll be in your", "2h"));
        notes.add(new Note(3, "Sandra Adams \nOui Oui", "I`ll be in your", "1h"));
        notes.add(new Note(4, "Trevor Hansen \nOrder Confirmation", "I`ll be in your", "30m"));
        notes.add(new Note(5, "Britta Holt \nRecipe to try", "I`ll be in your", "13m"));
        notes.add(new Note(6, "David Park \nGiants game", "I`ll be in your", "40m"));
        notes.add(new Note(7, "Ali Connors Brunch this weekend?", "I`ll be in your", "1h 30m"));
        notes.add(new Note(8, "Ali Connors Brunch this weekend?", "I`ll be in your", "5m"));
        notes.add(new Note(9, "Ali Connors Brunch this weekend?", "I`ll be in your", "12m"));


        LinearLayoutManager manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvNoteList=findViewById(R.id.rv_note_list);
        rvNoteList.setLayoutManager(manager);

        noteAdapter=new NoteAdapter(notes);

        rvNoteList.setAdapter(noteAdapter);
    }

}