package com.guo.xinzangapp.doctors;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guo.beans.UserPatient;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.consult.PatientListAdapter;
import com.guo.xinzangapp.sports.FeelingListActivity;

import java.util.List;

/**
 * Created by guo_w on 2019/4/24.
 */

public class DoctorPatientListAdapter extends RecyclerView.Adapter<DoctorPatientListAdapter.ViewHolder> {

    private List<UserPatient> patientList;
    private Context context;

    public DoctorPatientListAdapter(List<UserPatient> patientList, Context context) {
        this.patientList = patientList;
        this.context = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_list_item,parent,false);

        final DoctorPatientListAdapter.ViewHolder holder = new DoctorPatientListAdapter.ViewHolder(view);
        holder.patient_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                UserPatient userPatient=patientList.get(position);
                String patientName =userPatient.getUserName();


                Bundle bundle = new Bundle();
                bundle.putString("patientName",patientName);

                Intent intent = new Intent(context,DoctorFeelingListActivity.class);
//                intent.putExtras(bundle);
                intent.putExtra("Message",bundle);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserPatient userPatient = patientList.get(position);
        holder.patient_name.setText(userPatient.getUserName());
    }

    @Override
    public int getItemCount() {
        if(patientList==null) {
            return 0;
        }
        return patientList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView patient_name;
        View MessageListView;
        public ViewHolder(View view) {
            super(view);
            MessageListView = view;
            patient_name = (TextView) view.findViewById(R.id.patient_name);

        }
    }
}
