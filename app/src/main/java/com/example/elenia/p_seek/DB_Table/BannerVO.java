package com.example.elenia.p_seek.DB_Table;

import android.graphics.drawable.Drawable;

public class BannerVO {

    public static final String DB_TABLE_BANNER = "table_banner"; // 배너 테이블명
    public static final String BANNERID = "bannerId"; // 배너코드
    public static final String BANNERNAME = "bannerName"; // 작품명
    public static final String BANNERIMGSRC = "bannerImgSrc"; // 작품이미지

    private int mBanId;
    private String mBanName;
    private String mBanImgSrc;
    private Drawable mBanImg;

    public BannerVO(int ban_id, String banName, String banImgSrc){
        mBanId = ban_id;
        mBanName = banName;
        mBanImgSrc = banImgSrc;
    }

    public void setBanImg(Drawable banImg) {
        mBanImg = banImg;
    }

    public int getBanId() { return mBanId; }

    public String getBanName() {
        return mBanName;
    }

    public String getBanImgSrc() {
        return mBanImgSrc;
    }

    public Drawable getBanImg() {
        return mBanImg;
    }

    @Override
    public String toString() {
        return String.format("bannerVO 성공!!");
    }
}
