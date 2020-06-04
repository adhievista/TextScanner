package com.vista.textscanner.presenter;

import android.view.ActionMode;

import com.vista.textscanner.model.Database_Helper;
import com.vista.textscanner.model.Item;

import java.util.List;

public interface OcrPresenter {

    interface ocrDialog{
        void showLoading();
        void showResultOCR(String OCRText, String path);
        void hideDialog();
    }

    interface ocrList{
        void showAlert(List<Item> data, List<Item> selected_Item);
    }


}
