package com.example.elenia.p_seek.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.elenia.p_seek.Login.FragmentRegister;
import com.example.elenia.p_seek.MainActivity;
import com.example.elenia.p_seek.R;
import com.example.elenia.p_seek.fragment.FragmentHome;


public class FragmentLogin extends Fragment implements RadioGroup.OnCheckedChangeListener {

    FragmentManager fm;
    FragmentTransaction ft;

    TextView register;
    FragmentRegister registerFrag;
    FragmentHome fraghome;

    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    Button login_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_login, container, false);

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        login_btn = (Button) rootView.findViewById(R.id.login_btn1);

        radioGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton) rootView.findViewById(R.id.radio_btn1);
        radioButton2 = (RadioButton) rootView.findViewById(R.id.radio_btn2);

        register = (TextView) rootView.findViewById(R.id.register);
        registerFrag = new FragmentRegister();

        radioGroup.check(R.id.radio_btn2);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.replace(R.id.home, registerFrag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        radioGroup.setOnCheckedChangeListener(this);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setPage(1);

                fraghome = new FragmentHome();

                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // 모든 스택 초기화
                ft.replace(R.id.home, fraghome);
                ft.addToBackStack(null); // 홈 프래그먼트 스택 저장
                ft.commit();
            }
        });
        return rootView;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setLogin(group.getCheckedRadioButtonId() == R.id.radio_btn1 ? 1 : 0);
    }
}
