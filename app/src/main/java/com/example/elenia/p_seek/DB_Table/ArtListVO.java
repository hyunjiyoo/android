package com.example.elenia.p_seek.DB_Table;


import android.graphics.drawable.Drawable;

public class ArtListVO {

    public static final String DB_TABLE_ARTLIST = "table_artList"; // 작품리스트 테이블명
    public static final String ARTID = "artId"; // 작품코드
    public static final String ARTIST_ID = "artist_Id"; // 아티스트코드
    public static final String RANKING = "ranking"; // 랭킹

    private int mArtID;
    private int mArtistID;
    private int mRanking;

    public ArtListVO (int artId, int artistId, int ranking) {
        mArtID = artId;
        mArtistID = artistId;
        mRanking = ranking;
    }

    public int getmArtID() {
        return mArtID;
    }

    public int getmArtistID() {
        return mArtistID;
    }

    public int getmRanking() {
        return mRanking;
    }
}
