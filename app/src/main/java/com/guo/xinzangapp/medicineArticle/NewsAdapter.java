package com.guo.xinzangapp.medicineArticle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guo.beans.News;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.medicine.DrugAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guo_w on 2018/12/2.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private Context context;
    private List<News> newsList;

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_item,parent,false);
        final NewsAdapter.ViewHolder holder = new NewsAdapter.ViewHolder(view);
        holder.a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                News news2 = newsList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("news",news2);
                Intent intent = new Intent(context,medicineAticle1Activity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news=newsList.get(position);
        holder.a1.setText(news.getTitle());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView a1;
        View newListView;
        public ViewHolder(View view) {
            super(view);
            newListView = view;
            a1 = (TextView) view.findViewById(R.id.item_news2);
        }
    }
}
