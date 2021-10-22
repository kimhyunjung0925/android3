package com.koreait.com.ch10.searchmoviemodel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.koreait.com.R;
import com.koreait.com.Utils;
import com.koreait.com.ch10.KobisBoxofficeAdapter;
import com.koreait.com.ch10.boxofficemodel.DailyBoxOfficeVO;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
    }
}

class MovieListAdapter extends RecyclerView.Adapter {
    private List<MovieVO> list = new ArrayList<>();

    public void addItem(MovieVO vo) { list.add(vo);}


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_movie, parent, false);
        return new KobisBoxofficeAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MovieVO vo = list.get(position);
        holder.addItem(vo);
    }



    @Override
    public int getItemCount() { return list == null ? 0 : list.size(); }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMovieNm;
        private TextView tvRepNationNm;
        private TextView tvMovieNmEn;
        private TextView tvRepGenreNm;

        public MyViewHolder(View v) {
            super(v);

            tvMovieNm=v.findViewById(R.id.tvMovieNm);
            tvRepNationNm=v.findViewById(R.id.tvRepNationNm);
            tvMovieNmEn=v.findViewById(R.id.tvMovieNmEn);
            tvRepGenreNm=v.findViewById(R.id.tvRepGenreNm;
        }
        public void setItem(MovieVO vo) {
            tvMovieNm.setText(vo.getMovieNm());
            tvRepNationNm.setText(vo.getRepNationNm());
            tvMovieNmEn.setText(vo.getMovieNmEn());
            tvRepGenreNm.setText(vo.getRepGenreNm());

        }
    }
}
