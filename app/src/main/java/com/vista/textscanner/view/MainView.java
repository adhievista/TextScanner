package com.vista.textscanner.view;

import android.net.Uri;

import com.vista.textscanner.model.googlebook.Book;
import com.vista.textscanner.model.newocrapi.Response;

public interface MainView {

    interface BookApi {
        void onApiRequestResult(Book book);
    }

    interface NewOCRApi{
        void onOCRResult(Response result);
    }


    interface ImageProcessing{
        void onPathResult(String string);
        void onImageResult(Uri uri);
//        void onImageResult(String string);
    }

    interface Permission{
        void onPermissionGranted(int requestCode);

        void onPermissionDenied();

        void CheckPermissionResult(boolean result, int requestCode);
    }

    interface ocrResult{
        void onSaveResult(String save_result);
    }

//    void requestPermission();
//    void onPermissionResult();
}
