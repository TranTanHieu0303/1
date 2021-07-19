package com.example.hclflim.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hclflim.Object.DanhMuc;
import com.example.hclflim.Object.DanhmucAdapter;
import com.example.hclflim.Object.Phim;
import com.example.hclflim.Object.PhimAdapter;
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
import java.util.List;

public class DanhMucPhimMainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView view;
    String madm;
    FirebaseAuth firebaseAuth;
    DanhmucAdapter danhmucAdapter;
    PhimAdapter phimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_phim_main);
        madm = getIntent().getStringExtra("MaDM");
        toolbar = findViewById(R.id.toolbar_dm);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        view = findViewById(R.id.rcv_danhmuc);
        loadData();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        view.setLayoutManager(gridLayoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        view.setAdapter(danhmucAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_thongtin,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        final ArrayList<Phim> listphim = new ArrayList<>();
        DatabaseReference datadanhmuc = FirebaseDatabase.getInstance().getReference().child("Phim");
        datadanhmuc.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                Phim phim = snapshot.getValue(Phim.class);
                if(phim.getdanhmuc().equals(madm)) {
                    listphim.add(phim);
                    phimAdapter = new PhimAdapter(DanhMucPhimMainActivity.this,listphim);
                    view.setAdapter(phimAdapter);
                }


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