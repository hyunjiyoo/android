package com.example.elenia.p_seek.MyPageList.RecentList;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.elenia.p_seek.fragment.FragmentChartDetail;
import com.example.elenia.p_seek.R;

import java.util.ArrayList;
import java.util.List;

public class RecentListAdapter extends RecyclerView.Adapter<RecentListAdapter.ViewHolder>{

    private Context context;
    private final List<RecentListVO> item;
    int item_layout;

    FragmentManager fm;
    FragmentTransaction ft;
    FragmentChartDetail fragChart;

    public RecentListAdapter (Context context, List<RecentListVO> item, int item_layout) {
        this.context = context;
        this.item = item;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lately_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final RecentListVO recentListVO = item.get(position);

        // 마이페이지의 최근 감상작리스트에 데이터 셋.
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            holder.img.setBackground(recentListVO.getImg());
        } else {
            holder.img.setBackgroundDrawable(recentListVO.getImg());
        }
        holder.art.setText(recentListVO.getArtName());
        holder.artist.setText(recentListVO.getArtistName());

        fm = ((AppCompatActivity) context).getSupportFragmentManager();
        ft = fm.beginTransaction();
        fragChart = new FragmentChartDetail();

        // 아이템 하나씩 클릭할 때 해당 아이템 상세페이지 출력
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.img.getBackground() == recentListVO.getImg()){
                    ft.replace(R.id.home, fragChart);
                    ft.addToBackStack(null);
                    Bundle bundle = new Bundle();
                    ArrayList<String> data = new ArrayList();
                    String artName = recentListVO.getArtName();
                    data.add(artName);
                    String artistName = recentListVO.getArtistName();
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
        return this.item.size();
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
