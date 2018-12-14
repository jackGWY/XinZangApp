package com.guo.xinzangapp.medicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.guo.beans.drugInfo;
import com.guo.http.GetDrugInfo;
import com.guo.http.getDrugByA1;
import com.guo.http.getDrugByRandom;
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
    private drugInfo dInfo;
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
    @BindView(R.id.drug_search)
    ImageButton drug_search;
    @BindView(R.id.durg_edit)
    EditText drug_edit;


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.addtion:
                Toast.makeText(this,"we will add function late",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_type_list);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar);
        //setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

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
            Log.d("drugTypeListActivity","a1:"+dInfo.getA1());
        }

    }

    @OnClick({R.id.drugtype1,R.id.drugtype2,R.id.drugtype3,R.id.drugtype4,R.id.drugtype5,R.id.drugtype6,R.id.drugtype7,R.id.drug_search})
    public void onClick(final View view) {
        ArrayList<drugInfo> ResDrugList = new ArrayList<>();
        Bundle bundle = new Bundle();
        Intent intent = new Intent(drugTypeListActivity.this,medicineActivity.class);
        switch (view.getId()) {
            case R.id.drug_search:
                //从数据库中搜索
                String drugToSearch = drug_edit.getText().toString().trim();
                Log.d("drugToSearch:",drugToSearch);
                ExecutorService executorService = Executors.newCachedThreadPool();
                Future<drugInfo> result2 = executorService.submit(new getDrugByRandom(drugToSearch));
                try {
                    dInfo = result2.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (dInfo!=null) {
                    bundle.putSerializable("drugInfo",dInfo);
                    Intent intent2 = new Intent(drugTypeListActivity.this,bMedicine1Activity.class);
                    intent2.putExtras(bundle);
                    startActivity(intent2);
                    return;
                }
                else {
                    Intent intent3 = new Intent(drugTypeListActivity.this,NotFoundActivity.class);
                    startActivity(intent3);
                    return;
                }
            case R.id.drugtype1:
                ResDrugList.clear();
                for (drugInfo dInfo:DrugList) {
                    if (dInfo.getA2().equals("扩张冠脉药")) {
                        ResDrugList.add(dInfo);
                    }
                }
                break;
            case R.id.drugtype2:
                ResDrugList.clear();
                for (drugInfo dInfo:DrugList) {
                    if (dInfo.getA2().equals("洋地黄制剂")) {
                        ResDrugList.add(dInfo);
                    }
                }
                break;
            case R.id.drugtype3:
                ResDrugList.clear();
                for (drugInfo dInfo:DrugList) {
                    if (dInfo.getA2().equals("升压药")) {
                        ResDrugList.add(dInfo);
                    }
                }
                break;
            case R.id.drugtype4:
                ResDrugList.clear();
                for (drugInfo dInfo:DrugList) {
                    if (dInfo.getA2().equals("降压药")) {
                        ResDrugList.add(dInfo);
                    }
                }
                break;
            case R.id.drugtype5:
                ResDrugList.clear();
                for (drugInfo dInfo:DrugList) {
                    if (dInfo.getA2().equals("利尿剂")) {
                        ResDrugList.add(dInfo);
                    }
                }
                break;
            case R.id.drugtype6:
                ResDrugList.clear();
                for (drugInfo dInfo:DrugList) {
                    if (dInfo.getA2().equals("受体阻滞剂")) {
                        ResDrugList.add(dInfo);
                    }
                }
                break;
            case R.id.drugtype7:
                ResDrugList.clear();
                for (drugInfo dInfo:DrugList) {
                    if (dInfo.getA2().equals("抗心律失常药")) {
                        ResDrugList.add(dInfo);
                    }
                }
                break;
        }
        bundle.putSerializable("ResDrugList",ResDrugList);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
