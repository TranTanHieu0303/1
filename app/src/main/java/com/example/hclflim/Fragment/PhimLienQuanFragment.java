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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hclflim.Object.Phim;
import com.example.hclflim.Object.PhimAdapter;
import com.example.hclflim.Object.TaiKhoan;
import com.example.hclflim.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PhimLienQuanFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    String matheloai;
    RecyclerView recyclerView;
    ArrayList<Phim> phimArrayList;
    PhimAdapter phimAdapter;
    TaiKhoan taiKhoan;
    public PhimLienQuanFragment(String matheloai,TaiKhoan taiKhoan) {
        this.matheloai = matheloai;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.taiKhoan = taiKhoan;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layuot_phimlienquan,container,false);
        recyclerView = view.findViewById(R.id.rcv_phimlq);
        loadPhim();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(phimAdapter);
        return view;

    }

    private void loadPhim() {
        phimArrayList = new ArrayList<>();
        String[] dstl = matheloai.split(",");
        DatabaseReference data = FirebaseDatabase.getInstance().getReference().child("Phim");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()
                     ) {
                    Phim p = ds.getValue(Phim.class);
                    if(p.getTheLoai()!=null)
                    for (String s: dstl
                         ) {

                        if(p.getTheLoai().contains(s))
                            phimArrayList.add(p);
                    }


                }
                phimAdapter = new PhimAdapter(getContext(),phimArrayList,taiKhoan);
                recyclerView.setAdapter(phimAdapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}
