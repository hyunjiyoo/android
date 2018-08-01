package com.example.elenia.p_seek.MyPageList;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.elenia.p_seek.DB_Table.ArtistVO;
import com.example.elenia.p_seek.R;

import java.util.List;

public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.ViewHolder> {

    private Context context;
    private final List<ArtistVO> item;
    int item_layout;

    public ArtistListAdapter (Context context, List<ArtistVO> item, int item_layout) {
        this.context = context;
        this.item = item;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, null);
        return new ArtistListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ArtistVO artistVO = item.get(position);
        // 마이페이지의 아티스트 리스트에 데이터 셋.
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            holder.img.setBackground(artistVO.getArtistPic());
        } else {
            holder.img.setBackgroundDrawable(artistVO.getArtistPic());
        }
        holder.artist_name.setText(artistVO.getArtistName());
    }

    @Override
    public int getItemCount() {
        return this.item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        ImageView img;
        TextView artist_name;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);
            img = (ImageView) itemView.findViewById(R.id.artist_img);
            artist_name = (TextView) itemView.findViewById(R.id.artist_name);
        }
    }
}
