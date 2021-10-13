package com.example.hclflim.Object;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hclflim.Activity.PhimitemActivity;
import com.example.hclflim.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DaXemAdapter extends RecyclerView.Adapter<DaXemAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<Phim> dulieu;
    TaiKhoan taiKhoan;

    public DaXemAdapter(Context context, TaiKhoan taiKhoan, ArrayList<Phim> dulieu) {
        this.context = context;
        this.dulieu = dulieu;
        this.taiKhoan = taiKhoan;
    }

    @NonNull
    @NotNull
    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_da_xem_item,null);

        return new KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DaXemAdapter.KHUNGNHIN holder, int position) {
        Phim phim = dulieu.get(position);
        Picasso.with(context).load(phim.getHinhAnh())
                .placeholder(R.drawable.img_loading)
                .into(holder.img_hinh);
        holder.tv_ten.setText(phim.getTenPhim());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    public  class KHUNGNHIN extends RecyclerView.ViewHolder{

       LinearLayout layout ;
       ImageView img_hinh;
       TextView tv_ten;
       public KHUNGNHIN(@NonNull @NotNull View itemView) {
           super(itemView);
           layout = itemView.findViewById(R.id.layout_daxemitem);
           img_hinh = itemView.findViewById(R.id.img_hinhdaxem);
           tv_ten =itemView.findViewById(R.id.tv_tenphdaxem);
       }
   }
}
