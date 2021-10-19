package com.koreait.com.ch07;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.google.android.material.snackbar.Snackbar;
import com.koreait.com.R;

class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    List<Person> items = new ArrayList<>();

    public void addItem(Person item) {
        items.add(item);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.MyViewHolder holder, int position) {
        //Person item = items.get(position);
        //holder.setItem(item);
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvAge;

        public MyViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //클로저(closure)
                    String name = tvName.getText().toString();
                    String age = tvAge.getText().toString();
                    // Log.i("myLog",name + "," + age);
                    Snackbar.make(v, name + ","+ age,Snackbar.LENGTH_SHORT).show();

                }
            });

            tvName = v.findViewById(R.id.tvName);
            tvAge = v.findViewById(R.id.tvAge);
        }

        public void setItem(Person item) {
            tvName.setText(item.getName());
            // tvAge.setText(item.getAge()); 정수값은 R에서 관리하고있는 정수값만 사용가능
            // tvAge.setText(R.string.tv_01); strubg.xml에서 관리하고 있는 문자열을 입력할 때 정수값
            tvAge.setText(item.getAge() + "살");

            /*
            // int aa = 100; - 임의로 정수값 넣으면 에러
            // R에서 관리하고 있는 정수값을 이용
            int a = R.string.app_name;
            tvAge.setText(a);
            */

        }
    }
}

