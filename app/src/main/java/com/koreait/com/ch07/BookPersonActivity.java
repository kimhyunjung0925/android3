package com.koreait.com.ch07;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.koreait.com.R;
import com.koreait.com.Utils;
// import com.koreait.com.SimpleTextAdapter;

import java.util.LinkedList;

public class BookPersonActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private PersonAdapter adapter;
    private EditText etName;
    private EditText etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_person);

        rvList = findViewById(R.id.rvList);
        adapter = new PersonAdapter();
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);
    }

    public void clkReg(View v) {
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        int intAge = Utils.parseStringToInt(age);


        if (intAge == 0) {
            Toast.makeText(this,"문제가 발생하였습니다.",Toast.LENGTH_SHORT).show();
        } else {
            Person p = new Person(name, intAge);
            adapter.addItem(p);
            adapter.notifyDataSetChanged();
            etName.setText("");
            etAge.setText("");
        }

    }
}





/*
        Person p = new Person("홍길동슈퍼맨",20);
        adapter.addItem(p);
        adapter.addItem(p);
        adapter.addItem(p);
        adapter.addItem(p);
        adapter.notifyDataSetChanged();
        p.setName("원더우먼");
        adapter.notifyDataSetChanged();

        adapter.addItem(new Person("홍길동",20));
        adapter.addItem(new Person("난다김",22));
        adapter.addItem(new Person("블랙보리",24));
        adapter.addItem(new Person("홍길동",20));
        adapter.addItem(new Person("난다김",22));
        adapter.addItem(new Person("블랙보리",24));
        adapter.addItem(new Person("홍길동20",20));
        adapter.addItem(new Person("난다김",22));
        adapter.addItem(new Person("블랙보리",24));
        adapter.notifyDataSetChanged();
 */


