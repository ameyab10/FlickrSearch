package com.amb.wallpaper.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amb.wallpaper.R;
import com.amb.wallpaper.adapter.PhotosAdapter;
import com.amb.wallpaper.databinding.FragmentInterestingPhotosBinding;
import com.amb.wallpaper.model.Photo;
import com.amb.wallpaper.viewmodel.InterestingPhotosFragmentViewModel;

import java.util.List;

public class InterestingPhotosFragment extends Fragment implements InterestingPhotosFragmentViewModel.PhotosFragmentViewModelInteraction {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private InterestingPhotosFragmentViewModel mViewModel;
    private PhotosAdapter mPhotosAdapter;

    public InterestingPhotosFragment() {
        // Required empty public constructor
        mViewModel = new InterestingPhotosFragmentViewModel(this);
    }

    FragmentInterestingPhotosBinding mBinding;

    public static InterestingPhotosFragment newInstance(String param1, String param2) {
        InterestingPhotosFragment fragment = new InterestingPhotosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_interesting_photos, container, false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        LinearLayoutManager staggeredGridLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        GridLayoutManager staggeredGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mBinding.recyclerview.setLayoutManager(staggeredGridLayoutManager);
        mPhotosAdapter = new PhotosAdapter();
        mBinding.recyclerview.setAdapter(mPhotosAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.searchImages();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        if (mPhotosAdapter != null) {
            mPhotosAdapter.setPhotoList(photos);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
