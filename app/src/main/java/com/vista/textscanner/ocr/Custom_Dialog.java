package com.vista.textscanner.ocr;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.vista.textscanner.R;
import com.vista.textscanner.model.Database_Helper;
import com.vista.textscanner.model.Item;
import com.vista.textscanner.presenter.OcrPresenter;
import com.vista.textscanner.view.MainView;
import com.vista.textscanner.view.ocrView;

import java.util.List;
import java.util.Objects;

public class Custom_Dialog implements OcrPresenter.ocrDialog, OcrPresenter.ocrList {
    private Activity activity;
    private Dialog dialog;
    private MainView.ocrResult ocrResult;
    private Database_Helper data;
    private ocrView.ocrList ocrList;

    public Custom_Dialog(Activity activity, MainView.ocrResult ocrResult, Database_Helper data) {
        this.activity = activity;
        this.ocrResult = ocrResult;
        this.data = data;
    }

    public Custom_Dialog(Activity activity, ocrView.ocrList ocrList, Database_Helper data){
        this.activity = activity;
        this.ocrList = ocrList;
        this.data = data;
    }

    @Override
    public void showLoading(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.loading_layout);

        ImageView image = dialog.findViewById(R.id.loading_gif);

        Glide.with(activity)
                .asGif()
                .load(R.drawable.loading)
                .into(image);

        dialog.show();
    }

    @Override
    public void showResultOCR(String OCRText, String path){
        dialog = new Dialog(activity, R.style.CustomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_ocrresult);

        EditText result = dialog.findViewById(R.id.result_field);
        result.setText(OCRText);

        ImageButton close = dialog.findViewById(R.id.btn_close);
        ImageButton copy = dialog.findViewById(R.id.btn_copy);
        ImageButton save = dialog.findViewById(R.id.btn_save);
        Spinner kategori = dialog.findViewById(R.id.kategori);

        close.setOnClickListener(v -> {
            dialog.dismiss();
        });

        copy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData copiedtext = ClipData.newPlainText("OCRresult",result.getText().toString());
            clipboard.setPrimaryClip(copiedtext);
            Toast.makeText(activity, "Teks Dicopy", Toast.LENGTH_SHORT).show();
        });

        save.setOnClickListener(v->{
            String PKategori = kategori.getSelectedItem().toString();
            String hasilOcr = result.getText().toString();
            if(data.insertData(PKategori, hasilOcr, path)){
                ocrResult.onSaveResult("Save Selesai");
            } else {
                ocrResult.onSaveResult("Save Gagal");
            }
        });

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    @Override
    public void hideDialog(){
        dialog.dismiss();
    }

    @Override
    public void showAlert(List<Item> dataori, List<Item> selected_Item){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("Hapus Data");
        alertDialog.setMessage("Yakin akan Menghapus?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", (dialog, which) -> {
            //hapus data
            if(selected_Item.size()>0){
                for(int i = 0; i<selected_Item.size(); i++){
                    dataori.remove(selected_Item.get(i));
                    data.deleteData(selected_Item);
                    ocrList.onDeleteResult();
                }
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL", (dialog, which) -> {
            //cancel
            dialog.dismiss();
        });
        alertDialog.show();
    }
}
