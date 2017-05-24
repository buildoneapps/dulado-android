package com.buildone.dulado.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.buildone.dulado.R;
import com.buildone.dulado.application.AppConstants;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PhotoFragment extends Fragment {

    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    Unbinder unbinder;
    private String photoUrl;


    public PhotoFragment() {
        // Required empty public constructor
    }

    public static PhotoFragment newInstance(String photoUrl) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putString(AppConstants.INTENT_TAG_PHOTO_URI, photoUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            photoUrl = getArguments().getString(AppConstants.INTENT_TAG_PHOTO_URI);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        unbinder = ButterKnife.bind(this, view);
        Glide.with(this).load(photoUrl).centerCrop().into(ivPhoto);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
