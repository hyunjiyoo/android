package com.example.elenia.p_seek.List_Item;

import android.graphics.drawable.Drawable;

public class ListItemVO {

    Drawable image;
    String artName;
    String artistName;

    ListItemVO(Drawable image, String artName, String artistName) {
        this.image = image;
        this.artName = artName;
        this.artistName = artistName;
    }

    public Drawable getImage() {
        return image;
    }

    public String getArtName() {
        return artName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
