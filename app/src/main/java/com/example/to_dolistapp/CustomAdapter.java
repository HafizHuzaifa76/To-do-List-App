package com.example.to_dolistapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    CustomAdapter taskClassAdapter;
    LayoutInflater inflater;
    ArrayList<TaskClass> taskClassArrayList;
    TasksDatabase tasksDatabase;

    public CustomAdapter(Context context, ArrayList taskClassArrayList, TasksDatabase tasksDatabase){
        this.context = context;
        this.taskClassArrayList = taskClassArrayList;
        inflater = LayoutInflater.from(context);
        this.tasksDatabase = tasksDatabase;
//        this.taskClassAdapter = taskClassAdapter;
    }
    @Override
    public int getCount() {
        return taskClassArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.listview_style,null);
        CardView cardView = view.findViewById(R.id.cardView);
        TextView textView = view.findViewById(R.id.textView);
        TextView textView1 = view.findViewById(R.id.textView1);
        ImageButton imageButton = view.findViewById(R.id.imageButton);
        CheckBox checkBox = view.findViewById(R.id.checkBox);
        textView.setText(taskClassArrayList.get(position).getTask_to_do());
        textView1.setText(position+1 +"");


        ColorStateList colorStateList = ContextCompat.getColorStateList(context,R.color.blue);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder delete = new AlertDialog.Builder(context);
                delete.setTitle("Delete");
                delete.setIcon(R.drawable.baseline_delete_24);
                delete.setMessage(" Do you want to delete");
                delete.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
//                        taskClassArrayList = null;
                        tasksDatabase.deleteTaskFromDatabase(taskClassArrayList.get(position).getId());
                        taskClassArrayList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                delete.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                delete.show();
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()) {
                    Toast.makeText(context, "Task Done", Toast.LENGTH_SHORT).show();
                    taskClassArrayList.get(position).setChecked(1);
                }
            }
        });

        return view;
    }
}
