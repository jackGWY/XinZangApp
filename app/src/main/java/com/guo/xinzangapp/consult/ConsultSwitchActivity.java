package com.guo.xinzangapp.consult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.guo.xinzangapp.R;
import com.guo.xinzangapp.ViewPagerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsultSwitchActivity extends AppCompatActivity {

    @BindView(R.id.discuss)
    TextView dicuss;
    @BindView(R.id.communicate)
    TextView communicate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_switch);
        ButterKnife.bind(this);


    }
    @OnClick({R.id.discuss, R.id.communicate})
    public void onClick(final View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.discuss:
                intent = new Intent(ConsultSwitchActivity.this, consultActivity.class);
                startActivity(intent);
                break;
            case R.id.communicate:
                intent = new Intent(ConsultSwitchActivity.this, ViewPagerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
