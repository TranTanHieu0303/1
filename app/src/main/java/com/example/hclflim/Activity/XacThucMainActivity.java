package com.example.hclflim.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hclflim.Object.TaiKhoan;
import com.example.hclflim.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import org.jetbrains.annotations.NotNull;

public class XacThucMainActivity extends AppCompatActivity {
    CountryCodePicker ccp;
    EditText edt_sdt;
    Button btn_goima;
    String sdt;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_thuc_main);
        ccp = findViewById(R.id.ctp_manuoc);
        edt_sdt = findViewById(R.id.edt_sdt);
        btn_goima = findViewById(R.id.btn_goima);
        btn_goima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sdt = edt_sdt.getText().toString().trim();
                sdt = ccp.getSelectedCountryCodeWithPlus()+sdt;
                if(sdt.isEmpty() || sdt.length() < 10){
                    edt_sdt.setError("Enter a valid mobile");
                    edt_sdt.requestFocus();
                    return;
                }
                isOnline();
            }
        });
    }
    private void isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni =cm.getActiveNetworkInfo();
        if(ni!=null&& ni.isConnected()) {
            Query datataikhoan = FirebaseDatabase.getInstance().getReference().child("TaiKhoan").child(sdt);
            datataikhoan.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if(snapshot.getValue()!=null)
                    {
                        AlertDialog.Builder b = new AlertDialog.Builder(XacThucMainActivity.this);
                        b.setTitle("HCL film thông báo");
                        b.setMessage("Số điện thoại này đã có tài khoản, vui lòng sử dụng số khác");
                        b.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog al = b.create();
                        al.show();
                    }
                    else {
                        Intent intent = new Intent(XacThucMainActivity.this, GoiMaXacThucActivity.class);
                        intent.putExtra("sdt", sdt);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });


        }
        else {
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("HCL film thông báo");
            b.setMessage("Bạn không có kết nối mạng, vui lòng kiểm tra lại");
            b.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog al = b.create();
            al.show();
        }
    }
}