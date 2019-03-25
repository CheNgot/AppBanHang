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
    public LoaispAdapter(ArrayList<Loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    ArrayList<Loaisp> arrayListloaisp = new ArrayList<>();
    Context context;
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
    class ViewHolder{
        TextView txttenloaisp;
        ImageView imaglaoaisp;




    }//tạo để ánh xạ bên dong_listview qua để vẽ cho toàn bộ

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView ==null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.txttenloaisp= convertView.findViewById(R.id.txtloaisp);
            viewHolder.imaglaoaisp= convertView.findViewById(R.id.imgloaisp);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
            Loaisp loaisp = (Loaisp) getItem(position);
            viewHolder.txttenloaisp.setText(loaisp.getTenloaisp());
            Picasso.with(context).load(loaisp.getHinhanhloaisp())
                    .placeholder(R.drawable.noimage)//nếu hình ảnh chưa load xong thì sẽ hiện
                    .error(R.drawable.error)//load lỗi thì sẽ hiện
                    .into(viewHolder.imaglaoaisp);//load xong thì sẽ hiện
        }

        return convertView;
    }
}
