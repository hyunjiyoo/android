package com.example.elenia.p_seek.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.elenia.p_seek.DB_Table.ArtistVO;
import com.example.elenia.p_seek.DataBaseHelper;
import com.example.elenia.p_seek.MainActivity;
import com.example.elenia.p_seek.MyPageList.ArtistListAdapter;
import com.example.elenia.p_seek.MyPageList.RecentList.RecentListAdapter;
import com.example.elenia.p_seek.MyPageList.RecentList.RecentListVO;
import com.example.elenia.p_seek.R;
import com.example.elenia.p_seek.fragment.FragmentHome;

import java.util.ArrayList;

public class FragmentMyPage2 extends Fragment{

    Button logout_btn;
    FragmentManager fm;
    FragmentTransaction ft;
    FragmentHome fraghome;

    RecyclerView recent_list, artist_list, pick_list;
    DataBaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage2, container, false);

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        logout_btn = (Button) rootView.findViewById(R.id.logout_btn2);
        recent_list = (RecyclerView) rootView.findViewById(R.id.recent_list);
        artist_list = (RecyclerView) rootView.findViewById(R.id.artist_list);
        pick_list = (RecyclerView) rootView.findViewById(R.id.pick_list);

        // 로그아웃 버튼 클릭시 이벤트 발생
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm = getFragmentManager();
                ft = fm.beginTransaction();

                fraghome = new FragmentHome();

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setLogin(0);
                mainActivity.setPage(0);

                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 모든 스택 초기화
                ft.replace(R.id.home, fraghome);
                ft.addToBackStack(null); // 홈 프래그먼트 스택 저장
                ft.commit();
            }
        });

        // 최근 감상작
        LinearLayoutManager linearManager = new LinearLayoutManager(rootView.getContext());
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recent_list.setLayoutManager(linearManager);

        dbHelper = new DataBaseHelper(getContext());
        ArrayList<RecentListVO> recentListVO = dbHelper.selectRecentList();
        RecentListAdapter recentListAdapter = new RecentListAdapter(getContext(), recentListVO, R.layout.lately_list);
        recent_list.setAdapter(recentListAdapter);


        // 아티스트 리스트
        LinearLayoutManager a_linearManager = new LinearLayoutManager(rootView.getContext());
        a_linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        artist_list.setLayoutManager(a_linearManager);

        ArrayList<ArtistVO> artistVO = dbHelper.selectArtistList();
        ArtistListAdapter artistListAdapter = new ArtistListAdapter(getContext(), artistVO, R.layout.artist_item);
        artist_list.setAdapter(artistListAdapter);


        // 작품 PICK 리스트
        LinearLayoutManager p_linearManager = new LinearLayoutManager(rootView.getContext());
        p_linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        pick_list.setLayoutManager(p_linearManager);

        ArrayList<RecentListVO> pickListVO = dbHelper.selectRecentList();
        RecentListAdapter pickListAdapter = new RecentListAdapter(getContext(), pickListVO, R.layout.lately_list);
        pick_list.setAdapter(pickListAdapter);


        return rootView;
    }
}
