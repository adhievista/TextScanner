package com.vista.textscanner.imageprocessing;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import com.theartofdev.edmodo.cropper.CropImage;
import com.vista.textscanner.presenter.ImagePresenter;
import com.vista.textscanner.view.MainView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ImageHelper implements ImagePresenter {
    private MainView.ImageProcessing mainView;
    private Activity activity;
    private String path = Environment.getExternalStorageDirectory() + "/TextScanner";
    public String imagename;


    public ImageHelper(MainView.ImageProcessing mainView, Activity activity){
        this.mainView = mainView;
        this.activity = activity;
    }

    @Override
    public void openGallery(){
        Intent intent;
        intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), 200);
    }


    @Override
    public void openCamera(int camera_Code){
        makeDir();
        String timeStamp = new SimpleDateFormat("ddMMyyyy_mm_ss").format(new Date());
        File file = new File(path, timeStamp +".jpg");
        Uri uri = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", file);
        mainView.onImageResult(uri);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent, camera_Code);
    }

    @Override
    public void getPathFromUri(Uri uri){
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        // Get the cursor
        Cursor cursor = activity.getContentResolver().query(uri, filePathColumn, null, null, null);
        // Move to first row
        cursor.moveToFirst();
        //Get the column index of MediaStore.Images.Media.DATA
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        //Gets the String value in the column
        String imgDecodableString = cursor.getString(columnIndex);
        mainView.onPathResult(imgDecodableString);
    }

    @Override
    public void doCrop(Uri uri) {
        CropImage.activity(uri)
                .start(activity);
    }

    private void makeDir(){
        //create directory
        File filedir = new File(path);
        if (!filedir.exists()) {
            filedir = new File(path);
            filedir.mkdirs();
        }
    }


}
