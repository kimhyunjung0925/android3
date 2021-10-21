package com.koreait.com.ch10;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.koreait.com.R;
import com.koreait.com.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class WeekBoxofficeActivity extends AppCompatActivity {

        private WeekBoxofficeAdapter adapter;

        private DatePicker dpTargetDt;
        private RecyclerView rvList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_week_boxoffice);

            adapter = new WeekBoxofficeAdapter();

            dpTargetDt = findViewById(R.id.dpTargetDt);
            rvList = findViewById(R.id.rvList);
            rvList.setAdapter(adapter);

        }

        private void network(String targetDt) {
            Retrofit rf = new Retrofit.Builder()
                    .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            KobisService service = rf.create(KobisService.class);
            final String KEY = "9fa444fb8d829f6d36d0d985d1dbf83d";
            Call<BoxOfficeResultBodyVO> call = service.boxofficeSearchDailyBoxOfficeList(KEY, targetDt);

            call.enqueue(new Callback<BoxOfficeResultBodyVO>() {
                @Override
                public void onResponse(Call<BoxOfficeResultBodyVO> call, Response<BoxOfficeResultBodyVO> res) {
                    if(res.isSuccessful()) {
                        BoxOfficeResultBodyVO vo = res.body();

                        BoxOfficeResultVO resultVo = vo.getBoxOfficeResult();
                        List<DailyBoxOfficeVO> list = resultVo.getDailyBoxOfficeList();
                        // List<DailyBoxOfficeVO> list = vo.getBoxOfficeResult().getDailyBoxOfficeList(); 위 두줄을 한줄로

                        adapter.setList(list);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<BoxOfficeResultBodyVO> call, Throwable t) {

                }
            });
        }

        public void clkSearch(View v) {
            int day = dpTargetDt.getDayOfMonth();
            int mon = dpTargetDt.getMonth() + 1;
            int year = dpTargetDt.getYear();

            String date = String.format("%s%02d%02d",year,mon,day);
            network(date);

            Log.i("myLog", date);
        }
    }

    class WeekBoxofficeAdapter extends RecyclerView.Adapter<com.koreait.com.ch10.DailyBoxofficeAdapter.MyViewHolder> {

        private List<DailyBoxOfficeVO> list;

        public void setList(List<DailyBoxOfficeVO> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public com.koreait.com.ch10.DailyBoxofficeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.item_week_boxoffice, parent, false);
            return new com.koreait.com.ch10.DailyBoxofficeAdapter.MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull com.koreait.com.ch10.DailyBoxofficeAdapter.MyViewHolder holder, int position) {
            DailyBoxOfficeVO vo = list.get(position);
            holder.setItem(vo);
            // holder.setItem(list.get(position)); 위 두줄을 한줄로 바꾼 것
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }
/*
        static class WeekTextView extends androidx.appcompat.widget.AppCompatTextView {

            private TextView showRange;

            public WeekTextView (View v) {
                super(v.getContext());
                showRange = v.findViewById(R.id.showRange);
            }

            public void setItem(DailyBoxOfficeVO vo) {
                showRange.setText(vo.getShowRange());
            }
        }
        */

        static class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView tvTitle;
            private TextView tvAudienceCnt;
            private TextView showRange;

            public MyViewHolder(View v) {
                super(v);
                tvTitle = v.findViewById(R.id.tvTitle);
                tvAudienceCnt = v.findViewById(R.id.tvAudienceCnt);
                showRange = v.findViewById(R.id.showRange);

            }

            public void setItem(DailyBoxOfficeVO vo) {
                tvTitle.setText(vo.getMovieNm());
                String numberComma = Utils.getNumberComma(vo.getTvAudienceCnt());
                tvAudienceCnt.setText(numberComma + "명");
                showRange.setText(vo.getShowRange());
            }
        }
    }

