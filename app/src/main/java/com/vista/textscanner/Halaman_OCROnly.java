package com.vista.textscanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.vista.textscanner.api.getResponse;
import com.vista.textscanner.databinding.ActivityOcronlyBinding;
import com.vista.textscanner.imageprocessing.ImageHelper;
import com.vista.textscanner.model.Database_Helper;
import com.vista.textscanner.model.newocrapi.Response;
import com.vista.textscanner.ocr.Custom_Dialog;
import com.vista.textscanner.permission.PermissionTask;
import com.vista.textscanner.presenter.ApiPresenter;
import com.vista.textscanner.presenter.ImagePresenter;
import com.vista.textscanner.presenter.OcrPresenter;
import com.vista.textscanner.presenter.PermissionPresenter;
import com.vista.textscanner.view.MainView;
import com.vista.textscanner.view.ocrView;

public class Halaman_OCROnly extends AppCompatActivity implements MainView.ImageProcessing , MainView.Permission, MainView.NewOCRApi, MainView.ocrResult{

    private PermissionPresenter permissionPresenter;
    private ImagePresenter imagePresenter;
    private ApiPresenter.NewOCRRequestPresenter apiPresenter;
    private Bitmap image;
    private OcrPresenter.ocrDialog customDialog;
    private ocrView ocrView;
    private String path;
    private Uri imageUri;
    private Database_Helper data = new Database_Helper(this);
    ActivityOcronlyBinding binding;
    public final int camera_Code = 400;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inisialisasi layout
        binding = ActivityOcronlyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //inisialisasi presenter
        imagePresenter = new ImageHelper(this, this);
        permissionPresenter = new PermissionTask(this,this);
        apiPresenter = new getResponse(this, this, data);

        //inisialisasi animasi loading
        customDialog = new Custom_Dialog(this, this, data);

        //inisialisasi aksi button
        binding.ButtonKamera.setOnClickListener(v -> {
            imagePresenter.openCamera(camera_Code);
        });

        binding.ButtonGallery.setOnClickListener(v -> {
            permissionPresenter.checkPermission(101);
        });

        binding.ButtonReset.setOnClickListener(v -> {
            binding.ImagePreview.setImageResource(android.R.color.transparent);
            binding.ButtonOcr.setImageResource(android.R.drawable.ic_search_category_default);
            binding.ButtonOcr.setEnabled(false);
        });

        binding.ButtonOcr.setOnClickListener(v -> {
            BitmapDrawable drawable = (BitmapDrawable) binding.ImagePreview.getDrawable();
            image = drawable.getBitmap();
            if (image != null) {
                customDialog.showLoading();
                apiPresenter.doOCR(image);
            } else {
                Snackbar.make(binding.layoutOcr, "Error, harap pilih gambar kembali", Snackbar.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.i("permission", "requestCode = " + requestCode);
        if(requestCode==101){
            Snackbar.make(binding.layoutOcr, "Permission Granted", Snackbar.LENGTH_LONG).show();
            imagePresenter.openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200 && data!=null){
            //crop image
            imagePresenter.getPathFromUri(data.getData());
            imagePresenter.doCrop(data.getData());
        }

        //hasil kamera
        if (requestCode == camera_Code) {
           if(resultCode==RESULT_OK){
               imagePresenter.doCrop(imageUri);
           }
        }

        //Hasil Crop
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                binding.ImagePreview.setImageURI(result.getUri());
                binding.ButtonOcr.setImageResource(android.R.drawable.ic_menu_search);
                binding.ButtonOcr.setEnabled(true);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    public void onPathResult(String string) {
        path = string;
    }

    @Override
    public void onImageResult(Uri uri) {
        imageUri = uri;
    }

    @Override
    public void onPermissionGranted(int requestCode) {
        if(requestCode==101){
            Snackbar.make(binding.layoutOcr, "Storage Permission Already Granted", Snackbar.LENGTH_LONG).show();
            imagePresenter.openGallery();
        }
    }

    @Override
    public void onPermissionDenied() {

    }

    @Override
    public void CheckPermissionResult(boolean result, int requestCode) {
        if(result){
            if(requestCode==101){
                imagePresenter.openGallery();
            }
        } else {
            permissionPresenter.requestPermission(requestCode);
        }
    }

    @Override
    public void onOCRResult(Response result) {
        customDialog.hideDialog();
        customDialog.showResultOCR(result.getParsedResults().get(0).getParsedText(), path);
    }

    @Override
    public void onSaveResult(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
