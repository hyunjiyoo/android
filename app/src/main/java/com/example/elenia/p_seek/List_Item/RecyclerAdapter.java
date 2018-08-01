package com.example.elenia.p_seek.List_Item;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.elenia.p_seek.fragment.FragmentChartDetail;
import com.example.elenia.p_seek.R;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context context;
    private final List<ListItemVO> item;
    int item_layout;

    FragmentManager fm;
    FragmentTransaction ft;
    FragmentChartDetail fragChart;

    // RecyclerAdapter 생성자
    public RecyclerAdapter (Context context, List<ListItemVO> item, int item_layout) {
        this.context = context;
        this.item = item;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // 데이터 집합(item)에서 해당 요소를 가져옴
        final ListItemVO listItemVO = item.get(position);

        // items에서 image를 가져와 설정
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            holder.list_img.setBackground(listItemVO.getImage());
        } else {
            holder.list_img.setBackgroundDrawable(listItemVO.getImage());
        }

        // holder에서 작품이름, 아티스트이름 가져와 설정
        holder.list_artName.setText(listItemVO.getArtName());
        holder.list_artistName.setText(listItemVO.getArtistName());

        fm = ((AppCompatActivity) context).getSupportFragmentManager();
        ft = fm.beginTransaction();
        fragChart = new FragmentChartDetail();

        // item 하나하나 클릭시 이벤트 발생
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.list_img.getBackground() == listItemVO.getImage()){
                    ft.replace(R.id.home, fragChart);
                    ft.addToBackStack(null);
                    Bundle bundle = new Bundle();
                    ArrayList<String> data = new ArrayList();
                    String artName = listItemVO.getArtName();
                    data.add(artName);
                    String artistName = listItemVO.getArtistName();
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
        CardView cardview;
        RelativeLayout list_img;
        TextView list_artName;
        TextView list_artistName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView) itemView.findViewById(R.id.cardview);
            list_img = (RelativeLayout) itemView.findViewById(R.id.list_img);
            list_artName = (TextView) itemView.findViewById(R.id.list_artName);
            list_artistName = (TextView) itemView.findViewById(R.id.list_artistName);
        }
    }
}
