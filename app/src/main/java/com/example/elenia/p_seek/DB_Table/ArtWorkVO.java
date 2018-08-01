package com.example.elenia.p_seek.DB_Table;

import android.graphics.drawable.Drawable;

// 작품정보 테이블
public class ArtWorkVO {

    public static final String DB_TABLE_ARTWORK = "table_artWork"; // 작품정보 테이블명
    public static final String ARTID = "artId"; // 작품코드(PK)
    public static final String ARTNAME = "artName"; // 작품명
    public static final String ARTIMGSRC = "artImgSrc"; // 작품이미지 경로
    public static final String ARTCONTENT = "artContent"; // 작품설명
    public static final String ARTLOC = "artLoc"; // 전시관 위치좌표
    public static final String GENRE = "genre"; // 장르

    private int mArtID;
    private String mArtName;
    private String mArtImgSrc;
    private Drawable mArtImg;
    private String mArtContent;
    private double mArtLoc;
    private String mArtGenre;


    public ArtWorkVO(int artID, String artName, String artImgSrc) {
        mArtID = artID;
        mArtName = artName;
        mArtImgSrc = artImgSrc;
    }

    public ArtWorkVO(int artID, String artName, String artImgSrc, String artContent, double artLoc) {
        this(artID, artName, artImgSrc);
        mArtContent = artContent;
        mArtLoc = artLoc;
    }

    public void setArtImg(Drawable artImg) {
        mArtImg = artImg;
    }

    public int getArtID() {
        return mArtID;
    }

    public String getArtName() {
        return mArtName;
    }

    public Drawable getArtImg() {
        return mArtImg;
    }

    public String getArtImgSrc() {
        return mArtImgSrc;
    }

    public String getArtContent() {
        return mArtContent;
    }

    public double getArtLoc() {
        return mArtLoc;
    }

    public String getArtGenre() {
        return mArtGenre;
    }

}
