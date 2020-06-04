package com.vista.textscanner.presenter;


public interface PermissionPresenter{

    void requestPermission(int requestCode);

    void checkPermission(int requestCode);
}