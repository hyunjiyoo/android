package com.example.elenia.p_seek.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.elenia.p_seek.Banner.MyViewPagerAdapter;
import com.example.elenia.p_seek.DB_Table.HomeListItemVO;
import com.example.elenia.p_seek.DataBaseHelper;
import com.example.elenia.p_seek.LatelyList.LatelyListAdapter;
import com.example.elenia.p_seek.LatelyList.LatelyListVO;
import com.example.elenia.p_seek.List_Item.FragmentListItem;
import com.example.elenia.p_seek.R;
import com.example.elenia.p_seek.fragment.FragmentChartDetail;

import java.util.ArrayList;

public class FragmentHome extends Fragment implements View.OnClickListener {

    DataBaseHelper dbHelper;
    ViewPager viewPager;

    ImageView img01, img02, img03, img04, img05;
    TextView awName01, awName02, awName03, awName04, awName05;
    TextView atName01, atName02, atName03, atName04, atName05;

    LinearLayout c_layout1, c_layout2, c_layout3, c_layout4, c_layout5;

    TextView txt_list;

    RecyclerView lately_list;

    FragmentManager fm;
    FragmentTransaction ft;
    FragmentChartDetail fragChart;
    FragmentListItem listItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        txt_list = (TextView) rootView.findViewById(R.id.txt_list);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);

        img01 = (ImageView)rootView.findViewById(R.id.chart_img01);
        img02 = (ImageView)rootView.findViewById(R.id.chart_img02);
        img03 = (ImageView)rootView.findViewById(R.id.chart_img03);
        img04 = (ImageView)rootView.findViewById(R.id.chart_img04);
        img05 = (ImageView)rootView.findViewById(R.id.chart_img05);

        awName01 = (TextView) rootView.findViewById(R.id.chart_awName01);
        awName02 = (TextView) rootView.findViewById(R.id.chart_awName02);
        awName03 = (TextView) rootView.findViewById(R.id.chart_awName03);
        awName04 = (TextView) rootView.findViewById(R.id.chart_awName04);
        awName05 = (TextView) rootView.findViewById(R.id.chart_awName05);

        atName01 = (TextView) rootView.findViewById(R.id.chart_atName01);
        atName02 = (TextView) rootView.findViewById(R.id.chart_atName02);
        atName03 = (TextView) rootView.findViewById(R.id.chart_atName03);
        atName04 = (TextView) rootView.findViewById(R.id.chart_atName04);
        atName05 = (TextView) rootView.findViewById(R.id.chart_atName05);

        c_layout1 = (LinearLayout) rootView.findViewById(R.id.chart_layout1);
        c_layout2 = (LinearLayout) rootView.findViewById(R.id.chart_layout2);
        c_layout3 = (LinearLayout) rootView.findViewById(R.id.chart_layout3);
        c_layout4 = (LinearLayout) rootView.findViewById(R.id.chart_layout4);
        c_layout5 = (LinearLayout) rootView.findViewById(R.id.chart_layout5);

        lately_list = (RecyclerView) rootView.findViewById(R.id.lately_list);

        dbHelper = new DataBaseHelper(getContext());

        // 배너 어댑터 설정
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(rootView.getContext(), dbHelper.selectBanner());
        viewPager.setAdapter(adapter);

        // 차트리스트 보기 (More클릭)
        txt_list.setOnClickListener(this);

        // 차트 데이터 세팅
        ArrayList<HomeListItemVO> arrayHomeListItem = dbHelper.selectHomeList();

        HomeListItemVO vo = arrayHomeListItem.get(0);
        img01.setImageDrawable(vo.getArt());
        awName01.setText(vo.getArtName());
        atName01.setText(vo.getArtistName());

        vo = arrayHomeListItem.get(1);
        img02.setImageDrawable(vo.getArt());
        awName02.setText(vo.getArtName());
        atName02.setText(vo.getArtistName());

        vo = arrayHomeListItem.get(2);
        img03.setImageDrawable(vo.getArt());
        awName03.setText(vo.getArtName());
        atName03.setText(vo.getArtistName());

        vo = arrayHomeListItem.get(3);
        img04.setImageDrawable(vo.getArt());
        awName04.setText(vo.getArtName());
        atName04.setText(vo.getArtistName());

        vo = arrayHomeListItem.get(4);
        img05.setImageDrawable(vo.getArt());
        awName05.setText(vo.getArtName());
        atName05.setText(vo.getArtistName());

        // 차트 클릭시 상세페이지 전환
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        fragChart = new FragmentChartDetail();

        c_layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.replace(R.id.home, fragChart);
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putInt("chart_img", 1);
                fragChart.setArguments(bundle);
                ft.commit();
            }
        });
        c_layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.replace(R.id.home, fragChart);
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putInt("chart_img", 2);
                fragChart.setArguments(bundle);
                ft.commit();
            }
        });
        c_layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.replace(R.id.home, fragChart);
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putInt("chart_img", 3);
                fragChart.setArguments(bundle);
                ft.commit();
            }
        });
        c_layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.replace(R.id.home, fragChart);
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putInt("chart_img", 4);
                fragChart.setArguments(bundle);
                ft.commit();
            }
        });
        c_layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.replace(R.id.home, fragChart);
                ft.addToBackStack(null);
                Bundle bundle = new Bundle();
                bundle.putInt("chart_img", 5);
                fragChart.setArguments(bundle);
                ft.commit();
            }
        });

        // 최신차트 리스트
        LinearLayoutManager linearManager = new LinearLayoutManager(rootView.getContext());
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        lately_list.setLayoutManager(linearManager);

        ArrayList<LatelyListVO> arrayLatelyList = dbHelper.selectLatelyList();
        LatelyListAdapter latelyAdapter = new LatelyListAdapter(getContext(), arrayLatelyList, R.layout.lately_list);
        lately_list.setAdapter(latelyAdapter);

        return rootView;
    }

    // More 클릭시 화면전환 이벤트
    @Override
    public void onClick(View v) {
        if (v == txt_list) {
            listItem = new FragmentListItem();
            ft.replace(R.id.home, listItem);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
