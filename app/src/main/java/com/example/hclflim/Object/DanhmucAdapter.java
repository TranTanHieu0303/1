package com.example.hclflim.Object;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.hclflim.Activity.DanhMucPhimMainActivity;
import com.example.hclflim.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class DanhmucAdapter extends RecyclerView.Adapter<DanhmucAdapter.danhmucViewHoer> {
    Context context;
    private ArrayList<DanhMuc> danhMucs ;
    TaiKhoan taiKhoan;
    public DanhmucAdapter(Context context,ArrayList<DanhMuc> danhMucs,TaiKhoan taiKhoan) {
        this.context  = context;
        this.danhMucs = danhMucs;
        this.taiKhoan = taiKhoan;
    }

    @NonNull
    @Override
    public danhmucViewHoer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listphim,parent,false);
        return new danhmucViewHoer(v);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull danhmucViewHoer holder, int position) {
        DanhMuc danhMuc = danhMucs.get(position);
        holder.tv_dm.setText(danhMuc.getTenDM());
        holder.rev.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.rev.setFocusable(false);
        PhimAdapter phimAdapter = new PhimAdapter(context,danhMuc.getPhims(),taiKhoan);
        holder.rev.setAdapter(phimAdapter);
        holder.tv_tatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhMucPhimMainActivity.class);
                intent.putExtra("MaDM",danhMuc.getMaDM());
                intent.putExtra("TaiKhoan",taiKhoan);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(danhMucs!= null)
            return danhMucs.size();
        return 0;
    }


    public  class danhmucViewHoer extends RecyclerView.ViewHolder{
        private  RecyclerView rev ;
        private TextView tv_dm;
        private  TextView tv_tatca;
        public danhmucViewHoer(@NonNull View itemView) {
            super(itemView);
            rev = itemView.findViewById(R.id.recycle_lstPhim);
            tv_dm = itemView.findViewById(R.id.tv_TenMuc);
            tv_tatca = itemView.findViewById(R.id.tv_tatca);
        }
    }

}
