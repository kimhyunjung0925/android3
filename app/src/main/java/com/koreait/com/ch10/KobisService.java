package com.koreait.com.ch10;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KobisService {
    //일별 박스오피스
    @GET("boxoffice/searchDailyBoxOfficeList.json")
    Call<BoxOfficeResultBodyVO> boxofficeSearchDailyBoxOfficeList
    (@Query("key") String key, @Query("targetDt") String targetDt);

    //주간 박스오피스
    @GET("boxoffice/searchWeeklyBoxOfficeList.json?weekGb=0")
    Call<BoxOfficeResultBodyVO> SearchWeeklyBoxOfficeList
    (@Query("key") String key, @Query("targetDt") String targetDt);

}
