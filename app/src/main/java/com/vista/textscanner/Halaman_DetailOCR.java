package com.vista.textscanner;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vista.textscanner.databinding.ActivityOcrdetailBinding;
import com.vista.textscanner.model.Item;
import com.vista.textscanner.ocr.ocrActivity_Handler;
import com.vista.textscanner.view.ocrView;

import java.util.List;

public class Halaman_DetailOCR extends AppCompatActivity implements ocrView.ocrDetail{
    ActivityOcrdetailBinding binding;
    ocrActivity_Handler ocrhandler;
    List<Item> item;
    int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOcrdetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ocrhandler = new ocrActivity_Handler(this, this, this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            item = (List<Item>) extras.getSerializable("item");
            index = (int) extras.get("index");
            binding.areaKonten.setText(item.get(index).getContent());
            binding.detailKategori.setText(item.get(index).getType());
            ocrhandler.dateParser(item.get(index).getDatetime());
            ocrhandler.getColorCategory(item.get(index).getType());
            Log.d("getdata", "path =" + item.get(index).getImagepath());
        }

        binding.btnSalin.setOnClickListener(v -> {
            ocrhandler.CopyToClipboard(item.get(index).getContent());
        });

        binding.lihatGambar.setOnClickListener(v -> {
            ocrhandler.ShowImage(item.get(index).getImagepath());
        });

    }

    @Override
    public void onDateResult(String date) {
        binding.detailTanggal.setText(date);
    }

    @Override
    public void onGetColorResult(int color) {
        binding.detailKategori.setTextColor(color);
    }

    @Override
    public void onCopyResult(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
