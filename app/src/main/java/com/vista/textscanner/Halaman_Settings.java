package com.vista.textscanner;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vista.textscanner.databinding.ActivitySettingsBinding;
import com.vista.textscanner.model.Database_Helper;
import com.vista.textscanner.view.SettingsView;

public class Halaman_Settings extends AppCompatActivity implements SettingsView {

    private Database_Helper db = new Database_Helper(this,this);
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db.getApiKey();

        binding.btnSettingApply.setOnClickListener(v -> {
            String newapi = binding.apitext.getText().toString();
            if(newapi.length()>0){
                db.updateApiKey(newapi);
            } else {
                Toast.makeText(this, "Apikey tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnSettingReset.setOnClickListener(v -> {
            binding.apitext.setText("cf00b84bd688957");
        });
    }

    @Override
    public void onGetApiResult(String apikey) {
        binding.apitext.setText(apikey);
    }

    @Override
    public void onUpdateApiResult(String result, String newapikey) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        binding.apitext.setText(newapikey);
    }
}
