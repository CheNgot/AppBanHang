package com.example.quocthai.appbanhang.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quocthai.appbanhang.R;
import com.example.quocthai.appbanhang.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LoaispAdapter extends BaseAdapter {


     ArrayList<Loaisp> arrayListloaisp ;
     Context context;

    public LoaispAdapter(ArrayList<Loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }
    @Override
    public int getCount() {
        return arrayListloaisp.size();
        //trả về toàn bộ mảng
    }

    @Override
    public Object getItem(int position) {
        return arrayListloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHoder{
        TextView txttenloaisp;
        ImageView imaglaoaisp;




    }//tạo để ánh xạ bên dong_listview qua để vẽ cho toàn bộ

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHoder viewHoder=null;
        if (view == null){
            viewHoder = new ViewHoder();
            LayoutInflater inflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHoder.txttenloaisp = (TextView) view.findViewById(R.id.txtloaisp);
            viewHoder.imaglaoaisp = (ImageView) view.findViewById(R.id.imgloaisp);
            view.setTag(viewHoder);

        }else {
            viewHoder = (ViewHoder) view.getTag();
        }
        Loaisp loaisp = (Loaisp) getItem(i);
        viewHoder.txttenloaisp.setText(loaisp.getTenloaisp());
        Picasso.with(context).load(loaisp.getHinhanhloaisp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHoder.imaglaoaisp);
        return view;
    }
}
