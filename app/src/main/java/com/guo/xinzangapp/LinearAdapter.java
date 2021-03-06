package com.guo.xinzangapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guo.beans.drugInfo;
import com.guo.http.GetDrugInfo;

import java.util.List;

/**
 * Created by guo_w on 2017/11/22.
 */

public class LinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private OnItemClickListener mlistener;
    //private List<String> list;
    public LinearAdapter(Context context, OnItemClickListener listener){
        this.mContext=context;
        this.mlistener=listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_linear_item,parent,false));
//        if(viewType==0){
//            return new LinearViewHolder(LayoutInflater.from(mContext).inflate
//                    (R.layout.layout_linear_item,parent,false));
//        }
//        else{
//            return new LinearViewHolder2(LayoutInflater.from(mContext).inflate
//                    (R.layout.layout_linear_item2,parent,false));
//        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        GetDrugInfo getDrugInfo = new GetDrugInfo();
        List<drugInfo> drugInfoList =getDrugInfo.getDrugInfoFromServer();
        for (int i=0;i<drugInfoList.size();i++){
            for(int j=0;j<6;j++){
                if (position == (j+i*6)){
                    if (j==0){
                        ((LinearViewHolder)holder).textView1.setText(drugInfoList.get(i).getA1());
                    }
                    if (j==1){
                        ((LinearViewHolder)holder).textView2.setText(drugInfoList.get(i).getA2());
                    }
                    if (j==2){
                        ((LinearViewHolder)holder).textView3.setText(drugInfoList.get(i).getA3());
                    }
                    if (j==3){
                        ((LinearViewHolder)holder).textView4.setText(drugInfoList.get(i).getA4());
                    }
                    if (j==4){
                        ((LinearViewHolder)holder).textView5.setText(drugInfoList.get(i).getA5());
                    }
                    if (j==5){
                        ((LinearViewHolder)holder).textView6.setText(drugInfoList.get(i).getA6());
                    }

                }
            }
        }

//        if(getItemViewType(position)==0){
//            ((LinearViewHolder)holder).textView.setText("hello world!");
//        }
//        else {
//            ((LinearViewHolder2)holder).textView.setText("你好啊！");
//            //imageView也可以设置的，不设置默认机器人
//        }
        //应为LinearVIewHolder 有个textView方法的
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mlistener.OnClick(position);//调用接口的方法，LinearRecycleViewAcitivity中去实现
                //Toast.makeText(mContext,"click..."+position,Toast.LENGTH_SHORT).show();
            }
        });
        //holder.itemView.setOnLongClickListener();
    }

    @Override
    public int getItemViewType(int position) {//实际上有很多种VIEwType,根据接口返会的数据，不是根据位置
        if(position%2==0){
            return 0;
        }
        else{
            return 1;//自己定义的返回值
        }
        //return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {//itemCount 列表长度
        GetDrugInfo getDrugInfo = new GetDrugInfo();
        List<drugInfo> drugInfoList =getDrugInfo.getDrugInfoFromServer();
        return drugInfoList.size() * 6;
    }

//    class LinearViewHolder extends RecyclerView.ViewHolder{
//        private TextView textView;
//        public LinearViewHolder(View itemView) {
//            super(itemView);
//            textView=itemView.findViewById(R.id.tv_title7);
//        }
//    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private TextView textView5;
        private TextView textView6;

        public LinearViewHolder(View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.tv_1);
            textView2=itemView.findViewById(R.id.tv_2);
            textView3=itemView.findViewById(R.id.tv_3);
            textView4=itemView.findViewById(R.id.tv_4);
            textView5=itemView.findViewById(R.id.tv_5);
            textView6=itemView.findViewById(R.id.tv_6);

        }
    }

    public interface OnItemClickListener{
        void OnClick(int pos);
    }
    //长按的方法
}
