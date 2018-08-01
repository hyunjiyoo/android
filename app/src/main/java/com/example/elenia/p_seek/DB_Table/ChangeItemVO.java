package com.example.elenia.p_seek.DB_Table;

import android.graphics.drawable.Drawable;

public class ChangeItemVO {
    private String mArtName;
    private String mArtistName;

    public ChangeItemVO(String ArtName, String ArtistName) {
        mArtName = ArtName;
        mArtistName = ArtistName;
    }

    public String getArtName() {
        return mArtName;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String toString() {
        String string = "작품이름: " + getArtName() + ", 작가이름: " + getArtistName();
        return  string;
    }

}
