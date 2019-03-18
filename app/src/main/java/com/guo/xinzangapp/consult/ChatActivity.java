package com.guo.xinzangapp.consult;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.guo.beans.Chat;
import com.guo.beans.MessageTitle;
import com.guo.beans.Msg;
import com.guo.beans.UserPatient;
import com.guo.http.GetChatList;
import com.guo.http.GetUserPatients;
import com.guo.http.SaveChat;
import com.guo.xinzangapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.send_input)
    EditText sendInput;
    @BindView(R.id.message_send)
    ImageButton image_send;
    @BindView(R.id.communicate_with)
    TextView c_with;

    ExecutorService exec;
    private List<Chat> chatList = new ArrayList<>();
    String doctor="";
    String userName="";
    String userType = "patient";

    private List<Msg> msgList = new ArrayList<>();
    private ChatAdapter chatAdapter;

    public enum TYPE{
        RECEIVED,
        SENT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        userName = pref.getString("userName","");
        userType = pref.getString("userType","");
        ButterKnife.bind(this);

        Intent intent=getIntent();
        // 实例化一个Bundle
        Bundle bundle=intent.getExtras();
        //获取里面的Persion里面的数据
        doctor= (String) bundle.getSerializable("doctor");

        c_with.setText("与"+doctor+"聊天");

        exec = Executors.newCachedThreadPool();
        Future<List<Chat>> result = exec.submit(new GetChatList(userName,doctor));
        try {
            chatList=result.get();
            System.out.println("chatList............................"+chatList.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(chatList != null) {
            for(Chat chat : chatList) {
                Msg msg = new Msg();
                msg.setContent(chat.getMessage());
                msg.setType(Msg.TYPE.RECEIVED);
                if(userName.equals(chat.getBelong())){
                    msg.setType(Msg.TYPE.SENT);
                }
                msgList.add(msg);
            }
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_chat);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        chatAdapter= new ChatAdapter(msgList);
        recyclerView.setAdapter(chatAdapter);
    }

    @OnClick({R.id.message_send})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.message_send:
                String sendMessage =sendInput.getText().toString().trim();
                String SaveChatReture = "";
                exec = Executors.newCachedThreadPool();
                Future<String> future_result = null;
                if(userType.equals("patient")){
                    future_result = exec.submit(new SaveChat(userName,sendMessage,doctor,userName));
                } else {
                    future_result=exec.submit(new SaveChat(doctor,sendMessage,userName,userName));
                }
                try {
                    SaveChatReture=future_result.get();
                    System.out.println("SaveChatReture:"+SaveChatReture);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Msg msg = new Msg();
                msg.setType(Msg.TYPE.SENT);
                if(userType.equals("patient")){
                    msg.setType(Msg.TYPE.SENT);
                }
                msg.setContent(sendMessage);
                chatAdapter.addItem(msg);
                break;
        }
    }
}
