package com.example.quocthai.appbanhang.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
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
import com.example.quocthai.appbanhang.adapter.SanphamAdapter;
import com.example.quocthai.appbanhang.model.Loaisp;
import com.example.quocthai.appbanhang.model.Sanpham;
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
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
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
            Getdulieuspmoinhat();
            CatchOnItemListView();
        }
        else
        {
            CheckConnection.ShowToast_short(getApplicationContext(),"Kiểm tra lại kết nối");
            finish();
        }

    }

    private void CatchOnItemListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                    {
                        Intent intent= new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);

                    }
                    else
                    {
                        CheckConnection.ShowToast_short(getApplicationContext(),"Kiểm tra kết nối");
                    }
                    drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent= new Intent(MainActivity.this,Dienthoaiactivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            startActivity(intent);

                        }
                        else
                        {
                            CheckConnection.ShowToast_short(getApplicationContext(),"Kiểm tra kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent= new Intent(MainActivity.this,LapTopActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisp.get(position).getId());
                            startActivity(intent);

                        }
                        else
                        {
                            CheckConnection.ShowToast_short(getApplicationContext(),"Kiểm tra kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent= new Intent(MainActivity.this,LienHeActivity.class);

                            startActivity(intent);

                        }
                        else
                        {
                            CheckConnection.ShowToast_short(getApplicationContext(),"Kiểm tra kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent= new Intent(MainActivity.this,ThongTinActivity.class);

                            startActivity(intent);

                        }
                        else
                        {
                            CheckConnection.ShowToast_short(getApplicationContext(),"Kiểm tra kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void Getdulieuspmoinhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duondansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    int ID=0;
                    String tensanpham="";
                    Integer giasanpham=0;
                    String hinhanhsanpham="";
                    String motasanpham="";
                    int Idloaisanpham=0;
                    for(int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID=jsonObject.getInt("Id");
                            tensanpham=jsonObject.getString("TenSanPham");
                            giasanpham=jsonObject.getInt("GiaSanPham");
                            hinhanhsanpham=jsonObject.getString("HinhAnh");
                            motasanpham=jsonObject.getString("MoTa");
                            Idloaisanpham =jsonObject.getInt("IdLoai");
                            mangsanpham.add(new Sanpham(ID,tensanpham,giasanpham,hinhanhsanpham,motasanpham,Idloaisanpham));
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void Getdulieuloaisp() {
        RequestQueue requesQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response!= null){
                    for (int i = 0;i<response.length();i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("Id");
                            Log.d("BBB",jsonObject.getString("TenLoai"));
                            tenloaisp = (jsonObject.getString("TenLoai"));
                            hinhanhloaisp = jsonObject.getString("HinhLoai");
                            mangloaisp.add(new Loaisp(id,tenloaisp,hinhanhloaisp));
                            loaispAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add(new Loaisp(0,"Liên hệ","https://cdn4.iconfinder.com/data/icons/bussines-people/572/man-contact-computer-512.png"));
                    mangloaisp.add(new Loaisp(0,"Thông tin","http://www.smiphee.com/wp-content/uploads/2017/05/services-icon-png-2291-300x300.png"));


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_short(getApplicationContext(),error.toString());
            }
        });
        requesQueue.add(jsonArrayRequest);
    }


    private void ActionViewFilpper() {
        ArrayList<String> manghinhquangcao = new ArrayList<>();
        manghinhquangcao.add("https://cdn.tgdd.vn/qcao/26_03_2019_09_32_20_huawei-P30Pro-800-300.png");
        manghinhquangcao.add("https://cdn.tgdd.vn/qcao/27_03_2019_09_41_22_800-300.png");
        manghinhquangcao.add("https://cdn.tgdd.vn/qcao/09_02_2019_22_58_10_iphone-master-800-300.png");
        manghinhquangcao.add("https://cdn.tgdd.vn/qcao/22_03_2019_22_17_47_800-300.png");
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

        mangsanpham = new ArrayList<>();
        sanphamAdapter= new SanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(sanphamAdapter);

    }
 }
