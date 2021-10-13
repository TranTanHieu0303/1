package com.example.hclflim.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hclflim.Activity.ThongTinTKActivity;
import com.example.hclflim.Object.Phim;
import com.example.hclflim.Object.TaiKhoan;
import com.example.hclflim.R;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class AccountFragment extends Fragment {
    FirebaseAuth firebaseAuth;
    TaiKhoan taiKhoan;
    TextView tv_tentk;
    LinearLayout lay_taikhoan;
    public AccountFragment(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_fragment,container,false);
        tv_tentk = view.findViewById(R.id.tv_tenTK);
        tv_tentk.setText(taiKhoan.getTenKT());
        lay_taikhoan = view.findViewById(R.id.lay_ttTaiKhoan);
        lay_taikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThongTinTKActivity.class);
                intent.putExtra("TaiKhoan",taiKhoan);
                startActivity(intent);
            }
        });
        return  view;
    }
}
