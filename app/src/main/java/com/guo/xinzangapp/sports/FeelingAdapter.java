package com.guo.xinzangapp.sports;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guo.beans.Feelings;
import com.guo.beans.diary;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.diary.DiaryAdapter;
import com.guo.xinzangapp.diary.DiaryDetailActivity;

import java.util.List;

/**
 * Created by guo_w on 2019/3/14.
 */

public class FeelingAdapter extends RecyclerView.Adapter<FeelingAdapter.ViewHolder>{

    private List<Feelings> feelingList;
    private Context context;

    public FeelingAdapter(List<Feelings> feelingList, Context context) {
        this.feelingList = feelingList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feeling_title_item,parent,false);
        final FeelingAdapter.ViewHolder holder = new FeelingAdapter.ViewHolder(view);
        holder.sports.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Feelings d = feelingList.get(position);
                System.out.println(d.getFeeling());
                Bundle bundle = new Bundle();
                bundle.putSerializable("feelings",d);
                Intent intent = new Intent(context,FeelingDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Feelings feelings = feelingList.get(position);
        holder.sports.setText(feelings.getSports());
        holder.feeling.setText(feelings.getFeeling());
    }

    @Override
    public int getItemCount() {
        if (feelingList == null) {
            return 0;
        }
        return feelingList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView sports;
        TextView feeling;
        View feelingListView;
        public ViewHolder(View view) {
            super(view);
            feelingListView = view;
            sports = (TextView) view.findViewById(R.id.sport_title);
            feeling = (TextView) view.findViewById(R.id.feeling_title);
        }
    }
}
