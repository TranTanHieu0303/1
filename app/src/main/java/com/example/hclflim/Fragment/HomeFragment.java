 package com.example.hclflim.Fragment;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hclflim.Object.DanhMuc;
import com.example.hclflim.Object.DanhmucAdapter;
import com.example.hclflim.Object.Phim;
import com.example.hclflim.Object.PhimAdapter;
import com.example.hclflim.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.core.Query;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
    DanhmucAdapter danhmucAdapter;
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.home_fragment,null);
        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView  = v.findViewById(R.id.rv_danhsachphim);
        loadPhim();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(danhmucAdapter);
/*phimAdapter = new PhimAdapter(getContext(),dulieu);
recyclerView.setAdapter(phimAdapter);*/
        return v;
    }
    private void loadPhim() {
        final ArrayList<DanhMuc> listdanhmuc = new ArrayList<>();
        DatabaseReference datadanhmuc = FirebaseDatabase.getInstance().getReference().child("DanhMuc");
        datadanhmuc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    final DanhMuc danhMuc = new DanhMuc();
                    danhMuc.setMaDM((String) ds.child("MaDM").getValue());
                    danhMuc.setTenDM((String) ds.child("TenDM").getValue());
                    final ArrayList<Phim> dulieu = new ArrayList<>();
                    DatabaseReference allPost = FirebaseDatabase.getInstance().getReference().child("Phim");
                    allPost.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                Phim phim = ds.getValue(Phim.class);
                                String[] dm = phim.getdanhmuc().split(",");
                                for (String a:dm) {
                                    //Toast.makeText(getContext(),a,Toast.LENGTH_SHORT).show();
                                    if(a.equals(danhMuc.getMaDM())) {
                                        if(dulieu.size()<5)
                                        dulieu.add(phim);

                                    }
                                }

                            }
                            danhMuc.setPhims(dulieu);
                            listdanhmuc.add(danhMuc);
                            danhmucAdapter = new DanhmucAdapter(getContext(),listdanhmuc);
                            recyclerView.setAdapter(danhmucAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
