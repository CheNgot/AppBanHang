package com.example.quocthai.appbanhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quocthai.appbanhang.R;
import com.example.quocthai.appbanhang.activity.ChiTietSanPham;
import com.example.quocthai.appbanhang.model.Sanpham;
import com.example.quocthai.appbanhang.ultil.CheckConnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
    Context  context;
    ArrayList<Sanpham> arraysanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
        ItemHolder itemHolder= new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        Sanpham sanpham= arraysanpham.get(i);
        itemHolder.txttensanpham.setText(sanpham.getTenSanPham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,### ");
        itemHolder.txtgiasanpham.setText("Giá: "+decimalFormat.format(sanpham.getGiaSanPham())+"Đ");
        Picasso.with(context).load(sanpham.getHinhAnh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(itemHolder.imghinhsanpham);
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensanpham,txtgiasanpham;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsanpham=itemView.findViewById(R.id.imageviewsanpham);
            txttensanpham=itemView.findViewById(R.id.textviewtensanpham);
            txtgiasanpham=itemView.findViewById(R.id.textviewgiasanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,ChiTietSanPham.class);
                    intent.putExtra("thongtinsanpham",arraysanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnection.ShowToast_short(context,arraysanpham.get(getPosition()).getTenSanPham());
                    context.startActivity(intent);

                }
            });
        }
    }
}
