package com.example.elenia.p_seek.DB_Table;

import android.graphics.drawable.Drawable;


public class HomeListItemVO {

    private String mArtSrc;
    private Drawable mArt;
    private String mArtName;
    private String mArtistName;


    public HomeListItemVO(String artSrc, String ArtName, String ArtistName) {
        mArtSrc = artSrc;
        mArtName = ArtName;
        mArtistName = ArtistName;
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

    public String getArtName() {
        return mArtName;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String toString() {
        String string = "이미지: " + getArt() + ", 이미지경로: " + getArtSrc() + ", 작품이름: " + getArtName() + ", 작가이름: " + getArtistName();

        return  string;
    }
}
