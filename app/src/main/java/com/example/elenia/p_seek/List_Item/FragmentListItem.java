package com.example.elenia.p_seek.List_Item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elenia.p_seek.DB_Table.HomeListItemVO;
import com.example.elenia.p_seek.DataBaseHelper;
import com.example.elenia.p_seek.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentListItem extends Fragment {

    DataBaseHelper dbHelper;
    RecyclerView recyclerView;
    List<ListItemVO> listItem;
    HomeListItemVO vo;
    ListItemVO[] item;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_item_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        dbHelper = new DataBaseHelper(getContext());

        // recyclerView에 layoutManager 설정
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // ListItemVO에서 받아온 객체를 arrayList에 저장
        listItem = new ArrayList<>();
        ArrayList<HomeListItemVO> arrayHomeListItem = dbHelper.selectHomeList();
        item = new ListItemVO[10];

        for (int i = 0; i < item.length; i++) {
            vo = arrayHomeListItem.get(i);
            item[i] = new ListItemVO(vo.getArt(), vo.getArtName(), vo.getArtistName());
            listItem.add(item[i]);
        }

        // recyclerView에 어댑터 설정
        recyclerView.setAdapter(new RecyclerAdapter(getContext(), listItem, R.layout.list_item));
        return rootView;
    }

}
