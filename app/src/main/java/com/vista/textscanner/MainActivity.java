package com.vista.textscanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.vista.textscanner.databinding.ActivityMainBinding;
import com.vista.textscanner.imageprocessing.ImageHelper;
import com.vista.textscanner.model.googlebook.Book;
import com.vista.textscanner.model.googlebook.Items;
import com.vista.textscanner.model.newocrapi.Response;
import com.vista.textscanner.permission.PermissionTask;
import com.vista.textscanner.presenter.ApiPresenter;
import com.vista.textscanner.presenter.ImagePresenter;
import com.vista.textscanner.presenter.PermissionPresenter;
import com.vista.textscanner.api.getResponse;
import com.vista.textscanner.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView.BookApi, MainView.ImageProcessing, MainView.Permission, MainView.NewOCRApi{

    private ActivityMainBinding binding;
    private getResponse getResponse;
    private MainView mainView;
    private PermissionPresenter permissionPresenter;
    private ImagePresenter imagePresenter;
    private ApiPresenter.NewOCRRequestPresenter apiPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getResponse = new getResponse((MainView.BookApi)this);
        imagePresenter = new ImageHelper(this, this);
        permissionPresenter = new PermissionTask(this,this);
        apiPresenter = new getResponse((MainView.NewOCRApi) this, this);

        binding.teks.setText("Adi");
        binding.button.setOnClickListener(v -> {
              permissionPresenter.checkPermission(101);
        });

        binding.Proses.setOnClickListener(v -> {
            BitmapDrawable drawable = (BitmapDrawable) binding.sampul.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            apiPresenter.doOCR(bitmap);
        });


    }

    @Override
    public void onApiRequestResult(Book book) {
        Items[] items = book.getItems();
        binding.teks.setText(items[0].getVolumeInfo().getTitle() + items[0].getVolumeInfo().getImageLinks().getSmallThumbnail());
        String url = items[0].getVolumeInfo().getImageLinks().getThumbnail();
        Picasso.get().load(url.replace("http","https")).into(binding.sampul);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.i("permission", "requestCode = " + requestCode);
        if(requestCode==101){
            Snackbar.make(binding.layoutUtama, "Permission Granted", Snackbar.LENGTH_LONG).show();
            imagePresenter.openGallery();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("onSuccess", "request code = " + requestCode + "result code =" + resultCode);
        if(requestCode==200 && data!=null){
            //crop image
            imagePresenter.doCrop(data.getData());
//            imagePresenter.getPathFromUri(data.getData());
        }

        //Hasil Crop
        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Log.i("onSuccess", "masuk");
                binding.sampul.setImageURI(result.getUri());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


    @Override
    public void onPathResult(String string) {
        binding.teks.setText(string);
    }

    @Override
    public void onImageResult(Uri uri) {

    }




    @Override
    public void onPermissionGranted(int requestCode) {
        if(requestCode==101){
            Snackbar.make(binding.layoutUtama, "Storage Permission Already Granted", Snackbar.LENGTH_LONG).show();
            imagePresenter.openGallery();
        }
    }

    @Override
    public void onPermissionDenied() {
            Snackbar.make(binding.layoutUtama, "Permission Denied by User", Snackbar.LENGTH_LONG).show();
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
        binding.teks.setText(result.getParsedResults().get(0).getParsedText());
    }


}
