package com.vista.textscanner.networking;

import com.vista.textscanner.model.googlebook.Book;
import com.vista.textscanner.model.newocrapi.Response;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface NetworkInterface {
    @GET("volumes")
    Call<Book> getString(@Query("q") String isbn);

//    @POST("image")
//    Call<ParsedResultsItem> OCRProcess(@Query("apikey") String ApiKey, @Body String body);
    @Multipart
    @POST("image")
    Call<Response> OCRProcess(@Part("apikey") RequestBody ApiKey,
                              @Part MultipartBody.Part image);
}
