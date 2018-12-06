package com.guo.xinzangapp.diary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guo.beans.diary;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.medicine.DrugAdapter;

import java.util.List;

/**
 * Created by guo_w on 2018/12/6.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder>{

    private List<diary> diaryList;
    private Context context;

    public DiaryAdapter(List<diary> diaryList,Context context) {
        this.diaryList = diaryList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diary_title_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                diary d = diaryList.get(position);
                System.out.println(d.getTime());
                Bundle bundle = new Bundle();
                bundle.putSerializable("diary",d);
                Intent intent = new Intent(context,DiaryDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        diary d = diaryList.get(position);
        holder.reason.setText(d.getReason());
    }

    @Override
    public int getItemCount() {
        if (diaryList == null) {
            return 0;
        }
        return diaryList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView reason;
        View drugListView;
        public ViewHolder(View view) {
            super(view);
            drugListView = view;
            reason = (TextView) view.findViewById(R.id.diary_title_reason);
        }
    }
}
