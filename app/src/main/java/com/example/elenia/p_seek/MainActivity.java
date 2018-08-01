package com.example.elenia.p_seek;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.elenia.p_seek.Login.FragmentMyPage1;
import com.example.elenia.p_seek.Setting.FragmentSetting;
import com.example.elenia.p_seek.fragment.FragmentHome;
import com.example.elenia.p_seek.fragment.FragmentMyPage2;
import com.example.elenia.p_seek.fragment.FragmentMyPage3;


public class MainActivity extends AppCompatActivity {

    FragmentManager fm;
    FragmentTransaction ft;
    Button myMenu;
    FragmentMyPage1 fragMypage1;
    FragmentMyPage2 fragMypage2;
    FragmentMyPage3 fragMypage3;
    FragmentHome homeFrag;
    FragmentSetting fragSetting;

    int page;
    int login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myMenu = (Button) findViewById(R.id.mymenu_btn);

        page = 0; // 비로그인 했는지 판단   0이면 비로그인, 1이면 로그인
        login = 0; // 아티스트, 일반 판단   1이면 아티스트, default 0 = 일반

        fm = getSupportFragmentManager();

        ft = fm.beginTransaction();
        homeFrag = new FragmentHome();
        ft.add(R.id.home, homeFrag); // getFragmentManager().beginTransaction().replace(R.id.home, homeFrag);
        ft.commit();
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public void onClickMenu(View v) {
        fragMypage1 = new FragmentMyPage1();
        fragMypage2 = new FragmentMyPage2();
        fragMypage3 = new FragmentMyPage3();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        if (page == 1) {
            if(login == 1) {
                ft.replace(R.id.home, fragMypage2);
                ft.addToBackStack(null);
                ft.commit();
            }
            if(login == 0) {
                ft.replace(R.id.home, fragMypage3);
                ft.addToBackStack(null);
                ft.commit();
            }
        } else {
            ft.replace(R.id.home, fragMypage1);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    public void onClickSetting(View v) {
        fragSetting = new FragmentSetting();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        ft.replace(R.id.home, fragSetting);
        ft.addToBackStack(null);
        ft.commit();
    }


}
