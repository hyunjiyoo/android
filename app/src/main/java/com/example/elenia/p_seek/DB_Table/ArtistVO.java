package com.example.elenia.p_seek.DB_Table;

import android.graphics.drawable.Drawable;
import android.widget.Toast;

// 아티스트 테이블
public class ArtistVO {

    public static final String DB_TABLE_ARTIST = "table_artist"; // 아티스트 테이블명
    public static final String ARTISTID = "artistId"; // 아티스트코드(PK)
    public static final String ARTID = "artId"; // 작품코드
    public static final String ARTISTNAME = "artistName"; // 아티스트이름
    public static final String ARTISTPICSRC = "artistPicSrc"; // 아티스트얼굴 이미지 경로
    public static final String PIC_CODE = "pic_code"; // 사진여부 (있으면, 1)

    private int mArtistID;
    private int mArtID;
    private String mArtistName;
    private String mArtistPicSrc;
    private Drawable mArtistPic;
    private boolean picCode;

    public ArtistVO (String artistName, String artistPicSrc) {
        mArtistName = artistName;
        mArtistPicSrc = artistPicSrc;
    }

    public ArtistVO (int artistId, int artId, String artName) {
        mArtistID = artistId;
        mArtID = artId;
        mArtistName = artName;
    }

    public ArtistVO (int artistId, int artId, String artistName, String artistPicSrc) {
        this(artistId, artId, artistName);
        mArtistPicSrc = artistPicSrc;
        picCode = true;
    }

    public void setArtistPic(Drawable artistPic) {
        mArtistPic = artistPic;
    }

    public int getArtistID() {
        return mArtistID;
    }

    public int getArtID() {
        return mArtID;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public String getArtistPicSrc() {
        return mArtistPicSrc;
    }

    public Drawable getArtistPic() {
        return mArtistPic;
    }

    public boolean getPicCode() {
        return picCode;
    }

    @Override
    public String toString() {
        return String.format("Artist_ID : %d\n Artist_NAME : %s\n Pic_Code : %s", mArtistID, mArtistName, picCode ? "Y" : "N");
    }
}
