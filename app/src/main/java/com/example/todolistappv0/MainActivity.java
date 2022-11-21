package com.example.todolistappv0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager dbManager; //!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DatabaseManager(this); //!
        updateView();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Menu - Tells Android that there is a menu out there that we are going to "play" with
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    // !
    private void updateView() {
        ArrayList<ToDo> toDos = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);

        for (ToDo toDo : toDos) {
            // RadioButton rb = new RadioButton(this);
            TextView tv = new TextView(this); //!
            tv.setId(toDo.getId());
            tv.setText(toDo.toString());
            group.addView(tv);
            tv.setTextSize(30);
        }

        /*
        // set up event handling
        DeleteActivity.RadioButtonHandler rbh = new DeleteActivity.RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        // create a back button
        Button backButton = new Button(this);
        backButton.setText("Back");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteActivity.this.finish();
            }
        });
        */
        scrollView.addView(group);
        layout.addView(scrollView);

        /*
        // add a back button at the bottom of the view
        RelativeLayout.LayoutParams params
                = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT );
        params.addRule( RelativeLayout.ALIGN_PARENT_BOTTOM );
        params.addRule( RelativeLayout.CENTER_HORIZONTAL );
        params.setMargins( 0, 0, 0, 50 );
        layout.addView( backButton, params );
        */
        setContentView( layout );


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle action bar items here.

        int id = item.getItemId();

        switch (id) {

            case R.id.action_add:
                Log.w("MainActivity", "Add Selected");
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "Delete Selected");
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            case R.id.action_insert:
                Log.w("MainActivity", "Insert Selected");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // !
    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

}