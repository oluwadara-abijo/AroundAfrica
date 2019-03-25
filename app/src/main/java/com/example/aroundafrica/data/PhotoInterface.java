package com.example.aroundafrica.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoInterface {

    @GET("photos")
    Call<List<Photo>> getPhotos();

}
