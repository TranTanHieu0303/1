package com.example.hclflim.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.hclflim.Object.Phim;
import com.example.hclflim.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class XemPhimActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    Phim phim ;
    int REQUSET = 123;
    String KEY_APIYOUTUBE = "AIzaSyBLVlE0lsuW15FkerCxXwrD551Jq5B4bZs";
    YouTubePlayerView youTubePlayer;
    Fragment xemPhimPhuFragment;
    Toolbar toolbar;
    VideoView videoView;
    ActionBar actionBar;
    /*TabLayout tabLayout;
    ArrayList<Fragment> tabdata;
    String[] tablist= {"Thông Tin","Phim Liên Quan"};
    ViewPager viewPager;*/
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_xem_phim);
        phim = (Phim)getIntent().getSerializableExtra("itemphim");
        youTubePlayer = findViewById(R.id.vd_itemphim);
        youTubePlayer.initialize(KEY_APIYOUTUBE,this);
        videoView = findViewById(R.id.videoview);
        String  strurl = "https://firebasestorage.googleapis.com/v0/b/do-an-ltdt.appspot.com/o/video%2FReview%20X%C3%A0m%20%237-%20Ng%C6%B0%E1%BB%9Di%20Ph%C3%A1n%20X%E1%BB%AD.mp4?alt=media&token=03cfa19a-f57e-438e-884b-566b26405e11";
        Uri uri = Uri.parse(strurl);
        videoView.setVideoURI(uri);
        MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(videoView);
        videoView.setMediaController(vidControl);
        videoView.start();
       /* FragmentTransaction fragmentTransaction =  getFragmentManager().beginTransaction();
        xemPhimPhuFragment = new XemPhimPhuFragment(phim);
        fragmentTransaction.replace(R.id.frg_home,xemPhimPhuFragment);
        fragmentTransaction.commit();
        tabLayout = findViewById(R.id.tab_CTPhim);
        viewPager = findViewById(R.id.vp_CTphim);
        tabdata.add(new ThongTinPhimFragment(phim));
        tabdata.add(new ThongTinPhimFragment(phim));*/
        //YouTubePlayerSupportFragment fragmentManager = (YouTubePlayerSupportFragment)getSupportFragmentManager().findFragmentById(R.id.vd_itemphim);
        //fragmentManager.initialize(KEY_APIYOUTUBE,this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b)
            youTubePlayer.cueVideo(phim.getLinkPhim());
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(XemPhimActivity.this,REQUSET);
        }
        else
            Toast.makeText(this,"Lỗi rồi bạn ơi",Toast.LENGTH_SHORT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUSET)
        {
            youTubePlayer.initialize(KEY_APIYOUTUBE,XemPhimActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}