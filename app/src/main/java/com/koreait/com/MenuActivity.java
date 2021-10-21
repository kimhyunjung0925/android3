package com.koreait.com;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.koreait.com.ch07.BookPersonActivity;
import com.koreait.com.ch10.DailyBoxofficeActivity;

import com.koreait.com.ch10.WeekBoxofficeActivity;
import com.koreait.com.picsum.PicsumActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        int result = Utils.parseStringToInt("11a");
        Log.i("myLog",""+ result);

        result = Utils.parseStringToInt("11",5); // 11 리턴
        result = Utils.parseStringToInt("1a1",5); // 5 리턴, val값에 a있어서 defval리턴

    }

    public void call(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW
                , Uri.parse("tel:010-7118-4107"));
        startActivity(intent);
    }

    public void moveToActivity(View v) {
        int id = v.getId();

        Class c = null;
        if (id == R.id.menuBtn1) {
            c = MainActivity.class;
        } else if (id == R.id.menuBtn2) {
            c = LinearActivity.class;
        } else if (id == R.id.menuBtn3) {
            c = ConstraintActivity.class;
        } else if (id == R.id.menuBtn4) {
            c = WriteActivity.class;
        } else if (id == R.id.menuBtn5) {
            c = BookPersonActivity.class;
        } else if (id == R.id.menuBtn6) {
            c = ImageViewActivity.class;
        } else if(id == R.id.menuBtn7) {
            c = PicsumActivity.class;
        } else if(id == R.id.menuBtn8) {
            c = DailyBoxofficeActivity.class;
        } else if(id == R.id.menuBtn9) {
           c = WeekBoxofficeActivity.class;
        }

            if(c == null) {
                Snackbar.make(v,"준비중입니다.", Snackbar.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, c);
            startActivity(intent);
        }

        public void moveToActivityWithText (View v){
            TextView tv = (TextView) v;
            String text = (String) tv.getText();
            Log.i("myLog", text);

            Class c = null;
            switch (text) {
                case "메인":
                    c = MainActivity.class;
                    break;
                case "리니어레이아웃":
                    c = LinearActivity.class;
                    break;
                case "제약레이아웃":
                    c = ConstraintActivity.class;
                    break;
                case "글쓰기":
                    c = WriteActivity.class;
                    break;
            }

            Intent intent = new Intent(this, c);
            startActivity(intent);
        }

    }
