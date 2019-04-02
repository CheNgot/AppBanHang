package com.example.quocthai.appbanhang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quocthai.appbanhang.R;
import com.example.quocthai.appbanhang.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayLaptop;

    public LaptopAdapter(Context context, ArrayList<Sanpham> arrayLaptop) {
        this.context = context;
        this.arrayLaptop = arrayLaptop;
    }


    @Override
    public int getCount() {
        return arrayLaptop.size();
    }


    @Override
    public Object getItem(int position) {
        return arrayLaptop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder
    {
        public TextView txttenlaptop;
        public TextView txtgialaptop;
        public TextView txtmotalatop;
        public ImageView imglaptop;
    }
    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        LaptopAdapter.ViewHolder viewholder = null;
        if (convertview == null) {
            viewholder = new LaptopAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.dong_laptop, null);
            viewholder.txttenlaptop = convertview.findViewById(R.id.textviewtenlaptop);
            viewholder.txtgialaptop = convertview.findViewById(R.id.textviewgialaptop);
            viewholder.txtmotalatop = convertview.findViewById(R.id.motalaptop);
            viewholder.imglaptop = convertview.findViewById(R.id.imagviewlaptop);
            convertview.setTag(viewholder);
        } else {
            viewholder = (LaptopAdapter.ViewHolder) convertview.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewholder.txttenlaptop.setText(sanpham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.txtgialaptop.setText("Giá :" + decimalFormat.format(sanpham.getGiaSanPham()) + "Đ");
        viewholder.txtmotalatop.setMaxLines(2);
        viewholder.txtmotalatop.setEllipsize(TextUtils.TruncateAt.END);
        viewholder.txtmotalatop.setText(sanpham.getMoTa());
        Picasso.with(context).load(sanpham.getHinhAnh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewholder.imglaptop);

        return convertview;
    }
}
