package com.example.hclflim.Object;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hclflim.Activity.PhimitemActivity;
import com.example.hclflim.R;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<Phim> dulieu;
TaiKhoan taiKhoan;
    public PhimAdapter(Context context, ArrayList<Phim> dulieu,TaiKhoan taiKhoan) {
        this.context = context;
        this.dulieu = dulieu;
        this.taiKhoan = taiKhoan;
    }

    @NonNull
    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_itemphim,null);
        return  new KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN holder, int position) {
    Phim phim=dulieu.get(position);
    holder.phim = phim;
    
    Picasso.with(context).load(phim.getHinhAnh())
            .placeholder(R.drawable.img_loading)
            .into(holder.hinh);

    holder.TenPhim.setText(phim.getTenPhim());
    holder.layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FirebaseDatabase.getInstance().getReference().child("TaiKhoan").child(taiKhoan.getSDT()).child("DaXem").child(phim.getMaPhim()).setValue(phim);
            Intent intent = new Intent(context, PhimitemActivity.class);
            intent.putExtra("itemphim",phim);
            intent.putExtra("TaiKhoan",taiKhoan);
            context.startActivity(intent);
        }
    });
    }


    @Override
    public int getItemCount() {
        return dulieu.size();
    }



    public class KHUNGNHIN extends RecyclerView.ViewHolder
    {
        ImageView hinh;
        TextView TenPhim;
        Phim phim;
        ConstraintLayout layout;

        public KHUNGNHIN(@NonNull final View itemView) {
            super(itemView);
            hinh = itemView.findViewById(R.id.img_HinhAnh);
            layout = itemView.findViewById(R.id.lauout_itemphim);
            TenPhim = itemView.findViewById(R.id.tv_TenPhim);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            TenPhim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }
    }
}
