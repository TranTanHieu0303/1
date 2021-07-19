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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhimAdapter extends RecyclerView.Adapter<PhimAdapter.KHUNGNHIN> {
    Context context;
    ArrayList<Phim> dulieu;

    public PhimAdapter(Context context, ArrayList<Phim> dulieu) {
        this.context = context;
        this.dulieu = dulieu;
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
                    Intent intent = new Intent(itemView.getContext(), PhimitemActivity.class);
                    intent.putExtra("itemphim",phim);
                    itemView.getContext().startActivity(intent);
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
