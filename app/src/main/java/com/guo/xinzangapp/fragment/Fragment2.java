package com.guo.xinzangapp.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.guo.beans.MessageTitle;
import com.guo.http.GetMessageTitleList;
import com.guo.http.saveDialogQuestionTitle;
import com.guo.xinzangapp.MyActivity;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.consult.MessageTitleAdapter;
import com.guo.xinzangapp.consult.consultActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Fragment2 extends Fragment {
    private View view;

    FloatingActionButton imageAdd;

    List<MessageTitle> messageTitleList = new ArrayList<>();

    private MessageTitleAdapter messageTitleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment2, null);
        initView();

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String userName = pref.getString("userName","");

        FloatingActionButton imageAdd = (FloatingActionButton)view.findViewById(R.id.question_add);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Fragment2","imageAddButtom clicked..");
                dialog();
            }
        });
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<List<MessageTitle>> result = exec.submit(new GetMessageTitleList());
        try {
            messageTitleList=result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView_message_board);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        messageTitleAdapter = new MessageTitleAdapter(messageTitleList,getActivity());
        recyclerView.setAdapter(messageTitleAdapter);


        return view;
    }

    private void initView() {

    }
    //对话框
    protected void dialog() {
        final View layout = View.inflate(getActivity(), R.layout.message_title_dialog,
                null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请输入问题");
        builder.setIcon(R.drawable.rating_bar_bg);
//        builder.setMessage("文档：" + listDetail.getOriginname());
        builder.setView(layout);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获得dialog 中 edittext的值
                EditText edt_message_title = (EditText) layout.findViewById(R.id.edt_message_title);
                String edtMessageTitle = edt_message_title.getText().toString();
                Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                Log.d("consultActivity",edtMessageTitle);
                //获取请求的参数
                SharedPreferences pref;
                SharedPreferences.Editor editor;
                pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                final String userName = pref.getString("userName","");
                Log.d("consultActivity",userName);
                ExecutorService exec = Executors.newCachedThreadPool();
                exec.submit(new saveDialogQuestionTitle(edtMessageTitle,userName));

                MessageTitle messageTitle = new MessageTitle(edtMessageTitle,userName);
                messageTitleAdapter.addItem(messageTitle);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
