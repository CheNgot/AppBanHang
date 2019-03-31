package com.example.quocthai.appbanhang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class DienthoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayDienThoai;

    public DienthoaiAdapter(Context context, ArrayList<Sanpham> arrayDienThoai) {
        this.context = context;
        this.arrayDienThoai = arrayDienThoai;
    }

    @Override
    public int getCount() {
        return arrayDienThoai.size();
    }


    @Override
    public Object getItem(int position) {
        return arrayDienThoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Viewholder
    {
        public TextView txttendienthoai;
        public TextView txtgiadienthoai;
        public TextView txtmotadienthoai;
        public ImageView imgdienthoai;
    }
    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        Viewholder viewholder = null;
        if (convertview == null){
            viewholder= new Viewholder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.dong_dienthoai,null);
            viewholder.txttendienthoai = convertview.findViewById(R.id.textviewtendienthoai);
            viewholder.txtgiadienthoai = convertview.findViewById(R.id.textviewgiadienthoai);
            viewholder.txtmotadienthoai = convertview.findViewById(R.id.motadienthoai);
            viewholder.imgdienthoai = convertview.findViewById(R.id.imagviewdienthoai);
            convertview.setTag(viewholder);
        }else {
            viewholder = (Viewholder) convertview.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewholder.txttendienthoai.setText(sanpham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewholder.txtgiadienthoai.setText("Giá :" + decimalFormat.format(sanpham.getGiaSanPham()) + "Đ");
        viewholder.txtmotadienthoai.setMaxLines(2);
        viewholder.txtmotadienthoai.setEllipsize(TextUtils.TruncateAt.END);
        viewholder.txtmotadienthoai.setText(sanpham.getMoTa());
        Picasso.with(context).load(sanpham.getHinhAnh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewholder.imgdienthoai);

        return convertview;
    }
}
