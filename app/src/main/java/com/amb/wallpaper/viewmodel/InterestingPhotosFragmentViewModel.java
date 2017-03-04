package com.amb.wallpaper.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;

import com.amb.wallpaper.model.Photo;
import com.amb.wallpaper.model.PhotoContainer;
import com.amb.wallpaper.model.Photos;
import com.amb.wallpaper.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by abarsode on 3/4/17.
 */

public class InterestingPhotosFragmentViewModel extends BaseObservable {

    private PhotosFragmentViewModelInteraction mInteraction;

    public InterestingPhotosFragmentViewModel(PhotosFragmentViewModelInteraction interaction) {
        mInteraction = interaction;
    }

    public ObservableField<String> queryObservable = new ObservableField<>();

    public void searchImages() {

        ApiService.getInstance().getFlickrService().searchPhotos(queryObservable.get())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoContainer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Ameya", "Failed to get a response", e);
                    }

                    @Override
                    public void onNext(PhotoContainer photoContainer) {
                        if (photoContainer != null && photoContainer.getPhotos() != null) {
                            Log.d("Ameya", "Got a response");
                            mInteraction.setPhotos(photoContainer.getPhotos().getPhotoList());
                        } else {
                            Log.e("Ameya", "Response is null");
                        }
                    }
                });

    }

    public interface PhotosFragmentViewModelInteraction {

        void setPhotos(List<Photo> photos);
    }
}
