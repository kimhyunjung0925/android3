package com.koreait.com.ch10;
import static com.koreait.com.Utils.getNumberComma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.number.Scale;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.koreait.com.R;
import com.koreait.com.Utils;
import com.koreait.com.picsum.PicsumVO;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyBoxofficeActivity extends AppCompatActivity {

    private BoxOfficeAdapter adapter;

    private DatePicker dpTargetDt;
    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_boxoffice);

        adapter = new BoxOfficeAdapter();

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

