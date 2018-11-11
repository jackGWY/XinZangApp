package com.guo.xinzangapp;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public class LinearRecycleViewActivity extends AppCompatActivity {
    private RecyclerView mRvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab01);

        mRvMain=(RecyclerView)findViewById(R.id.rv_main);
        mRvMain.setLayoutManager(new LinearLayoutManager(LinearRecycleViewActivity.this));
        mRvMain.addItemDecoration(new MyDecoration());//自己新建的类,RecycleView.addItemDecoration有很多方法
        mRvMain.setAdapter(new LinearAdapter(LinearRecycleViewActivity.this, new LinearAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int pos) {//这个是自己定义的接口中的方法
                Toast.makeText(LinearRecycleViewActivity.this,"click"+pos,Toast.LENGTH_SHORT).show();
                //别忘记show，这里setAdater 实现了接口，而在Adapter中没有
            }
        }));
        //contenxt 其实就是Activity的class
    }

    class MyDecoration extends RecyclerView.ItemDecoration{
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            //每个元素下面添加了1dp间距，空出来背景的颜色
            outRect.set(0,0,0,1);

        }
    }
}
