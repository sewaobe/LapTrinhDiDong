package com.example.week10_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.week10_firebase.databinding.ActivitySignupBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EditText editTextFullName = binding.editTextFullName;
        EditText editTextUserName = binding.editTextUserNameSP;
        EditText editTextPassword = binding.editTextPasswordSP;
        Button btnBack = (Button) binding.btnBack;
        Button btnRegister = (Button) binding.btnConfirmSignup;
        FirebaseAuth auth = FirebaseAuth.getInstance();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextUserName.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                Toast.makeText(SignupActivity.this, email, Toast.LENGTH_SHORT).show();

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                           if(task.isSuccessful()) {
                               Toast.makeText(SignupActivity.this, "Bạn đã đăng kí thành công", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                               startActivity(intent);
                           }
                           else {
                               String error = task.getException() != null ? task.getException().getMessage() : "Lỗi không xác định";
                               Toast.makeText(SignupActivity.this, "Đăng ký thất bại: " + error, Toast.LENGTH_LONG).show();                           }
                        });
            }
        });
    }
}