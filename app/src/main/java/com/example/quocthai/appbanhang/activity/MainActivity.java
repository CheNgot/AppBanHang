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

import com.example.quocthai.appbanhang.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        ActionBar();
        ActionViewFilpper();
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



    }
}
