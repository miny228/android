package com.example.myappproject.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myappproject.dto.MemberDto;

import java.io.Serializable;

public class MemberDao implements Serializable {

    SQLiteDatabase db;

    public MemberDao(SQLiteDatabase db){
        this.db = db;
    }

    public void crateTable(){
        String sql = " CREATE TABLE IF NOT EXISTS member(" +
                " email text primary key," +
                " pass text not null); ";
        db.execSQL(sql);
    }

    public void insert(MemberDto memberDto){
        String sql = " INSERT INTO member(email, pass) " +
                "values('"+ memberDto.getEmail() +"',"+
                "'"+ memberDto.getPass() +"'"+
                ");";

        Log.d("sql-------------->>>>> ", sql);
        db.execSQL(sql);
    }

    public boolean login(MemberDto memberDto){
        boolean result = false;
        String sql = " select count(email) from member" +
                " where email = '"+memberDto.getEmail()+"'" +
                " and pass = '"+memberDto.getPass()+"'";

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int count = cursor.getInt(0);
            if(count == 1){
                result = true;
            }
        }
        return result;
    }

    public int emailCheck(MemberDto memberDto){
        int result = 0;
        String sql = " select count(email) from member" +
                " where email = '"+memberDto.getEmail()+"' ";

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int count = cursor.getInt(0);
            result = count;
        }
        Log.d("sql-------------->>>>> ", String.valueOf(result));
        return result;
    }

}
