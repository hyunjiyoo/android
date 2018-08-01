package com.example.elenia.p_seek.Login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.elenia.p_seek.fragment.FragmentLogin;
import com.example.elenia.p_seek.R;

public class FragmentMyPage1 extends Fragment {

    TextView go_login;
    FragmentLogin loginFrag;
    FragmentManager fm;
    FragmentTransaction ft;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mypage1, container, false);

        go_login = (TextView) rootView.findViewById(R.id.go_login);
        loginFrag = new FragmentLogin();

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getFragmentManager().beginTransaction().replace(R.id.home, loginFrag).commit();
                ft.replace(R.id.home, loginFrag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return rootView;
    }
}
