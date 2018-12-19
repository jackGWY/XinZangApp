package com.guo.xinzangapp.consult;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guo.beans.MessageTitle;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.diary.DiaryAdapter;

import java.util.List;

/**
 * Created by guo_w on 2018/12/16.
 */

public class MessageTitleAdapter extends RecyclerView.Adapter<MessageTitleAdapter.ViewHolder> {

    private List<MessageTitle> MessageTitleList;
    private Context context;

    public MessageTitleAdapter(List<MessageTitle> messageTitleList, Context context) {
        MessageTitleList = messageTitleList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_title_item,parent,false);
        final MessageTitleAdapter.ViewHolder holder = new MessageTitleAdapter.ViewHolder(view);
        holder.title.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                MessageTitle messageTitle = MessageTitleList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("messageTitle",messageTitle);
                Intent intent = new Intent(context,MessageBoardDetailActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MessageTitle messageTitle = MessageTitleList.get(position);
        holder.title.setText(messageTitle.getTitle());
        holder.owner.setText(messageTitle.getOwner());
    }

    @Override
    public int getItemCount() {
        return MessageTitleList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView owner;
        View MessageListView;
        public ViewHolder(View view) {
            super(view);
            MessageListView = view;
            title = (TextView) view.findViewById(R.id.message_title);
            owner = (TextView) view.findViewById(R.id.message_owner);
        }
    }

    //添加数据
    public void addItem(MessageTitle data) {
        MessageTitleList.add(data);
        int position = MessageTitleList.size()-1;
        notifyItemInserted(position);//通知演示插入动画
        notifyDataSetChanged();//通知重新绑定所有数据与界面
        //notifyItemRangeChanged(position,MessageTitleList.size()-position);//通知数据与界面重新绑定
    }

}
