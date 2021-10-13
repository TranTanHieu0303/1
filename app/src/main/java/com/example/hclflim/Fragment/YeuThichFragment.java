package com.example.hclflim.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hclflim.Object.DaXemAdapter;
import com.example.hclflim.Object.Phim;
import com.example.hclflim.Object.TaiKhoan;
import com.example.hclflim.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class YeuThichFragment extends Fragment {
    RecyclerView recyclerView;
    TaiKhoan taiKhoan;
    ArrayList<Phim> listPhims = new ArrayList<>();
    DaXemAdapter daXemAdapter;

    public YeuThichFragment(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.da_xem_fragment,container,false);
        recyclerView = view.findViewById(R.id.rcv_daxem);
        loaddulieu();
        return view;
    }

    private void loaddulieu() {
        Query Phimdata = FirebaseDatabase.getInstance().getReference().child("TaiKhoan").child(taiKhoan.getSDT()).child("YeuThich");
        Phimdata.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                Phim phim = snapshot.getValue(Phim.class);
                if(phim!=null)
                    listPhims.add(phim);
                daXemAdapter = new DaXemAdapter(getContext(),taiKhoan,listPhims);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(daXemAdapter);
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

    }
}
