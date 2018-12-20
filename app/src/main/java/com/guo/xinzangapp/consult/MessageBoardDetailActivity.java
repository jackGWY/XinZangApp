package com.guo.xinzangapp.consult;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.guo.beans.MessageBoard;
import com.guo.beans.MessageTitle;
import com.guo.beans.diary;
import com.guo.beans.drugInfo;
import com.guo.http.GetDiaryList;
import com.guo.http.getCommentList;
import com.guo.http.saveDialogQuestionTitle;
import com.guo.http.saveMessageQA;
import com.guo.xinzangapp.R;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MessageBoardDetailActivity extends AppCompatActivity {

    private List<MessageBoard> MessageBoardList;
    private MessageTitle mTitle;
    private MessageDetailAdapter messageDetailAdapter;

    private TextView title;
    private TextView titleOwner;
    private ImageButton imageDiscuss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board_detail);

        Intent intent=getIntent();
        // 实例化一个Bundle
        Bundle bundle=intent.getExtras();
        //获取里面的Persion里面的数据
        mTitle= (MessageTitle) bundle.getSerializable("messageTitle");
        title = (TextView)findViewById(R.id.question_title);
        titleOwner = (TextView) findViewById(R.id.title_user);
        imageDiscuss= (ImageButton) findViewById(R.id.image_discuss);
        title.setText(mTitle.getTitle());
        titleOwner.setText(mTitle.getOwner());
        ExecutorService exec = Executors.newCachedThreadPool();
        String title = mTitle.getTitle();
        Future<List<MessageBoard>> result = exec.submit(new getCommentList(title));
        try {
            MessageBoardList = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_question_discuss);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        messageDetailAdapter = new MessageDetailAdapter(MessageBoardList,MessageBoardDetailActivity.this);
        recyclerView.setAdapter(messageDetailAdapter);

        imageDiscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("messageBoardActivity","点击弹出问题输入框");
                dialog();
            }
        });
    }

    //对话框
    protected void dialog() {
        final View layout = View.inflate(MessageBoardDetailActivity.this, R.layout.question_answer_dialog,
                null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MessageBoardDetailActivity.this);
        builder.setTitle("请输入回答");
        builder.setIcon(R.drawable.rating_bar_bg);
//        builder.setMessage("文档：" + listDetail.getOriginname());
        builder.setView(layout);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获得dialog 中 edittext的值
                EditText edt_question_answer = (EditText) layout.findViewById(R.id.edt_question_answer);
                String edtAnswer = edt_question_answer.getText().toString();
                Toast.makeText(MessageBoardDetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                Log.d("MessageDetailActivity",edtAnswer);
                //获取请求的参数
                SharedPreferences pref;
                SharedPreferences.Editor editor;
                pref = PreferenceManager.getDefaultSharedPreferences(MessageBoardDetailActivity.this);
                final String userName = pref.getString("userName","");
                Log.d("MessageDetailActivity",userName);
                MessageBoard messageBoard = new MessageBoard();

                messageBoard.setTopicTitle(mTitle.getTitle());
                messageBoard.setToppicOwner(mTitle.getOwner());
                messageBoard.setCommentOwner(userName);
                messageBoard.setComment(edtAnswer);

                ExecutorService exec = Executors.newCachedThreadPool();
                exec.submit(new saveMessageQA(messageBoard));

                messageDetailAdapter.addItem(messageBoard);
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
