package com.koreait.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LinearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_exam2);
    }
    public void moveToActivity(View v) {
        int id = v.getId();
        Class c = null;
        if (id == R.id.menuBtn4) {
            c = MenuActivity.class;
        }
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

}
