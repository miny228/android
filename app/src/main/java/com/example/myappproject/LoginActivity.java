package com.example.myappproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.myappproject.dto.MemberDto;

public class LoginActivity extends Activity {

    ImageView btnSignUp, imLogin;
    EditText editEmail, editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        imLogin = (ImageView) findViewById(R.id.imLogin);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editPass = (EditText)findViewById(R.id.editPass);

        btnSignUp = (ImageView) findViewById(R.id.btnSignUp);

        //회원가입 이동
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(signupIntent);
            }
        });

        //로그인 클릭 이벤트
        imLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valiCheck()){
                    MemberDto memberDto = new MemberDto();
                    memberDto.setEmail(editEmail.getText().toString());
                    memberDto.setPass(editPass.getText().toString());

                    if(MainActivity.memberDao.login(memberDto)){
                       //로그인 성공
                       Intent hotplaceIntent = new Intent(getApplicationContext(), HotplaceActivity.class);
                       //데이터 객체 전송 : hotplaceIntent.putExtra(key, value);
                       startActivity(hotplaceIntent);

                    }else{
                        //로그인 실패
                        AlertDialog.Builder resultDlg = new AlertDialog.Builder(LoginActivity.this);
                        resultDlg.setTitle("로그인 실패!");
                        resultDlg.setIcon(R.drawable.rate_star_small_on);
                        resultDlg.setMessage("아이디와 비밀번호가 일치 하지 않습니다. 다시 로그인해주세요.");
                        resultDlg.setNegativeButton("닫기",null);
                        resultDlg.show();
                        editEmail.setText("");
                        editPass.setText("");
                        editEmail.requestFocus();
                    }

                }
            }
        });

    }//onCreate

    public boolean valiCheck(){
        if(editEmail.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"이메일를 입력해주세요", Toast.LENGTH_SHORT).show();
            editEmail.requestFocus();
            return false;
        }else if(editPass.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"패스워드를 입력해주세요", Toast.LENGTH_SHORT).show();
            editPass.requestFocus();
            return false;
        }else{
            return true;
        }
    }

}//LoginActivity
