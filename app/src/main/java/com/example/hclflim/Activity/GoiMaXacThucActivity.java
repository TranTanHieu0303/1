package com.example.hclflim.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hclflim.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class GoiMaXacThucActivity extends AppCompatActivity {
    String sdt ;
    FirebaseAuth firebaseAuth;
    EditText editText;
    Button btn_sigin,btn_goilaima;
    private String verificationId;
    TextView tv_thb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goi_ma_xac_thuc);
        firebaseAuth = FirebaseAuth.getInstance();
        sdt = getIntent().getStringExtra("sdt");
        editText = findViewById(R.id.edt_meotp);
        sendVerificationCode(sdt);
        btn_sigin = findViewById(R.id.btn_goima);
        tv_thb = findViewById(R.id.tv_thongbao);
        btn_sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editText.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editText.setError("Enter code...");
                    editText.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });
        btn_goilaima = findViewById(R.id.btn_goilaima);
        btn_goilaima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode(sdt);
                new CountDownTimer(60000,1000){
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tv_thb.setText("Gởi lại mã sau"+millisUntilFinished/1000+"giây");
                    }
                    @Override
                    public void onFinish() {
                        tv_thb.setVisibility(View.INVISIBLE);
                    }
                };
            }
        });
        new CountDownTimer(60000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                tv_thb.setText("Gởi lại mã sau"+millisUntilFinished/1000+"giây");
            }

            @Override
            public void onFinish() {
                tv_thb.setVisibility(View.INVISIBLE);
            }
        };
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Intent intent = new Intent(GoiMaXacThucActivity.this, RegisterActivity.class);
                    intent.putExtra("sdt",sdt);
                    startActivity(intent);

                } else {
                    Toast.makeText(GoiMaXacThucActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendVerificationCode(String sdt) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(sdt)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull @NotNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                editText.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull @NotNull FirebaseException e) {
            Toast.makeText(GoiMaXacThucActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }
    };
}