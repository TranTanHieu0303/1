 package com.example.hclflim.Fragment;

import android.app.DownloadManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import androidx.viewpager.widget.ViewPager;

import com.example.hclflim.Object.DanhMuc;
import com.example.hclflim.Object.DanhmucAdapter;
import com.example.hclflim.Object.Phim;
import com.example.hclflim.Object.PhimAdapter;
import com.example.hclflim.Object.QuangCaoAdapter;
import com.example.hclflim.Object.TaiKhoan;
import com.example.hclflim.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

 public class HomeFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
    DanhmucAdapter danhmucAdapter;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    QuangCaoAdapter caoAdapter;
    Timer timer;
    ArrayList<Phim> listPhims = new ArrayList<>();
    TaiKhoan taiKhoan;
    public HomeFragment(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.home_fragment,null);
        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView  = v.findViewById(R.id.rv_danhsachphim);
        viewPager = v.findViewById(R.id.vp_quangcao);
        circleIndicator = v.findViewById(R.id.ci_quangcao);
        loadquangcao();
        loadPhim();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(danhmucAdapter);
        viewPager.setAdapter(caoAdapter);

/*phimAdapter = new PhimAdapter(getContext(),dulieu);
recyclerView.setAdapter(phimAdapter);*/
        return v;
    }

     private void loadquangcao() {
         Query Phimdata = FirebaseDatabase.getInstance().getReference().child("Phim").limitToLast(5);
         Phimdata.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                 for (DataSnapshot ds:snapshot.getChildren()) {
                     Phim p = ds.getValue(Phim.class);
                     if(listPhims.size()<=5)
                     listPhims.add(p);
                 }
                 caoAdapter = new QuangCaoAdapter(getContext(),listPhims);
                 viewPager.setAdapter(caoAdapter);
                 circleIndicator.setViewPager(viewPager);
                 caoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
                 autoslider();
             }

             @Override
             public void onCancelled(@NonNull @NotNull DatabaseError error) {

             }
         });

     }

     private void autoslider() {
        if(listPhims == null|| listPhims.isEmpty()|| viewPager == null)
            return;
        if(timer ==null)
            timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
               int item =  viewPager.getCurrentItem();
               int totalitem  = listPhims.size()-1;
               if(item<totalitem) {
                   item++;
                   viewPager.setCurrentItem(item);
               }else
               {
                   viewPager.setCurrentItem(0);
               }
                }
            });
            }
        },500,2000);
     }

     @Override
     public void onDestroy() {
         super.onDestroy();
         if(timer!=null) {
             timer.cancel();
             timer =null;
         }
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
                            danhmucAdapter = new DanhmucAdapter(getContext(),listdanhmuc,taiKhoan);
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
