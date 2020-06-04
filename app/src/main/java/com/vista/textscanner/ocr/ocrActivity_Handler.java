package com.vista.textscanner.ocr;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.vista.textscanner.R;
import com.vista.textscanner.view.ocrView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ocrActivity_Handler {
    private ocrView.ocrDetail ocrDetail;
    private Context context;
    private Activity activity;
    private Dialog dialog;

    public ocrActivity_Handler(ocrView.ocrDetail ocrDetail, Context context, Activity activity) {
        this.ocrDetail = ocrDetail;
        this.context = context;
        this.activity = activity;
    }


    public void getColorCategory(String text){
        switch(text){
            case "Tidak Dikategori":
                ocrDetail.onGetColorResult(ContextCompat.getColor(context, R.color.Kategori1));
                break;
            case "Pekerjaan":
                ocrDetail.onGetColorResult(ContextCompat.getColor(context, R.color.Kategori2));
                break;
            case "Rumah":
                ocrDetail.onGetColorResult(ContextCompat.getColor(context, R.color.Kategori3));
                break;
            case "Sekolah":
                ocrDetail.onGetColorResult(ContextCompat.getColor(context, R.color.Kategori4));
                break;
            case "oke":
                ocrDetail.onGetColorResult(ContextCompat.getColor(context, R.color.colorAccent));
                break;
        }
    }

    public void dateParser(String dateString) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss" /* 10-Sep-2013 09:53:37*/);
            Date d = sd.parse(dateString);
            sd = new SimpleDateFormat("dd-MM-yyyy");
            ocrDetail.onDateResult(sd.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void CopyToClipboard(String text){
        ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData copiedtext = ClipData.newPlainText("OCRresult",text);
        clipboard.setPrimaryClip(copiedtext);
        ocrDetail.onCopyResult("Teks Dicopy");
    }

    public void ShowImage(String path){
        dialog = new Dialog(activity, R.style.CustomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.view_image);

        ImageView image = dialog.findViewById(R.id.image);
        Glide.with(activity)
                .load(path)
                .placeholder(R.drawable.error).into(image);
        dialog.show();
    }


}
