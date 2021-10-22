package com.koreait.com.ch10;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.koreait.com.R;
import com.koreait.com.ch10.boxofficemodel.BoxOfficeResultBodyVO;
import com.koreait.com.ch10.boxofficemodel.BoxOfficeResultVO;
import com.koreait.com.ch10.boxofficemodel.DailyBoxOfficeVO;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class WeekBoxOfficeActivity extends AppCompatActivity {

    private KobisBoxofficeAdapter adapter;
    private Spinner spinner;
    private TextView range;
    private DatePicker dpTargetDt;
    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_box_office);

        adapter = new KobisBoxofficeAdapter();

        dpTargetDt = findViewById(R.id.dpTargetDt);
        rvList = findViewById(R.id.rvList);
        rvList.setAdapter(adapter);

        range = findViewById(R.id.range);

        spinner = (Spinner) findViewById(R.id.wkSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.week_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }

    private void network(String targetDt, String weekGb) {
        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KobisService service = rf.create(KobisService.class);
        final String KEY = "c368df0859f6194cec7d22fe3a4756dc";
        Call<BoxOfficeResultBodyVO> call = service.boxOfficeSearchWeeKBoxOfficeList(KEY,targetDt,weekGb);

        call.enqueue(new Callback<BoxOfficeResultBodyVO>() {
            @Override
            public void onResponse(Call<BoxOfficeResultBodyVO> call, Response<BoxOfficeResultBodyVO> res) {
                if(res.isSuccessful()) {
                    BoxOfficeResultBodyVO vo = res.body();
                    Log.d("myLog", "response 성공");

                    BoxOfficeResultVO resultVo = vo.getBoxOfficeResult();
                    List<DailyBoxOfficeVO> list = resultVo.getWeeklyBoxOfficeList();
                    //List<DailyBoxOfficeVO> list2 = vo.getBoxOfficeResult().getWeeklyBoxOfficeList();

                    adapter.setList(list);
                    adapter.notifyDataSetChanged();

                    range.setText(vo.getBoxOfficeResult().getShowRange());
                }else {
                    Log.d("myLog", "response 실패");
                }
            }

            @Override
            public void onFailure(Call<BoxOfficeResultBodyVO> call, Throwable t) {
                Log.d("myLog", "통신 실패");
            }
        });

    }
    //참고 - https://onedaycodeing.tistory.com/65
    //참고 - https://developer.android.com/guide/topics/ui/controls/spinner?hl=ko#java

    public void clkSearchWeek(View v) {
        int day = dpTargetDt.getDayOfMonth();
        int mon = dpTargetDt.getMonth() + 1;
        int year= dpTargetDt.getYear();
        String weekGb;
        String date = String.format("%s%02d%02d",year,mon,day);


        spinner = (Spinner) findViewById(R.id.wkSpinner);


        SpinnerActivity sa = new SpinnerActivity();
//        spinner.setOnItemSelectedListener(sa);
        String strVal = spinner.getSelectedItem().toString(); // 값가져오기.
        switch (strVal){
            case "주간":
                weekGb = "0";
                break;
            case "주중":
                weekGb = "2";
                break;
            default:
                weekGb = "1";
        }
        network(date, weekGb);
        Log.i("myLog", weekGb);
        Log.i("myLog", date);

    }

}
class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}