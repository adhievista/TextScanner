package com.vista.textscanner.api;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import com.vista.textscanner.model.googlebook.Book;
import com.vista.textscanner.networking.NetworkClient;
import com.vista.textscanner.presenter.ApiPresenter;
import com.vista.textscanner.view.MainView;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class getResponse extends NetworkClient implements ApiPresenter.BookRequestPresenter, ApiPresenter.NewOCRRequestPresenter {
    private MainView.BookApi mainView1;
    private MainView.NewOCRApi mainViewOCR;
    private NetworkClient networkClient;
    private Activity activity;

    public static final String NewOCRApi = "https://api.ocr.space/parse/";
    public static final String ApiKey = "cf00b84bd688957";

    public getResponse(MainView.BookApi mainView) {
        this.mainView1 = mainView;
        this.networkClient = new NetworkClient();
    }

    public getResponse(MainView.NewOCRApi mainViewOCR, Activity activity) {
        this.mainViewOCR = mainViewOCR;
        this.activity = activity;
        this.networkClient = new NetworkClient();
    }

    @Override
    public void RequestData(String isbn, String URL) {
        networkClient
                .getRetrofit(URL)
                .getString(isbn)
                .enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        //Toast.makeText()
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                Log.i("onSuccess", response.raw().toString());
                                mainView1.onApiRequestResult(response.body());
                            } else {
                                Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {

                    }
                });
    }

    @Override
    public void doOCR(Bitmap bitmap) {
        AsyncTask.execute(() -> {
            Log.i("onSuccess", "Masuk");
            File file = new File(activity.getCacheDir(), "tempImg.jpg");
            try {
                file.createNewFile();
                FileOutputStream fo = new FileOutputStream(file);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                fo.write(bytes.toByteArray());
                fo.close();

                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                // MultipartBody.Part is used to send also the actual file name
                MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
                // add another part within the multipart request
                RequestBody apikey = RequestBody.create(MediaType.parse("multipart/form-data"), "cf00b84bd688957");


                networkClient
                        .getRetrofitOCR(NewOCRApi)
                        .OCRProcess(apikey, body)
                        .enqueue(new Callback<com.vista.textscanner.model.newocrapi.Response>() {
                            @Override
                            public void onResponse(@NotNull Call<com.vista.textscanner.model.newocrapi.Response> call, @NotNull Response<com.vista.textscanner.model.newocrapi.Response> respons) {
                                if (respons.isSuccessful()) {
                                    if (respons.body() != null) {
                                        Log.i("onSuccess", respons.raw().toString());
                                        Log.i("onSuccess", respons.body().toString());
                                        mainViewOCR.onOCRResult(respons.body());
                                    } else {
                                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(@NotNull Call<com.vista.textscanner.model.newocrapi.Response> call, @NotNull Throwable t) {
                                Log.i("onSuccess", "error" + t.getMessage());
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
