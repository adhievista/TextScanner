package com.vista.textscanner.view;

public interface ocrView {
    void saveResult(String text);

    interface ocrDetail{
        void onDateResult(String date);
        void onGetColorResult(int color);
        void onCopyResult(String text);
    }

    interface ocrList{
        void onDeleteResult();
        void onMultipleSelectResult();
    }
}
