package com.example.hclflim.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hclflim.Object.Phim;
import com.example.hclflim.Object.QuocGia;
import com.example.hclflim.Object.TheLoai;
import com.example.hclflim.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ThongTinPhimFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();
    ArrayList<QuocGia> quocGia = new ArrayList<>();
    final ArrayList<TheLoai> theLoai =new ArrayList<>();
    Phim p ;
    String t = "" ;
    TextView tv_tenphim,tv_thoiluong,tv_dienvien,tv_quocgia,tv_theloai,tv_sotap,tv_mota;
    public ThongTinPhimFragment(Phim p) {
        this.p = p;
        this.firebaseAuth = FirebaseAuth.getInstance();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.layout_thongtin,container,false);
        tv_tenphim = v.findViewById(R.id.tv_ttTenphim);
        tv_thoiluong = v.findViewById(R.id.tv_ttThoiluongHT);
        tv_dienvien = v.findViewById(R.id.tv_ttQuocGiaHT);
        tv_quocgia = v.findViewById(R.id.tv_ttDienvienHT);
        tv_theloai = v.findViewById(R.id.tv_ttTheLoaiHT);
        tv_sotap = v.findViewById(R.id.tv_ttSotapHT);
        tv_mota = v.findViewById(R.id.tv_ttMotaHT);
        data.child("QuocGia").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                QuocGia qGia =snapshot.getValue(QuocGia.class);
                quocGia.add(qGia);
                loadtab();
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        data.child("TheLoai").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                TheLoai thLoai = snapshot.getValue(TheLoai.class);
                String[] strtl = p.getTheLoai().split(",");
                for (String item:strtl
                     ) {
                    if(thLoai.getMaTL().equals(item))
                    {
                        p.setTheLoai(p.getTheLoai().replaceAll(thLoai.getMaTL(),thLoai.getTenTL()));
                    }
                }
                loadtab();

            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        return v;

    }
    private void loadtab() {
        tv_tenphim.setText(p.getTenPhim());
        tv_thoiluong.setText(p.getThoiluong());
        tv_dienvien.setText(p.getDienvien());
        tv_quocgia.setText(p.getQuocGia());
        tv_theloai.setText(p.getTheLoai());
        tv_sotap.setText(p.getSotap());
        tv_mota.setText(p.getMoTa());
    }

}
