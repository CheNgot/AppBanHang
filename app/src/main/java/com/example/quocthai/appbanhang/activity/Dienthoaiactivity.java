package com.example.quocthai.appbanhang.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.quocthai.appbanhang.R;
import com.example.quocthai.appbanhang.adapter.DienthoaiAdapter;
import com.example.quocthai.appbanhang.model.Sanpham;
import com.example.quocthai.appbanhang.ultil.CheckConnection;
import com.example.quocthai.appbanhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dienthoaiactivity extends AppCompatActivity {
    Toolbar toolbardt;
    ListView listViewdienthoai;
    DienthoaiAdapter dienthoaiAdapter;
    ArrayList<Sanpham> mangdt;
    int iddt=0;
    int Page=1;
    View footerview;
    boolean isLoading= false;
    myHandler myHandler;
    boolean limitdata = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dienthoaiactivity);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext()))
        {
            GetLoaiId();
            ActionToolbar();
            GetData(Page);
            LoadMoreData();
        }
        else
        {
            CheckConnection.ShowToast_short(getApplicationContext(),"Kiểm tra kết nối!!!");
            finish();
        }

    }

    private void LoadMoreData() {
        listViewdienthoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",mangdt.get(position));
                startActivity(intent);
            }
        });
        //sự kiện vuốt của listview
        listViewdienthoai.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            //vuốt đến vị trí nào đó thì sẽ trả về trong function này
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            //vuốt listview trả về trong function này
            public void onScroll(AbsListView absListView, int firstItem, int visibleItem, int totalItem) {
                //lấy vị trí đầu + số item nhìn thấy mà bằng tổng item thì đang vuốt đến cuối
                if(firstItem+visibleItem==totalItem && totalItem !=0 && isLoading==false && limitdata==false)
                {
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int Page) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan=Server.Duongdandienthoai+String.valueOf(Page);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String ten="";
                int gia=0;
                String hinhanh="";
                String mota="";
                int idloaisp=0;
                if(response!=null && response.length()!=2)
                {
                    listViewdienthoai.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i< response.length();i++)
                        {
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            id=jsonObject.getInt("Id");
                            ten=jsonObject.getString("TenSanPham");
                            gia = jsonObject.getInt("GiaSanPham");
                            hinhanh=jsonObject.getString("HinhAnh");
                            mota=jsonObject.getString("MoTa");
                            idloaisp= jsonObject.getInt("IdLoai");
                            mangdt.add(new Sanpham(id,ten,gia,hinhanh,mota,idloaisp));
                            dienthoaiAdapter.notifyDataSetChanged();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    limitdata= true;
                    listViewdienthoai.removeFooterView(footerview);
                    CheckConnection.ShowToast_short(getApplicationContext(),"Đã hết dữ liệu khỏi kéo nữa!!!");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_short(getApplicationContext(),error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param= new HashMap<String, String>();
                param.put("idsp",String.valueOf(iddt));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void GetData() {

    }

    private void ActionToolbar() {
        setSupportActionBar(toolbardt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void GetLoaiId() {
        iddt= getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("BBB",iddt+"");

    }

    private void Anhxa() {
        toolbardt = findViewById(R.id.toolbardienthoai);
        listViewdienthoai = findViewById(R.id.listviewdienthoai);
        mangdt= new ArrayList<>();
        dienthoaiAdapter=new DienthoaiAdapter(getApplicationContext(),mangdt);
        listViewdienthoai.setAdapter(dienthoaiAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        myHandler = new myHandler();
    }
    public  class myHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
                    listViewdienthoai.addFooterView(footerview);
                    break;

                case 1:

                    GetData(++Page);
                    isLoading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread
    {
        @Override
        public void run() {
            myHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = myHandler.obtainMessage(1);
            //obtainMessage là phương thức liên kết Thread với Handler
            myHandler.sendMessage(message);
            super.run();
        }
    }
}
