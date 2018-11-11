package com.guo.http;

import com.guo.beans.drugInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Created by guo_w on 2018/11/11.
 */

public class GetDrugInfo {
    private final String url = "http://192.168.123.226:8080/heart/drug/getDrugInfo";
    public List<drugInfo> getDrugInfoFromServer(){
        final List<drugInfo> drugInfoList = new ArrayList<>();
        try {
            String jsonString = new HttpRequestor().doGet(url);
            JSONArray jsonArray = new JSONArray(jsonString);
            ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // 初始化map数组对象
                String a1 = jsonObject.getString("a1");
                String a2 = jsonObject.getString("a2");
                String a3 = jsonObject.getString("a3");
                String a4 = jsonObject.getString("a4");
                String a5 = jsonObject.getString("a5");
                String a6 = jsonObject.getString("a6");
                System.out.println("a1");
                System.out.println("a2");
                drugInfo dInfo = new drugInfo(a1,a2,a3,a4,a5,a6);
                drugInfoList.add(dInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       return drugInfoList;
    }
}
