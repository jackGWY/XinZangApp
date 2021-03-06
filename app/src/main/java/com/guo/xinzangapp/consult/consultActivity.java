package com.guo.xinzangapp.consult;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.guo.beans.MessageTitle;
import com.guo.beans.diary;
import com.guo.http.GetDiaryList;
import com.guo.http.GetMessageTitleList;
import com.guo.http.saveDialogQuestionTitle;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.diary.DiaryAdapter;
import com.guo.xinzangapp.diary.diaryListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class consultActivity extends AppCompatActivity {

    @BindView(R.id.question_add)
    FloatingActionButton imageAdd;

    List<MessageTitle> messageTitleList = new ArrayList<>();

    private MessageTitleAdapter messageTitleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");

        ButterKnife.bind(this);
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<List<MessageTitle>> result = exec.submit(new GetMessageTitleList());
        try {
            messageTitleList=result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (MessageTitle messageTitle : messageTitleList) {
            Log.d("consultActivity:",messageTitle.getTitle());
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_message_board);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        messageTitleAdapter = new MessageTitleAdapter(messageTitleList,consultActivity.this);
        recyclerView.setAdapter(messageTitleAdapter);
    }

    @OnClick({R.id.question_add})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.question_add:
                Log.d("consultActivity","imageAddButtom clicked..");
                dialog();
                break;
        }
    }
    //对话框
    protected void dialog() {
        final View layout = View.inflate(consultActivity.this, R.layout.message_title_dialog,
                null);
        AlertDialog.Builder builder = new AlertDialog.Builder(consultActivity.this);
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
                Toast.makeText(consultActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                Log.d("consultActivity",edtMessageTitle);
                //获取请求的参数
                SharedPreferences pref;
                SharedPreferences.Editor editor;
                pref = PreferenceManager.getDefaultSharedPreferences(consultActivity.this);
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
