package com.example.myappproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappproject.dto.MemberDto;

public class SignupActivity extends Activity {

    CheckBox checkBox;
    EditText emailId, pass, passCheck;
    ImageView imSignup, homeSignup;
    Button btnEmailCheck;
    TextView tvEmailCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        imSignup = (ImageView) findViewById(R.id.imSignup);
        homeSignup = (ImageView) findViewById(R.id.homeSignup);
        emailId = (EditText) findViewById(R.id.emailId);
        pass = (EditText) findViewById(R.id.pass);
        passCheck = (EditText) findViewById(R.id.passCheck);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        btnEmailCheck = (Button)findViewById(R.id.btnEmailCheck);
        tvEmailCheck = (TextView)findViewById(R.id.tvEmailCheck);

        //이메일 중복 확인
        btnEmailCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberDto memberDto = new MemberDto();
                //꼭 값을 받아와야한다.
                memberDto.setEmail(emailId.getText().toString());

                int result = MainActivity.memberDao.emailCheck(memberDto);
                if(result == 0){
                    tvEmailCheck.setVisibility(View.VISIBLE);
                    tvEmailCheck.setTextColor(Color.BLUE);
                    tvEmailCheck.setText("사용 가능한 이메일 입니다.");
                }else{
                    tvEmailCheck.setVisibility(View.VISIBLE);
                    tvEmailCheck.setTextColor(Color.RED);
                    tvEmailCheck.setText("해당 이메일이 존재합니다.");
                    emailId.setText("");
                }
            }
        });


        //회원가입 이미지 클릭
        imSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valiCheck()){
                    MemberDto memberDto = new MemberDto();
                    memberDto.setEmail(emailId.getText().toString());
                    memberDto.setPass(pass.getText().toString());

                    //메인액티비티 Dao에 insert진행 후 호출하기
                    MainActivity.memberDao.insert(memberDto);

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        //home 이벤트 처리
        homeSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }//onCreate

    public boolean valiCheck(){
        if(emailId.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"이메일를 입력해주세요", Toast.LENGTH_SHORT).show();
            emailId.requestFocus();
            return false;
        }else if(pass.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"패스워드를 입력해주세요", Toast.LENGTH_SHORT).show();
            pass.requestFocus();
            return false;
        }else if(passCheck.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"패스워드를 확인해주세요", Toast.LENGTH_SHORT).show();
            passCheck.requestFocus();
            return false;
        }else if(!checkBox.isChecked()){
            Toast.makeText(getApplicationContext(),"동의를 해주세요", Toast.LENGTH_SHORT).show();
            checkBox.requestFocus();
            return false;
        }else{
            //패스워드 확인 - 위에선 그냥 길이만 있는지 없는지 만 확인하기
           if(pass.getText().toString().equals(passCheck.getText().toString())){
               return true;
           }else {
               Toast.makeText(getApplicationContext(),"패스워드가 일치하지 않습니다! 다시입력해주세요", Toast.LENGTH_SHORT).show();
               pass.setText("");
               passCheck.setText("");
               pass.requestFocus();
               return false;
           }
        }
    }

}//SignupActivity
