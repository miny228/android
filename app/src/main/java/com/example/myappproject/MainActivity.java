package com.example.myappproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myappproject.dao.MemberDao;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    //호출방법 : 클래스명.객체명 or 클래스명.메서드 으로 호출한다.
    static MemberDao memberDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //데이터베이스 생성
        db = openOrCreateDatabase("gotour",MODE_PRIVATE,null);
        memberDao = new MemberDao(db);
        memberDao.crateTable();

        ImageView tvStart = (ImageView) findViewById(R.id.tvStart);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "고투어 시작", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });

    }//onCreate

}//MainActivity