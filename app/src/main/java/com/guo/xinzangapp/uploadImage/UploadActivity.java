package com.guo.xinzangapp.uploadImage;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.guo.xinzangapp.R;

import java.io.File;

public class UploadActivity extends AppCompatActivity {

    // 要上传的文件路径，放在SD卡根目录下
    private String uploadFile = Environment.getExternalStorageDirectory()
            .getPath() + "/1.jpg";
    private TextView file;
    private ImageView image;
    private Button upload;
    private Button download;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        file = (TextView) findViewById(R.id.file);
        file.setText(uploadFile);
        image = (ImageView) findViewById(R.id.image);
        upload = (Button) findViewById(R.id.uploadImage);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(uploadFile);
                new FileUploadAsyncTask(UploadActivity.this).execute(file);
            }
        });
        download = (Button) findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new FileDownLoadAsyncTask(UploadActivity.this).execute(image);
            }
        });
    }

}

