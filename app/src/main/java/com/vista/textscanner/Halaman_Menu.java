package com.vista.textscanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vista.textscanner.databinding.ActivityMenuBinding;
import com.vista.textscanner.model.Database_Helper;

public class Halaman_Menu extends AppCompatActivity {
    private Database_Helper db = new Database_Helper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inisiasi layout
        com.vista.textscanner.databinding.ActivityMenuBinding binding = ActivityMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.ButtonSatu.setOnClickListener(v -> {
            Intent intent = new Intent(this, Halaman_OCROnly.class);
            startActivity(intent);
        });

        binding.ButtonDua.setOnClickListener(v -> {
            Intent intent = new Intent(this, Halaman_OCRList.class);
            startActivity(intent);
        });




    }
}
