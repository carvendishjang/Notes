package io.github.carvendishjang.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DraftActivity extends AppCompatActivity {

    EditText noteEditText;
    Intent intent;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);

        intent = getIntent();

        noteEditText = findViewById(R.id.noteEditText);

        final int noteNumber = intent.getIntExtra("noteNumber", 0);

        if (noteNumber < MainActivity.notes.size()) {
            noteEditText.setText(MainActivity.notes.get(noteNumber));
        }



        noteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (noteNumber == MainActivity.notes.size()) {
                    MainActivity.notes.add("");
                }


                if (noteEditText.getText().toString() == null) {

                    MainActivity.notes.remove(noteNumber);
                    MainActivity.arrayAdapter.notifyDataSetChanged();

                } else {

                    MainActivity.notes.set(noteNumber, noteEditText.getText().toString());
                    MainActivity.arrayAdapter.notifyDataSetChanged();

                }

                //
               sharedPreferences = getApplicationContext().getSharedPreferences("io.github.carvendishjang.notes", Context.MODE_PRIVATE);

                try {


                    sharedPreferences.edit().putString("notes", ObjectSerializer.serialize(MainActivity.notes)).apply();


                } catch (Exception e) {
                    e.printStackTrace();
                }
                //

            }

            @Override
            public void afterTextChanged(Editable editable) {




            }
        });



    }



}
