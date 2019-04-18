package com.guo.xinzangapp.BarChart;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guo.beans.Feelings;
import com.guo.http.getFeelings;
import com.guo.xinzangapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BarChartActivity extends AppCompatActivity {

    List<Double> datas = new ArrayList<>();
    List<String> description = new ArrayList<>();
    private List<Feelings> feelingsList;
    private int yellowColor = Color.argb(255, 253, 197, 53);
    private int greenColor = Color.argb(255, 27, 147, 76);
    private int redColor = Color.argb(255, 211, 57, 53);
    private int blueColor = Color.argb(255, 76, 139, 245);

    LBarChartView LBarChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");

        ExecutorService exec = Executors.newCachedThreadPool();
        Future<List<Feelings>> result =exec.submit(new getFeelings(userName));
        try {
            feelingsList = result.get();
            for(Feelings f : feelingsList) {
                System.out.println(f.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(feelingsList!=null && feelingsList.size()!=0){
//            for(Feelings f : feelingsList) {
//                datas.add(f.getHeartRate()*1.0);
//            }
            for(int i=0;i<feelingsList.size();i++){
                datas.add(feelingsList.get(i).getHeartRate()*1.0);
                description.add((i+1)+"");
            }
        }



        LBarChartView = (LBarChartView) findViewById(R.id.frameNewBase);
        initNewBarDatas();
    }

    private void initNewBarDatas() {
//        final List<Double> datas = new ArrayList<>();
//        final List<String> description = new ArrayList<>();

//        datas.add(100d);
//        datas.add(20d);
//        datas.add(40d);
//        datas.add(50d);
//        datas.add(60d);
//        datas.add(200d);
//
//        datas.add(10d);
//        datas.add(30d);
//        datas.add(40d);
//        datas.add(15d);
//        datas.add(38d);
//        datas.add(60d);
//        datas.add(10d);
//
//        description.add("one");
//        description.add("two");
//        description.add("three");
//        description.add("four");
//        description.add("five");
//        description.add("six");
//        description.add("seven");
//        description.add("eight");
//        description.add("eight1");
//        description.add("eight2");
//        description.add("eight3");
//        description.add("eight4");
//        description.add("eight5");

        LBarChartView.setDatas(datas, description, true);
//        LBarChartView.setDragInerfaces(new DragInerfaces() {
//            @Override
//            public void onEnd() {
//                final List<Double> datas = new ArrayList<>();
//                final List<String> description = new ArrayList<>();
//                datas.add(40d);
//                datas.add(15d);
//                datas.add(38d);
//                datas.add(60d);
//                datas.add(10d);
//
//                description.add("one");
//                description.add("two");
//                description.add("three");
//                description.add("four");
//                description.add("five");
//                LBarChartView.addEndMoreData(datas, description);
//            }
//
//            @Override
//            public void onStart() {
//            }
//        });
    }
}
