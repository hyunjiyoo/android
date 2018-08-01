package com.example.elenia.p_seek.MyPageList.RecentList;

import android.graphics.drawable.Drawable;

public class RecentListVO {

    private Drawable img; // 작품 이미지
    private String artName; // 작품이름
    private String artistName; // 작가이름


    public RecentListVO (Drawable img, String artName, String artistName) {
        this.img = img;
        this.artName = artName;
        this.artistName = artistName;
    }

    public Drawable getImg() {
        return img;
    }

    public String getArtName() {
        return artName;
    }

    public String getArtistName() {
        return artistName;
    }


    public String toString() {
        String string = "이미지: " + getImg() + ", 작가이름: " + getArtistName() + ", 작품이름: " + getArtName();

        return  string;
    }
}
