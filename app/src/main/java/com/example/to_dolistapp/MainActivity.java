package com.example.to_dolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    CustomAdapter taskClassAdapter;
    EditText editText;
    ImageButton imageButton2;
    ImageButton imageButton;
    TasksDatabase tasksDatabase = new TasksDatabase(MainActivity.this);
    ArrayList<TaskClass> taskClassArrayList;
//    ArrayList <TaskClass> taskClassArrayList = new ArrayList<>();
    CheckBox checkBox;
    ListView listView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton = findViewById(R.id.imageButton);
        editText = findViewById(R.id.editText);
//        addTaskInDatabase("hh");

        taskClassArrayList = tasksDatabase.fetchTaskFromDatabase();

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter text", Toast.LENGTH_SHORT).show();
                }
                else {
//                    taskClassArrayList = new ArrayList<>();
                    String text = editText.getText().toString().toUpperCase(Locale.ROOT);
                    tasksDatabase.insertTaskInDatabase(text);
//                    taskClassArrayList = tasksDatabase.fetchTaskFromDatabase();
                    Toast.makeText(MainActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                }
                taskClassAdapter = new CustomAdapter(MainActivity.this, taskClassArrayList,tasksDatabase);
                listView.setAdapter(taskClassAdapter);
                editText.setText("");
                tasksDatabase.deleteTaskFromDatabase(4);
            }
        });
        taskClassAdapter = new CustomAdapter(MainActivity.this, taskClassArrayList,tasksDatabase);
        listView.setAdapter(taskClassAdapter);

    }

}