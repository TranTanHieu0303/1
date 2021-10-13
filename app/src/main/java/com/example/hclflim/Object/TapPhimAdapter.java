package com.example.hclflim.Object;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TapPhimAdapter extends RecyclerView.Adapter<TapPhimAdapter.viewhodel> {
    Context context;
    Phim phim;
    ArrayList<TapPhim> tapPhims;

    public TapPhimAdapter(Context context, Phim phim, ArrayList<TapPhim> tapPhim) {
        this.context = context;
        this.phim = phim;
        this.tapPhims = tapPhim;
    }

    @NonNull
    @NotNull
    @Override
    public viewhodel onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_itemphim,null);
        return new viewhodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TapPhimAdapter.viewhodel holder, int position) {
        holder.phim = phim;
        TapPhim tapPhim = tapPhims.get(position);
        Picasso.with(context).load(tapPhim.getHinhAnh())
                .placeholder(R.drawable.img_loading)
                .into(holder.hinh);

        holder.tenTap.setText(tapPhim.getTenTap());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhimitemActivity.class);
                intent.putExtra("itemphim",phim);
                intent.putExtra("tap", tapPhim);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tapPhims.size();
    }

    public  class viewhodel extends RecyclerView.ViewHolder{
        ImageView hinh;
        TextView tenTap;
        TapPhim tapphim;
        Phim phim;
        ConstraintLayout layout;
        public viewhodel(@NonNull @NotNull View itemView) {
            super(itemView);
            hinh = itemView.findViewById(R.id.img_HinhAnh);
            layout = itemView.findViewById(R.id.lauout_itemphim);
            tenTap = itemView.findViewById(R.id.tv_TenPhim);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
