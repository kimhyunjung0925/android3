package com.koreait.com.ch10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreait.com.R;
import com.koreait.com.Utils;
import com.koreait.com.ch10.boxofficemodel.DailyBoxOfficeVO;

import java.util.List;

    public class KobisBoxofficeAdapter extends RecyclerView.Adapter<KobisBoxofficeAdapter.MyViewHolder> {
        private List<DailyBoxOfficeVO> list;

        public void setList(List<DailyBoxOfficeVO> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public KobisBoxofficeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.item_daily_boxoffice, parent, false);
            return new KobisBoxofficeAdapter.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull KobisBoxofficeAdapter.MyViewHolder holder, int position) {
            DailyBoxOfficeVO vo = list.get(position);
            holder.setItem(vo);
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        static class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView tvTitle;
            private TextView tvAudienceCnt;

            public MyViewHolder(View v) {
                super(v);

                tvTitle = v.findViewById(R.id.tvTitle);
                tvAudienceCnt = v.findViewById(R.id.tvAudienceCnt);
            }

            public void setItem(DailyBoxOfficeVO vo) {
                tvTitle.setText(vo.getMovieNm());
                tvAudienceCnt.setText(Utils.getNumberComma(vo.getTvAudienceCnt()) + " 명");
            }

        }
    }

