package com.example.quocthai.appbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quocthai.appbanhang.R;
import com.example.quocthai.appbanhang.activity.GioHangActivity;
import com.example.quocthai.appbanhang.activity.MainActivity;
import com.example.quocthai.appbanhang.ultil.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> gioHangArrayList;

    public GioHangAdapter(Context context, ArrayList<GioHang> gioHangArrayList) {
        this.context = context;
        this.gioHangArrayList = gioHangArrayList;
    }


    @Override
    public int getCount() {
        return gioHangArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangArrayList.get(position);
    }
    public class ViewHolder
    {
        public TextView txttengiohang,txtgiagiohang;
        public ImageView imggiohang;
        public Button btnminus,btnvalue,btnplus;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int i, View view, ViewGroup viewGroup)
    {
        ViewHolder viewHolder= null;
        if (view ==null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_giohang, null);
            viewHolder.txttengiohang = view.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang = view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang = view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnminus = view.findViewById(R.id.buttonminus);
            viewHolder.btnplus = view.findViewById(R.id.buttonplus);
            viewHolder.btnvalue = view.findViewById(R.id.buttonvalue);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }
        GioHang giohang = (GioHang) getItem(i);
        viewHolder.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGia())+"Đ");
        Picasso.with(context).load(giohang.getHinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imggiohang);
        viewHolder.btnvalue.setText(giohang.getSoluong()+"");
        int sl= Integer.parseInt(viewHolder.btnvalue.getText().toString());
        if(sl>=10)
        {
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        else if(sl<=1)
        {

            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }
        else if (sl>=1)
        {
            viewHolder.btnplus.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        final ViewHolder finalViewHolder = viewHolder;
        final ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btnvalue.getText().toString())+1;
                int slhientai = MainActivity.manggiohang.get(i).getSoluong();
                long giaht =MainActivity.manggiohang.get(i).getGia();
                MainActivity.manggiohang.get(i).setSoluong(slmoinhat);
                long giamoinhat = (giaht*slmoinhat)/slhientai;
                MainActivity.manggiohang.get(i).setGia(giamoinhat);
                DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
                finalViewHolder1.txtgiagiohang.setText(decimalFormat.format(giamoinhat)+"Đ");
                GioHangActivity.EvenUltil();
                if(slmoinhat>9)
                {
                    finalViewHolder.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }
                else
                {
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }

            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btnvalue.getText().toString())-1;
                int slhientai = MainActivity.manggiohang.get(i).getSoluong();
                long giaht =MainActivity.manggiohang.get(i).getGia();
                MainActivity.manggiohang.get(i).setSoluong(slmoinhat);
                long giamoinhat = (giaht*slmoinhat)/slhientai;
                MainActivity.manggiohang.get(i).setGia(giamoinhat);
                DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
                finalViewHolder1.txtgiagiohang.setText(decimalFormat.format(giamoinhat)+"Đ");
                GioHangActivity.EvenUltil();
                if(slmoinhat<2)
                {
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }
                else
                {
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }

            }
        });

        return view;
    }

}
