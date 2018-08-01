package com.example.elenia.p_seek.Banner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.elenia.p_seek.DB_Table.BannerVO;
import com.example.elenia.p_seek.R;

import java.util.ArrayList;

public class MyViewPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private ArrayList<BannerVO> mBannerArr;

    public MyViewPagerAdapter(Context ctx, ArrayList<BannerVO> bannerArr) {
        super();
        mLayoutInflater = LayoutInflater.from(ctx);
        mBannerArr = bannerArr;
    }

    @Override
    public int getCount() {
        return mBannerArr.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = mLayoutInflater.inflate(R.layout.item_banner, null);
        ImageView iv = v.findViewById(R.id.banner_img);
        iv.setImageDrawable(mBannerArr.get(position).getBanImg());
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
