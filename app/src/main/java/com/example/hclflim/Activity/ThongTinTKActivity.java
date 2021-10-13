package com.example.hclflim.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hclflim.Object.TaiKhoan;
import com.example.hclflim.R;

public class ThongTinTKActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tv_tentk,tv_sdt;
    TaiKhoan taiKhoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_tkactivity);
        taiKhoan = (TaiKhoan) getIntent().getSerializableExtra("TaiKhoan");
        toolbar = findViewById(R.id.tollbar_ttTaiKhoan);
        tv_sdt = findViewById(R.id.tv_taikhoandn);
        tv_tentk = findViewById(R.id.tv_tenht);
        tv_tentk.setText(taiKhoan.getTenKT());
        tv_sdt.setText(taiKhoan.getSDT());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}