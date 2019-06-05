package com.example.quocthai.appbanhang.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quocthai.appbanhang.R;
import com.example.quocthai.appbanhang.model.Sanpham;
import com.example.quocthai.appbanhang.ultil.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {
    Toolbar toolbarChitiet;
    ImageView imgChitiet;
    TextView txtten,txtgia,txtmota;
    Spinner spinner;
    Button btndatmua;
    int id=0;
    String TenChitiet="";
    int GiaChitiet=0;
    String HinhanhChitiet="";
    String MotaChitiet="";
    int Idsanpham=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionToolbar();
        GetInformation();
        CatchEventSpinner();
        EventButton();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent=new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if  (MainActivity.manggiohang.size()>0){
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists=false;
                    for (int i = 0;i<MainActivity.manggiohang.size();i++){
                        if (MainActivity.manggiohang.get(i).getIdsp()==id){
                            MainActivity.manggiohang.get(i).setSoluong(MainActivity.manggiohang.get(i).getSoluong()+sl);
                            if (MainActivity.manggiohang.get(i).getSoluong()>=10){
                                MainActivity.manggiohang.get(i).setSoluong(10);
                            }
                            MainActivity.manggiohang.get(i).setGia(GiaChitiet * MainActivity.manggiohang.get(i).getSoluong());
                            exists=true;
                        }
                    }
                    if (exists==false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long Giamoi = soluong * GiaChitiet;
                        MainActivity.manggiohang.add(new GioHang(id,TenChitiet,Giamoi,HinhanhChitiet,soluong));
                    }
                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * GiaChitiet;
                    MainActivity.manggiohang.add(new GioHang(id,TenChitiet,Giamoi,HinhanhChitiet,soluong));
                }
                Intent intent=new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });

    }

    private void CatchEventSpinner() {
        Integer[] soluong=new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayadapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayadapter);

    }

    private void GetInformation() {

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getId();
        TenChitiet = sanpham.getTenSanPham();
        GiaChitiet = sanpham.getGiaSanPham();
        HinhanhChitiet = sanpham.getHinhAnh();
        MotaChitiet = sanpham.getMoTa();
        Idsanpham = sanpham.getIdLoai();
        txtten.setText(TenChitiet);
        DecimalFormat decimaFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá :"+decimaFormat.format(GiaChitiet)+"Đ");
        txtmota.setText(MotaChitiet);
        Picasso.with(getApplicationContext()).load(HinhanhChitiet)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imgChitiet);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void Anhxa() {
        toolbarChitiet = findViewById(R.id.toolbarchitiet);
        imgChitiet = findViewById(R.id.imageviewchitietsanpham);
        txtten = findViewById(R.id.texviewchitiettensanpham);
        txtgia = findViewById(R.id.texviewchitietgiasanpham);
        txtmota = findViewById(R.id.textviewmotachitietsanpham);
        spinner = findViewById(R.id.spinenrchitietsoluong);
        btndatmua = findViewById(R.id.buttondamua);

    }
}
