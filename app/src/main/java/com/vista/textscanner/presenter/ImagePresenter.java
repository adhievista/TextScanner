package com.vista.textscanner.presenter;

import android.graphics.Bitmap;
import android.net.Uri;

public interface ImagePresenter {
    void openGallery();
    void openCamera(int camera_Code);
    void getPathFromUri(Uri uri);
    void doCrop(Uri uri);
}

