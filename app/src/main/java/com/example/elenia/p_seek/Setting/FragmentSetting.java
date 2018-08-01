package com.example.elenia.p_seek.Setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.elenia.p_seek.R;

public class FragmentSetting extends Fragment {

    FragmentManager fm;
    FragmentTransaction ft;

    Switch onOff;
    Switch onOff2;
    LinearLayout goDevel;
    LinearLayout goNotifi;
    LinearLayout goCS;
    LinearLayout goContect;

    FragmentCS fragCS;
    FragmentDeveloper fragDev;
    FragmentNotifi fragNoti;
    FragmentContect fragContect;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_setting, container, false);

        goCS = (LinearLayout) rootView.findViewById(R.id.go_cs_lay);
        goDevel = (LinearLayout) rootView.findViewById(R.id.go_developer_lay);
        goNotifi = (LinearLayout) rootView.findViewById(R.id.go_notifi_lay);
        goContect = (LinearLayout) rootView.findViewById(R.id.go_contect_lay);

        onOff = (Switch) rootView.findViewById(R.id.notifi_onOff);
        onOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(getContext(),"작가 소식 받기 활성화", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(),"작가 소식 받기 비활성화", Toast.LENGTH_SHORT).show();
                }

            }
        });

        onOff2 = (Switch) rootView.findViewById(R.id.notifi_onOff2);
        onOff2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(getContext(),"마케팅 수신 동의 활성화", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(),"마케팅 수신 동의 비활성화", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragCS = new FragmentCS();

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.home, fragCS);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        goDevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragDev = new FragmentDeveloper();

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.home, fragDev);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        goNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragNoti = new FragmentNotifi();

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.home, fragNoti);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        goContect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragContect = new FragmentContect();

                fm = getFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.home, fragContect);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return rootView;
    }
}
