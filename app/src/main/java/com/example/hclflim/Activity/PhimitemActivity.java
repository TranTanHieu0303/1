package com.example.hclflim.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.hclflim.Fragment.PhimLienQuanFragment;
import com.example.hclflim.Fragment.TapphimFragment;
import com.example.hclflim.Fragment.ThongTinPhimFragment;
import com.example.hclflim.Object.PagerAdapter;
import com.example.hclflim.Object.Phim;
import com.example.hclflim.Object.QuocGia;
import com.example.hclflim.Object.TaiKhoan;
import com.example.hclflim.Object.TapPhim;
import com.example.hclflim.Object.TapPhimAdapter;
import com.example.hclflim.Object.TheLoai;
import com.example.hclflim.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
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

public class PhimitemActivity  extends AppCompatActivity
        implements YouTubePlayer.OnInitializedListener  {
    TabLayout tabLayout;
    ViewPager viewPager;
    public Phim p;
    ArrayList<TapPhim> tapPhims = new ArrayList<>();
    ArrayList<Fragment> tabdata = new ArrayList<>();
    TapPhim tapPhim;
    String[] tablist = {"Thông Tin","Phim Liên Quan"};
    String[] tablist2 = {"Thông Tin","Tập Phim","Phim Liên Quan"};
    YouTubePlayerView videoView;
    ActionBar actionBar;
    Toolbar toolbar;
    TaiKhoan taiKhoan;
    int REQUSET = 123;
    String KEY_APIYOUTUBE = "AIzaSyBLVlE0lsuW15FkerCxXwrD551Jq5B4bZs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phimitem);
        p = (Phim) getIntent().getSerializableExtra("itemphim");
        tapPhim = (TapPhim)getIntent().getSerializableExtra("tap");
        taiKhoan = (TaiKhoan) getIntent().getSerializableExtra("TaiKhoan");
        toolbar = findViewById(R.id.toll_1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        YouTubePlayerSupportFragment frag = (YouTubePlayerSupportFragment)getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
        frag.initialize(KEY_APIYOUTUBE, this);
        tabLayout = findViewById(R.id.tab_phim);
        viewPager = findViewById(R.id.vp_phim);
        loaddulieu();
       /* tabdata.add(new ThongTinPhimFragment(p));
        tabdata.add(new PhimLienQuanFragment(p.getTheLoai()));
        FragmentManager fragmentManager = getSupportFragmentManager();
        PagerAdapter pagerAdapter = new PagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.MakeTab(tabdata,tablist);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);*/
        //videoView = findViewById(R.id.vd_itemphim);

        //videoView.initialize(KEY_APIYOUTUBE,this);*/

        }

    private void loaddulieu() {
        Query dataTapphim = FirebaseDatabase.getInstance().getReference().child("Phim").child(p.getMaPhim()).child("TapPhim");
        dataTapphim.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds :snapshot.getChildren()
                     ) {
                    TapPhim tapPhim = ds.getValue(TapPhim.class);
                    if(tapPhim!=null)
                        tapPhims.add(tapPhim);
                }
                if(tapPhims.size()>0)
                {
                    if(tapPhim!=null)
                        tabdata.add(new ThongTinPhimFragment(p,tapPhim.getTenTap()));
                    else
                        tabdata.add(new ThongTinPhimFragment(p));
                    tabdata.add(new TapphimFragment(p,tapPhims));
                    tabdata.add(new PhimLienQuanFragment(p.getTheLoai(),taiKhoan));
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    PagerAdapter pagerAdapter = new PagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
                    pagerAdapter.MakeTab(tabdata,tablist2);
                    tabLayout.setupWithViewPager(viewPager);
                    viewPager.setAdapter(pagerAdapter);
                }
                else
                {
                    tabdata.add(new ThongTinPhimFragment(p));
                    tabdata.add(new PhimLienQuanFragment(p.getTheLoai(),taiKhoan));
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    PagerAdapter pagerAdapter = new PagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
                    pagerAdapter.MakeTab(tabdata,tablist);
                    tabLayout.setupWithViewPager(viewPager);
                    viewPager.setAdapter(pagerAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
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
            case R.id.love:
            {
                FirebaseDatabase.getInstance().getReference().child("TaiKhoan").child(taiKhoan.getSDT()).child("YeuThich").child(p.getMaPhim()).setValue(p);
            }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

/*
    private void loadTheLoai() {
        DatabaseReference dataTheLoai = FirebaseDatabase.getInstance().getReference().child("TheLoai");
        dataTheLoai.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot item:snapshot.getChildren()
                ) {
                    TheLoai thLoai = item.getValue(TheLoai.class);
                    theLoai.add(thLoai);
                }
                loadtab();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    private void loadQuocGia() {
        DatabaseReference dataQuocGia = FirebaseDatabase.getInstance().getReference().child("QuocGia");
        dataQuocGia.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot item: snapshot.getChildren()
                ) {
                    QuocGia qGia =item.getValue(QuocGia.class);
                    quocGia.add(qGia);
                }
                loadtab();
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }*

 */



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b)
            if(tapPhim==null)
                 youTubePlayer.cueVideo(p.getLinkPhim());
            else
                youTubePlayer.cueVideo(tapPhim.getLinkTap());

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(PhimitemActivity.this,REQUSET);
        }
        else
            Toast.makeText(this,"Lỗi rồi bạn ơi",Toast.LENGTH_SHORT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUSET)
        {
            videoView.initialize(KEY_APIYOUTUBE,PhimitemActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /*
    public   class  PhimitemActivity2 extends AppCompatActivity{
    TabLayout tabLayout;
    ViewPager viewPager;
    public Phim p;
    ArrayList<Fragment> tabdata = new ArrayList<>();
    String[] tablist = {"Thông Tin","Phim Liên Quan"};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phimitem);
        p = (Phim) getIntent().getSerializableExtra("itemphim");
        tabLayout = findViewById(R.id.tab_CTPhim);
        viewPager = findViewById(R.id.vp_CTphim);
        tabdata.add(new ThongTinPhimFragment(p));
        tabdata.add(new ThongTinPhimFragment(p));
        FragmentManager fragmentManager = getSupportFragmentManager();
        PagerAdapter pagerAdapter = new PagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.MakeTab(tabdata,tablist);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(pagerAdapter);
    }*/
//}
}
