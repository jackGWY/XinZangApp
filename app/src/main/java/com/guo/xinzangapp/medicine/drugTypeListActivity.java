package com.guo.xinzangapp.medicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.guo.beans.drugInfo;
import com.guo.http.GetDrugInfo;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.medicineActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class drugTypeListActivity extends AppCompatActivity {
    private List<drugInfo> DrugList = new ArrayList<>();
    @BindView(R.id.drugtype1)
    TextView drugtype1;
    @BindView(R.id.drugtype2)
    TextView drugtype2;
    @BindView(R.id.drugtype3)
    TextView drugtype3;
    @BindView(R.id.drugtype4)
    TextView drugtype4;
    @BindView(R.id.drugtype5)
    TextView drugtype5;
    @BindView(R.id.drugtype6)
    TextView drugtype6;
    @BindView(R.id.drugtype7)
    TextView drugtype7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_type_list);
        ButterKnife.bind(this);
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<List<drugInfo>> result = exec.submit(new GetDrugInfo());
        try {
            DrugList = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (drugInfo dInfo : DrugList) {
            Log.d("medicineActivity","a1:"+dInfo.getA1());
        }
    }

    @OnClick({R.id.drugtype1,R.id.drugtype2,R.id.drugtype3,R.id.drugtype4,R.id.drugtype5,R.id.drugtype6,R.id.drugtype7})
    public void onClick(final View view) {
        ArrayList<drugInfo> ResDrugList = new ArrayList<>();
        switch (view.getId()) {
            case R.id.drugtype1:
                ResDrugList.clear();
                for (drugInfo dInfo:DrugList) {
                    if (dInfo.getA2().equals("扩张冠脉药")) {
                        ResDrugList.add(dInfo);
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("ResDrugList",ResDrugList);
                Intent intent = new Intent(drugTypeListActivity.this,medicineActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.drugtype2:
                break;
            case R.id.drugtype3:
                break;
            case R.id.drugtype4:
                break;
            case R.id.drugtype5:
                break;
            case R.id.drugtype6:
                break;
            case R.id.drugtype7:
                break;
        }
    }
}
