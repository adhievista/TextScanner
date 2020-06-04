package com.vista.textscanner.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import com.vista.textscanner.presenter.ApiPresenter;
import com.vista.textscanner.view.MainView;

import java.io.ByteArrayOutputStream;

public class OCRAsyncTask extends AsyncTask<String, String, String> {
//    private MainView.;
    public OCRAsyncTask(ApiPresenter.NewOCRRequestPresenter api) {
//        this.api = api;
    }

    @Override
    protected String doInBackground(String... path) {
        Bitmap gambar = BitmapFactory.decodeFile(path[0]);
        ByteArrayOutputStream imageByteArray = new ByteArrayOutputStream();
        gambar.compress(Bitmap.CompressFormat.JPEG, 100, imageByteArray);
        byte[] imageData = imageByteArray.toByteArray();

        return Base64.encodeToString(imageData, Base64.DEFAULT);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
//        api.doOCR(s);
    }
}
