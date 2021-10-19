package com.koreait.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 이벤트 연결 버튼 클릭시 실행될 메소드 연결
    public void clkBtn(View v) {
        Button btn = (Button) v;
        String btnText = (String) btn.getText();
        Toast.makeText(this, btnText + "를 클릭했어요.", Toast.LENGTH_LONG).show();
    }

    public void ddd(View v) {
        TextView tv = (TextView) v;
        Toast.makeText(this, tv.getText(), Toast.LENGTH_SHORT).show();
    }


}