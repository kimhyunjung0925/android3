package com.koreait.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class WriteActivity extends AppCompatActivity {

    private EditText etMsg;
    private Button btnSend;
    private RecyclerView rvList; // view영역

    private List<String> msgList; // data
    private SimpleTextAdapter sta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        etMsg = findViewById(R.id.etMsg);
        btnSend = findViewById(R.id.btnSend);
        rvList = findViewById(R.id.rvList);

        msgList = new LinkedList<>();
        msgList.add("A");
        msgList.add("B");
        msgList.add("C");
        msgList.add("D");

        // LinearLayoutManager llm = new LinearLayoutManager(this);
        // rvList.setLayoutManager(llm);

        sta = new SimpleTextAdapter(msgList);
        rvList.setAdapter(sta);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //콜백 메소드
                String msg = etMsg.getText().toString();
                Log.i("myLog", msg);
                etMsg.setText("");
                msgList.add(msg);
                // sta.notifyDataSetChanged();
            }
        });
    }

    public void refresh(View v) {
        sta.notifyDataSetChanged();
    }
}
class MyOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Log.i("myLog", "111111");
    }
}

class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.MyViewHolder> {

    private List<String> list;

    SimpleTextAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_textview,parent,false);
        return new SimpleTextAdapter.MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position) {
        Log.i("myLog","position" + position);
        String str = list.get(position);
        holder.tvMsg.setText(str);
    }

    @Override
    public int getItemCount() {
        Log.i("myLog","getItemCount" + list.size());
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvMsg;
        public MyViewHolder(View v) {
            super(v);
            tvMsg = v.findViewById(R.id.tvMsg);
        }
    }
}