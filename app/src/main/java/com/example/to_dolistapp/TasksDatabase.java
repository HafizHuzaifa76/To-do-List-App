package com.example.to_dolistapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TasksDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TasksDB";
    ArrayList<TaskClass> taskClassArrayList = new ArrayList<>();
    private static final int DATABASE_ID = 1;
    private static final String TABLE_TASKS = "Tasks";
    private static final String KEY_ID = "id";
    private static final String KEY_TASK_TO_DO = "task_to_do";

    public TasksDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_ID);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " +TABLE_TASKS +
                " (" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +KEY_TASK_TO_DO +" TEXT);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_TASKS);
        onCreate(sqLiteDatabase);
    }

    public void insertTaskInDatabase(String t){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TASK_TO_DO,t);
        long latest = database.insert(TABLE_TASKS,null,values);
        taskClassArrayList.add(new TaskClass((int) latest,t));
        database.close();
    }

    public ArrayList<TaskClass> fetchTaskFromDatabase(){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+TABLE_TASKS,null);
        while (cursor.moveToNext()){
            taskClassArrayList.add(new TaskClass(cursor.getInt(0),cursor.getString(1)));
        }
        return taskClassArrayList;
    }

    public void deleteTaskFromDatabase(int i){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_TASKS,KEY_ID +" = " +i,null);
    }
}
