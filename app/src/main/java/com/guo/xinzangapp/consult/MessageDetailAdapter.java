package com.guo.xinzangapp.consult;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guo.beans.MessageBoard;
import com.guo.xinzangapp.R;

import java.util.List;

/**
 * Created by guo_w on 2018/12/20.
 */

public class MessageDetailAdapter extends RecyclerView.Adapter<MessageDetailAdapter.ViewHolder> {

    private List<MessageBoard> MessageBoardList;
    private Context context;

    public MessageDetailAdapter(List<MessageBoard> messageBoardList, Context context) {
        MessageBoardList = messageBoardList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_board_detail_item,parent,false);
        final MessageDetailAdapter.ViewHolder holder = new MessageDetailAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MessageBoard messageBoard = MessageBoardList.get(position);
        holder.message_detail.setText(messageBoard.getComment());
        holder.answer_owner.setText(messageBoard.getCommentOwner());
    }

    @Override
    public int getItemCount() {
        if (MessageBoardList == null) {
            return 0;
        }
        return MessageBoardList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView message_detail;
        TextView answer_owner;
        View MessageListView;
        public ViewHolder(View view) {
            super(view);
            MessageListView = view;
            message_detail = (TextView) view.findViewById(R.id.message_detail);
            answer_owner = (TextView) view.findViewById(R.id.answer_owner);
        }
    }
}
