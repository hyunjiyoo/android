package com.example.elenia.p_seek.LatelyList;

import android.graphics.drawable.Drawable;


public class LatelyListVO {
    private String mArtSrc;
    private Drawable mArt; // 작품 이미지
    private String mArtName; // 작품이름
    private String mArtistName; // 작가이름


    public LatelyListVO (String artSrc, String artName, String artistName) {
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

    public String toString() {
        String string = "이미지: " + getArt() + ", 이미지경로: " + getArtSrc() + ", 작가이름: " + getArtistName() + ", 작품이름: " + getArtName();

        return  string;
    }
}
