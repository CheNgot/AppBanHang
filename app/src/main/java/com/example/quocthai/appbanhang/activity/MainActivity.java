package com.example.quocthai.appbanhang.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.quocthai.appbanhang.R;
import com.example.quocthai.appbanhang.adapter.LoaispAdapter;
import com.example.quocthai.appbanhang.model.Loaisp;
import com.example.quocthai.appbanhang.ultil.CheckConnection;
import com.example.quocthai.appbanhang.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            ActionBar();
            ActionViewFilpper();
            Getdulieuloaisp();
        }
        else
        {
            CheckConnection.ShowToast_short(getApplicationContext(),"Kiểm tra lại kết nối");
            finish();
        }

    }

    private void Getdulieuloaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    for(int i=0;i<=response.length();i++)
                    {
                        try {
                            loaispAdapter.notifyDataSetChanged();
                            JSONObject jsonObject= response.getJSONObject(i);
                            id=jsonObject.getInt("Id");
                            tenloaisp=jsonObject.getString("TenLoai");
                            hinhanhloaisp=jsonObject.getString("HinhLoai");
                            mangloaisp.add(new Loaisp(id,tenloaisp,hinhanhloaisp));
                            loaispAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add(3,new Loaisp(0,"Liên Hệ","https://cdn.onlinewebfonts.com/svg/img_289167.png"));
                    mangloaisp.add(4,new Loaisp(0,"Thông Tin","https://cdn3.iconfinder.com/data/icons/media-icons-23/100/info2-512.png"));

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFilpper() {
        ArrayList<String> manghinhquangcao = new ArrayList<>();
        manghinhquangcao.add("https://object-storage.tyo1.cloud.z.com/v1/zc_6c13a3172446411fab7837a8a5479710/mobilecityvn/images/2019/01/xiaomi-redmi-note-7-pink.jpg");
        manghinhquangcao.add("https://object-storage.tyo1.cloud.z.com/v1/zc_6c13a3172446411fab7837a8a5479710/mobilecityvn/images/2019/01/xiaomi-mi-8-lite-hong.jpg");
        manghinhquangcao.add("https://object-storage.tyo1.cloud.z.com/v1/zc_6c13a3172446411fab7837a8a5479710/mobilecityvn/images/2019/01/iphone-xs-max-white.jpg");
        manghinhquangcao.add("https://object-storage.tyo1.cloud.z.com/v1/zc_6c13a3172446411fab7837a8a5479710/mobilecityvn/images/2019/01/iphone-8-plus-red.jpg");
        for (int i=0;i<manghinhquangcao.size();i++)
        {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(manghinhquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_silde_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_silde_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    private void ActionBar()
    {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size) ;
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void AnhXa()
    {
        toolbar =  findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerView = findViewById(R.id.recyclervew);
        navigationView = findViewById(R.id.navigationview);
        listViewmanhinhchinh = findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = findViewById(R.id.drawerlayout);
        mangloaisp= new ArrayList<>();
        mangloaisp.add(0,new Loaisp(0,"Trang Chính","https://previews.123rf.com/images/dstarky/dstarky1702/dstarky170200496/71408124-home-icon-or-logo-in-modern-line-style-high-quality-black-outline-pictogram-for-web-site-design-and-.jpg"));
        loaispAdapter = new LoaispAdapter(mangloaisp,getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaispAdapter);


    }
 }
