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
    Toolbar toolbarchitietsanpham;
    ImageView imgchitetsanpham;
    TextView txtten,txtgia,txtmota;
    Spinner spinnerchitietsanpham;
    Button btndatmua;
    int id=0;
    String ten="";
    int gia=0;
    String hinhanh="";
    String mota="";
    int idloaisp=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionToolBar();
        GetInformation();
        CatchEventOnSpinner();
        EventButton();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menugiohang :
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.manggiohang.size()>0)
                {
                    int sl= Integer.parseInt(spinnerchitietsanpham.getSelectedItem().toString());
                    boolean exists =false;
                    for(int i=0;i<MainActivity.manggiohang.size();i++)
                    {
                        if(MainActivity.manggiohang.get(i).getIdsp()==id)
                        {
                            MainActivity.manggiohang.get(i).setSoluong(MainActivity.manggiohang.get(i).getSoluong()+sl);
                            if(MainActivity.manggiohang.get(i).getSoluong()>=10)
                            {
                                MainActivity.manggiohang.get(i).setSoluong(10);
                            }
                            MainActivity.manggiohang.get(i).setGia(gia* MainActivity.manggiohang.get(i).getSoluong());
                            exists= true;
                        }


                    }
                    if(exists==true)
                    {
                        int soluong= Integer.parseInt(spinnerchitietsanpham.getSelectedItem().toString() );
                        long giamoi=soluong*gia;
                        MainActivity.manggiohang.add(new GioHang(id,ten,giamoi,hinhanh,soluong));
                    }
                }
                else
                {
                    int soluong= Integer.parseInt(spinnerchitietsanpham.getSelectedItem().toString() );
                    long giamoi=soluong*gia;
                    MainActivity.manggiohang.add(new GioHang(id,ten,giamoi,hinhanh,soluong));
                }
                Intent intent = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventOnSpinner() {
        Integer[]soluong= new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter= new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinnerchitietsanpham.setAdapter(arrayAdapter);
    }

    private void GetInformation() {

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getId();
        ten= sanpham.getTenSanPham();
        gia = sanpham.getGiaSanPham();
        mota = sanpham.getMoTa();
        idloaisp = sanpham.getIdLoai();
        hinhanh = sanpham.getHinhAnh();
        txtten.setText(ten);
        txtmota.setText(mota);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá: "+decimalFormat.format(gia)+"đồng");
        Picasso.with(getApplicationContext()).load(hinhanh)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imgchitetsanpham);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarchitietsanpham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitietsanpham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarchitietsanpham = findViewById(R.id.toolbarchitiet);
        txtten = findViewById(R.id.texviewchitiettensanpham);
        txtgia = findViewById(R.id.texviewchitietgiasanpham);
        spinnerchitietsanpham = findViewById(R.id.spinenrchitietsoluong);
        txtmota =findViewById(R.id.textviewmotachitietsanpham);
        btndatmua = findViewById(R.id.buttondamua);
        imgchitetsanpham= findViewById(R.id.imageviewchitietsanpham);


    }
}
