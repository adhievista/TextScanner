package com.vista.textscanner.presenter;

import android.graphics.Bitmap;

public interface ApiPresenter {

    interface BookRequestPresenter{
        void RequestData(String isbn, String URL);
    }

    interface NewOCRRequestPresenter{
        void doOCR(Bitmap bitmap);
    }
}
