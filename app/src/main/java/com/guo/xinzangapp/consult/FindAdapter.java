package com.guo.xinzangapp.consult;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guo.beans.MessageTitle;
import com.guo.beans.UserPatient;
import com.guo.http.GetMessageTitleList;
import com.guo.http.saveRelation;
import com.guo.xinzangapp.LoginActivity;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.homeActivity;
import com.guo.xinzangapp.medicineArticle.medicineAticle1Activity;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by guo_w on 2019/3/17.
 */

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder>{

    private List<UserPatient> doctorList;
    private String userName;
    private Context context;

//    private SharedPreferences.Editor editor;
//    private SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

    public FindAdapter(List<UserPatient> doctorList, String userName, Context context) {
        this.doctorList = doctorList;
        this.userName = userName;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_doctor_item,parent,false);
        final FindAdapter.ViewHolder holder = new FindAdapter.ViewHolder(view);

        holder.say.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                editor = pref.edit();
//                editor.putString("userName", uname);
//                editor.apply();

                int position = holder.getAdapterPosition();
                UserPatient userPatient = doctorList.get(position);

                String doctor = userPatient.getUserName();
                Bundle bundle = new Bundle();
                bundle.putSerializable("doctor",doctor);
                Intent intent = new Intent(context,ChatActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.attach.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String count="";
                int position = holder.getAdapterPosition();
                UserPatient userPatient = doctorList.get(position);//userName,userPatient.getUserName()
                ExecutorService exec = Executors.newCachedThreadPool();
                Future<String> result = exec.submit(new saveRelation(userName,userPatient.getUserName()));
                try {
                    count=result.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if(count.equals("")) {
                    Toast.makeText(context, "失败，请重新操作", Toast.LENGTH_SHORT).show();
                }
                else if(count.equals("1")) {
                    Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserPatient userPatient = doctorList.get(position);
        holder.tv_add.setText(userPatient.getUserName());
    }

    @Override
    public int getItemCount() {
        if(doctorList == null){
            return 0;
        }
        return doctorList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_add;
        ImageView attach;
        ImageView say;
        View MessageListView;
        public ViewHolder(View view) {
            super(view);
            MessageListView = view;
            tv_add = (TextView) view.findViewById(R.id.add_doctor);
            attach = (ImageView) view.findViewById(R.id.attach);
            say = (ImageView) view.findViewById(R.id.say_y);
        }
    }
}
