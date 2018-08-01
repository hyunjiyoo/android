package com.example.elenia.p_seek.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elenia.p_seek.DB_Table.ChangeItemVO;
import com.example.elenia.p_seek.DB_Table.ChartDetailItemVO;
import com.example.elenia.p_seek.DataBaseHelper;
import com.example.elenia.p_seek.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;

public class FragmentChartDetail extends Fragment implements OnMapReadyCallback {

    DataBaseHelper dbHelper;
    public GoogleMap mMap;
    ImageView appbar_img;
    TextView artName;
    TextView artistName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.chart_detail, container, false);

        MapView mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        appbar_img = (ImageView)rootView.findViewById(R.id.appbar_image);
        artName = (TextView) rootView.findViewById(R.id.chart_artName);
        artistName = (TextView)rootView.findViewById(R.id.chart_artistName);

        // Fragment에서 데이터 전송
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int flag = bundle.getInt("chart_img");
            ArrayList<String> adapterFlag = bundle.getStringArrayList("art");

            // HomeFragment에서 피식차트 눌렀을 때 아이템 하나당 발생하는 이벤트
            switch (flag) {
                case 1: changeDetailView(flag);
                    break;
                case 2: changeDetailView(flag);
                    break;
                case 3: changeDetailView(flag);
                    break;
                case 4: changeDetailView(flag);
                    break;
                case 5: changeDetailView(flag);
                    break;
            }

            // 최근 감상작리스트 등 해당 작품 클릭시 해당 작품의 상세페이지 전환
            if ( adapterFlag != null) {
                changedAdapter(adapterFlag);
            }
        }
        return rootView;
    }

    // 리스트에서 상세페이지 화면전환
    public void changedAdapter(ArrayList<String> data) {
        String a = data.get(0); // artName
        String b = data.get(1); // artistName

        dbHelper = new DataBaseHelper(getContext());
        ArrayList<ChartDetailItemVO> arrayChartDetail = dbHelper.selectChartDetailViewChange();

        for(int i = 0; i < arrayChartDetail.size(); i++) {
            ChartDetailItemVO vo = arrayChartDetail.get(i);

            if (a.equals(vo.getArtName()) && b.equals(vo.getArtistName())) {
                appbar_img.setImageDrawable(vo.getArt());
                artName.setText(vo.getArtName());
                artistName.setText(vo.getArtistName());
            }
        }
    }

    // 차트리스트에서 상세페이지 화면전환
    public void changeDetailView (int index) {
        switch (index){
            case  1: {
                dbHelper = new DataBaseHelper(getContext());
                ArrayList<ChartDetailItemVO> arrayChartDetail = dbHelper.selectChartDetailViewChange();
                ChartDetailItemVO vo = arrayChartDetail.get(0);
                appbar_img.setImageDrawable(vo.getArt());
                artName.setText(vo.getArtName());
                artistName.setText(vo.getArtistName());
                break;
            }
            case  2: {
                dbHelper = new DataBaseHelper(getContext());
                ArrayList<ChartDetailItemVO> arrayChartDetail = dbHelper.selectChartDetailViewChange();
                ChartDetailItemVO vo = arrayChartDetail.get(1);
                appbar_img.setImageDrawable(vo.getArt());
                artName.setText(vo.getArtName());
                artistName.setText(vo.getArtistName());
                break;
            }
            case  3: {
                dbHelper = new DataBaseHelper(getContext());
                ArrayList<ChartDetailItemVO> arrayChartDetail = dbHelper.selectChartDetailViewChange();
                ChartDetailItemVO vo = arrayChartDetail.get(2);
                appbar_img.setImageDrawable(vo.getArt());
                artName.setText(vo.getArtName());
                artistName.setText(vo.getArtistName());
                break;
            }
            case  4: {
                dbHelper = new DataBaseHelper(getContext());
                ArrayList<ChartDetailItemVO> arrayChartDetail = dbHelper.selectChartDetailViewChange();
                ChartDetailItemVO vo = arrayChartDetail.get(3);
                appbar_img.setImageDrawable(vo.getArt());
                artName.setText(vo.getArtName());
                artistName.setText(vo.getArtistName());
                break;
            }
            case  5: {
                dbHelper = new DataBaseHelper(getContext());
                ArrayList<ChartDetailItemVO> arrayChartDetail = dbHelper.selectChartDetailViewChange();
                ChartDetailItemVO vo = arrayChartDetail.get(4);
                appbar_img.setImageDrawable(vo.getArt());
                artName.setText(vo.getArtName());
                artistName.setText(vo.getArtistName());
                break;
            }
        }

    }

    @Override
    public void onMapReady(GoogleMap map) {

        mMap = map;

        // 새롬관 위치 좌표값
        LatLng Searom = new LatLng(37.450714, 127.127104);

        MarkerOptions markerOptions = new MarkerOptions();

        // 새롬관위치에 마커지정 및 카메라 이동
        markerOptions.position(Searom);
        markerOptions.title("새롬관");
        markerOptions.snippet("가천대 새롬관");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Searom,15));

    }
}
