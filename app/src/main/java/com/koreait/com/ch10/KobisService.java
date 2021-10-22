package com.koreait.com.ch10;

import com.koreait.com.ch10.boxofficemodel.BoxOfficeResultBodyVO;
import com.koreait.com.ch10.searchmoviemodel.MovieListResultBodyVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KobisService {
    //일별 박스오피스
    @GET("boxoffice/searchDailyBoxOfficeList.json")
    Call<BoxOfficeResultBodyVO> boxofficeSearchDailyBoxOfficeList
    (@Query("key") String key, @Query("targetDt") String targetDt);

    //주간 박스오피스
    @GET("boxoffice/searchWeeklyBoxOfficeList.json")
    Call<BoxOfficeResultBodyVO> boxOfficeSearchWeeKBoxOfficeList
    (@Query("key") String key, @Query("targetDt") String targetDt, @Query("weekGb") String weekGb);

    //영화목록
    @GET("movie/searchMovieList.json")
    Call<MovieListResultBodyVO> searchMovieList
    (@Query("key") String key);

}
