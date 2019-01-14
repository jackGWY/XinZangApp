package com.guo.xinzangapp.medicine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guo.beans.MessageTitle;
import com.guo.beans.drugInfo;
import com.guo.xinzangapp.R;

import org.w3c.dom.Text;

import java.util.List;


/**
 * Created by guo_w on 2018/11/29.
 */

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.ViewHolder> {

    private List<drugInfo> mDrugList;
    private Context context;

    public DrugAdapter (List<drugInfo> druglist, Context context) {
        mDrugList = druglist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.druglist_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.a1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                drugInfo dInfo = mDrugList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("drugInfo",dInfo);
                Intent intent = new Intent(context,bMedicine1Activity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        drugInfo dInfo = mDrugList.get(position);
        holder.a1.setText(dInfo.getA1());
    }

    @Override
    public int getItemCount() {
        return mDrugList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView a1;
        View drugListView;
        public ViewHolder(View view) {
            super(view);
            drugListView = view;
            a1 = (TextView) view.findViewById(R.id.item_textview);
        }
    }

    //添加数据
    public void addItem(drugInfo dInfo) {
        mDrugList.add(dInfo);
        int position = mDrugList.size()-1;
        notifyItemInserted(position);//通知演示插入动画
        notifyDataSetChanged();//通知重新绑定所有数据与界面
        //notifyItemRangeChanged(position,MessageTitleList.size()-position);//通知数据与界面重新绑定
    }
}
