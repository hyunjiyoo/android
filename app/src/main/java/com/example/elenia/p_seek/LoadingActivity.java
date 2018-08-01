package com.example.elenia.p_seek;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.example.elenia.p_seek.DB_Table.ArtWorkVO;
import com.example.elenia.p_seek.DB_Table.BannerVO;
import com.example.elenia.p_seek.DB_Table.InternalFileManager;
import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loading_activity);

        createDB();
        callNextActivity();
    }

    private void createDB() {
        InternalFileManager internalMgr = InternalFileManager.getInstance(this);
        DataBaseHelper dbHelper = new DataBaseHelper(this);

        ArrayList<ArtWorkVO> artWorkList = dbHelper.selectArtWork();
        if (artWorkList.size() == 0) dbHelper.insertArt();

        ArrayList<BannerVO> bannerList = dbHelper.selectBanner();
        if (bannerList.size() == 0) dbHelper.insertBanner();

        Log.d("ccc", "모든항목 추가 완료");
    }

    private void callNextActivity() {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                finish();
            }
        };
        handler.postDelayed(r, 1000);
    }
}
