package com.amb.wallpaper.service;

import com.amb.wallpaper.model.PhotoContainer;
import com.amb.wallpaper.model.Photos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by abarsode on 3/4/17.
 */

public interface FlickrService {

//    URL: https://api.flickr.com/services/rest/?method=flickr.photos.search&
// api_key=81a7b722eeee55dabf8437a32b42f24b&text=cats&format=json&nojsoncallback=1&api_sig=094848d7ae6d5c0fecdbcdb768ab8bfe


    @GET("services/rest?method=flickr.photos.search")
    Observable<PhotoContainer> searchPhotos(@Query("text") String query);
}
