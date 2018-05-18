package com.example.bewika.recipeguide;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String getType;
    private static final String TAG = "myapp";

    //Tag สำหรับอ้างอิงการร้องขอไปเซิฟเวอร์(วอลเล่) กรณียกเลิกการร้องขอ
    public static final String REQUEST_TAG = "myrequest";
    private RequestQueue mQueue; //instance request(volley)
    ProgressDialog pDialog;
    private List<Data> datas = new ArrayList<>(); //List Data
//    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //รับค่าที่เป็นชื่อประเภทอาหาร เช่น ต้ม ผัด ทอด นึ่ง
        Bundle bundle = getIntent().getExtras();
        getType = getIntent().getExtras().getString("type");
        TextView txtType = (TextView)findViewById(R.id.textType);
        txtType.setText(getType);

        String url = "http://172.22.0.58/recipe/typemenu.php";
//        tv = (TextView)findViewById(R.id.txtType1);

        //show Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
//        pDialog.show();

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
