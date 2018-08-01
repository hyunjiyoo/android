package com.example.elenia.p_seek.DB_Table;

import android.graphics.drawable.Drawable;

public class ChartDetailItemVO {

    private String mArtSrc;
    private Drawable mArt; // 작품 이미지
    private String mArtistName; // 작가이름
    private String mArtName; // 작품이름
    private String mArtContent; // 작품설명
    private double mArtLoc; // 전시회 위치 좌표값
    private String mArtLocAddress;
    private String mArtLocCallNum;
    private String mArtReview;

    public ChartDetailItemVO (String artSrc, String artName, String artistName) {
        mArtSrc = artSrc;
        mArtName = artName;
        mArtistName = artistName;
    }

    public void setArt(Drawable art) {
        mArt = art;
    }

    public String getArtSrc() {
        return mArtSrc;
    }

    public Drawable getArt() {
        return mArt;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getArtName() {
        return mArtName;
    }

    public String getArtContent() {
        return mArtContent;
    }

    public double getArtLoc() {
        return mArtLoc;
    }

    public String getArtLocAddress() {
        return mArtLocAddress;
    }

    public String getArtLocCallNum() {
        return mArtLocCallNum;
    }

    public String getArtReview() {
        return mArtReview;
    }
}
