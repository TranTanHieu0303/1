package com.example.hclflim.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hclflim.Object.Phim;
import com.example.hclflim.Object.TapPhim;
import com.example.hclflim.Object.TapPhimAdapter;
import com.example.hclflim.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TapphimFragment extends Fragment {
    Phim phim;
    ArrayList<TapPhim> tapPhims;
    RecyclerView recyclerView;
    TapPhimAdapter tapPhimAdapter;

    public TapphimFragment(Phim phim, ArrayList<TapPhim> tapPhims) {
        this.phim = phim;
        this.tapPhims = tapPhims;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tapphim_fragment,container,false);
        recyclerView  = view.findViewById(R.id.rcv_tapphim);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        tapPhimAdapter = new TapPhimAdapter(getContext(),phim,tapPhims);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tapPhimAdapter);
        return  view;
    }
}
