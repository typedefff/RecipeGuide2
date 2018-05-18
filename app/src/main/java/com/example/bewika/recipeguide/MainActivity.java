package com.example.bewika.recipeguide;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "myapp";

    //Tag สำหรับอ้างอิงการร้องขอไปเซิฟเวอร์(วอลเล่) กรณียกเลิกการร้องขอ
    public static final String REQUEST_TAG = "myrequest";
    private RequestQueue mQueue; //instance request(volley)
    ProgressDialog pDialog;
    private List<Data> datas = new ArrayList<>(); //List Data
//    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //url called php many record
//        String url = "http://192.168.2.97/recipe/typemenu.php";
//        String url = "http://172.22.3.61/recipe/typemenu.php";
        String url = "http://172.22.0.58/recipe/typemenu.php";
//        tv = (TextView)findViewById(R.id.txtType1);

        //show Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
//        pDialog.show();


        //sent request JsonArrayRequest to SERVER (use VOLLEY)
        JsonArrayRequest jsRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        JSONObject jsObj ;
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                jsObj = response.getJSONObject(i);
                                //Gson transfer data type DATA
                                Data dataitem = gson.fromJson(String.valueOf(jsObj), Data.class);
                                datas.add(dataitem);
                                Log.d(TAG, "gson " + dataitem.getId());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (datas.size() > 0) {
                            displayListview();
                        }
                        pDialog.hide();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d(TAG,error.toString());
                        Toast.makeText(getBaseContext(),error.toString(),
                                Toast.LENGTH_SHORT).show();
                        pDialog.hide();
                    }
                });

        //Create INSTANT queue the sent Request to QUEUE for running
        mQueue = Volley.newRequestQueue(this);
        jsRequest.setTag(REQUEST_TAG);
        mQueue.add(jsRequest);
    }

    //cancle case switch app
    @Override
    protected void onStop(){
        super.onStop();
        if (mQueue != null){
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    //send data LIST to Adapter show in ListView
    public void displayListview(){
        MyAdapter adapter = new MyAdapter(this, datas);
        ListView lv = (ListView) findViewById(R.id.ListView);
        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    //ส่งเป็น id
        int id = datas.get(i).getId();
        Intent itn = new Intent(this, Menu.class);
        itn.putExtra("recID ",id);
        itn.putExtra("type",datas.get(i).getType());
        startActivity(itn);
    }
}
