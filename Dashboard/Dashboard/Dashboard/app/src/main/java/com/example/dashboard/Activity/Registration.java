package com.example.dashboard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dashboard.Activity.Login;
import com.example.dashboard.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    EditText edtNameSignUp, edtEmailSignUp, edtPasswordSignUp, edtPasswordSignUpCF;
    Button btnSignUp;
    FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupuser);
        initView();
        addEvent();
    }

    private void initView() {
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        edtNameSignUp = findViewById(R.id.edtNameSignUp);
        edtEmailSignUp = findViewById(R.id.edtEmailSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtPasswordSignUpCF = findViewById(R.id.edtPasswordSignUpCF);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    private void addEvent() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtNameSignUp.getText().toString().trim();
                String email = edtEmailSignUp.getText().toString().trim();
                String passWord = edtPasswordSignUp.getText().toString().trim();
                String passWordCF = edtPasswordSignUpCF.getText().toString().trim();
                if (name.length() <= 0 || email.length() <= 0 || passWord.length() <= 0 || passWordCF.length() <= 0) {
                    Toast.makeText(Registration.this, "Vui Lòng Nhập Đủ Thông Tin!", Toast.LENGTH_SHORT).show();
                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(Registration.this, "Vui lòng nhập đúng định dạng email", Toast.LENGTH_SHORT).show();
                    }
                    if (passWord.length() < 7 && passWordCF.length() < 7) {
                        Toast.makeText(Registration.this, "Mật Khẩu có ít nhất 7 ký tự", Toast.LENGTH_SHORT).show();
                    } else if (!passWord.equals(passWordCF)) {
                        Toast.makeText(Registration.this, "Mật Khẩu chưa trùng nhau", Toast.LENGTH_SHORT).show();
                    } else {
                        // kiểm tra xem email đã được dùng hay chưa
                        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                if (task.isSuccessful()) {
                                    SignInMethodQueryResult result = task.getResult();
                                    if (result.getSignInMethods().isEmpty()) {
                                        // Email chưa được sử dụng, tiến hành đăng ký
                                        auth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (!task.isSuccessful()) {
                                                    Toast.makeText(Registration.this, "Tạo Tài Khoản Thất Bại", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    // Đăng ký thành công, thêm thông tin vào Firestore
                                                    String uid = auth.getCurrentUser().getUid(); // Lấy UID của người dùng
                                                    addUserToFirestore(uid, email, name, passWord);
                                                    Toast.makeText(Registration.this, "Đăng Ký Thành Công!", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(Registration.this, Login.class));
                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(Registration.this, "Email đã được sử dụng. Vui lòng chọn email khác.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    private void addUserToFirestore(String uid, String email, String name, String password) {
        // Tạo một Map để đại diện cho dữ liệu người dùng
        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        user.put("name", name);
        user.put("password", password);
        // Thêm dữ liệu vào Firestore
        firestore.collection("Users").document(uid).set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Dữ liệu đã được thêm thành công vào Firestore
                        } else {
                            // Đã xảy ra lỗi khi thêm dữ liệu
                            Toast.makeText(Registration.this, "Lỗi khi thêm dữ liệu vào Firestore", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
