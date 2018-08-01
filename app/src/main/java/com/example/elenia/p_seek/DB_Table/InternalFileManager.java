package com.example.elenia.p_seek.DB_Table;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.LruCache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class InternalFileManager {

    private static InternalFileManager mInstance;
    private Context mCtx;
    private LruCache<String, Bitmap> mMemoryCache;

    public static InternalFileManager getInstance() {
        return mInstance;
    }

    public static InternalFileManager getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new InternalFileManager();
            mInstance.mCtx = ctx.getApplicationContext();

            // 최대의 가용 메모리양을 구한다. 이를 초과하는 메모리 사용은 메모리 부족 오류를 야기하게 된다.
            // 킬로바이트 단위의 정수 값으로 LruCache를 설정한다.
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

            // 최대 가용 메모리의 1/8에 해당하는 만큼 캐시사이즈를 할당한다
            final int cacheSize = maxMemory / 8;

            mInstance.mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    // 아이템 수가 아닌 킬로바이트 단위로 캐시 사이즈를 측정합니다.
                    return bitmap.getByteCount() / 1024;
                }
            };
        }
        return mInstance;
    }

    // 내부 저장소에 이미지 저장
    public boolean saveInternalImage(int imgId, String fileName) {
        FileOutputStream fos;

        Bitmap bitmap = BitmapFactory.decodeResource(mCtx.getResources(), imgId);

        try {
            fos = mCtx.openFileOutput(fileName, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 파일명으로 내부저장소의 이미지 가져오기
    public Drawable loadInternalImage(final String fileName) {
        Drawable resDrawable = null;
        // 캐시에 이미지 가져오기
        final Bitmap cacheingBitmap = getBitmapFromMemCache(fileName);

        if (cacheingBitmap != null) {
            // 캐시에 이미지 존재시 해당 이미지로 반환
            resDrawable = new BitmapDrawable(mCtx.getResources(), cacheingBitmap);
        } else {
            // 캐시에 이미지 없을때 내부저장소에서 불러온 이미지 반환
            try {
                File filePath = mCtx.getFileStreamPath(fileName);
                FileInputStream fi = new FileInputStream(filePath);
                Bitmap bitmap = BitmapFactory.decodeStream(fi);
                resDrawable = new BitmapDrawable(mCtx.getResources(), bitmap);

                // 내부저장소에서 가져온 이미지 백그라운드로 캐시에 저장
                BitmapWorkerTask task = new BitmapWorkerTask();
                ImgDic imgDic = new ImgDic();
                imgDic.bitmap = bitmap;
                imgDic.imgName = fileName;
                task.execute(imgDic);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resDrawable;
    }

    // 비트맵 이미지를 캐시에 저장
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    // 캐시에서 비트맵 이미지 불러오기
    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

    // 백그라운드로 캐시에 이미지 기록
    class BitmapWorkerTask extends AsyncTask<ImgDic, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(ImgDic... imgDics) {
            addBitmapToMemoryCache(imgDics[0].imgName, imgDics[0].bitmap);
            return imgDics[0].bitmap;
        }
    }

    // 백그라운드로 캐시에 이미지 저장할 VO 객체
    class ImgDic {
        String imgName;
        Bitmap bitmap;
    }
}