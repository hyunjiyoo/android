package com.example.elenia.p_seek.Login;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.elenia.p_seek.R;

public class FragmentRegister extends Fragment {

    Button artist;
    Button normal;

    FragmentManager fm;
    FragmentTransaction ft;

    FragmentRegiArtist artistFrag;
    FragmentRegPerson personFrag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_register, container, false);

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        artistFrag = new FragmentRegiArtist();
        personFrag = new FragmentRegPerson();

        artist = (Button) rootView.findViewById(R.id.regi_art_btn);
        normal = (Button) rootView.findViewById(R.id.regi_normal_btn);

        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.replace(R.id.home, artistFrag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft.replace(R.id.home, personFrag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return rootView;
    }
}
