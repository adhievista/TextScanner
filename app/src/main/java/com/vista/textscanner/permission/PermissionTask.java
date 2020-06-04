package com.vista.textscanner.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.vista.textscanner.presenter.PermissionPresenter;
import com.vista.textscanner.view.MainView;

public class PermissionTask implements PermissionPresenter {
    private Activity activity;
    private MainView.Permission permissionView;

    public PermissionTask(Activity activity, MainView.Permission permissionView) {
        this.activity = activity;
        this.permissionView = permissionView;
    }

    @Override
    public void requestPermission(int requestCode) {
        if (checkIfAlreadyhavePermission()) {
            permissionView.onPermissionGranted(requestCode);
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA}, requestCode);
        }
    }

    @Override
    public void checkPermission(int requestCode) {
        permissionView.CheckPermissionResult(checkIfAlreadyhavePermission(), requestCode);
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED && result3 == PackageManager.PERMISSION_GRANTED;
    }
}
