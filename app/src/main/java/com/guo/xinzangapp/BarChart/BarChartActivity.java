package com.guo.xinzangapp.BarChart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guo.xinzangapp.R;

import java.util.ArrayList;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {

    private int yellowColor = Color.argb(255, 253, 197, 53);
    private int greenColor = Color.argb(255, 27, 147, 76);
    private int redColor = Color.argb(255, 211, 57, 53);
    private int blueColor = Color.argb(255, 76, 139, 245);

    LBarChartView LBarChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        LBarChartView = (LBarChartView) findViewById(R.id.frameNewBase);
        initNewBarDatas();
    }

    private void initNewBarDatas() {
        final List<Double> datas = new ArrayList<>();
        final List<String> description = new ArrayList<>();

        datas.add(100d);
        datas.add(20d);
        datas.add(40d);
        datas.add(50d);
        datas.add(60d);
        datas.add(200d);

        datas.add(10d);
        datas.add(30d);
        datas.add(40d);
        datas.add(15d);
        datas.add(38d);
        datas.add(60d);
        datas.add(10d);

        description.add("one");
        description.add("two");
        description.add("three");
        description.add("four");
        description.add("five");
        description.add("six");
        description.add("seven");
        description.add("eight");
        description.add("eight1");
        description.add("eight2");
        description.add("eight3");
        description.add("eight4");
        description.add("eight5");

        LBarChartView.setDatas(datas, description, true);
        LBarChartView.setDragInerfaces(new DragInerfaces() {
            @Override
            public void onEnd() {
                final List<Double> datas = new ArrayList<>();
                final List<String> description = new ArrayList<>();
                datas.add(40d);
                datas.add(15d);
                datas.add(38d);
                datas.add(60d);
                datas.add(10d);

                description.add("one");
                description.add("two");
                description.add("three");
                description.add("four");
                description.add("five");
                LBarChartView.addEndMoreData(datas, description);
            }

            @Override
            public void onStart() {
            }
        });
    }
}
