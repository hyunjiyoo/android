package com.example.elenia.p_seek.LatelyList;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.elenia.p_seek.fragment.FragmentChartDetail;
import com.example.elenia.p_seek.R;

import java.util.ArrayList;


public class LatelyListAdapter extends RecyclerView.Adapter<LatelyListAdapter.ViewHolder> {

    Context mContext;
    ArrayList<LatelyListVO> mList;
    int mItem_layout;
    FragmentManager fm;
    FragmentTransaction ft;
    FragmentChartDetail fragChart;

    public LatelyListAdapter(Context context, ArrayList<LatelyListVO> list, int item_layout) {
        mContext = context;
        mList = list;
        mItem_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lately_list, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final LatelyListVO item = mList.get(position);

        // HomeFragment�� �ֽ���Ʈ�� ������ �־��ֱ�
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            holder.img.setBackground(item.getArt());
        } else {
            holder.img.setBackgroundDrawable(item.getArt());
        }
       holder.art.setText(item.getArtName());
       holder.artist.setText(item.getArtistName());

        fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
        ft = fm.beginTransaction();
        fragChart = new FragmentChartDetail();

        // ��ü �ϳ��� Ŭ���ϸ� �ش� �����Ϳ� ���� �������� ���
       holder.layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(holder.img.getBackground() == item.getArt()){
                   ft.replace(R.id.home, fragChart);
                   ft.addToBackStack(null);
                   Bundle bundle = new Bundle();
                   ArrayList<String> data = new ArrayList();
                   String artName = item.getArtName();
                   data.add(artName);
                   String artistName = item.getArtistName();
                   data.add(artistName);
                   bundle.putStringArrayList("art", data);
                   fragChart.setArguments(bundle);
                   ft.commit();
               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layout;
        ImageView img;
        TextView art;
        TextView artist;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = (RelativeLayout) itemView.findViewById(R.id.lately_list_layout);
            img = (ImageView) itemView.findViewById(R.id.lately_list_artwork);
            art = (TextView) itemView.findViewById(R.id.lately_list_artname);
            artist = (TextView) itemView.findViewById(R.id.lately_list_artist);
        }
    }
}
