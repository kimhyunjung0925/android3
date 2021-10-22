package com.koreait.com.ch10.searchmoviemodel;

import com.koreait.com.ch10.boxofficemodel.DailyBoxOfficeVO;

import java.util.List;

public class MovieListResultVO {
    private String totCnt;
    //private String source;
    private List<MovieListResultVO> movieList;


    public String getTotCnt() { return totCnt; }
    public void setTotCnt(String totCnt) { this.totCnt = totCnt; }

    //public String getSource() { return source; }
    //public void setSource(String source) { this.source = source; }

    public List<MovieListResultVO> getMovieList() { return movieList; }
    public void setMovieList(List<MovieListResultVO> movieList) { this.movieList = movieList; }
}
