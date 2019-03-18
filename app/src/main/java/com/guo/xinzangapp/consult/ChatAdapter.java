package com.guo.xinzangapp.consult;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guo.beans.MessageBoard;
import com.guo.beans.Msg;
import com.guo.xinzangapp.R;

import java.util.List;

/**
 * Created by guo_w on 2019/3/18.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{


    private List<Msg> msgList;

    public ChatAdapter(List<Msg> msgList) {
        this.msgList = msgList;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_item,parent,false);
        final ChatAdapter.ViewHolder holder = new ChatAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        Msg msg=msgList.get(position);
        switch (msg.getType()){
            case RECEIVED://接收的消息
                holder.leftLayout.setVisibility(View.VISIBLE);
                holder.rightLayout.setVisibility(View.GONE);
                holder.leftMsg.setText(msg.getContent());
                break;
            case SENT://发出的消息
                holder.leftLayout.setVisibility(View.GONE);
                holder.rightLayout.setVisibility(View.VISIBLE);
                holder.rightMsg.setText(msg.getContent());
                break;
        }
    }

    @Override
    public int getItemCount() {
        if(msgList==null){
            System.out.println("msgList is null..........................");
            return 0;
        }
        return msgList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;
        LinearLayout rightLayout;

        TextView leftMsg;
        TextView rightMsg;

        public ViewHolder(View itemView) {
            super(itemView);
            leftLayout=(LinearLayout) itemView.findViewById(R.id.left_layout);
            rightLayout=(LinearLayout)itemView.findViewById(R.id.right_layout);
            leftMsg=(TextView) itemView.findViewById(R.id.left_msg);
            rightMsg=(TextView) itemView.findViewById(R.id.right_msg);
        }
    }

    //添加数据
    public void addItem(Msg data) {
        msgList.add(data);
        int position = msgList.size()-1;
        notifyItemInserted(position);//通知演示插入动画
        notifyDataSetChanged();//通知重新绑定所有数据与界面
        //notifyItemRangeChanged(position,MessageTitleList.size()-position);//通知数据与界面重新绑定
    }
}
