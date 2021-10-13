package com.example.hclflim.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hclflim.Object.TaiKhoan;
import com.example.hclflim.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText mFullName, mPassword, mPhone,mPassword2;
    Button mBtnRegister;
    TextView mBtnLogin;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;
    Toolbar toolbar;
    String sdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sdt = getIntent().getStringExtra("sdt");
        toolbar = findViewById(R.id.tool_1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFullName=findViewById(R.id.tvfullname);
        mPassword=findViewById(R.id.tvpassword);
        mPhone=findViewById(R.id.tvphone);
        mPhone.setText(sdt);
        mPhone.setEnabled(false);
        mBtnRegister=findViewById(R.id.btnRegister);
        mBtnLogin=findViewById(R.id.tvcreateText);
        mPassword2 = findViewById(R.id.tvpassword2);
        fStore=FirebaseFirestore.getInstance();
        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password=mPassword.getText().toString().trim();
                final String fullname =mFullName.getText().toString();
                final String phone = mPhone.getText().toString();
                String xtPass = mPassword2.getText().toString();
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password không được để trống");
                    return;
                }
                if(TextUtils.isEmpty(fullname)){
                    mFullName.setError("Password không được để trống");
                    return;
                }

                if(password.length()<8){
                    mPassword.setError("Mật khẩu phải trên 8 kí tự!");
                    return;
                }
                if(password.length()>15){
                    mPassword.setError("Mật khẩu thấp hơn 15 kí tự!");
                    return;
                }
                if(!password.equals(xtPass)) {
                    mPassword2.setError("Cần trùng với ô mật khẩu");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //Tạo tài khoản người dùng với sdt đã đăng kí.
                try {
                    TaiKhoan taiKhoan = new TaiKhoan(fullname,phone,password);
                    FirebaseDatabase.getInstance().getReference().child("TaiKhoan").child(taiKhoan.getSDT()).setValue(taiKhoan);
                    AlertDialog.Builder b = new AlertDialog.Builder(RegisterActivity.this);
                    b.setTitle("HCL film thông báo");
                    b.setMessage("Đăng ký tài khoản thành công\nĐăng Nhập Ngay");
                    b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            intent.putExtra("taiKhoan",taiKhoan);
                            startActivity(intent);
                            finish();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();

                }
                catch (Exception ex ){
                    AlertDialog.Builder b = new AlertDialog.Builder(RegisterActivity.this);
                    b.setTitle("HCL film");
                    b.setMessage(ex.getMessage());
                    b.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog al = b.create();
                    al.show();
                }


                //đăng kí trên firebase
                /*fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Tạo tài khoảng thành công", Toast.LENGTH_SHORT).show();
                            userID=fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference=fStore.collection("user").document(userID);
                            Map<String , Object> user=new HashMap<>();
                            user.put("fName",fullname);
                            user.put("email",email);
                            user.put("phone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(" TAG","onSuccess user Profile created for"+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull  Exception e) {
                                    Log.d("TAG","onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Tạo tài khoảng thất bại!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                        return;
                    }
                });*/
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });


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