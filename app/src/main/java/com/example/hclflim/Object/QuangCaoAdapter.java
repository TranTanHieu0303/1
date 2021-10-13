package com.example.hclflim.Object;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import com.example.hclflim.Activity.PhimitemActivity;
import com.example.hclflim.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class QuangCaoAdapter extends PagerAdapter {
    Context context;
    ArrayList<Phim> phims;

    public QuangCaoAdapter(Context context, ArrayList<Phim> phims) {
        this.context = context;
        this.phims = phims;
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_quangcao,container,false);
        ImageView imageView = view.findViewById(R.id.img_QuangCao);
        TextView textView = view.findViewById(R.id.tv_quangcao);
        Phim phim = phims.get(position);
        if(phim!=null) {
            Picasso.with(context).load(phim.getHinhAnh())
                    .placeholder(R.drawable.img_loading)
                    .into(imageView);
            textView.setText(phim.getTenPhim());
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), PhimitemActivity.class);
                intent.putExtra("itemphim",phim);
                view.getContext().startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(phims !=null)
        return phims.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView((View) object);
    }
}
