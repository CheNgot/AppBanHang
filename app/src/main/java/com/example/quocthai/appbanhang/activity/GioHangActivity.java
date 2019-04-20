package com.example.quocthai.appbanhang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quocthai.appbanhang.R;
import com.example.quocthai.appbanhang.adapter.GioHangAdapter;
import com.example.quocthai.appbanhang.ultil.CheckConnection;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {

    ListView lvgiohang;
    TextView txtthongbao;
    TextView txttongien;
    Button btnthanhtoan;
    Button btntieptucmua;
    Toolbar toolbargiohang;
    GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        Anhxa();
        ActionToolBar();
        CheckData();
        EvenUltil();
        
    }

    private void EvenUltil() {
        long tongtien =0;
        for (int i=0;i<MainActivity.manggiohang.size();i++)
        {
            tongtien +=MainActivity.manggiohang.get(i).getGia();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongien.setText(decimalFormat.format(tongtien)+" Đ");
    }


    private void CheckData() {
        if(MainActivity.manggiohang.size()<=0)
        {
            gioHangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);

        }
        else
        {
            gioHangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        lvgiohang = findViewById(R.id.listviewgioahang);
        txtthongbao = findViewById(R.id.textviewthongbao);
        txttongien= findViewById(R.id.gia);
        btnthanhtoan = findViewById(R.id.buttonthanhtoangiohang);
        btntieptucmua = findViewById(R.id.buttontieptucmuahang);
        toolbargiohang = findViewById(R.id.toolbargiohang);
        gioHangAdapter = new GioHangAdapter(GioHangActivity.this,MainActivity.manggiohang);
        lvgiohang.setAdapter(gioHangAdapter);
    }
}
