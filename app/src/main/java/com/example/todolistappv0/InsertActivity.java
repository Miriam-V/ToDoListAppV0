package com.example.todolistappv0;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {

    // Reference to the DatabaseManager
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this); // reference to dbManager?
        setContentView(R.layout.activity_insert); // Wired this activity with activity_insert
    }


    public void insert(View view) {
        // Retrieve "Task" entry

        Log.w("InsertActivity", "Insert Button Pushed");

        EditText taskET = findViewById(R.id.input_task);
        String task = taskET.getText().toString();

        // Insert into Database
        ToDo todo = new ToDo(0, task);
        dbManager.insert(todo); // call insert method
        Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show(); // Alerts user that a task was added?


        // Clear Data
        taskET.setText("");
    }

    public void goBack(View view) {
        this.finish();
    }
}
