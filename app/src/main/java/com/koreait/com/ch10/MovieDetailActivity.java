package com.koreait.com.ch10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.koreait.com.R;
import com.koreait.com.ch10.boxofficemodel.BoxOfficeResultVO;
import com.koreait.com.ch10.boxofficemodel.DailyBoxOfficeVO;
import com.koreait.com.ch10.searchmoviemodel.MovieInfoResultBodyVO;
import com.koreait.com.ch10.searchmoviemodel.MovieInfoResultVO;
import com.koreait.com.ch10.searchmoviemodel.MovieInfoVO;
import com.koreait.com.ch10.searchmoviemodel.MovieListResultVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView tvMovieNm;
    private TextView tvMovieNmEn;
    private TextView tvShowTm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        String movieCd = intent.getStringExtra("movieCd");
        Log.i("myLog","detail - movieCd : " + movieCd);
        getData(movieCd);

        tvMovieNm = findViewById(R.id.tvMovieNm);
        tvMovieNmEn = findViewById(R.id.tvMovieNmEn);
        tvShowTm = findViewById(R.id.tvShowTm);
        }

        private void getData(String movieCd) {
            Retrofit rf = new Retrofit.Builder()
                    .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            KobisService service = rf.create(KobisService.class);
            final String KEY = "1a0a7ecf96ad3364d8de70e91560767a";
            Call<MovieInfoResultBodyVO> call = service.searchMovieInfo(KEY, movieCd);
            call.enqueue(new Callback<MovieInfoResultBodyVO>() {
                @Override
                public void onResponse(Call<MovieInfoResultBodyVO> call, Response<MovieInfoResultBodyVO> res) {
                    if (res.isSuccessful()) { //state : 200 - 통신 성공!!!
                        MovieInfoResultBodyVO result = res.body();
                        //두줄로
                        //MovieInfoResultVO result2 = result.getMovieInfoResult();
                        //MovieInfoVO data = result2.getMovieInfo();

                        //한줄로
                        MovieInfoVO data = result.getMovieInfoResult().getMovieInfo();

                        tvMovieNm.setText(data.getMovieNm());
                        tvMovieNmEn.setText(data.getMovieNmEn());
                        tvShowTm.setText(data.getShowTm());

                    } else {

                    }
                }

                @Override
                public void onFailure(Call<MovieInfoResultBodyVO> call, Throwable t) {
                    //baseurl 틀리거나, 그 서버가 죽어있을 때, 망에 문제있을 때

                }
            });
        }
}