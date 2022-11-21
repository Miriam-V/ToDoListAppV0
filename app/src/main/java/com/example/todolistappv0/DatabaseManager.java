package com.example.todolistappv0;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "todoDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_TODOLIST = "todo";
    private static final String ID = "id";
    private static final String TASK = "task";

    public DatabaseManager(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // method that is going to create the database for us?
    @Override
    public void onCreate(SQLiteDatabase db) {
        // build SQL create statement
        String sqlCreate = "create table " + TABLE_TODOLIST + "( " + ID;
        sqlCreate += " integer primary key autoincrement, " + TASK;
        sqlCreate += " text ) " ;

        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_TODOLIST );
        // Re-create tables
        onCreate(db);
    }

    public void insert (ToDo todo) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_TODOLIST;
        sqlInsert += " values( null, '" + todo.getTask( );
        sqlInsert += "' )";

        db.execSQL( sqlInsert );
        db.close( );
    }


    public ArrayList<ToDo> selectAll() {
        String sqlQuery = "select * from " + TABLE_TODOLIST;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<ToDo> toDos = new ArrayList<ToDo>( );
        while( cursor.moveToNext( ) ) {
            ToDo currentToDo
                    = new ToDo( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ));
            toDos.add( currentToDo );
        }
        db.close( );
        return toDos;
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_TODOLIST;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL( sqlDelete );
        db.close( );
    }
}
