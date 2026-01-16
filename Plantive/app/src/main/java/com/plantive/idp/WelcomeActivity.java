package com.plantive.idp;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);

        MaterialButton btnSignIn = findViewById(R.id.btnSignIn);
        MaterialButton btnSignUp = findViewById(R.id.btnSignUp);

        btnSignIn.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        });

        btnSignUp.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        });
    }
}
