package com.koreait.com.ch10;

public class DailyBoxOfficeVO {
    private String rank; //영화순위
    private String movieNm; //영화이름
    private String openDt; //개봉일

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getMovieNm() {
        return movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getOpenDt() {
        return openDt;
    }

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }
}
