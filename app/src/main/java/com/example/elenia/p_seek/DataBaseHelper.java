package com.example.elenia.p_seek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.elenia.p_seek.DB_Table.ArtWorkVO;
import com.example.elenia.p_seek.DB_Table.ArtistVO;
import com.example.elenia.p_seek.DB_Table.BannerVO;
import com.example.elenia.p_seek.DB_Table.ChartDetailItemVO;
import com.example.elenia.p_seek.DB_Table.HomeListItemVO;
import com.example.elenia.p_seek.DB_Table.InternalFileManager;
import com.example.elenia.p_seek.LatelyList.LatelyListVO;
import com.example.elenia.p_seek.MyPageList.RecentList.RecentListVO;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_FILE_NAME = "pSeek.db";
    private static final int DB_VERSION = 2;
    private InternalFileManager mInternalFileMgr;

    public DataBaseHelper(Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 작품정보 테이블생성
        String artWorkTable = "CREATE TABLE IF NOT EXISTS " + ArtWorkVO.DB_TABLE_ARTWORK + "( "
                + ArtWorkVO.ARTID + " INTEGER PRIMARY KEY, "
                + ArtWorkVO.ARTNAME + " TEXT , "
                + ArtWorkVO.ARTIMGSRC + " TEXT , "
                + ArtWorkVO.ARTCONTENT + " TEXT , "
                + ArtWorkVO.ARTLOC + " REAL , "
                + ArtWorkVO.GENRE + " TEXT);";

        db.execSQL(artWorkTable);

        // 아티스트 테이블생성
        String artistTable = "CREATE TABLE IF NOT EXISTS " + ArtistVO.DB_TABLE_ARTIST + "( "
                + ArtistVO.ARTISTID + " INTEGER PRIMARY KEY, "
                + ArtistVO.ARTID + " INTEGER , "
                + ArtistVO.ARTISTNAME + " TEXT, "
                + ArtistVO.ARTISTPICSRC + " TEXT, "
                + ArtistVO.PIC_CODE + " INTEGER);";

        db.execSQL(artistTable);

        // 배너 테이블 생성
        String BannerTable = "CREATE TABLE IF NOT EXISTS " + BannerVO.DB_TABLE_BANNER + "( "
                + BannerVO.BANNERID + " INTEGER , "
                + BannerVO.BANNERNAME + " TEXT , "
                + BannerVO.BANNERIMGSRC + " TEXT)";

        db.execSQL(BannerTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion == DB_VERSION) {
            db.execSQL("DROP TABLE IF EXISTS " + ArtWorkVO.DB_TABLE_ARTWORK);
            db.execSQL("DROP TABLE IF EXISTS " + ArtistVO.DB_TABLE_ARTIST);
            db.execSQL("DROP TABLE IF EXISTS " + BannerVO.DB_TABLE_BANNER);

            onCreate(db);
        }
    }

    // 작품 데이터 넣어주기 (초기화)
    public void insertArt() {
        SQLiteDatabase db = getWritableDatabase();
        String [] imgSrcs = {"img01", "img02", "img03", "img04", "img05", "img06", "img07", "img08", "img09", "img10"};
        int [] imgIDs = {R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04, R.drawable.img05, R.drawable.img06, R.drawable.img07, R.drawable.img08, R.drawable.img09, R.drawable.img10};
        String [] artNames = {"Lookout", "Kh02", "Heart Kids", "Whhachho_사랑..", "Teal Rose", "Spring Whale", "The Open Window, Collioure", "Family Tree Round", "여행을 두고 온 풍경 Na-122", "Red Horse"};
        String [] artists = {"카렌 홀링워스", "키스 해링", "로메로 브리토", "이숙자", "알버트 쿠치어", "너대니얼 마서", "앙리 마티스", "모 뮬란", "나윤찬", "너대니얼 마서"};

        mInternalFileMgr = InternalFileManager.getInstance();

        // 첫 기본 데이터로써 10 항목 추가
        for (int i = 0; i < 10; i++) {
            ContentValues artWorkVO = new ContentValues();
            artWorkVO.put(ArtWorkVO.ARTID, 1001 + i);       // ID 값 관련해서 수정할꺼면 1001 대신 다른 것으로
            artWorkVO.put(ArtWorkVO.ARTNAME, artNames[i]);
            artWorkVO.put(ArtWorkVO.ARTIMGSRC, imgSrcs[i]);

            if (mInternalFileMgr != null) mInternalFileMgr.saveInternalImage(imgIDs[i], imgSrcs[i]);

            db.insert(ArtWorkVO.DB_TABLE_ARTWORK, null, artWorkVO);

            ContentValues artistVO = new ContentValues();
            artistVO.put(ArtistVO.ARTID, 1001 + i);       // ID 값 관련해서 수정할꺼면 1001 대신 다른 것으로
            artistVO.put(ArtistVO.ARTISTNAME, artists[i]);
            artistVO.put(ArtistVO.ARTISTID, 2001 + i);

            // 키스 해링 작가의 경우만 사진 추가
            if (artists[i].equals("키스 해링")) {
                artistVO.put(ArtistVO.ARTISTPICSRC, "artist01");
                artistVO.put(ArtistVO.PIC_CODE, 1);
                mInternalFileMgr.saveInternalImage(R.drawable.artist01, "artist01");
            } else artistVO.put(ArtistVO.PIC_CODE, 0);

            db.insert(ArtistVO.DB_TABLE_ARTIST, null, artistVO);
        }

        Log.d("ccc", "insertArt 성공!!!");

        db.close();

        insertPicArtists();
    }

    // 작가 사진 있는 작가 추가 (참조할것이 없기때문에 ID 필드 추가 안함)
    private void insertPicArtists() {
        int [] artistPicResIDs = {R.drawable.artist02, R.drawable.artist03, R.drawable.artist04, R.drawable.artist05, R.drawable.artist06};
        String [] artistNames = {"살바도르 달리", "파블로 피카소", "알렉산드로 멘디니", "앤디 워홀", "모딜리아니"};
        String [] artistPicSrc = {"artist02", "artist03", "artist04", "artist05", "artist06"};

        SQLiteDatabase db = getWritableDatabase();
        mInternalFileMgr = InternalFileManager.getInstance();

        for (int i = 0; i < artistPicResIDs.length; i++) {
            ContentValues artistVO = new ContentValues();
            artistVO.put(ArtistVO.ARTISTNAME, artistNames[i]);
            artistVO.put(ArtistVO.PIC_CODE, 1);
            artistVO.put(ArtistVO.ARTISTPICSRC, artistPicSrc[i]);

            mInternalFileMgr.saveInternalImage(artistPicResIDs[i], artistPicSrc[i]);
            db.insert(ArtistVO.DB_TABLE_ARTIST, null, artistVO);
        }
        db.close();
    }

    // 배너 데이터 넣어주기 (초기화)
    public void insertBanner() {
        SQLiteDatabase db = getWritableDatabase();
        String [] imgSrcs = {"image01", "image02", "image03", "image04"};
        int [] imgIDs = {R.drawable.image01, R.drawable.image02, R.drawable.image03, R.drawable.image04};

        // 첫 기본 데이터로써 4 항목 추가
        for (int i = 0; i < 4; i++) {
            ContentValues cv = new ContentValues();
            cv.put(BannerVO.BANNERID, i + 1); // ID 값을 0부터 시작할꺼면 +1 삭제
            cv.put(BannerVO.BANNERIMGSRC, imgSrcs[i]);

            mInternalFileMgr = InternalFileManager.getInstance();
            if (mInternalFileMgr != null) mInternalFileMgr.saveInternalImage(imgIDs[i], imgSrcs[i]);

            db.insert(BannerVO.DB_TABLE_BANNER, null, cv);
        }

        Log.d("ccc", "insertBanner 성공!!!");
        db.close();
    }

    public ArrayList<ArtWorkVO> selectArtWork() {
        ArrayList<ArtWorkVO> resArr = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();

        String selectSql = "SELECT * FROM " + ArtWorkVO.DB_TABLE_ARTWORK;
        Cursor cursor = db.rawQuery(selectSql, null);

        if (cursor.moveToFirst()) {
            do {
                int awId = cursor.getInt(0);
                String awName = cursor.getString(1);

                ArtWorkVO aw = new ArtWorkVO(awId, awName, null);
                resArr.add(aw);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        Log.d("ccc", "selectArtWork 성공!!!" + resArr.toString());
        return resArr;
    }

    public ArrayList<ArtistVO> selectArtist() {
        ArrayList<ArtistVO> resArr = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();

        String selectSql = "SELECT * FROM " + ArtistVO.DB_TABLE_ARTIST;
        Cursor cursor = db.rawQuery(selectSql, null);

        if (cursor.moveToFirst()) {
            do {
                int atistId = cursor.getInt(0);
                int atId = cursor.getInt(1);
                String atName = cursor.getString(2);

                ArtistVO at = new ArtistVO(atistId, atId, atName);
                resArr.add(at);
            } while (cursor.moveToNext());
        }

        Log.d("ccc", "selectArtist 성공!!!" + resArr.toString());
        cursor.close();
        db.close();

        return resArr;
    }

    // 인기차트 데이터 가져오기
    public  ArrayList<HomeListItemVO> selectHomeList() {
        ArrayList<HomeListItemVO> resArr = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();

        // ARTID를 통해 ArtWorkVO와 ArtistVO table JOIN
        String selectSql = "SELECT awt." + ArtWorkVO.ARTIMGSRC + ", awt." + ArtWorkVO.ARTNAME + ", att." + ArtistVO.ARTISTNAME +
                " FROM " + ArtWorkVO.DB_TABLE_ARTWORK + " AS awt, " + ArtistVO.DB_TABLE_ARTIST + " AS att" +
                " WHERE awt." + ArtWorkVO.ARTID + " = att." + ArtistVO.ARTID;

        Cursor cursor = db.rawQuery(selectSql, null);

        if (cursor.moveToFirst()) {
            do {
                // 작품이름 가져오기
                String ArtName = cursor.getString(1);
                // 작가이름 가져오기
                String ArtistName = cursor.getString(2);

                // 이미지 가져오기
                mInternalFileMgr = InternalFileManager.getInstance();
                if(mInternalFileMgr != null) {
                    String imgSrc = cursor.getString(0);
                    Drawable img = mInternalFileMgr.loadInternalImage(imgSrc);

                    // HomeListItemVo ArrayList 에 추가하기
                    HomeListItemVO vo = new HomeListItemVO(imgSrc, ArtName, ArtistName);
                    vo.setArt(img);
                    resArr.add(vo);
                }
            } while (cursor.moveToNext());
        }

        Log.d("ccc", "ArrayList HomeListItem 성공");
        cursor.close();
        db.close();

        return resArr;
    }

    // ChartDetailView 변환
    public  ArrayList<ChartDetailItemVO> selectChartDetailViewChange() {
        ArrayList<ChartDetailItemVO> resArr = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();

        String selectSql = "SELECT aw." + ArtWorkVO.ARTIMGSRC + ", aw." + ArtWorkVO.ARTNAME + ", at." + ArtistVO.ARTISTNAME +
                " FROM " + ArtWorkVO.DB_TABLE_ARTWORK + " AS aw, " + ArtistVO.DB_TABLE_ARTIST + " AS at" +
                " WHERE aw." + ArtWorkVO.ARTID + " = at." + ArtistVO.ARTID ;
        Cursor cursor = db.rawQuery(selectSql, null);

        if (cursor.moveToFirst()) {
            do {
                // 작품이름 가져오기
                String ArtName = cursor.getString(1);
                // 작가이름 가져오기
                String ArtistName = cursor.getString(2);
                // 이미지 가져오기
                mInternalFileMgr = InternalFileManager.getInstance();
                if(mInternalFileMgr != null) {
                    String imgSrc = cursor.getString(0);
                    Drawable img = mInternalFileMgr.loadInternalImage(imgSrc);

                    // HomeListItemVo ArrayList 에 추가하기
                    ChartDetailItemVO vo = new ChartDetailItemVO(imgSrc, ArtName, ArtistName);
                    vo.setArt(img);
                    Log.d("ccc", vo.toString());
                    resArr.add(vo);
                }
            } while (cursor.moveToNext());
        }
        Log.d("ccc", "ArrayList ChartDetailItem 성공");
        cursor.close();
        db.close();
        return resArr;
    }

    // 배너객체 조회
    public ArrayList<BannerVO> selectBanner() {
        ArrayList<BannerVO> resArr = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();

        String selectSql = "SELECT * FROM " + BannerVO.DB_TABLE_BANNER;
        Cursor cursor = db.rawQuery(selectSql, null);
        Log.d("ccc", "selectBanner: " + cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                int banId = cursor.getInt(cursor.getColumnIndex(BannerVO.BANNERID));
                String banName = cursor.getString(cursor.getColumnIndex(BannerVO.BANNERNAME));

                mInternalFileMgr = InternalFileManager.getInstance();
                if(mInternalFileMgr != null) {
                    String imgSrc = cursor.getString(cursor.getColumnIndex(BannerVO.BANNERIMGSRC));
                    Drawable img = mInternalFileMgr.loadInternalImage(imgSrc);
                    BannerVO vo = new BannerVO(banId, banName, imgSrc);
                    vo.setBanImg(img);

                    resArr.add(vo);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  resArr;
    }

    // 최신차트 리스트
    public  ArrayList<LatelyListVO> selectLatelyList() {
        ArrayList<LatelyListVO> resArr = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();

        String selectSql = "SELECT aw." + ArtWorkVO.ARTIMGSRC + ", aw." + ArtWorkVO.ARTNAME + ", at." + ArtistVO.ARTISTNAME +
                " FROM " + ArtWorkVO.DB_TABLE_ARTWORK + " AS aw, " + ArtistVO.DB_TABLE_ARTIST + " AS at" +
                " WHERE aw." + ArtWorkVO.ARTID + " = at." + ArtistVO.ARTID ;
        Cursor cursor = db.rawQuery(selectSql, null);

        if (cursor.moveToFirst()) {
            do {
                // 작품이름 가져오기
                String ArtName = cursor.getString(1);
                // 작가이름 가져오기
                String ArtistName = cursor.getString(2);
                // 이미지 가져오기
                mInternalFileMgr = InternalFileManager.getInstance();
                if(mInternalFileMgr != null) {
                    String imgSrc = cursor.getString(0);
                    Drawable img = mInternalFileMgr.loadInternalImage(imgSrc);

                    // HomeListItemVo ArrayList 에 추가하기
                    LatelyListVO vo = new LatelyListVO(imgSrc, ArtName, ArtistName);
                    vo.setArt(img);
                    Log.d("ccc", vo.toString());
                    resArr.add(vo);
                }

            } while (cursor.moveToNext());
        }

        Log.d("ccc", "ArrayList LatelyListItem 성공");

        cursor.close();
        db.close();
        return resArr;
    }

    // 최근감상 리스트
    public  ArrayList<RecentListVO> selectRecentList() {
        ArrayList<RecentListVO> resArr = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();
        mInternalFileMgr = InternalFileManager.getInstance();

        String selectSql = "SELECT aw." + ArtWorkVO.ARTIMGSRC + ", aw." + ArtWorkVO.ARTNAME + ", at." + ArtistVO.ARTISTNAME +
                " FROM " + ArtWorkVO.DB_TABLE_ARTWORK + " AS aw, " + ArtistVO.DB_TABLE_ARTIST + " AS at" +
                " WHERE aw." + ArtWorkVO.ARTID + " = at." + ArtistVO.ARTID ;
        Cursor cursor = db.rawQuery(selectSql, null);

        if (cursor.moveToFirst()) {
            do {
                // 작품이름 가져오기
                String ArtName = cursor.getString(1);
                // 작가이름 가져오기
                String ArtistName = cursor.getString(2);
                // 이미지 가져오기
                if(mInternalFileMgr != null) {
                    String imgSrc = cursor.getString(0);
                    Drawable img = mInternalFileMgr.loadInternalImage(imgSrc);

                    // HomeListItemVo ArrayList 에 추가하기
                    RecentListVO vo = new RecentListVO(img, ArtName, ArtistName);
                    Log.d("ccc", vo.toString());
                    resArr.add(vo);
                }

            } while (cursor.moveToNext());
        }

        Log.d("ccc", "ArrayList RecentListItem 성공");
        cursor.close();
        db.close();
        return resArr;
    }

    // 아티스트 리스트
    public ArrayList<ArtistVO>  selectArtistList() {
        ArrayList<ArtistVO> resArr = new ArrayList();

        SQLiteDatabase db = getReadableDatabase();
        mInternalFileMgr = InternalFileManager.getInstance();

        String selectSql = "SELECT at." + ArtistVO.ARTISTNAME + ", at." + ArtistVO.ARTISTPICSRC +
                " FROM " + ArtistVO.DB_TABLE_ARTIST + " AS at" +
                " WHERE at." + ArtistVO.PIC_CODE + " = " + 1;
        Log.d("ddd", "selectArtistList: 쿼리 \n" + selectSql);
        Cursor cursor = db.rawQuery(selectSql, null);
        Log.d("ddd", "selectArtistList: Cursor Cnt " + cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                // 아티스트이름 가져오기
                String ArtistName = cursor.getString(0);
                //int picCode = cursor.getInt(2);
                // 이미지 가져오기
                if(mInternalFileMgr != null) {
                    String imgSrc = cursor.getString(1);
                    Drawable img = mInternalFileMgr.loadInternalImage(imgSrc);

                    // HomeListItemVo ArrayList 에 추가하기
                    ArtistVO vo = new ArtistVO(ArtistName, imgSrc);
                    vo.setArtistPic(img);
                    Log.d("ccc", vo.toString());
                    resArr.add(vo);
                }
            } while (cursor.moveToNext());
        }
        Log.d("ccc", "ArrayList ArtistListItem 성공");

        cursor.close();
        db.close();
        return resArr;
    }
}
