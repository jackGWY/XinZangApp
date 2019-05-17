package com.guo.xinzangapp.medicineArticle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guo.beans.HeartNews;
import com.guo.xinzangapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by guo_w on 2019/5/17.
 */

public class HeartNewsAdapter extends RecyclerView.Adapter<HeartNewsAdapter.ViewHolder> {

    private Context context;
    private List<HeartNews> newsList;

    public HeartNewsAdapter(Context context, List<HeartNews> newsList) {
        this.context = context;
        this.newsList = newsList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.heart_news_item,parent,false);
        final HeartNewsAdapter.ViewHolder holder = new HeartNewsAdapter.ViewHolder(view);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                HeartNews heartNews = newsList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("news",heartNews);
                Intent intent = new Intent(context,medicineAticle1Activity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HeartNews heartNews = newsList.get(position);
        holder.tv_title.setText(heartNews.getTitle());
//        holder.image_title.

        Glide.with(context).load(heartNews.getPicurl()).into(holder.image_title);//加载网络图片

    }

    @Override
    public int getItemCount() {
        if(newsList==null || newsList.size()==0){
            return 0;
        }
        return newsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView image_title;
        View newListView;
        LinearLayout linearLayout;
        public ViewHolder(View view) {
            super(view);
            newListView = view;
            image_title = (ImageView) view.findViewById(R.id.image_title);
            tv_title = (TextView) view.findViewById(R.id.heart_news_titile);
            linearLayout=(LinearLayout) view.findViewById(R.id.linear_news);
        }
    }
}
