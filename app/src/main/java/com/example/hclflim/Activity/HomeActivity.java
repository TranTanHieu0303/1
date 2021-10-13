package com.example.hclflim.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.hclflim.Fragment.AccountFragment;
import com.example.hclflim.Fragment.DaXemFragment;
import com.example.hclflim.Fragment.DashBoardFragment;
import com.example.hclflim.Fragment.HomeFragment;
import com.example.hclflim.Fragment.YeuThichFragment;
import com.example.hclflim.Object.TaiKhoan;
import com.example.hclflim.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    Fragment fragmentchon;
    BottomNavigationView bottomNavigationView;
    TaiKhoan taiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        taiKhoan = (TaiKhoan)getIntent().getSerializableExtra("Taik");
        frameLayout = findViewById(R.id.frg_home);
        bottomNavigationView = findViewById(R.id.bn_menuchin);
        fragmentchon = new HomeFragment(taiKhoan);
        loadfragment(fragmentchon);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id)
                {
                    case R.id.home:
                        fragmentchon = new HomeFragment(taiKhoan);
                        break;
                    case R.id.dashboard:
                        fragmentchon = new DaXemFragment(taiKhoan);
                        break;
                    case R.id.notification:
                        fragmentchon = new YeuThichFragment(taiKhoan);
                        break;
                    case R.id.account:
                        fragmentchon = new AccountFragment(taiKhoan);
                        break;
                }
                loadfragment(fragmentchon);
                return true;
            }
        });

    }

    private void loadfragment(Fragment frg) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frg_home, frg);
        transaction.commit();
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }*/
}